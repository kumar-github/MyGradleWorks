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
public class TemplateTradesMappingAddPopupController implements Initializable
{
	private static final Logger LOGGER = LogManager.getLogger(TemplateTradesMappingAddPopupController.class);
	private static final String TEMPLATE_TRADE_MAPPING_TYPE = "S";

	@FXML
	private TextField externalSourceCommodityTextField;
	@FXML
	private ComboBox<Integer> ictsTemplateTradeComboBox;
	@FXML
	private Label titleLabel;
	@FXML
	private Button saveButton;
	@FXML
	private Button cancelButton;

	private final ObservableList<Integer> observableIctsTemplateTradesList = FXCollections.observableArrayList();
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
		ApplicationHelper.controllersMap.putInstance(TemplateTradesMappingAddPopupController.class, this);
		System.out.println(TEMPLATE_TRADE_MAPPING_TYPE);
	}

	private void doAssertion()
	{
		assert this.externalSourceCommodityTextField != null : "fx:id=\"externalSourceCommodityTextField\" was not injected. Check your FXML file TemplateTradesMappingAddPopupView.fxml";
	}

	private void doInitialDataBinding()
	{
		this.ictsTemplateTradeComboBox.setItems(this.observableIctsTemplateTradesList);
		this.saveButton.disableProperty().bind(this.externalSourceCommodityTextField.textProperty().isEmpty().or(this.ictsTemplateTradeComboBox.valueProperty().isNull()));
	}

	private void initializeGUI()
	{
		this.fetchIctsTemplateTrades();
	}

	private void setAnyUIComponentStateIfNeeded()
	{
		Platform.runLater(() -> this.titleLabel.requestFocus());
	}

	private void fetchIctsTemplateTrades()
	{
		this.observableIctsTemplateTradesList.clear();
		this.observableIctsTemplateTradesList.addAll();
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