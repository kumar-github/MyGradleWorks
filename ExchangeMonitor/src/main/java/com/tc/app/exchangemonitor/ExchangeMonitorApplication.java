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

	public static void main(final String[] args)
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
		for(int i = 0; i < 1000; i++)
		{
			final double progress = (100 * i) / 1000;
			LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
		}
	}

	@Override
	public void start(final Stage primaryStage)
	{
		//System.out.println(HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("GenNewTransactionSP"));
		//System.out.println(HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("testStoredProc"));
		//System.out.println(HibernateUtil.beginTransaction().getNamedQuery("testStoredProc").list());
		//HibernateUtil.beginTransaction().getNamedQuery("testStoredProc").executeUpdate();
		//HibernateUtil.beginTransaction().getNamedQuery("testStoredProc").list();

		LOGGER.debug("ExchangeMonitorApplication start called by ", Thread.currentThread().getName());
		// Do all the heavy lifting stuff. One Question. Can we do the heavy lifting stuff in init() instead here?
		// then load the primary stage
		try
		{
			this.primaryStage = primaryStage;
			this.primaryScene = this.createPrimaryScene();

			this.initializePrimaryStage();
			this.initializePrimaryScene();

			//animateStageIfNeeded();

			this.primaryStage.setScene(this.primaryScene);
			this.animateStageIfNeeded();

			primaryStage.show();
			primaryStage.toFront();
		}
		catch(final Exception ex)
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
		this.undecoratePrimaryStage();

		/* commented the below line. don't do it here instead do it in the respective view's controller class.*/
		//primaryStage.setTitle("Exchange Monitor");

		this.primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/exchange-512.png")));
		this.primaryStage.setFullScreen(false);
		this.primaryStage.setResizable(false);

		this.primaryStage.sizeToScene();
		this.primaryStage.centerOnScreen();
	}

	private void initializePrimaryScene()
	{
		this.primaryScene.setFill(Color.TRANSPARENT);
	}

	private void undecoratePrimaryStage()
	{
		this.primaryStage.initStyle(StageStyle.UNDECORATED);
		this.primaryStage.initStyle(StageStyle.TRANSPARENT);
	}

	private Scene createPrimaryScene()
	{
		final MainWindowView mainWindowView = new MainWindowView(this.primaryStage);
		return mainWindowView.getScene();
	}

	private void animateStageIfNeeded()
	{
		this.setFadeInTransition();
		//setRotateTransition();
	}

	private void setFadeInTransition()
	{
		//super.setOpacity(0);
		this.primaryScene.getRoot().setOpacity(0.0);
		//primaryStage.setOpacity(0);
		this.primaryStage.showingProperty().addListener((observableValue, oldValue, newValue) -> {
			if(newValue)
			{
				final FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), this.primaryScene.getRoot());
				fadeTransition.setToValue(1);
				fadeTransition.play();
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