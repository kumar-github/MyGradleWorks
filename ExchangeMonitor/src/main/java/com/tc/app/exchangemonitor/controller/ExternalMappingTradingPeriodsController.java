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

public class ExternalMappingTradingPeriodsController implements Initializable
{
	private ObservableList<IExternalMappingEntity> externalMappingTradingPeriodsObservableList = FXCollections.observableArrayList();
	private FilteredList<IExternalMappingEntity> externalMappingTradingPeriodsFilteredList = new FilteredList<IExternalMappingEntity>(externalMappingTradingPeriodsObservableList, ExternalMappingPredicates.applyNymexTradingPeriodsPredicate);
	private SortedList<IExternalMappingEntity> externalMappingTradingPeriodsSortedList = new SortedList<IExternalMappingEntity>(externalMappingTradingPeriodsFilteredList);
	
	@FXML
	private TableView<IExternalMappingEntity> externalMappingTradingPeriodsTableView;
	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceCommodityTableColumn;
	@FXML
	private TableColumn<IExternalMappingEntity, String> tradingPeriodOffsetMonthTableColumn;

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
		externalMappingTradingPeriodsSortedList.comparatorProperty().bind(externalMappingTradingPeriodsTableView.comparatorProperty());
		externalMappingTradingPeriodsTableView.setItems(externalMappingTradingPeriodsSortedList);
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
		tradingPeriodOffsetMonthTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAliasValue()));
	}
	
	private void initializeListeners()
	{
	}
	
	private void fetchTradersExternalMapping()
	{
		externalMappingTradingPeriodsObservableList.addAll(ReferenceDataCache.fetchExternalMappings());
	}
}