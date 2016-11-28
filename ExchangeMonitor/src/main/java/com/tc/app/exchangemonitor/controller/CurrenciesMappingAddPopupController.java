/**
 * Copyright (c) 2016 by amphorainc.com. All rights reserved.
 * created on Nov 17, 2016
 */
package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tc.app.exchangemonitor.model.Commodity;
import com.tc.app.exchangemonitor.util.ApplicationHelper;
import com.tc.app.exchangemonitor.util.ReferenceDataCache;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Saravana Kumar M
 *
 */
public class CurrenciesMappingAddPopupController implements Initializable
{
	private static final Logger LOGGER = LogManager.getLogger(CurrenciesMappingAddPopupController.class);
	private static final String CURRENCY_MAPPING_TYPE = "U";

	@FXML
	private TextField externalSourceCurrencyTextField;
	@FXML
	private ComboBox<Commodity> ictsCurrencyComboBox;
	//private ComboBox<String> ictsCurrencyComboBox;
	@FXML
	private Label titleLabel;
	@FXML
	private Button saveButton;
	@FXML
	private Button cancelButton;

	//private final ObservableList<String> observableIctsCurrencyList = FXCollections.observableArrayList();
	private final ObservableList<Commodity> observableIctsCurrencyList = FXCollections.observableArrayList();

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(final URL location, final ResourceBundle resources)
	{
		this.addThisControllerToControllersMap();
		this.doAssertion();
		this.doInitialDataBinding();
		this.initializeGUI();
		this.setAnyUIComponentStateIfNeeded();
	}

	private void addThisControllerToControllersMap()
	{
		ApplicationHelper.controllersMap.putInstance(CurrenciesMappingAddPopupController.class, this);
		System.out.println(CURRENCY_MAPPING_TYPE);
	}

	private void doAssertion()
	{
		assert this.externalSourceCurrencyTextField != null : "fx:id=\"externalSourceCurrencyTextField\" was not injected. Check your FXML file CurrenciesMappingAddPopupView.fxml";
	}

	private void doInitialDataBinding()
	{
		this.ictsCurrencyComboBox.setItems(this.observableIctsCurrencyList);
		this.saveButton.disableProperty().bind(this.externalSourceCurrencyTextField.textProperty().isEmpty().or(this.ictsCurrencyComboBox.valueProperty().isNull()));
	}

	private void initializeGUI()
	{
		this.fetchIctsCurrencies();
	}

	private void setAnyUIComponentStateIfNeeded()
	{
		Platform.runLater(() -> this.titleLabel.requestFocus());
	}

	private void fetchIctsCurrencies()
	{
		this.observableIctsCurrencyList.clear();
		this.observableIctsCurrencyList.addAll(ReferenceDataCache.fetchAllActiveCommodities().values());
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