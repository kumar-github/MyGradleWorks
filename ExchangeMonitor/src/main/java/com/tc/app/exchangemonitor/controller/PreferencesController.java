package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tc.app.exchangemonitor.util.StaticConstantsHelper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

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
	@FXML
	private CheckBox enableRowToolTipsCheckBox;
	@FXML
	private CheckBox enableRowContextMenuCheckBox;

	public PreferencesController()
	{
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		syncPreferencesScreenWithRegistryData();
	}

	private void syncPreferencesScreenWithRegistryData()
	{
		displayAccountsWithPermissionCheckBox.setSelected(PreferencesHelper.getUserPreferences().getBoolean(StaticConstantsHelper.SHOULD_DISPLAY_ACCOUNTS_WITH_PERMISSION, false));
		enableAnimationsCheckBox.setSelected(PreferencesHelper.getUserPreferences().getBoolean(StaticConstantsHelper.SHOULD_ENABLE_ANIMATIONS, true));
		enableRowColorsCheckBox.setSelected(PreferencesHelper.getUserPreferences().getBoolean(StaticConstantsHelper.SHOULD_ENABLE_ROW_COLORS, true));
		enableRowToolTipsCheckBox.setSelected(PreferencesHelper.getUserPreferences().getBoolean(StaticConstantsHelper.SHOULD_ENABLE_ROW_TOOLTIPS, true));
	}

	@FXML
	public void handleResetCredentialsCheckBoxClick(ActionEvent event)
	{
		CheckBox sourceCheckBox = ((CheckBox) event.getSource());
		if(sourceCheckBox.isSelected())
		{
			try
			{
				//PreferencesHelper.getUserPreferences().clear();
				PreferencesHelper.forgetLoginCredentials();
				LOGGER.info("Credentials reset successfully.");
			}
			catch(Exception exception)
			{
				exception.printStackTrace();
			}
		}
		else
		{
		}
	}

	@FXML
	public void handleDisplayAccountsWithPermissionCheckBoxClick(ActionEvent event)
	{
		CheckBox sourceCheckBox = ((CheckBox) event.getSource());
		try
		{
			if(sourceCheckBox.isSelected())
			{
				PreferencesHelper.getUserPreferences().putBoolean(StaticConstantsHelper.SHOULD_DISPLAY_ACCOUNTS_WITH_PERMISSION, true);
				LOGGER.info("Hereafter, Only Accounts with permission will be displayed.");
			}
			else
			{
				PreferencesHelper.getUserPreferences().putBoolean(StaticConstantsHelper.SHOULD_DISPLAY_ACCOUNTS_WITH_PERMISSION, false);
				LOGGER.info("Hereafter, All Accounts will be displayed.");
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}

	@FXML
	public void handleEnableAnimationsCheckBoxClick(ActionEvent event)
	{
		CheckBox sourceCheckBox = ((CheckBox) event.getSource());
		try
		{
			if(sourceCheckBox.isSelected())
			{
				PreferencesHelper.getUserPreferences().putBoolean(StaticConstantsHelper.SHOULD_ENABLE_ANIMATIONS, true);
				LOGGER.info("Hereafter, All Animations will be enabled.");
			}
			else
			{
				PreferencesHelper.getUserPreferences().putBoolean(StaticConstantsHelper.SHOULD_ENABLE_ANIMATIONS, false);
				LOGGER.info("Hereafter, All Animations will be disabled.");
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}

	@FXML
	public void handleEnableRowColorsCheckBoxClick(ActionEvent event)
	{
		CheckBox sourceCheckBox = ((CheckBox) event.getSource());
		try
		{
			if(sourceCheckBox.isSelected())
			{
				PreferencesHelper.getUserPreferences().putBoolean(StaticConstantsHelper.SHOULD_ENABLE_ROW_COLORS, true);
				LOGGER.info("Hereafter, Row Colors will be enabled. Records with different status will be displayed in different colors.");
			}
			else
			{
				PreferencesHelper.getUserPreferences().putBoolean(StaticConstantsHelper.SHOULD_ENABLE_ROW_COLORS, false);
				LOGGER.info("Hereafter, Row Colors will be disabled.");
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}

	@FXML
	public void handleEnableRowToolTipsCheckBoxClick(ActionEvent event)
	{
		CheckBox sourceCheckBox = ((CheckBox) event.getSource());
		try
		{
			if(sourceCheckBox.isSelected())
			{
				PreferencesHelper.getUserPreferences().putBoolean(StaticConstantsHelper.SHOULD_ENABLE_ROW_TOOLTIPS, true);
				LOGGER.info("Hereafter, Row ToolTips will be enabled. You will see few informations of the trade as a tooltip.");
			}
			else
			{
				PreferencesHelper.getUserPreferences().putBoolean(StaticConstantsHelper.SHOULD_ENABLE_ROW_TOOLTIPS, false);
				LOGGER.info("Hereafter, Row ToolTips will be disabled.");
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}

	@FXML
	public void handleEnableRowContextMenuCheckBoxClick(ActionEvent event)
	{
		CheckBox sourceCheckBox = ((CheckBox) event.getSource());
		try
		{
			if(sourceCheckBox.isSelected())
			{
				PreferencesHelper.getUserPreferences().putBoolean(StaticConstantsHelper.SHOULD_ENABLE_ROW_CONTEXT_MENU, true);
				LOGGER.info("Hereafter, Row context menu will be enabled. Right click on a record will give you few menu options.");
			}
			else
			{
				PreferencesHelper.getUserPreferences().putBoolean(StaticConstantsHelper.SHOULD_ENABLE_ROW_CONTEXT_MENU, false);
				LOGGER.info("Hereafter, Row context menu will be disabled.");
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
}