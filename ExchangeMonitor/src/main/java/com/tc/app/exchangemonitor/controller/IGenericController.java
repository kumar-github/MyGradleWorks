package com.tc.app.exchangemonitor.controller;

import javafx.fxml.Initializable;

public interface IGenericController extends Initializable
{
	/* add the instantiated controller object to map, so that we can use in the future. */
	public abstract void addThisControllerToControllersMap();

	/* so that we can track if any fxml variables are not injected. */
	public abstract void doAssertion();

	/* This is to bind the observable variables to the actual UI control. Once bound, the data in the observable variable will automatically displayed on the UI control. Note: Initially the variables 
	 * value may be null, so nothing appears on the UI control but whenever the value gets changed on the variable either directly or indirectly that will reflect on the UI control automatically.
	 */
	public abstract void doInitialDataBinding();

	/* This will initialize the user interface ensuring all UI controls are loaded with the proper data. We need to fetch data from DB and construct checkboxes, buttons etc... and display on the UI. */
	public abstract void initializeGUI();
	
	/* This will create the listeners but wont attach it to any components */
	public abstract void createListeners();
	
	/* This will attach the listeners to the respective UI controls so that when app is launched, everything is ready for user interaction. */
	public abstract void attachListeners();
}