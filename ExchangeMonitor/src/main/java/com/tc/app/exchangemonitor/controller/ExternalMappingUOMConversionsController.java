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

public class ExternalMappingUOMConversionsController implements Initializable
{
	private ObservableList<IExternalMappingEntity> externalMappingUOMConversionsObservableList = FXCollections.observableArrayList();
	private FilteredList<IExternalMappingEntity> externalMappingUOMConversionsFilteredList = new FilteredList<IExternalMappingEntity>(externalMappingUOMConversionsObservableList, ExternalMappingPredicates.applyNymexUOMConversionsPredicate);
	private SortedList<IExternalMappingEntity> externalMappingUOMConversionsSortedList = new SortedList<IExternalMappingEntity>(externalMappingUOMConversionsFilteredList);
	
	@FXML
	private TableView<IExternalMappingEntity> externalMappingUOMConversionsTableView;
	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceCommodityTableColumn;
	@FXML
	private TableColumn<IExternalMappingEntity, String> toUomCodeTableColumn;
	@FXML
	private TableColumn<IExternalMappingEntity, String> toUomConvRateTableColumn;

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
		externalMappingUOMConversionsSortedList.comparatorProperty().bind(externalMappingUOMConversionsTableView.comparatorProperty());
		externalMappingUOMConversionsTableView.setItems(externalMappingUOMConversionsSortedList);
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
		externalSourceCommodityTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue1()));
		toUomCodeTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue3()));
		toUomConvRateTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAliasValue()));
	}
	
	private void initializeListeners()
	{
	}
	
	private void fetchTradersExternalMapping()
	{
		externalMappingUOMConversionsObservableList.addAll(ReferenceDataCache.fetchExternalMappings());
	}
}