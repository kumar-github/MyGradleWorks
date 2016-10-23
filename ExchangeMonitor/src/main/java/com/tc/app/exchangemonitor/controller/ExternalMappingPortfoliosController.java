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

public class ExternalMappingPortfoliosController implements Initializable
{
	/*
	private ObservableList<ExternalMapping> externalMappingPortfoliosObservableList = FXCollections.observableArrayList();
	private FilteredList<ExternalMapping> externalMappingPortfoliosFilteredList = new FilteredList<ExternalMapping>(externalMappingPortfoliosObservableList, ExternalMappingPredicates.applyNymexPortfoliosPredicate);
	private SortedList<ExternalMapping> externalMappingPortfoliosSortedList = new SortedList<ExternalMapping>(externalMappingPortfoliosFilteredList);
	*/
	
	private ObservableList<IExternalMappingEntity> externalMappingPortfoliosObservableList = FXCollections.observableArrayList();
	private FilteredList<IExternalMappingEntity> externalMappingPortfoliosFilteredList = new FilteredList<IExternalMappingEntity>(externalMappingPortfoliosObservableList, ExternalMappingPredicates.applyNymexPortfoliosPredicate);
	private SortedList<IExternalMappingEntity> externalMappingPortfoliosSortedList = new SortedList<IExternalMappingEntity>(externalMappingPortfoliosFilteredList);

	@FXML
	private TableView<IExternalMappingEntity> externalMappingPortfoliosTableView;

	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceCommodityTableColumn;
	
	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceTraderTableColumn;

	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceTradingPeriodTableColumn;

	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceAccountTableColumn;

	@FXML
	private TableColumn<ExternalMapping, String> ictsPortfolioTableColumn;

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
		externalMappingPortfoliosSortedList.comparatorProperty().bind(externalMappingPortfoliosTableView.comparatorProperty());
		externalMappingPortfoliosTableView.setItems(externalMappingPortfoliosSortedList);
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
		externalSourceTraderTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue2()));
		externalSourceTradingPeriodTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue3()));
		externalSourceAccountTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue4()));
		ictsPortfolioTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAliasValue()));
	}
	
	private void initializeListeners()
	{
	}
	
	private void fetchTradersExternalMapping()
	{
		externalMappingPortfoliosObservableList.addAll(ReferenceDataCache.fetchExternalMappings());
	}
}