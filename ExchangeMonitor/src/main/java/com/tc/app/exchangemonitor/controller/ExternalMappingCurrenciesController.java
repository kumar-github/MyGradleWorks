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

public class ExternalMappingCurrenciesController implements Initializable
{
	/*
	private ObservableList<ExternalMapping> externalMappingCurrenciesObservableList = FXCollections.observableArrayList();
	private FilteredList<ExternalMapping> externalMappingCurrenciesFilteredList = new FilteredList<ExternalMapping>(externalMappingCurrenciesObservableList, ExternalMappingPredicates.applyNymexCurrenciesPredicate);
	private SortedList<ExternalMapping> externalMappingCurrenciesSortedList = new SortedList<ExternalMapping>(externalMappingCurrenciesFilteredList);
	*/
	
	private ObservableList<IExternalMappingEntity> externalMappingCurrenciesObservableList = FXCollections.observableArrayList();
	private FilteredList<IExternalMappingEntity> externalMappingCurrenciesFilteredList = new FilteredList<IExternalMappingEntity>(externalMappingCurrenciesObservableList, ExternalMappingPredicates.applyNymexCurrenciesPredicate);
	private SortedList<IExternalMappingEntity> externalMappingCurrenciesSortedList = new SortedList<IExternalMappingEntity>(externalMappingCurrenciesFilteredList);
	
	@FXML
	private TableView<IExternalMappingEntity> externalMappingCurrenciesTableView;
	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceCurrencyTableColumn;
	@FXML
	private TableColumn<IExternalMappingEntity, String> ictsCurrencyTableColumn;

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
		externalMappingCurrenciesSortedList.comparatorProperty().bind(externalMappingCurrenciesTableView.comparatorProperty());
		externalMappingCurrenciesTableView.setItems(externalMappingCurrenciesSortedList);
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
		externalSourceCurrencyTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue1()));
		ictsCurrencyTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAliasValue()));
	}
	
	private void initializeListeners()
	{
	}
	
	private void fetchTradersExternalMapping()
	{
		externalMappingCurrenciesObservableList.addAll(ReferenceDataCache.fetchExternalMappings());
	}
}