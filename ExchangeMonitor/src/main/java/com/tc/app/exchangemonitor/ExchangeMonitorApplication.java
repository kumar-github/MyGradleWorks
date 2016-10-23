package com.tc.app.exchangemonitor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.javafx.application.LauncherImpl;
import com.tc.app.exchangemonitor.util.HibernateUtil;
import com.tc.app.exchangemonitor.util.ReferenceDataCache;
import com.tc.app.exchangemonitor.view.java.MainWindowView;
import com.tc.framework.injection.Injector;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

@SuppressWarnings("restriction")
public class ExchangeMonitorApplication extends Application
{
	//private Rectangle2D primaryMonitor = Screen.getPrimary().getVisualBounds();
	private static final Logger LOGGER = LogManager.getLogger(ExchangeMonitorApplication.class);

	private Stage primaryStage = null;
	private Scene primaryScene = null;

	public ExchangeMonitorApplication()
	{
		LOGGER.debug("ExchangeMonitorApplication constructor called by ", Thread.currentThread().getName());
	}

	public static void main(String[] args)
	{
		LOGGER.debug("ExchangeMonitorApplication main called by ", Thread.currentThread().getName());
		Application.launch();
		//LauncherImpl.launchApplication(ExchangeMonitorApplication.class, ExchangeMonitionApplicationPreloader.class, args);
	}

	@Override
	public void init()
	{
		LOGGER.debug("ExchangeMonitorApplication init called by ", Thread.currentThread().getName());
		HibernateUtil.getSessionFactory();
		ReferenceDataCache.loadAllReferenceData();
		for(int i=0; i<1000; i++)
		{
			double progress = (100 * i) / 1000;
			LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
		}
	}

	@Override
	public void start(Stage primaryStage)
	{
		LOGGER.debug("ExchangeMonitorApplication start called by ", Thread.currentThread().getName());
		// Do all the heavy lifting stuff. One Question. Can we do the heavy lifting stuff in init() instead here?
		// then load the primary stage
		try
		{
			this.primaryStage = primaryStage;
			this.primaryScene = createPrimaryScene();

			initializePrimaryStage();
			initializePrimaryScene();

			//animateStageIfNeeded();

			this.primaryStage.setScene(primaryScene);
			animateStageIfNeeded();

			primaryStage.show();
			primaryStage.toFront();
		}
		catch(Exception ex)
		{
			LOGGER.error(ex);
			Injector.forgetAll();
			Platform.exit();
		}
		finally
		{
			//Injector.forgetAll();
			//Platform.exit();
		}
	}

	private void initializePrimaryStage()
	{
		undecoratePrimaryStage();

		/* commented the below line. don't do it here instead do it in the respective view's controller class.*/
		//primaryStage.setTitle("Exchange Monitor");

		primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/exchange-512.png")));
		primaryStage.setFullScreen(false);
		primaryStage.setResizable(false);

		primaryStage.sizeToScene();
		primaryStage.centerOnScreen();
	}

	private void initializePrimaryScene()
	{
		primaryScene.setFill(Color.TRANSPARENT);
	}

	private void undecoratePrimaryStage()
	{
		this.primaryStage.initStyle(StageStyle.UNDECORATED);
		this.primaryStage.initStyle(StageStyle.TRANSPARENT);
	}


	private Scene createPrimaryScene()
	{
		MainWindowView mainWindowView = new MainWindowView(primaryStage);
		return mainWindowView.getScene();
	}

	private void animateStageIfNeeded()
	{
		setFadeInTransition();
		//setRotateTransition();
	}

	private void setFadeInTransition()
	{
		//super.setOpacity(0);
		primaryScene.getRoot().setOpacity(0.0);
		//primaryStage.setOpacity(0);
		primaryStage.showingProperty().addListener(new ChangeListener<Boolean>()
		{
			@Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
				if (t1.booleanValue())
				{
					FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), primaryScene.getRoot());
					fadeTransition.setToValue(1);
					fadeTransition.play();
				}
			}
		});
	}

	@Override
	public void stop() throws Exception
	{
		LOGGER.debug("ExchangeMonitorApplication stop called by ", Thread.currentThread().getName());
		super.stop();
		Injector.forgetAll();
		HibernateUtil.closeSessionFactory();
		LOGGER.info("Application Terminated.");
		Platform.exit();
		System.exit(0);
	}
}