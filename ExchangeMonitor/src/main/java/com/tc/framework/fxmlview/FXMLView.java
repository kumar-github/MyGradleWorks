package com.tc.framework.fxmlview;

import static java.util.ResourceBundle.getBundle;

import java.io.IOException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.tc.app.exchangemonitor.util.FilePathUtil;
import com.tc.framework.injection.Injector;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

public abstract class FXMLView
{
	public final static String DEFAULT_ENDING = "View";
	public final static String FXML_FILE_EXTENSION = ".fxml";
	public final static String CSS_FILE_EXTENSION = ".css";
	protected ObjectProperty<Object> presenterProperty;
	protected FXMLLoader fxmlLoader;
	protected String resourceBundleNameIncludingPath;
	protected ResourceBundle resourceBundle;
	protected final Function<String, Object> injectionContext;
	protected URL resource;
	protected final static Executor PARENT_CREATION_POOL = Executors.newCachedThreadPool(runnable -> {
		Thread thread = Executors.defaultThreadFactory().newThread(runnable);
		thread.setDaemon(true);
		return thread;
	});

	/**
	 * Constructs the view lazily (fxml is not loaded) with empty injection context.
	 */
	public FXMLView()
	{
		this(f -> null);
	}

	/**
	 *
	 * @param injectionContext the function is used as a injection source.
	 * Values matching for the keys are going to be used for injection into the
	 * corresponding presenter.
	 */
	public FXMLView(Function<String, Object> injectionContext)
	{
		this.injectionContext = injectionContext;
		this.init(getFXMLFileName());
	}

	private void init(final String conventionalFXMLFileName)
	{
		this.presenterProperty = new SimpleObjectProperty<>();
		this.resource = getClass().getResource(conventionalFXMLFileName);
		this.resourceBundleNameIncludingPath = getResourceBundleName();
		this.resourceBundle = getResourceBundle(resourceBundleNameIncludingPath);
	}

	/**
	 *
	 * @return the name of the fxml file derived from the FXML view java class. e.g. The
	 * name for the AirhacksView.java is going to be airhacks.fxml.
	 */
	private final String getFXMLFileName()
	{
		//return getResource(true, FXML_FILE_EXTENSION);
		return getFXMLResource(true, FXML_FILE_EXTENSION);
	}

	//private String getResource(String extension)
	private String getResource(boolean mandatory, String extension)
	{
		String name = getConventionalNameWithExtension(extension);
		URL found = getClass().getResource(name);
		
		if(found != null)
			return name;
		
		name = FilePathUtil.getFXMLFileForClass(getClass());
		found = getClass().getResource(name);
		
		if(found != null)
			return name;
		
		System.err.println("File: " + name + " not found, attempting with next conventional name:(\"View\" stripped.)");
		name = getNextConventionalNameWithExtension(extension);
		found = getClass().getResource(name);
		
		if (mandatory && found == null)
		{
			final String message = "Cannot load file " + name;
			System.err.println(message);
			System.err.println("Stopping initialization phase...");
			throw new IllegalStateException(message);
		}
		return name;
	}
	
	private String getFXMLResource(boolean mandatory, String extension)
	{
		String name = getConventionalNameWithExtension(extension);
		URL found = getClass().getResource(name);
		
		if(found != null)
			return name;
		
		name = FilePathUtil.getFXMLFileForClass(getClass());
		found = getClass().getResource(name);
		
		if(found != null)
			return name;
		
		System.err.println("File: " + name + " not found, attempting with next conventional name:(\"View\" stripped.)");
		name = getNextConventionalNameWithExtension(extension);
		found = getClass().getResource(name);
		
		if (mandatory && found == null)
		{
			final String message = "Cannot load file " + name;
			System.err.println(message);
			System.err.println("Stopping initialization phase...");
			throw new IllegalStateException(message);
		}
		return name;
	}
	
	private String getCSSResource(boolean mandatory, String extension)
	{
		String name = getConventionalNameWithExtension(extension);
		URL found = getClass().getResource(name);
		
		if(found != null)
			return name;
		
		name = FilePathUtil.getCSSFileForClass(getClass());
		found = getClass().getResource(name);
		
		if(found != null)
			return name;
		
		System.err.println("File: " + name + " not found, attempting with next conventional name:(\"View\" stripped.)");
		name = getNextConventionalNameWithExtension(extension);
		found = getClass().getResource(name);
		
		if (mandatory && found == null)
		{
			final String message = "Cannot load file " + name;
			System.err.println(message);
			System.err.println("Stopping initialization phase...");
			throw new IllegalStateException(message);
		}
		return name;
	}

	/**
	 *
	 * @param ending the suffix to append
	 * @return the conventional name with stripped ending
	 */
	protected String getConventionalNameWithExtension(String extension)
	{
		return getConventionalName() + extension;
	}
	
	/**
	 *
	 * @param ending the suffix to append
	 * @return the conventional name with stripped ending
	 */
	protected String getNextConventionalNameWithExtension(String extension)
	{
		return getConventionalNameWithEndStriped() + extension;
	}

	/**
	 * @return the name of the view without the "View" suffix.
	 */
	protected String getConventionalNameWithEndStriped()
	{
		final String clazzWithEnding = this.getConventionalName();
		return stripEnding(clazzWithEnding);
	}
	
	/**
	 * @return the name of the view with the "View" suffix.
	 */
	protected String getConventionalName()
	{
		return this.getClass().getSimpleName();
	}
	
	private FXMLLoader loadSynchronously(final URL resource, ResourceBundle bundle, final String conventionalName) throws IllegalStateException
	{
		final FXMLLoader loader = new FXMLLoader(resource, bundle);
		loader.setControllerFactory((Class<?> p) -> Injector.instantiatePresenter(p, this.injectionContext));
		try
		{
			loader.load();
		}
		catch (IOException ex)
		{
			throw new IllegalStateException("Cannot load " + conventionalName, ex);
		}
		return loader;
	}

	private void initializeFXMLLoader()
	{
		if (this.fxmlLoader == null) {
			this.fxmlLoader = this.loadSynchronously(resource, resourceBundle, resourceBundleNameIncludingPath);
			this.presenterProperty.set(this.fxmlLoader.getController());
		}
	}

	/**
	 * Initializes the view by loading the FXML (if not happened yet) and
	 * returns the top Node (parent) specified in
	 *
	 * @return
	 */
	public Parent getView()
	{
		this.initializeFXMLLoader();
		Parent parent = fxmlLoader.getRoot();
		addCSSIfAvailable(parent);
		return parent;
	}

	/**
	 * Initializes the view synchronously and invokes and passes the created
	 * parent Node to the consumer within the FX UI thread.
	 *
	 * @param consumer - an object interested in received the Parent as callback
	 */
	public void getView(Consumer<Parent> consumer)
	{
		Supplier<Parent> supplier = this::getView;
		Executor fxExecutor = Platform::runLater;
		CompletableFuture.supplyAsync(supplier, fxExecutor).thenAccept(consumer);
	}

	/**
	 * Creates the view asynchronously using an internal thread pool and passes the parent node withing the UI Thread.
	 * @param consumer - an object interested in receiving the Parent as callback
	 */
	public void getViewAsync(Consumer<Parent> consumer) {
		PARENT_CREATION_POOL.execute(() -> getView(consumer));
	}

	/**
	 * Scene Builder creates for each FXML document a root container. This method omits the root container (e.g. AnchorPane) and gives you the access to its first child.
	 * @return the first child of the AnchorPane
	 */
	public Node getViewWithoutRootContainer()
	{
		final ObservableList<Node> children = getView().getChildrenUnmodifiable();
		if (children.isEmpty())
		{
			return null;
		}
		return children.listIterator().next();
	}

	private void addCSSIfAvailable(Parent parent)
	{
		URL uri = getClass().getResource(getStyleSheetName());
		if (uri == null)
		{
			return;
		}
		String uriToCss = uri.toExternalForm();
		parent.getStylesheets().add(uriToCss);
	}

	private String getStyleSheetName()
	{
		//return getResource(false, CSS_FILE_EXTENSION);
		return getCSSResource(false, CSS_FILE_EXTENSION);
	}

	/**
	 * In case the view was not initialized yet, the conventional fxml
	 * (airhacks.fxml for the AirhacksView and AirhacksPresenter) are loaded and
	 * the specified presenter / controller is going to be constructed and
	 * returned.
	 *
	 * @return the corresponding controller / presenter (usually for a
	 * AirhacksView the AirhacksPresenter)
	 */
	public Object getPresenter()
	{
		this.initializeFXMLLoader();
		return this.presenterProperty.get();
	}

	/**
	 * Does not initialize the view. Only registers the Consumer and waits until the the view is going to be created / the method FXMLView#getView or
	 * FXMLView#getViewAsync invoked.
	 *
	 * @param presenterConsumer listener for the presenter construction
	 */
	public void getPresenter(Consumer<Object> presenterConsumer)
	{
		this.presenterProperty.addListener((ObservableValue<? extends Object> o, Object oldValue, Object newValue) -> {
			presenterConsumer.accept(newValue);
		});
	}
	
	private String getResourceBundleName()
	{
		//String conventionalName = getConventionalName();
		//return this.getClass().getPackage().getName() + "." + conventionalName;
		//return FilePathUtil.getViewSpecificPropertiesFileForClass(getClass());
		return FilePathUtil.getResourceBundlePropertiesFileForClass(getClass());
	}

	private static String stripEnding(String clazz)
	{
		if (!clazz.endsWith(DEFAULT_ENDING))
		{
			return clazz;
		}
		int viewIndex = clazz.lastIndexOf(DEFAULT_ENDING);
		return clazz.substring(0, viewIndex);
	}

	//public static ResourceBundle getResourceBundle(String resourceBundleNameIncludingPath)
	public ResourceBundle getResourceBundle(String resourceBundleNameIncludingPath)
	{
		try
		{
			return getBundle(resourceBundleNameIncludingPath);
			//return getBundle(FilePathUtil.getPROPSPackageNameForClass(getClass()));
		}
		catch (MissingResourceException ex)
		{
			return null;
		}
	}

	/**
	 * @return an existing resource bundle, or null
	 */
	public ResourceBundle getResourceBundle()
	{
		return this.resourceBundle;
	}
	
	/*
	 * The below method may be useful in the future to fetch the fxml, css, properties files from different packages. 
	 */
	/*private String getFXMLResource()
	{
		URL u1 = this.getClass().getResource("/com/tc/app/exchangemonitor/view/fxml/ExchangeMonitorAppView.fxml");
		URL u2 = this.getClass().getClassLoader().getResource("com/tc/app/exchangemonitor/view/fxml/ExchangeMonitorAppView.fxml");
		URL u3 = Paths.get("com/tc/app/exchangemonitor/view/fxml/ExchangeMonitorAppView.fxml").toUri().toURL();
		String name = getConventionalNameWithExtension(FXML_FILE_EXTENSION);
		URL found = getClass().getResource("/com/tc/app/exchangemonitor/view/fxml/"+name);
		if(found != null)
			return name;
		
		if (found == null)
		{
			final String message = "Cannot load file " + name;
			System.err.println(message);
			System.err.println("Stopping initialization phase...");
			throw new IllegalStateException(message);
		}
		return name;
	}*/
}