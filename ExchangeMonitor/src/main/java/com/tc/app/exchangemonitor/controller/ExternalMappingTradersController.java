package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.tc.app.exchangemonitor.entitybase.IExternalMappingEntity;
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

public class ExternalMappingTradersController implements Initializable
{
	@FXML
	private TableView<IExternalMappingEntity> externalMappingTradersTableView;
	
	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceTraderTableColumn;
	
	@FXML
	private TableColumn<IExternalMappingEntity, String> ictsTraderTableColumn;
	
	private ObservableList<IExternalMappingEntity> externalMappingTradersObservableList = FXCollections.observableArrayList();
	private FilteredList<IExternalMappingEntity> externalMappingTradersFilteredList = new FilteredList<IExternalMappingEntity>(externalMappingTradersObservableList, ExternalMappingPredicates.applyNymexTradersPredicate);
	private SortedList<IExternalMappingEntity> externalMappingTradersSortedList = new SortedList<IExternalMappingEntity>(externalMappingTradersFilteredList);

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
		externalMappingTradersSortedList.comparatorProperty().bind(externalMappingTradersTableView.comparatorProperty());
		externalMappingTradersTableView.setItems(externalMappingTradersSortedList);
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
		externalSourceTraderTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue1()));
		ictsTraderTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAliasValue()));
	}
	
	private void initializeListeners()
	{
	}
	
	private void fetchTradersExternalMapping()
	{
		externalMappingTradersObservableList.addAll(ReferenceDataCache.fetchExternalMappings());
	}
}