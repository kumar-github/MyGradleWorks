/**
 * Copyright (c) 2016 by amphorainc.com. All rights reserved.
 * created on Nov 17, 2016
 */
package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tc.app.exchangemonitor.model.Uom;
import com.tc.app.exchangemonitor.util.ApplicationHelper;

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
public class UOMConversionsMappingAddPopupController implements Initializable
{
	private static final Logger LOGGER = LogManager.getLogger(UOMConversionsMappingAddPopupController.class);
	private static final String UOMCONVERSION_MAPPING_TYPE = "Q";

	@FXML
	private Label titleLabel;
	@FXML
	private TextField externalSourceCommodityTextField;
	@FXML
	private ComboBox<Uom> toUOMCodeComboBox;
	@FXML
	private TextField toUOMConvRateTextField;
	@FXML
	private Button saveButton;
	@FXML
	private Button cancelButton;

	private final ObservableList<Uom> observableUomsList = FXCollections.observableArrayList();

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
		ApplicationHelper.controllersMap.putInstance(UOMConversionsMappingAddPopupController.class, this);
		System.out.println(UOMCONVERSION_MAPPING_TYPE);
	}

	private void doAssertion()
	{
		assert this.externalSourceCommodityTextField != null : "fx:id=\"externalSourceCommodityTextField\" was not injected. Check your FXML file UOMConversionsMappingAddPopupView.fxml";
	}

	private void doInitialDataBinding()
	{
		this.toUOMCodeComboBox.setItems(this.observableUomsList);
		this.saveButton.disableProperty().bind(this.externalSourceCommodityTextField.textProperty().isEmpty().or(this.toUOMCodeComboBox.valueProperty().isNull()).or(this.toUOMConvRateTextField.textProperty().isEmpty()));
	}

	private void initializeGUI()
	{
		this.fetchIctsUoms();
	}

	private void setAnyUIComponentStateIfNeeded()
	{
		Platform.runLater(() -> this.titleLabel.requestFocus());
	}

	private void fetchIctsUoms()
	{
		this.observableUomsList.clear();
		this.observableUomsList.addAll();
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