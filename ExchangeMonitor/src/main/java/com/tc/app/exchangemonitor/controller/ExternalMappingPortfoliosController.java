package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tc.app.exchangemonitor.entitybase.IExternalMappingEntity;
import com.tc.app.exchangemonitor.model.ExternalMapping;
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

public class ExternalMappingPortfoliosController implements Initializable
{
	private static final Logger LOGGER = LogManager.getLogger(ExternalMappingPortfoliosController.class);

	private final ObservableList<IExternalMappingEntity> externalMappingPortfoliosObservableList = FXCollections.observableArrayList();
	private final FilteredList<IExternalMappingEntity> externalMappingPortfoliosFilteredList = new FilteredList<>(this.externalMappingPortfoliosObservableList, null);
	private final SortedList<IExternalMappingEntity> externalMappingPortfoliosSortedList = new SortedList<>(this.externalMappingPortfoliosFilteredList);

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
		this.initializeTableView();
	}

	private void addThisControllerToControllersMap()
	{
		ApplicationHelper.controllersMap.putInstance(ExternalMappingPortfoliosController.class, this);
	}

	private void doAssertion()
	{
	}

	private void doInitialDataBinding()
	{
		this.externalMappingPortfoliosSortedList.comparatorProperty().bind(this.externalMappingPortfoliosTableView.comparatorProperty());
		this.externalMappingPortfoliosTableView.setItems(this.externalMappingPortfoliosSortedList);
	}

	private void initializeGUI()
	{
		//this.fetchPortfoliosExternalMapping();
		this.fetchExternalMapping();
	}

	private void initializeTableView()
	{
		this.initializeExternalMappingPortfoliosTableView();
	}

	private void initializeExternalMappingPortfoliosTableView()
	{
		this.externalSourceCommodityTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue1()));
		this.externalSourceTraderTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue2()));
		this.externalSourceTradingPeriodTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue3()));
		this.externalSourceAccountTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue4()));
		this.ictsPortfolioTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAliasValue()));
	}

	/*
	private void fetchPortfoliosExternalMapping()
	{
		final String selectedExternalTradeSource = ((RadioButton) ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Predicate<IExternalMappingEntity> predicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(selectedExternalTradeSource);
		this.externalMappingPortfoliosObservableList.addAll(ExternalMappingPredicates.filterExternalMappings(ReferenceDataCache.fetchExternalMappings(), predicate.and(ExternalMappingPredicates.isPortfolioPredicate)));
		LOGGER.info("Fetched Mappings Count : " + this.externalMappingPortfoliosObservableList.size());
	}
	 */

	private void fetchExternalMapping()
	{
		final String selectedExternalTradeSource = ((RadioButton) ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Predicate<IExternalMappingEntity> predicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(selectedExternalTradeSource);
		this.externalMappingPortfoliosObservableList.addAll(ReferenceDataCache.fetchExternalMappings());
		this.updateFilter(predicate);
	}

	public void updateFilter(final Predicate<IExternalMappingEntity> predicate)
	{
		this.externalMappingPortfoliosFilteredList.setPredicate(predicate.and(ExternalMappingPredicates.isPortfolioPredicate));
	}

	@FXML
	private void handleAddMapingButtonClick()
	{
	}
}