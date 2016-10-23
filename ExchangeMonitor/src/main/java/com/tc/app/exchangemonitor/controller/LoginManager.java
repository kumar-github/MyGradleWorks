package com.tc.app.exchangemonitor.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Manages control flow for logins */
public class LoginManager
{
	private Scene scene;

	public LoginManager(Scene scene)
	{
		this.scene = scene;
	}

	/**
	 * Callback method invoked to notify that a user has been authenticated.
	 * Will show the main application screen.
	 */ 
	//public void authenticated(String sessionID)
	public void authenticated()
	{
		//showMainView(sessionID);
		((Stage)scene.getWindow()).close();
	}

	/**
	 * Callback method invoked to notify that a user has logged out of the main application.
	 * Will show the login application screen.
	 */ 
	public void logout()
	{
		constructLoginScreen();
	}

	public void constructLoginScreen()
	{
		try
		{
			//FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tc/app/exchangemonitor/view/fxml/LoginView.fxml"));

			scene.setRoot((Parent) loader.load());
			LoginController controller = loader.<LoginController>getController();
			controller.initManager(this);
		}
		catch (IOException ex)
		{
			Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void showMainView(String sessionID)
	{
		try
		{
			//FXMLLoader loader = new FXMLLoader(getClass().getResource("mainview.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Mainview.fxml"));
			scene.setRoot((Parent) loader.load());
			//MainViewController controller = loader.<MainViewController>getController();
			//controller.initSessionID(this, sessionID);
		}
		catch (IOException ex)
		{
			Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}