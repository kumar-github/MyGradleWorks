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

public class ExternalMappingUOMConversionsController implements Initializable
{
	private static final Logger LOGGER = LogManager.getLogger(ExternalMappingUOMConversionsController.class);

	private final ObservableList<IExternalMappingEntity> externalMappingUOMConversionsObservableList = FXCollections.observableArrayList();
	private final FilteredList<IExternalMappingEntity> externalMappingUOMConversionsFilteredList = new FilteredList<>(this.externalMappingUOMConversionsObservableList, null);
	private final SortedList<IExternalMappingEntity> externalMappingUOMConversionsSortedList = new SortedList<>(this.externalMappingUOMConversionsFilteredList);

	@FXML
	private TableView<IExternalMappingEntity> externalMappingUOMConversionsTableView;
	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceCommodityTableColumn;
	@FXML
	private TableColumn<IExternalMappingEntity, String> toUomCodeTableColumn;
	@FXML
	private TableColumn<IExternalMappingEntity, String> toUomConvRateTableColumn;
	@FXML
	private Button addMappingButton;
	@FXML
	private Button deleteMappingButton;
	@FXML
	private Button updateMappingButton;
	@FXML
	private Button refreshMappingButton;

	@Override
	public void initialize(final URL location, final ResourceBundle resources)
	{
		this.addThisControllerToControllersMap();
		this.doAssertion();
		this.doInitialDataBinding();
		this.initializeGUI();
		this.setAnyUIComponentStateIfNeeded();
		this.setComponentToolTipIfNeeded();
		this.initializeListeners();
		this.initializeTableView();
	}

	private void addThisControllerToControllersMap()
	{
		ApplicationHelper.controllersMap.putInstance(ExternalMappingUOMConversionsController.class, this);
	}

	private void doAssertion()
	{
	}

	private void doInitialDataBinding()
	{
		this.externalMappingUOMConversionsSortedList.comparatorProperty().bind(this.externalMappingUOMConversionsTableView.comparatorProperty());
		this.externalMappingUOMConversionsTableView.setItems(this.externalMappingUOMConversionsSortedList);
	}

	private void initializeGUI()
	{
		//this.fetchUOMConversionsExternalMapping();
		this.fetchExternalMapping();
	}

	private void setAnyUIComponentStateIfNeeded()
	{
	}

	private void setComponentToolTipIfNeeded()
	{
	}

	private void initializeTableView()
	{
		this.initializeExternalMappingUOMConversionsTableView();
	}

	private void initializeExternalMappingUOMConversionsTableView()
	{
		this.externalSourceCommodityTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue1()));
		this.toUomCodeTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue3()));
		this.toUomConvRateTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAliasValue()));
	}

	private void initializeListeners()
	{
	}

	/*
	private void fetchUOMConversionsExternalMapping()
	{
		final String selectedExternalTradeSource = ((RadioButton) ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Predicate<IExternalMappingEntity> predicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(selectedExternalTradeSource);
		this.externalMappingUOMConversionsObservableList.addAll(ExternalMappingPredicates.filterExternalMappings(ReferenceDataCache.fetchExternalMappings(), predicate.and(ExternalMappingPredicates.isUomConversionPredicate)));
		LOGGER.info("Fetched Mappings Count : " + this.externalMappingUOMConversionsObservableList.size());
	}
	 */

	private void fetchExternalMapping()
	{
		final String selectedExternalTradeSource = ((RadioButton) ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Predicate<IExternalMappingEntity> predicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(selectedExternalTradeSource);
		this.externalMappingUOMConversionsObservableList.addAll(ReferenceDataCache.fetchExternalMappings());
		this.updateFilter(predicate);
	}

	public void updateFilter(final Predicate<IExternalMappingEntity> predicate)
	{
		this.externalMappingUOMConversionsFilteredList.setPredicate(predicate.and(ExternalMappingPredicates.isUomConversionPredicate));
	}

	@FXML
	private void handleAddMapingButtonClick()
	{
	}
}