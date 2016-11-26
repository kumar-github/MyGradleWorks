package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tc.app.exchangemonitor.entitybase.IExternalMappingEntity;
import com.tc.app.exchangemonitor.model.predicates.ExternalMappingPredicates;
import com.tc.app.exchangemonitor.util.ApplicationHelper;
import com.tc.app.exchangemonitor.util.ReferenceDataCache;
import com.tc.app.exchangemonitor.viewmodel.ExternalMappingsViewModel;

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

public class ExternalMappingBrokersController implements Initializable
{
	private static final Logger LOGGER = LogManager.getLogger(ExternalMappingBrokersController.class);

	private final ObservableList<IExternalMappingEntity> externalMappingBrokersObservableList = FXCollections.observableArrayList();
	private final FilteredList<IExternalMappingEntity> externalMappingBrokersFilteredList = new FilteredList<>(this.externalMappingBrokersObservableList, null);
	private final SortedList<IExternalMappingEntity> externalMappingBrokersSortedList = new SortedList<>(this.externalMappingBrokersFilteredList);

	@FXML
	private TableView<IExternalMappingEntity> externalMappingBrokersTableView;
	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceBrokerTableColumn;
	@FXML
	private TableColumn<IExternalMappingEntity, String> brokerTypeTableColumn;
	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceTraderTableColumn;
	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceAccountTableColumn;
	@FXML
	private TableColumn<IExternalMappingEntity, String> ictsBrokerTableColumn;

	@Inject
	private ExternalMappingsViewModel externalMappingsViewModel;

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
		ApplicationHelper.controllersMap.putInstance(ExternalMappingBrokersController.class, this);
	}

	private void doAssertion()
	{
	}

	private void doInitialDataBinding()
	{
		this.externalMappingBrokersSortedList.comparatorProperty().bind(this.externalMappingBrokersTableView.comparatorProperty());
		this.externalMappingBrokersTableView.setItems(this.externalMappingBrokersSortedList);

		this.externalMappingsViewModel.isAnyRowSelectedInBrokersViewProperty().bind(this.externalMappingBrokersTableView.getSelectionModel().selectedItemProperty().isNotNull());
	}

	private void initializeGUI()
	{
		this.fetchBrokersExternalMapping();
	}

	private void initializeTableView()
	{
		this.initializeExternalMappingTradersTableView();
	}

	private void initializeExternalMappingTradersTableView()
	{
		this.externalSourceBrokerTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue1()));
		this.brokerTypeTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue2()));
		this.externalSourceTraderTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue3()));
		this.externalSourceAccountTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue4()));
		this.ictsBrokerTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAliasValue()));
	}

	private void fetchBrokersExternalMapping()
	{
		final String selectedExternalTradeSource = ((RadioButton) ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Predicate<IExternalMappingEntity> predicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(selectedExternalTradeSource);
		this.externalMappingBrokersObservableList.addAll(ExternalMappingPredicates.filterExternalMappings(ReferenceDataCache.fetchExternalMappings(), predicate.and(ExternalMappingPredicates.isBrokerPredicate)));
		LOGGER.info("Fetched Mappings Count : " + this.externalMappingBrokersObservableList.size());
	}

	public void updateFilter(final Predicate<IExternalMappingEntity> predicate)
	{
		this.externalMappingBrokersFilteredList.setPredicate(predicate);
	}
}