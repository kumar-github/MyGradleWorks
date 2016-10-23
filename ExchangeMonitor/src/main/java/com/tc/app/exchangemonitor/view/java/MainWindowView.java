package com.tc.app.exchangemonitor.view.java;

import java.util.Objects;

import com.tc.framework.fxmlview.FXMLView;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class MainWindowView extends FXMLView
{
	private double xOffset = 0.0;
	private double yOffset = 0.0;
	public MainWindowView()
	{
	}
	
	private Stage primaryStage;
	public MainWindowView(Stage primaryStage)
	{
		this.primaryStage = Objects.requireNonNull(primaryStage, "Primary Stage cannot be NULL");
		this.primaryStage.setOnCloseRequest((WindowEvent windowEvent) -> closeStageWithAnimation(windowEvent));
	}
	
	@Override
	public Parent getView()
	{
		return super.getView();
	}
	
	private Scene primaryScene;
	public Scene getScene()
	{
		primaryScene = new Scene(getView());
		makePrimarySceneDraggable();
		return primaryScene;
	}

	/* Since our stage is undecorated, we cannot drag it. This method will make the scene draggable. */
	private void makePrimarySceneDraggable()
	{
		primaryScene.setOnMousePressed(event ->
		{
			xOffset = primaryStage.getX() - event.getScreenX();
			yOffset = primaryStage.getY() - event.getScreenY();
		});

		primaryScene.setOnMouseDragged(event ->
		{
			primaryStage.setX(event.getScreenX() + xOffset);
			primaryStage.setY(event.getScreenY() + yOffset);
		});
	}
	
	private void closeStageWithAnimation(WindowEvent windowEvent)
	{
		windowEvent.consume();
		/*
		RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.5), this.primaryScene.getRoot());
		rotateTransition.setOnFinished((ActionEvent actionEvent)->{
			this.primaryStage.close();
			Platform.exit();
			System.exit(0);
		});
		
		rotateTransition.setFromAngle(0);
		rotateTransition.setByAngle(360);
		rotateTransition.play();*/
		TranslateTransition tt = new TranslateTransition(Duration.seconds(2), this.primaryScene.getRoot());
		tt.setOnFinished((ActionEvent actionEvent)->{
			this.primaryStage.close();
			Platform.exit();
			System.exit(0);
		});
		tt.setFromX(100);
		tt.setToX(1200);
		//tt.setCycleCount(Timeline.INDEFINITE);
		tt.play();
	}
}