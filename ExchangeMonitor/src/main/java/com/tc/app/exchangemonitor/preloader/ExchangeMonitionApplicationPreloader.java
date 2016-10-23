package com.tc.app.exchangemonitor.preloader;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tc.app.exchangemonitor.controller.DatabaseUtil;
import com.tc.app.exchangemonitor.controller.LoginController;
import com.tc.app.exchangemonitor.controller.LoginManager;
import com.tc.app.exchangemonitor.controller.PreferencesUtil;
import com.tc.app.exchangemonitor.view.java.PreloaderView;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class ExchangeMonitionApplicationPreloader extends Preloader
{
	private static final Logger LOGGER = LogManager.getLogger(ExchangeMonitionApplicationPreloader.class);
	private static final boolean shouldShowLoginScreen;
	private static final boolean DEFAULT_BOOLEAN_VALUE = false;
	//private ProgressBar progressBar;
	private Stage preloaderStage;
	private Scene preloaderScene;

	static
	{
		shouldShowLoginScreen = !PreferencesUtil.getUserPreferences().getBoolean("isAuthenticatedUser", DEFAULT_BOOLEAN_VALUE);
	}

	public ExchangeMonitionApplicationPreloader()
	{
		LOGGER.debug("ExchangeMonitorApplicationPreloader constructor called by ", Thread.currentThread().getName());
	}

	@Override
	public void init() throws Exception
	{
		LOGGER.debug("ExchangeMonitorApplicationPreloader init called by ", Thread.currentThread().getName());
		super.init();
		/* If the preloader has complex UI it's initialization can be done in here. Ensure that you do it with Platform.runLater() */
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		LOGGER.debug("ExchangeMonitorApplicationPreloader start called by ", Thread.currentThread().getName());

		if(shouldShowLoginScreen)
		{
			showLoginScreen();
		}
		else
		{
			boolean status = authenticateInBackground();
			if(!status)
			{
				LOGGER.info("Automatic Login Failed.");
				PreferencesUtil.forgetLoginCredentials();
				PreferencesUtil.getUserPreferences().clear();
				showLoginScreen();
			}
			//HibernateUtil.HIBERNATE_CONNECTION_URL_VALUE = PreferencesUtil.getUserPreferences().get("connectionURL", "");
			LoginController.CONNECTION_URL = PreferencesUtil.getUserPreferences().get("connectionURL", "");
		}
		showPreloaderScreen(primaryStage);
	}

	@Override
	public void handleApplicationNotification(PreloaderNotification info)
	{
		if(info instanceof ProgressNotification)
		{
			//this.preloaderViewModel.updateProgressText(this.MESSAGES[this.counter]);
		}
	}

	@Override
	public void handleProgressNotification(ProgressNotification info)
	{
		super.handleProgressNotification(info);
		if(info.getProgress() != 1 && !preloaderStage.isShowing())
		{
			preloaderStage.show();
		}
		//progressBar.setProgress(info.getProgress());
	}

	@Override
	public void handleStateChangeNotification(StateChangeNotification info)
	{
		super.handleStateChangeNotification(info);
		StateChangeNotification.Type notificationType = info.getType();

		switch(notificationType)
		{
		case BEFORE_LOAD:
			// Called after preloader#start is called.
			LOGGER.debug("ExchangeMonitorApplicationPreloader ", notificationType);
			break;

		case BEFORE_INIT:
			//Called before application#init is called.
			LOGGER.debug("ExchangeMonitorApplicationPreloader ", notificationType);
			break;

		case BEFORE_START:
			//Called after application#init and before application#start is called.
			LOGGER.debug("ExchangeMonitorApplicationPreloader ", notificationType);
			if(preloaderStage.isShowing())
			{
				FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), preloaderStage.getScene().getRoot());
				fadeTransition.setFromValue(1.0);
				fadeTransition.setToValue(0.0);
				final Stage tempStage = preloaderStage;

				EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>()
				{
					public void handle(ActionEvent event)
					{
						tempStage.hide();
					}
				};
				fadeTransition.setOnFinished(eventHandler);
				fadeTransition.play();
				//this.preloaderStage.hide();
			}
			break;
		}
	}

	private Scene createPreloaderScene()
	{
		//progressBar = new ProgressBar();
		//BorderPane borderPane = new BorderPane();
		//borderPane.setCenter(progressBar);
		//return new Scene(borderPane, 300, 150);

		PreloaderView preloaderView = new PreloaderView();
		preloaderScene = new Scene(preloaderView.getView());
		preloaderScene.setFill(Color.TRANSPARENT);
		return preloaderScene;

		/*
		LoginView loginView = new LoginView();
		return new Scene(loginView.getView());
		 */
	}

	private Scene createLoginScene()
	{
		Scene loginScene = new Scene(new StackPane());
		LoginManager loginManager = new LoginManager(loginScene);
		loginManager.constructLoginScreen();
		return loginScene;
	}

	private void showLoginScreen()
	{
		Stage loginStage = new Stage(StageStyle.TRANSPARENT);
		loginStage.setOnCloseRequest((WindowEvent windowEvent) -> Platform.runLater(() -> { Platform.exit(); System.exit(0); } ));
		loginStage.setScene(createLoginScene());
		loginStage.showAndWait();
	}

	private void showPreloaderScreen(final Stage primaryStage)
	{
		preloaderStage = primaryStage;
		preloaderStage.setScene(createPreloaderScene());

		preloaderStage.sizeToScene();
		preloaderStage.centerOnScreen();
		preloaderStage.setAlwaysOnTop(true);
		preloaderStage.initStyle(StageStyle.TRANSPARENT);
		preloaderStage.show();
		preloaderStage.toFront();
	}

	private boolean authenticateInBackground()
	{
		boolean isAuthorized = DEFAULT_BOOLEAN_VALUE;

		try
		{
			isAuthorized = DatabaseUtil.makeTestConnection(PreferencesUtil.getUserPreferences().get("connectionURL", ""), null, null);
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
		}

		return isAuthorized;
	}
}