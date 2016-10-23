package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.tc.app.exchangemonitor.entitybase.IExternalMappingEntity;
import com.tc.app.exchangemonitor.model.ExternalMapping;
import com.tc.app.exchangemonitor.model.predicates.ExternalMappingPredicates;
import com.tc.app.exchangemonitor.util.ReferenceDataCache;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ExternalMappingBrokersController implements Initializable
{
	/*
	private ObservableList<ExternalMapping> externalMappingBrokersObservableList = FXCollections.observableArrayList();
	private FilteredList<ExternalMapping> externalMappingBrokersFilteredList = new FilteredList<ExternalMapping>(externalMappingBrokersObservableList, ExternalMappingPredicates.applyNymexBrokersPredicate);
	private SortedList<ExternalMapping> externalMappingBrokersSortedList = new SortedList<ExternalMapping>(externalMappingBrokersFilteredList);
	*/
	private ObservableList<IExternalMappingEntity> externalMappingBrokersObservableList = FXCollections.observableArrayList();
	private FilteredList<IExternalMappingEntity> externalMappingBrokersFilteredList = new FilteredList<IExternalMappingEntity>(externalMappingBrokersObservableList, ExternalMappingPredicates.applyNymexBrokersPredicate);
	private SortedList<IExternalMappingEntity> externalMappingBrokersSortedList = new SortedList<IExternalMappingEntity>(externalMappingBrokersFilteredList);
	
	@FXML
	private TableView<IExternalMappingEntity> externalMappingBrokersTableView;
	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceBrokerTableColumn;
	@FXML
	private TableColumn<IExternalMappingEntity, String> brokerTypeTableColumn;
	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceTraderTableColumn;
	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceAccountTableColumn;
	@FXML
	private TableColumn<IExternalMappingEntity, String> ictsBrokerTableColumn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		addThisControllerToControllersMap();
		doAssertion();
		doInitialDataBinding();
		initializeGUI();
		setAnyUIComponentStateIfNeeded();
		setComponentToolTipIfNeeded();
		initializeListeners();
		initializeTableView();
	}
	
	private void addThisControllerToControllersMap()
	{
	}
	
	private void doAssertion()
	{
	}
	
	private void doInitialDataBinding()
	{
		externalMappingBrokersSortedList.comparatorProperty().bind(externalMappingBrokersTableView.comparatorProperty());
		externalMappingBrokersTableView.setItems(externalMappingBrokersSortedList);
	}
	
	private void initializeGUI()
	{
		fetchTradersExternalMapping();
	}

	private void setAnyUIComponentStateIfNeeded()
	{
	}
	
	private void setComponentToolTipIfNeeded()
	{
	}
	
	private void initializeTableView()
	{
		initializeExternalMappingTradersTableView();
	}
	
	private void initializeExternalMappingTradersTableView()
	{
		externalSourceBrokerTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue1()));
		brokerTypeTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue2()));
		externalSourceTraderTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue3()));
		externalSourceAccountTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue4()));
		ictsBrokerTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAliasValue()));
	}
	
	private void initializeListeners()
	{
	}
	
	private void fetchTradersExternalMapping()
	{
		externalMappingBrokersObservableList.addAll(ReferenceDataCache.fetchExternalMappings());
	}
}