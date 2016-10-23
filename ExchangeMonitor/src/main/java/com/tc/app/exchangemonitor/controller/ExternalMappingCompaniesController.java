package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.tc.app.exchangemonitor.model.ExternalMapping;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ExternalMappingCompaniesController implements Initializable
{
	@FXML
	private TableView<ExternalMapping> externalMappingTradersTableView;
	@FXML
	private TableColumn<ExternalMapping, String> externalSourceCompanyTableColumn;
	@FXML
	private TableColumn<ExternalMapping, String> companyTypeTableColumn;
	@FXML
	private TableColumn<ExternalMapping, String> companyCountryTableColumn;
	@FXML
	private TableColumn<ExternalMapping, String> ictsCompanyTableColumn;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
	}
}