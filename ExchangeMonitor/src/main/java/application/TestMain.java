package application;

import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TestMain extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			//MessageDigest md = MessageDigest.getInstance("");
			MessageDigest md = MessageDigest.getInstance("SHA-1");
		      byte[] digestBytes = md.digest("admin".getBytes("UTF-8"));
		      //new String(Base64.encodeBase64(digestBytes), "ASCII");
		      String s = new String(Base64.getEncoder().encode(digestBytes), "ASCII");
		      System.out.println(s);
			
			LocalTime startTime = LocalTime.of(0, 0, 0);
			LocalTime endTime = LocalTime.of(23, 0, 0);
			
			LocalDate startDate = LocalDate.now();
			LocalDate endDate = LocalDate.now();
			
			LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);
			LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);
			
			String selectedStartDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss").format(startDateTime);
			String selectedEndDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss").format(endDateTime);
			System.out.println(startDateTime);
			System.out.println(endDateTime);
			
			
			//VBox root = FXMLLoader.load(this.getClass().getResource("LoginView.fxml"));
			//FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tc/app/exchangemonitor/view/fxml/LoginView.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
			Scene scene = new Scene(new StackPane());
			scene.setRoot((Parent) loader.load());
			//Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			//LoginManager loginManager = new LoginManager(scene);
			//loginManager.showLoginScreen();

			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);	
			primaryStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}