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

public class ExternalMappingTradersController implements Initializable
{
	private static final Logger LOGGER = LogManager.getLogger(ExternalMappingTradersController.class);

	@FXML
	private TableView<IExternalMappingEntity> externalMappingTradersTableView;

	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceTraderTableColumn;

	@FXML
	private TableColumn<IExternalMappingEntity, String> ictsTraderTableColumn;

	@Inject
	private ExternalMappingsViewModel externalMappingsViewModel;

	private final ObservableList<IExternalMappingEntity> externalMappingTradersObservableList = FXCollections.observableArrayList();
	//private final FilteredList<IExternalMappingEntity> externalMappingTradersFilteredList = new FilteredList<>(this.externalMappingTradersObservableList, ExternalMappingPredicates.isNymexTraderPredicate);
	private final FilteredList<IExternalMappingEntity> externalMappingTradersFilteredList = new FilteredList<>(this.externalMappingTradersObservableList, null);
	private final SortedList<IExternalMappingEntity> externalMappingTradersSortedList = new SortedList<>(this.externalMappingTradersFilteredList);

	@Override
	public void initialize(final URL location, final ResourceBundle resources)
	{
		this.addThisControllerToControllersMap();
		this.doAssertion();
		this.doInitialDataBinding();
		this.initializeGUI();
		this.createListeners();
		this.attachListeners();
		this.initializeTableView();
	}

	private void addThisControllerToControllersMap()
	{
		ApplicationHelper.controllersMap.putInstance(ExternalMappingTradersController.class, this);
	}

	private void doAssertion()
	{
	}

	private void doInitialDataBinding()
	{
		this.externalMappingTradersSortedList.comparatorProperty().bind(this.externalMappingTradersTableView.comparatorProperty());
		this.externalMappingTradersTableView.setItems(this.externalMappingTradersSortedList);

		this.externalMappingsViewModel.isAnyRowSelectedInTradersViewProperty().bind(this.externalMappingTradersTableView.getSelectionModel().selectedItemProperty().isNotNull());
	}

	private void initializeGUI()
	{
		this.fetchTradersExternalMapping();
	}

	private void createListeners()
	{
	}

	private void attachListeners()
	{
	}

	private void initializeTableView()
	{
		this.initializeExternalMappingTradersTableView();
	}

	private void initializeExternalMappingTradersTableView()
	{
		this.externalSourceTraderTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue1()));
		this.ictsTraderTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAliasValue()));
	}

	private void fetchTradersExternalMapping()
	{
		final String selectedExternalTradeSource = ((RadioButton)ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Predicate<IExternalMappingEntity> predicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(selectedExternalTradeSource);
		this.externalMappingTradersObservableList.addAll(ExternalMappingPredicates.filterExternalMappings(ReferenceDataCache.fetchExternalMappings(), predicate.and(ExternalMappingPredicates.isTraderPredicate)));
		LOGGER.info("Fetched Mappings Count : " + this.externalMappingTradersObservableList.size());
	}

	public void updateFilter(final Predicate<IExternalMappingEntity> predicate)
	{
		this.externalMappingTradersFilteredList.setPredicate(predicate);
	}
}