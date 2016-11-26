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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ExternalMappingCurrenciesController implements Initializable
{
	private static final Logger LOGGER = LogManager.getLogger(ExternalMappingCurrenciesController.class);

	private final ObservableList<IExternalMappingEntity> externalMappingCurrenciesObservableList = FXCollections.observableArrayList();
	private final FilteredList<IExternalMappingEntity> externalMappingCurrenciesFilteredList = new FilteredList<>(this.externalMappingCurrenciesObservableList, null);
	private final SortedList<IExternalMappingEntity> externalMappingCurrenciesSortedList = new SortedList<>(this.externalMappingCurrenciesFilteredList);

	@FXML
	private TableView<IExternalMappingEntity> externalMappingCurrenciesTableView;
	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceCurrencyTableColumn;
	@FXML
	private TableColumn<IExternalMappingEntity, String> ictsCurrencyTableColumn;

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
		ApplicationHelper.controllersMap.putInstance(ExternalMappingCurrenciesController.class, this);
	}

	private void doAssertion()
	{
	}

	private void doInitialDataBinding()
	{
		this.externalMappingCurrenciesSortedList.comparatorProperty().bind(this.externalMappingCurrenciesTableView.comparatorProperty());
		this.externalMappingCurrenciesTableView.setItems(this.externalMappingCurrenciesSortedList);
	}

	private void initializeGUI()
	{
		this.fetchCurrenciesExternalMapping();
	}

	private void initializeTableView()
	{
		this.initializeExternalMappingCurrenciesTableView();
	}

	private void initializeExternalMappingCurrenciesTableView()
	{
		this.externalSourceCurrencyTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue1()));
		this.ictsCurrencyTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAliasValue()));
	}

	private void fetchCurrenciesExternalMapping()
	{
		final String selectedExternalTradeSource = ((RadioButton) ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Predicate<IExternalMappingEntity> predicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(selectedExternalTradeSource);
		this.externalMappingCurrenciesObservableList.addAll(ExternalMappingPredicates.filterExternalMappings(ReferenceDataCache.fetchExternalMappings(), predicate.and(ExternalMappingPredicates.isCurrencyPredicate)));
		LOGGER.info("Fetched Mappings Count : " + this.externalMappingCurrenciesObservableList.size());
	}

	public void updateFilter(final Predicate<IExternalMappingEntity> predicate)
	{
		this.externalMappingCurrenciesFilteredList.setPredicate(predicate);
	}
}