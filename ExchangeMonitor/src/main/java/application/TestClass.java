package application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tc.app.exchangemonitor.util.HibernateUtil;
import com.tc.app.exchangemonitor.util.ReferenceDataCache;
import com.tc.framework.injection.Injector;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TestClass extends Application
{
	//private Rectangle2D primaryMonitor = Screen.getPrimary().getVisualBounds();
	private static final Logger LOGGER = LogManager.getLogger(TestClass.class);

	public TestClass()
	{
		LOGGER.debug("ExchangeMonitorApplication constructor called by ", Thread.currentThread().getName());
	}

	public static void main(String[] args)
	{
		//com.sun.security.auth.module.NTSystem NTSystem = new com.sun.security.auth.module.NTSystem();
		//System.out.println(NTSystem.getName());
		LOGGER.debug("ExchangeMonitorApplication main called by ", Thread.currentThread().getName());
		//Application.launch();
		launch(args);
	}

	@Override
	public void init()
	{
		LOGGER.debug("ExchangeMonitorApplication init called by ", Thread.currentThread().getName());
		HibernateUtil.getSessionFactory();
		ReferenceDataCache.loadAllReferenceData();
	}

	@Override
	public void start(Stage primaryStage)
	{
		LOGGER.debug("ExchangeMonitorApplication start called by ", Thread.currentThread().getName());
		// Do all the heavy lifting stuff. One Question. Can we do the heavy lifting stuff in init() instead here?
		// then load the primary stage
		try
		{
			//VBox root = FXMLLoader.load(this.getClass().getResource("LoginView.fxml"));
			HBox root = FXMLLoader.load(this.getClass().getResource("TestView.fxml"));
			Scene scene = new Scene(root);
			//Scene scene = new Scene(vbox);
			primaryStage.setScene(scene);
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			//primaryStage.initStyle(StageStyle.TRANSPARENT);
			//primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.show();
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