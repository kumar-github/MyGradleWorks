package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;
import org.controlsfx.control.StatusBar;

import com.tc.app.exchangemonitor.util.ApplicationHelper;
import com.tc.app.exchangemonitor.view.java.PreferencesView;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainWindowController implements Initializable
{
	private static final Logger LOGGER = LogManager.getLogger(MainWindowController.class);

	/**
	 * ============================================================================================================================================================================
	 * All Variables injected through FXML starts here
	 * ============================================================================================================================================================================
	 */

	@FXML
	private BorderPane mainWindowBorderPane;
	@FXML
	private TabPane mainWindowTabPane;
	@FXML
	private ImageView homeImageView;
	@FXML
	private ImageView minimizeImageView;
	@FXML
	private ImageView maximizeOrRestoreImageView;
	@FXML
	private ImageView closeImageView;
	@FXML
	private StatusBar mainWindowStatusBar;
	@FXML
	private Button monitorStatusButton;
	@FXML
	private Button allTradesCountButton;
	@FXML
	private Button pendingTradesCountButton;
	@FXML
	private Button completedTradesCountButton;
	@FXML
	private Button failedTradesCountButton;
	@FXML
	private Button skippedTradesCountButton;
	@FXML
	private Separator leftSeparator;

	/**
	 * ============================================================================================================================================================================
	 * All Variables injected through FXML ends here
	 * ============================================================================================================================================================================
	 */

	/**
	 * ============================================================================================================================================================================
	 * All Variables injected through @Inject starts here
	 * ============================================================================================================================================================================
	 */

	@Inject
	private String APPLICATION_TITLE;

	@Inject
	private String APPLICATION_TITLE_WITH_USERNAME;

	/**
	 * ============================================================================================================================================================================
	 * All Variables injected through @Inject ends here
	 * ============================================================================================================================================================================
	 */

	/**
	 * ============================================================================================================================================================================
	 * All other variable declaration starts here
	 * ============================================================================================================================================================================
	 */

	private ChangeListener<Tab> mainWindowTabPaneChangeListener = null;

	private BoundingBox savedBounds;
	private boolean isInMaximizedState = false;
	private boolean isInRestoredState = true;

	/**
	 * ============================================================================================================================================================================
	 * All other variable declaration ends here
	 * ============================================================================================================================================================================
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		addThisControllerToControllersMap();
		doAssertion();
		doInitialDataBinding();
		setComponentToolTipIfNeeded();
		initializeGUI();
		createListeners();
		attachListeners();
	}

	private void addThisControllerToControllersMap()
	{
		ApplicationHelper.controllersMap.putInstance(MainWindowController.class, this);
	}

	private void doAssertion()
	{
		assert homeImageView != null : "fx:id=\"homeImageView\" was not injected. Check your FXML file MainWindowView.fxml";
	}

	private void doInitialDataBinding()
	{
		monitorStatusButton.textProperty().bind(Bindings.format("%s", Bindings.when(isRunningProperty()).then("Monitor Running").otherwise("Monitor Not Running")));
		allTradesCountButton.textProperty().bind(allTradesCountProperty().asString());
		pendingTradesCountButton.textProperty().bind(pendingTradesCountProperty().asString());
		completedTradesCountButton.textProperty().bind(completedTradesCountProperty().asString());
		failedTradesCountButton.textProperty().bind(failedTradesCountProperty().asString());
		skippedTradesCountButton.textProperty().bind(skippedTradesCountProperty().asString());

		mainWindowStatusBar.textProperty().bind(statusMessagesProperty());
		mainWindowStatusBar.progressProperty().bind(progressStatusesProperty());
	}

	private void setComponentToolTipIfNeeded()
	{
		Tooltip.install(homeImageView, new Tooltip("Home"));
		Tooltip.install(minimizeImageView, new Tooltip("Minimize"));
		Tooltip.install(maximizeOrRestoreImageView, new Tooltip("Maximize"));
		Tooltip.install(closeImageView, new Tooltip("Close"));
	}

	private void initializeGUI()
	{
	}

	private void createListeners()
	{
		mainWindowTabPaneChangeListener = (observableValue, oldValue, newValue) -> {
			handleMainWindowTabPaneTabChange(oldValue, newValue);
		};
	}

	private void attachListeners()
	{
		mainWindowTabPane.getSelectionModel().selectedItemProperty().addListener(mainWindowTabPaneChangeListener);
	}

	/**
	 * ============================================================================================================================================================================
	 * Methods injected through FXML starts here
	 * ============================================================================================================================================================================
	 */

	@FXML
	private void handleHomeImageViewClick(MouseEvent mouseEvent)
	{
		if(mouseEvent.getButton() == MouseButton.PRIMARY)
			showPreferencesPopOver();
	}

	@FXML
	private void handleTitleBarHBoxClick(MouseEvent mouseEvent)
	{
		if(mouseEvent.getClickCount() > 1)
		{
			handleMaximizeOrRestoreImageViewClick(mouseEvent);
		}
	}

	@FXML
	private void handleMinimizeImageViewClick()
	{
		minimizeStage();
	}

	@FXML
	private void handleMaximizeOrRestoreImageViewClick(MouseEvent mouseEvent)
	{
		/* We may be here bcoz user clicked maximize of restore. so first find out. */
		if(isInMaximizedState)
		{
			/* do restore stuff. */
			restoreStage();
			removeCSSStyleFromNode(maximizeOrRestoreImageView, "mainWindowViewRestoreImageViewStyle");
			addCSSStyleToNode(maximizeOrRestoreImageView, "mainWindowViewMaximizeImageViewStyle");
			Tooltip.install(maximizeOrRestoreImageView, new Tooltip("Maximize"));

			isInRestoredState = true;
			isInMaximizedState = false;
		}
		else if(isInRestoredState)
		{
			/* do maximize stuff. */
			saveStageBounds();
			maximizeStage();

			removeCSSStyleFromNode(maximizeOrRestoreImageView, "mainWindowViewMaximizeImageViewStyle");
			addCSSStyleToNode(maximizeOrRestoreImageView, "mainWindowViewRestoreImageViewStyle");
			Tooltip.install(maximizeOrRestoreImageView, new Tooltip("Restore"));

			isInMaximizedState = true;
			isInRestoredState = false;
		}
	}

	@FXML
	private void handleCloseImageViewClick(MouseEvent mouseEvent)
	{
		/* don't close the stage by yourself, instead just raise a close request event and leave it. we will handle it somewhere. */
		Stage primaryStage = (Stage) (mainWindowBorderPane.getScene().getWindow());
		Platform.runLater(() -> {
			primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
		});
	}

	/**
	 * ============================================================================================================================================================================
	 * Methods injected through FXML ends here
	 * ============================================================================================================================================================================
	 */

	/**
	 * ============================================================================================================================================================================
	 * All Listeners methods starts here
	 * ============================================================================================================================================================================
	 */

	private void handleMainWindowTabPaneTabChange(Tab previousTab, Tab currentTab)
	{
		shouldShowStatusBarButtons(currentTab.getText().equals("Monitor") ? true : false);
	}

	private void shouldShowStatusBarButtons(boolean shouldShow)
	{
		allTradesCountButton.setVisible(shouldShow);
		completedTradesCountButton.setVisible(shouldShow);
		pendingTradesCountButton.setVisible(shouldShow);
		failedTradesCountButton.setVisible(shouldShow);
		skippedTradesCountButton.setVisible(shouldShow);
		leftSeparator.setVisible(shouldShow);
	}

	/**
	 * ============================================================================================================================================================================
	 * All Listeners methods ends here
	 * ============================================================================================================================================================================
	 */

	/**
	 * ============================================================================================================================================================================
	 * General Methods starts here
	 * ============================================================================================================================================================================
	 */

	VBox preferencesVbox = (VBox) new PreferencesView().getView();

	private void showPreferencesPopOver()
	{
		PopOver preferencesPopOver = new PopOver();
		preferencesPopOver.setTitle("Preferences");
		preferencesPopOver.setDetached(true);
		preferencesPopOver.setArrowLocation(ArrowLocation.TOP_LEFT);
		preferencesPopOver.setAutoFix(true);
		preferencesPopOver.setAutoHide(true);
		preferencesPopOver.setHideOnEscape(true);
		preferencesPopOver.setCornerRadius(4);
		//popOver.setContentNode(new PreferencesView().getView());
		preferencesPopOver.setContentNode(preferencesVbox);
		preferencesPopOver.show(homeImageView);
	}

	public void minimizeStage()
	{
		if(!Platform.isFxApplicationThread())
		{
			Platform.runLater(() -> _minimize());
		}
		else
		{
			_minimize();
		}
	}

	/* According to my code convention, methods starts with underscore (_) are very low level methods. so avoid calling them directly. there will be a helper method 
	 * available with same name without underscore (_) try using that.  */
	private void _minimize()
	{
		//BorderPane mainApplicationBorderPane = ApplicationHelper.controllersMap.getInstance(MainApplicationController.class).getMainApplicationBorderPane();
		//((Stage)(mainApplicationBorderPane.getScene().getWindow())).setIconified(true);
		((Stage) mainWindowBorderPane.getScene().getWindow()).setIconified(true);
	}

	public void restoreStage()
	{
		if(!Platform.isFxApplicationThread())
		{
			Platform.runLater(() -> _restore());
		}
		else
		{
			_restore();
		}
	}

	private void _restore()
	{
		if(!isInRestoredState)
		{
			//primaryStage.setMaximized(true); /* Technically this should work but it is not bcoz of undecoration. */

			//BorderPane mainApplicationBorderPane = ApplicationHelper.controllersMap.getInstance(MainApplicationController.class).getMainApplicationBorderPane();
			Stage primaryStage = ((Stage) (mainWindowBorderPane.getScene().getWindow()));
			primaryStage.setX(savedBounds.getMinX());
			primaryStage.setY(savedBounds.getMinY());
			primaryStage.setWidth(savedBounds.getWidth());
			primaryStage.setHeight(savedBounds.getHeight());
		}
	}

	public void maximizeStage()
	{
		/* If we are here, then user maximized the application. Since the app is initially loaded in the restore mode, the maximize button will be visible and it should be 
		 * toggled back and forth from maximize image to restore image as the user clicks maximize and restore buttons.
		 * Here we remove the old css style and set the new css style. In this case remove the "applicationMainWindowMaximizeImageViewStyle" css style which shows a maximize image and set the 
		 * "applicationMainWindowRestoreImageViewStyle" css style which shows a restore image.
		 */
		if(!Platform.isFxApplicationThread())
		{
			Platform.runLater(() -> _maximize());
		}
		else
		{
			_maximize();
		}
	}

	private void _maximize()
	{
		if(!isInMaximizedState)
		{
			//primaryStage.setMaximized(true); /* Technically this should work but it is not bcoz of undecoration. */

			//BorderPane mainApplicationBorderPane = ApplicationHelper.controllersMap.getInstance(MainApplicationController.class).getMainApplicationBorderPane();

			//Get current screen of the stage
			Stage primaryStage = ((Stage) (mainWindowBorderPane.getScene().getWindow()));
			ObservableList<Screen> screens = Screen.getScreensForRectangle(primaryStage.getX(), primaryStage.getY(), primaryStage.getWidth(), primaryStage.getHeight());
			Rectangle2D bounds = screens.get(0).getVisualBounds();
			primaryStage.setX(bounds.getMinX());
			primaryStage.setY(bounds.getMinY());
			primaryStage.setWidth(bounds.getWidth());
			primaryStage.setHeight(bounds.getHeight());
		}
	}

	private void addCSSStyleToNode(Node aNode, String cssStyle)
	{
		aNode.getStyleClass().add(cssStyle);
	}

	private void removeCSSStyleFromNode(Node aNode, String cssStyle)
	{
		aNode.getStyleClass().remove(cssStyle);
	}

	private void saveStageBounds()
	{
		//BorderPane mainApplicationBorderPane = ApplicationHelper.controllersMap.getInstance(MainApplicationController.class).getMainApplicationBorderPane();
		Stage primaryStage = ((Stage) (mainWindowBorderPane.getScene().getWindow()));
		savedBounds = new BoundingBox(primaryStage.getX(), primaryStage.getY(), primaryStage.getWidth(), primaryStage.getHeight());
	}

	public String getAPPLICATION_TITLE()
	{
		return APPLICATION_TITLE;
	}

	public String getAPPLICATION_TITLE_WITH_USERNAME()
	{
		//return APPLICATION_TITLE_WITH_USERNAME;
		//return System.getenv("username") + " @ " + getAPPLICATION_TITLE();
		return String.join(" ", System.getenv("username"), "@", getAPPLICATION_TITLE());
	}

	/**
	 * ============================================================================================================================================================================
	 * General Methods ends here
	 * ============================================================================================================================================================================
	 */

	private StringProperty statusMessagesProperty = null;

	//private StringProperty statusMessagesProperty()
	public StringProperty statusMessagesProperty()
	{
		if(statusMessagesProperty == null)
		{
			statusMessagesProperty = new SimpleStringProperty();
		}
		return statusMessagesProperty;
	}

	private DoubleProperty progressStatusesProperty = null;

	//private DoubleProperty progressStatusesProperty()
	public DoubleProperty progressStatusesProperty()
	{
		if(progressStatusesProperty == null)
		{
			progressStatusesProperty = new SimpleDoubleProperty();
		}
		return progressStatusesProperty;
	}

	private BooleanProperty isRunningProperty = null;

	public BooleanProperty isRunningProperty()
	{
		if(isRunningProperty == null)
		{
			isRunningProperty = new SimpleBooleanProperty();
		}
		return isRunningProperty;
	}

	private IntegerProperty allTradesCountProperty = null;

	public IntegerProperty allTradesCountProperty()
	{
		if(allTradesCountProperty == null)
		{
			allTradesCountProperty = new SimpleIntegerProperty();
		}
		return allTradesCountProperty;
	}

	private IntegerProperty pendingTradesCountProperty = null;

	public IntegerProperty pendingTradesCountProperty()
	{
		if(pendingTradesCountProperty == null)
		{
			pendingTradesCountProperty = new SimpleIntegerProperty();
		}
		return pendingTradesCountProperty;
	}

	private IntegerProperty completedTradesCountProperty = null;

	public IntegerProperty completedTradesCountProperty()
	{
		if(completedTradesCountProperty == null)
		{
			completedTradesCountProperty = new SimpleIntegerProperty();
		}
		return completedTradesCountProperty;
	}

	private IntegerProperty failedTradesCountProperty = null;

	public IntegerProperty failedTradesCountProperty()
	{
		if(failedTradesCountProperty == null)
		{
			failedTradesCountProperty = new SimpleIntegerProperty();
		}
		return failedTradesCountProperty;
	}

	private IntegerProperty skippedTradesCountProperty = null;

	public IntegerProperty skippedTradesCountProperty()
	{
		if(skippedTradesCountProperty == null)
		{
			skippedTradesCountProperty = new SimpleIntegerProperty();
		}
		return skippedTradesCountProperty;
	}
}

/* ******************************************************************************************************************************************************************************************************* */

/**
 * ============================================================================================================================================================================
 * All temporarily commented code starts here. We may need in future for reference
 * ============================================================================================================================================================================
 */

/*tradeAccountListView.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
@Override
public BooleanProperty call(String item) {
//public ObservableValue<Boolean> call(String item) {
    /*BooleanProperty observable = new SimpleBooleanProperty();
    observable.addListener((obs, wasSelected, isNowSelected) -> System.out.println("Check box for "+item+" changed from "+wasSelected+" to "+isNowSelected));
    return observable ;
}
}));*/

// set the cell factory
/*Callback<String, ObservableValue<Boolean>> getProperty = new Callback<String, ObservableValue<Boolean>>() {
@Override
public BooleanProperty call(String item) {
    // given a person, we return the property that represents
    // whether or not they are invited. We can then bind to this
    // bidirectionally.
    //return item;
	System.out.println(item + " is clicked");
	return null;
}};

tradeAccountListView.setCellFactory(CheckBoxListCell.forListView(getProperty));*/

/*
public void handleSearchByKey2(String oldVal, String newVal)
{
	// If the number of characters in the text box is less than last time it must be because the user pressed delete
	if(oldVal != null && (newVal.length() < oldVal.length()))
	{
		// Restore the lists original set of entries and start from the beginning
		tradeAccountListView.setItems(FXCollections.observableArrayList(externalTradeAccounts));
	}

	// Break out all of the parts of the search text by splitting on white space
	String[] parts = newVal.toUpperCase().split(" ");

	// Filter out the entries that don't contain the entered text
	ObservableList<String> subentries = FXCollections.observableArrayList();
	//for (Object entry: tradeAccountListView.getItems())
	for(String entry: tradeAccountListView.getItems())
	{
		boolean match = true;
		for(String part: parts)
		{
			// The entry needs to contain all portions of the search string *but* in any order
			if(!entry.toUpperCase().contains(part))
			{
				match = false;
				break;
			}
		}

		if (match)
		{
			subentries.add(entry);
		}
	}
	tradeAccountListView.setItems(subentries);
}
 */

/**
 * ============================================================================================================================================================================
 * All temporarily commented code ends here. We may need in future for reference
 * ============================================================================================================================================================================
 */