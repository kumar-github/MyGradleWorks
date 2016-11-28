/**
 * Copyright (c) 2016 by amphorainc.com. All rights reserved.
 * created on Nov 17, 2016
 */
package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tc.app.exchangemonitor.util.ApplicationHelper;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Saravana Kumar M
 *
 */
public class AccountsMappingAddPopupController implements Initializable
{
	private static final Logger LOGGER = LogManager.getLogger(AccountsMappingAddPopupController.class);
	private static final String ACCOUNT_MAPPING_TYPE = "K";

	@FXML
	private Label titleLabel;
	@FXML
	private TextField externalSourceAccountTextField;
	@FXML
	private Button saveButton;
	@FXML
	private Button cancelButton;

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(final URL location, final ResourceBundle resources)
	{
		//ictsTraderComboBox.cell

		this.addThisControllerToControllersMap();
		this.doAssertion();
		this.doInitialDataBinding();
		this.initializeGUI();
		this.setAnyUIComponentStateIfNeeded();
	}

	private void addThisControllerToControllersMap()
	{
		ApplicationHelper.controllersMap.putInstance(AccountsMappingAddPopupController.class, this);
		System.out.println(ACCOUNT_MAPPING_TYPE);
	}

	private void doAssertion()
	{
		assert this.externalSourceAccountTextField != null : "fx:id=\"externalSourceAccountTextField\" was not injected. Check your FXML file AccountsMappingAddPopupView.fxml";
	}

	private void doInitialDataBinding()
	{
		this.saveButton.disableProperty().bind(this.externalSourceAccountTextField.textProperty().isEmpty());
	}

	private void initializeGUI()
	{
	}

	private void setAnyUIComponentStateIfNeeded()
	{
		Platform.runLater(() -> this.titleLabel.requestFocus());
	}

	@FXML
	private void handleSaveButtonClick()
	{
	}

	@FXML
	private void handleCancelButtonClick()
	{
		((Stage) this.cancelButton.getScene().getWindow()).close();
	}
}