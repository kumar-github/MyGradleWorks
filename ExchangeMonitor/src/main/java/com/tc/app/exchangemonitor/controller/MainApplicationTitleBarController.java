package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import com.tc.app.exchangemonitor.util.ApplicationHelper;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainApplicationTitleBarController implements Initializable
{
	@FXML
	private ImageView optionsImageView;

	@FXML
	private ImageView minimizeImageView;

	@FXML
	private ImageView maximizeorRestoreImageView;

	@FXML
	private ImageView closeImageView;
	
	@Inject
	private String APPLICATION_TITLE;
	
	private boolean isInMaximizedState = false;
	private boolean isInRestoredState = true;
	private BoundingBox savedBounds;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		setComponentToolTipIfNeeded();
	}

	private void setComponentToolTipIfNeeded()
	{
		Tooltip.install(optionsImageView, new Tooltip("Settings"));
		Tooltip.install(minimizeImageView, new Tooltip("Minimize"));
		Tooltip.install(maximizeorRestoreImageView, new Tooltip("Maximize"));
		Tooltip.install(closeImageView, new Tooltip("Close"));
	}

	@FXML
	private void handleOptionsImageViewClick(MouseEvent mouseEvent)
	{
		if(mouseEvent.getButton() == MouseButton.PRIMARY)
			showOptionsPopOver();
	}

	private void showOptionsPopOver()
	{
		PopOver popOver = new PopOver();
		popOver.setDetachable(false);
		//popOver.setArrowLocation(ArrowLocation.LEFT_TOP);
		//popOver.setArrowLocation(ArrowLocation.TOP_CENTER);
		popOver.setArrowLocation(ArrowLocation.TOP_LEFT);
		popOver.setAutoFix(true);
		popOver.setAutoHide(true);
		popOver.setHideOnEscape(true);
		popOver.setCornerRadius(0);
		//popOver.setArrowIndent(10);
		//popOver.setArrowSize(10);
		//popOver.setAnchorX(10);
		//popOver.setAnchorY(50);
		/*TitledPane pane1 = new TitledPane("Pane1", new Button("Button1"));
		TitledPane pane2 = new TitledPane("Pane2", new Button("Button2"));
		TitledPane pane3 = new TitledPane("Pane3", new Button("Button3"));
		Accordion acc = new Accordion();
		acc.getPanes().addAll(pane1, pane2, pane3);
		popOver.setContentNode(acc);*/

		popOver.show(optionsImageView);
	}

	@FXML
	private void handleTitleBarHBoxClick(MouseEvent mouseEvent)
	{
		if (mouseEvent.getClickCount() > 1)
		{
			handleMaximizeorRestoreImageViewClick(mouseEvent);
		}
	}

	@FXML
	private void handleMinimizeImageViewClick()
	{
		minimizeStage();
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
		BorderPane mainApplicationBorderPane = ApplicationHelper.controllersMap.getInstance(MainApplicationController.class).getMainApplicationBorderPane();
		((Stage)(mainApplicationBorderPane.getScene().getWindow())).setIconified(true);
	}

	@FXML
	private void handleMaximizeorRestoreImageViewClick(MouseEvent mouseEvent)
	{
		/* We may be here bcoz user clicked maximize of restore. so first find out. */
		if(isInMaximizedState)
		{
			/* do restore stuff. */
			restoreStage();
			removeCSSStyleFromNode(maximizeorRestoreImageView, "applicationMainWindowRestoreImageViewStyle");
			addCSSStyleToNode(maximizeorRestoreImageView, "applicationMainWindowMaximizeImageViewStyle");
			Tooltip.install(maximizeorRestoreImageView, new Tooltip("Maximize"));

			isInRestoredState = true;
			isInMaximizedState = false;
		}
		else if(isInRestoredState)
		{
			/* do maximize stuff. */
			saveStageBounds();
			maximizeStage();

			removeCSSStyleFromNode(maximizeorRestoreImageView, "applicationMainWindowMaximizeImageViewStyle");
			addCSSStyleToNode(maximizeorRestoreImageView, "applicationMainWindowRestoreImageViewStyle");
			Tooltip.install(maximizeorRestoreImageView, new Tooltip("Restore"));

			isInMaximizedState = true;
			isInRestoredState = false;
		}
	}

	@FXML
	private void handleCloseImageViewClick(MouseEvent mouseEvent)
	{
		/*Stage primaryStage = (Stage)(mainApplicationBorderPane.getScene().getWindow());
		primaryStage.close();
		Platform.exit();
		System.exit(0);*/
		BorderPane mainApplicationBorderPane = ApplicationHelper.controllersMap.getInstance(MainApplicationController.class).getMainApplicationBorderPane();

		Stage primaryStage = (Stage)(mainApplicationBorderPane.getScene().getWindow());
		Platform.runLater(() -> {
			primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
		});
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
			
			BorderPane mainApplicationBorderPane = ApplicationHelper.controllersMap.getInstance(MainApplicationController.class).getMainApplicationBorderPane();

			//Get current screen of the stage
			Stage primaryStage = ((Stage)(mainApplicationBorderPane.getScene().getWindow()));
			ObservableList<Screen> screens = Screen.getScreensForRectangle(primaryStage.getX(), primaryStage.getY(), primaryStage.getWidth(), primaryStage.getHeight());
			Rectangle2D bounds = screens.get(0).getVisualBounds();
			primaryStage.setX(bounds.getMinX());
			primaryStage.setY(bounds.getMinY());
			primaryStage.setWidth(bounds.getWidth());
			primaryStage.setHeight(bounds.getHeight());
		}
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
			
			BorderPane mainApplicationBorderPane = ApplicationHelper.controllersMap.getInstance(MainApplicationController.class).getMainApplicationBorderPane();

			Stage primaryStage = ((Stage)(mainApplicationBorderPane.getScene().getWindow()));
			primaryStage.setX(savedBounds.getMinX());
			primaryStage.setY(savedBounds.getMinY());
			primaryStage.setWidth(savedBounds.getWidth());
			primaryStage.setHeight(savedBounds.getHeight());
		}
	}

	private void removeCSSStyleFromNode(Node aNode, String cssStyle)
	{
		aNode.getStyleClass().remove(cssStyle);
	}

	private void addCSSStyleToNode(Node aNode, String cssStyle)
	{
		aNode.getStyleClass().add(cssStyle);
	}
	
	private void saveStageBounds()
	{
		BorderPane mainApplicationBorderPane = ApplicationHelper.controllersMap.getInstance(MainApplicationController.class).getMainApplicationBorderPane();
		Stage primaryStage = ((Stage)(mainApplicationBorderPane.getScene().getWindow()));
		savedBounds = new BoundingBox(primaryStage.getX(), primaryStage.getY(), primaryStage.getWidth(), primaryStage.getHeight());
	}
	
	public String getAPPLICATION_TITLE()
	{
		return APPLICATION_TITLE;
	}
}