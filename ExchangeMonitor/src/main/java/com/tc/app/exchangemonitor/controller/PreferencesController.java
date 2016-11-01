/**
 * This comment is a file comment
 * This is 2nd line
 * file name is PreferencesController.java
 * package name is com.tc.app.exchangemonitor.controller
 * project name is ExchangeMonitor
 * type name is PreferencesController
 * user is Saravana Kumar M
 */
package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

/**
 * This comment is for types.
 * This is 2nd line.
 * author is @author Saravana Kumar M
 *
 * tags are 
 */
public class PreferencesController implements Initializable
{
	private static final Logger LOGGER = LogManager.getLogger(PreferencesController.class);

	@FXML
	private CheckBox resetCredentialsCheckBox;
	@FXML
	private CheckBox displayAccountsWithPermissionCheckBox;
	@FXML
	private CheckBox enableAnimationsCheckBox;
	@FXML
	private CheckBox enableRowColorsCheckBox;

	/**
	 * This comment is for constructors
	 * This is 2nd line.
	 * tags are 
	 */
	public PreferencesController()
	{
	}

	/* (non-Javadoc)
	 * This comment is for overriding methods
	 * This is 2nd line
	 * see to overriden @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}

	@FXML
	public void handle(ActionEvent event)
	{
		CheckBox sourceCheckBox = ((CheckBox)event.getSource());
		if(sourceCheckBox.isSelected() && sourceCheckBox.getText().equals("Reset Credentials"))
		{
			try
			{
				PreferencesHelper.getUserPreferences().clear();
				LOGGER.info("Credentials reset successfully.");
			}
			catch (BackingStoreException exception)
			{
				exception.printStackTrace();
			}
		}
		else if(sourceCheckBox.isSelected() && sourceCheckBox.getText().equals("Reset Credentials"))
		{
		}
	}
}