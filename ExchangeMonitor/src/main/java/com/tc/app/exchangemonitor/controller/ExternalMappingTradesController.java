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

public class ExternalMappingTradesController implements Initializable
{
	@FXML
	private TableView<IExternalMappingEntity> externalMappingTradesTableView;
	
	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceCommodityTableColumn;
	
	@FXML
	private TableColumn<IExternalMappingEntity, String> ictsTemplateTradeTableColumn;
	
	private ObservableList<IExternalMappingEntity> externalMappingTradesObservableList = FXCollections.observableArrayList();
	private FilteredList<IExternalMappingEntity> externalMappingTradesFilteredList = new FilteredList<IExternalMappingEntity>(externalMappingTradesObservableList, ExternalMappingPredicates.applyNymexTemplateTradesPredicate);
	private SortedList<IExternalMappingEntity> externalMappingTradesSortedList = new SortedList<IExternalMappingEntity>(externalMappingTradesFilteredList);

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
		externalMappingTradesSortedList.comparatorProperty().bind(externalMappingTradesTableView.comparatorProperty());
		externalMappingTradesTableView.setItems(externalMappingTradesSortedList);
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
		ictsTemplateTradeTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAliasValue()));
	}
	
	private void initializeListeners()
	{
	}
	
	private void fetchTradersExternalMapping()
	{
		externalMappingTradesObservableList.addAll(ReferenceDataCache.fetchExternalMappings());
	}
}