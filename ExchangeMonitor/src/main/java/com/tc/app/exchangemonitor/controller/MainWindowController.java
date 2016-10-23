package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;
import org.controlsfx.control.StatusBar;

import com.tc.app.exchangemonitor.util.ApplicationHelper;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
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
	 * 																																							All Variables injected through FXML starts here
	 * ============================================================================================================================================================================
	 */

	@FXML
	private BorderPane mainWindowBorderPane;
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

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Variables injected through FXML ends here
	 * ============================================================================================================================================================================
	 */

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Variables injected through @Inject starts here
	 * ============================================================================================================================================================================
	 */

	@Inject
	private String APPLICATION_TITLE;

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Variables injected through @Inject ends here
	 * ============================================================================================================================================================================
	 */

	/**
	 * ============================================================================================================================================================================
	 * 																																							All other variable declaration starts here
	 * ============================================================================================================================================================================
	 */

	private BoundingBox savedBounds;
	private boolean isInMaximizedState = false;
	private boolean isInRestoredState = true;

	/**
	 * ============================================================================================================================================================================
	 * 																																							All other variable declaration ends here
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

	/**
	 * ============================================================================================================================================================================
	 * 																																							Methods injected through FXML starts here
	 * ============================================================================================================================================================================
	 */

	@FXML
	private void handleHomeImageViewClick(MouseEvent mouseEvent)
	{
		if(mouseEvent.getButton() == MouseButton.PRIMARY)
			showOptionsPopOver();
	}

	@FXML
	private void handleTitleBarHBoxClick(MouseEvent mouseEvent)
	{
		if (mouseEvent.getClickCount() > 1)
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
		Stage primaryStage = (Stage)(mainWindowBorderPane.getScene().getWindow());
		Platform.runLater(() -> {
			primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
		});
	}

	/**
	 * ============================================================================================================================================================================
	 * 																																							Methods injected through FXML ends here
	 * ============================================================================================================================================================================
	 */

	/**
	 * ============================================================================================================================================================================
	 * 																																							General Methods starts here
	 * ============================================================================================================================================================================
	 */

	CheckBox ch1 = new CheckBox("Reset Credentials");
	CheckBox ch2 = new CheckBox("Display Accounts with permission");
	private void showOptionsPopOver()
	{
		PopOver popOver = new PopOver();
		popOver.setTitle("Options");
		//popOver.setDetachable(false);
		popOver.setDetachable(true);
		popOver.setDetached(true);
		popOver.setArrowLocation(ArrowLocation.TOP_LEFT);
		popOver.setAutoFix(true);
		popOver.setAutoHide(true);
		popOver.setHideOnEscape(true);
		//popOver.setCornerRadius(0);
		popOver.setCornerRadius(4);
		popOver.show(homeImageView);
		
		VBox vbox = new VBox(15.0);
		vbox.setPadding(new Insets(10.0));
		vbox.getChildren().add(ch1);
		vbox.getChildren().add(ch2);	
		//ch2.setStyle("-fx-border-color: lightblue; -fx-border-insets: -5; -fx-border-radius: 5; -fx-border-style: dotted; -fx-border-width: 2;");
		ch1.setOnAction(event -> handle(event));
		ch2.setOnAction(event -> handle(event));
		popOver.setContentNode(vbox);
	}
	
	private void handle(ActionEvent event)
	{
		CheckBox ch = ((CheckBox)event.getSource());
		if(ch.isSelected() && ch.getText().equals("Reset Credentials"))
		{
			try
			{
				PreferencesUtil.getUserPreferences().clear();
				LOGGER.info("Credentials reset successfully.");
			}
			catch (BackingStoreException exception)
			{
				exception.printStackTrace();
			}
		}
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
		((Stage)mainWindowBorderPane.getScene().getWindow()).setIconified(true);
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
			Stage primaryStage = ((Stage)(mainWindowBorderPane.getScene().getWindow()));
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
			Stage primaryStage = ((Stage)(mainWindowBorderPane.getScene().getWindow()));
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
		Stage primaryStage = ((Stage)(mainWindowBorderPane.getScene().getWindow()));
		savedBounds = new BoundingBox(primaryStage.getX(), primaryStage.getY(), primaryStage.getWidth(), primaryStage.getHeight());
	}

	public String getAPPLICATION_TITLE()
	{
		return APPLICATION_TITLE;
	}

	/**
	 * ============================================================================================================================================================================
	 * 																																							General Methods starts here
	 * ============================================================================================================================================================================
	 */

	private StringProperty statusMessagesProperty = null;
	//private StringProperty statusMessagesProperty()
	public StringProperty statusMessagesProperty()
	{
		if (statusMessagesProperty == null)
		{
			statusMessagesProperty = new SimpleStringProperty();
		}
		return statusMessagesProperty;
	}

	private DoubleProperty progressStatusesProperty = null;
	//private DoubleProperty progressStatusesProperty()
	public DoubleProperty progressStatusesProperty()
	{
		if (progressStatusesProperty == null)
		{
			progressStatusesProperty = new SimpleDoubleProperty();
		}
		return progressStatusesProperty;
	}

}

/* ******************************************************************************************************************************************************************************************************* */

/**
 * ============================================================================================================================================================================
 * 																																							All temporarily commented code starts here. We may need in future for reference
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
 * 																																							All temporarily commented code ends here. We may need in future for reference
 * ============================================================================================================================================================================
 */