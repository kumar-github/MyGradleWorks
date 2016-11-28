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

public class ExternalMappingTemplateTradesController implements Initializable
{
	private static final Logger LOGGER = LogManager.getLogger(ExternalMappingTemplateTradesController.class);

	@FXML
	private TableView<IExternalMappingEntity> externalMappingTradesTableView;

	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceCommodityTableColumn;

	@FXML
	private TableColumn<IExternalMappingEntity, String> ictsTemplateTradeTableColumn;
	@FXML
	private Button addMappingButton;
	@FXML
	private Button deleteMappingButton;
	@FXML
	private Button updateMappingButton;
	@FXML
	private Button refreshMappingButton;

	private final ObservableList<IExternalMappingEntity> externalMappingTradesObservableList = FXCollections.observableArrayList();
	private final FilteredList<IExternalMappingEntity> externalMappingTradesFilteredList = new FilteredList<>(this.externalMappingTradesObservableList, null);
	private final SortedList<IExternalMappingEntity> externalMappingTradesSortedList = new SortedList<>(this.externalMappingTradesFilteredList);

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
		ApplicationHelper.controllersMap.putInstance(ExternalMappingTemplateTradesController.class, this);
	}

	private void doAssertion()
	{
	}

	private void doInitialDataBinding()
	{
		this.externalMappingTradesSortedList.comparatorProperty().bind(this.externalMappingTradesTableView.comparatorProperty());
		this.externalMappingTradesTableView.setItems(this.externalMappingTradesSortedList);
	}

	private void initializeGUI()
	{
		//this.fetchTradesExternalMapping();
		this.fetchExternalMapping();
	}

	private void initializeTableView()
	{
		this.initializeExternalMappingTradesTableView();
	}

	private void initializeExternalMappingTradesTableView()
	{
		this.externalSourceCommodityTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue1()));
		this.ictsTemplateTradeTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAliasValue()));
	}

	/*
	private void fetchTradesExternalMapping()
	{
		final String selectedExternalTradeSource = ((RadioButton) ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Predicate<IExternalMappingEntity> predicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(selectedExternalTradeSource);
		this.externalMappingTradesObservableList.addAll(ExternalMappingPredicates.filterExternalMappings(ReferenceDataCache.fetchExternalMappings(), predicate.and(ExternalMappingPredicates.isTemplateTradePredicate)));
		LOGGER.info("Fetched Mappings Count : " + this.externalMappingTradesObservableList.size());
	}
	 */

	private void fetchExternalMapping()
	{
		final String selectedExternalTradeSource = ((RadioButton) ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Predicate<IExternalMappingEntity> predicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(selectedExternalTradeSource);
		this.externalMappingTradesObservableList.addAll(ReferenceDataCache.fetchExternalMappings());
		this.updateFilter(predicate);
	}

	public void updateFilter(final Predicate<IExternalMappingEntity> predicate)
	{
		this.externalMappingTradesFilteredList.setPredicate(predicate.and(ExternalMappingPredicates.isTemplateTradePredicate));
	}

	@FXML
	private void handleAddMapingButtonClick()
	{
	}
}