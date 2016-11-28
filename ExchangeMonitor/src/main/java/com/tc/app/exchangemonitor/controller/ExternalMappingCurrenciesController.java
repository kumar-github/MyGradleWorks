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
import com.tc.app.exchangemonitor.view.java.TradersMappingAddPopupView;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
		//this.fetchCurrenciesExternalMapping();
		this.fetchExternalMapping();
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

	/*
	private void fetchCurrenciesExternalMapping()
	{
		final String selectedExternalTradeSource = ((RadioButton) ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Predicate<IExternalMappingEntity> predicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(selectedExternalTradeSource);
		this.externalMappingCurrenciesObservableList.addAll(ExternalMappingPredicates.filterExternalMappings(ReferenceDataCache.fetchExternalMappings(), predicate.and(ExternalMappingPredicates.isCurrencyPredicate)));
		LOGGER.info("Fetched Mappings Count : " + this.externalMappingCurrenciesObservableList.size());
	}
	 */

	private void fetchExternalMapping()
	{
		final String selectedExternalTradeSource = ((RadioButton) ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Predicate<IExternalMappingEntity> predicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(selectedExternalTradeSource);
		this.externalMappingCurrenciesObservableList.addAll(ReferenceDataCache.fetchExternalMappings());
		this.updateFilter(predicate);
	}

	public void updateFilter(final Predicate<IExternalMappingEntity> predicate)
	{
		this.externalMappingCurrenciesFilteredList.setPredicate(predicate.and(ExternalMappingPredicates.isCurrencyPredicate));
	}

	@FXML
	private void handleAddMapingButtonClick()
	{
		this.showAddCurrenciesMappingView();
	}

	private void showAddCurrenciesMappingView()
	{
		final Stage tempStage = new Stage(StageStyle.TRANSPARENT);
		/* To make this stage appears on top of the application window. Else, if the application is displayed in the secondary monitor the child stage will still visible on the primary monitor. */
		tempStage.initOwner(this.addMappingButton.getScene().getWindow());
		tempStage.initModality(Modality.APPLICATION_MODAL);
		tempStage.setScene(new Scene(new TradersMappingAddPopupView().getView()));
		tempStage.showAndWait();

		/* We will come back here once the user pressed cancel or login. Do we need to do anything here?. */
		System.out.println("Stage Operation Completed.");
	}
}