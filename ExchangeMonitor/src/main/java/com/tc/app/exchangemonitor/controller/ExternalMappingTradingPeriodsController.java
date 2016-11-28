package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tc.app.exchangemonitor.entitybase.IExternalMappingEntity;
import com.tc.app.exchangemonitor.model.predicates.ExternalMappingPredicates;
import com.tc.app.exchangemonitor.util.ApplicationHelper;
import com.tc.app.exchangemonitor.util.ReferenceDataCache;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ExternalMappingTradingPeriodsController implements Initializable
{
	private static final Logger LOGGER = LogManager.getLogger(ExternalMappingTradingPeriodsController.class);

	@FXML
	private TableView<IExternalMappingEntity> externalMappingTradingPeriodsTableView;
	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceCommodityTableColumn;
	@FXML
	private TableColumn<IExternalMappingEntity, String> tradingPeriodOffsetMonthTableColumn;
	@FXML
	private Button addMappingButton;
	@FXML
	private Button deleteMappingButton;
	@FXML
	private Button updateMappingButton;
	@FXML
	private Button refreshMappingButton;

	private final ObservableList<IExternalMappingEntity> externalMappingTradingPeriodsObservableList = FXCollections.observableArrayList();
	private final FilteredList<IExternalMappingEntity> externalMappingTradingPeriodsFilteredList = new FilteredList<>(this.externalMappingTradingPeriodsObservableList, null);
	private final SortedList<IExternalMappingEntity> externalMappingTradingPeriodsSortedList = new SortedList<>(this.externalMappingTradingPeriodsFilteredList);

	@Override
	public void initialize(final URL location, final ResourceBundle resources)
	{
		this.addThisControllerToControllersMap();
		this.doAssertion();
		this.doInitialDataBinding();
		this.initializeGUI();
		this.initializeTableView();
	}

	private void addThisControllerToControllersMap()
	{
		ApplicationHelper.controllersMap.putInstance(ExternalMappingTradingPeriodsController.class, this);
	}

	private void doAssertion()
	{
	}

	private void doInitialDataBinding()
	{
		this.externalMappingTradingPeriodsSortedList.comparatorProperty().bind(this.externalMappingTradingPeriodsTableView.comparatorProperty());
		this.externalMappingTradingPeriodsTableView.setItems(this.externalMappingTradingPeriodsSortedList);
	}

	private void initializeGUI()
	{
		//this.fetchTradingPeriodsExternalMapping();
		this.fetchExternalMapping();
	}

	private void initializeTableView()
	{
		this.initializeExternalMappingTradingPeriodsTableView();
	}

	private void initializeExternalMappingTradingPeriodsTableView()
	{
		this.externalSourceCommodityTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue1()));
		this.tradingPeriodOffsetMonthTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAliasValue()));
	}

	/*
	private void fetchTradingPeriodsExternalMapping()
	{
		final String selectedExternalTradeSource = ((RadioButton) ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Predicate<IExternalMappingEntity> predicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(selectedExternalTradeSource);
		this.externalMappingTradingPeriodsObservableList.addAll(ExternalMappingPredicates.filterExternalMappings(ReferenceDataCache.fetchExternalMappings(), predicate.and(ExternalMappingPredicates.isTradingPeriodPredicate)));
		LOGGER.info("Fetched Mappings Count : " + this.externalMappingTradingPeriodsObservableList.size());
	}
	 */

	private void fetchExternalMapping()
	{
		final String selectedExternalTradeSource = ((RadioButton) ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Predicate<IExternalMappingEntity> predicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(selectedExternalTradeSource);
		this.externalMappingTradingPeriodsObservableList.addAll(ReferenceDataCache.fetchExternalMappings());
		this.updateFilter(predicate);
	}

	public void updateFilter(final Predicate<IExternalMappingEntity> predicate)
	{
		this.externalMappingTradingPeriodsFilteredList.setPredicate(predicate.and(ExternalMappingPredicates.isTradingPeriodPredicate));
	}

	@FXML
	private void handleAddMapingButtonClick()
	{
	}
}