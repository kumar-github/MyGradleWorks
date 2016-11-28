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

public class ExternalMappingAccountsController implements Initializable
{
	private static final Logger LOGGER = LogManager.getLogger(ExternalMappingAccountsController.class);

	@FXML
	private TableView<IExternalMappingEntity> externalMappingAccountsTableView;

	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceAccountTableColumn;
	@FXML
	private Button addMappingButton;
	@FXML
	private Button deleteMappingButton;
	@FXML
	private Button updateMappingButton;
	@FXML
	private Button refreshMappingButton;

	private final ObservableList<IExternalMappingEntity> externalMappingAccountsObservableList = FXCollections.observableArrayList();
	private final FilteredList<IExternalMappingEntity> externalMappingAccountsFilteredList = new FilteredList<>(this.externalMappingAccountsObservableList, null);
	private final SortedList<IExternalMappingEntity> externalMappingAccountsSortedList = new SortedList<>(this.externalMappingAccountsFilteredList);

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
		ApplicationHelper.controllersMap.putInstance(ExternalMappingAccountsController.class, this);
	}

	private void doAssertion()
	{
	}

	private void doInitialDataBinding()
	{
		this.externalMappingAccountsSortedList.comparatorProperty().bind(this.externalMappingAccountsTableView.comparatorProperty());
		this.externalMappingAccountsTableView.setItems(this.externalMappingAccountsSortedList);
	}

	private void initializeGUI()
	{
		//this.fetchAccountsExternalMapping();
		this.fetchExternalMapping();
	}

	private void initializeTableView()
	{
		this.initializeExternalMappingAccountsTableView();
	}

	private void initializeExternalMappingAccountsTableView()
	{
		this.externalSourceAccountTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue1()));
	}

	/*
	private void fetchAccountsExternalMapping()
	{
		final String selectedExternalTradeSource = ((RadioButton) ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Predicate<IExternalMappingEntity> predicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(selectedExternalTradeSource);
		this.externalMappingAccountsObservableList.addAll(ExternalMappingPredicates.filterExternalMappings(ReferenceDataCache.fetchExternalMappings(), predicate.and(ExternalMappingPredicates.isAccountPredicate)));
		LOGGER.info("Fetched Mappings Count : " + this.externalMappingAccountsObservableList.size());
	}
	 */

	private void fetchExternalMapping()
	{
		final String selectedExternalTradeSource = ((RadioButton) ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Predicate<IExternalMappingEntity> predicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(selectedExternalTradeSource);
		this.externalMappingAccountsObservableList.addAll(ReferenceDataCache.fetchExternalMappings());
		this.updateFilter(predicate);
	}

	public void updateFilter(final Predicate<IExternalMappingEntity> predicate)
	{
		this.externalMappingAccountsFilteredList.setPredicate(predicate.and(ExternalMappingPredicates.isAccountPredicate));
	}

	@FXML
	private void handleAddMapingButtonClick()
	{
	}
}