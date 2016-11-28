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
import com.tc.app.exchangemonitor.view.java.CompaniesMappingAddPopupView;

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

public class ExternalMappingCompaniesController implements Initializable
{
	private static final Logger LOGGER = LogManager.getLogger(ExternalMappingCompaniesController.class);

	@FXML
	private TableView<IExternalMappingEntity> externalMappingCompaniesTableView;
	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceCompanyTableColumn;
	@FXML
	private TableColumn<IExternalMappingEntity, String> companyTypeTableColumn;
	@FXML
	private TableColumn<IExternalMappingEntity, String> companyCountryTableColumn;
	@FXML
	private TableColumn<IExternalMappingEntity, String> ictsCompanyTableColumn;
	@FXML
	private Button addMappingButton;
	@FXML
	private Button deleteMappingButton;
	@FXML
	private Button updateMappingButton;
	@FXML
	private Button refreshMappingButton;

	private final ObservableList<IExternalMappingEntity> externalMappingCompaniesObservableList = FXCollections.observableArrayList();
	private final FilteredList<IExternalMappingEntity> externalMappingCompaniesFilteredList = new FilteredList<>(this.externalMappingCompaniesObservableList, null);
	private final SortedList<IExternalMappingEntity> externalMappingCompaniesSortedList = new SortedList<>(this.externalMappingCompaniesFilteredList);


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
		ApplicationHelper.controllersMap.putInstance(ExternalMappingCompaniesController.class, this);
	}

	private void doAssertion()
	{
	}

	private void doInitialDataBinding()
	{
		this.externalMappingCompaniesSortedList.comparatorProperty().bind(this.externalMappingCompaniesTableView.comparatorProperty());
		this.externalMappingCompaniesTableView.setItems(this.externalMappingCompaniesSortedList);
	}

	private void initializeGUI()
	{
		//this.fetchCompaniesExternalMapping();
		this.fetchExternalMapping();
	}

	private void initializeTableView()
	{
		this.initializeExternalMappingCompaniesTableView();
	}

	private void initializeExternalMappingCompaniesTableView()
	{
		this.externalSourceCompanyTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue1()));
		this.companyTypeTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue2()));
		this.companyCountryTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue4()));
		this.ictsCompanyTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAliasValue()));
	}

	/*
	private void fetchCompaniesExternalMapping()
	{
		final String selectedExternalTradeSource = ((RadioButton) ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Predicate<IExternalMappingEntity> predicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(selectedExternalTradeSource);
		this.externalMappingCompaniesObservableList.addAll(ExternalMappingPredicates.filterExternalMappings(ReferenceDataCache.fetchExternalMappings(), predicate.and(ExternalMappingPredicates.isCompanyPredicate)));
		LOGGER.info("Fetched Mappings Count : " + this.externalMappingCompaniesObservableList.size());
	}
	 */

	private void fetchExternalMapping()
	{
		final String selectedExternalTradeSource = ((RadioButton) ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Predicate<IExternalMappingEntity> predicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(selectedExternalTradeSource);
		this.externalMappingCompaniesObservableList.addAll(ReferenceDataCache.fetchExternalMappings());
		this.updateFilter(predicate);
	}

	public void updateFilter(final Predicate<IExternalMappingEntity> predicate)
	{
		this.externalMappingCompaniesFilteredList.setPredicate(predicate.and(ExternalMappingPredicates.isCompanyPredicate));
	}

	@FXML
	public void handleAddMapingButtonClick()
	{
		this.showAddCompaniesMappingView();
	}

	private void showAddCompaniesMappingView()
	{
		final Stage tempStage = new Stage(StageStyle.TRANSPARENT);
		tempStage.initOwner(this.addMappingButton.getScene().getWindow());
		tempStage.initModality(Modality.APPLICATION_MODAL);
		tempStage.setScene(new Scene(new CompaniesMappingAddPopupView().getView()));
		tempStage.showAndWait();

		/* We will come back here once the user pressed cancel or login. Do we need to do anything here?. */
		System.out.println("Stage Operation Completed.");
	}
}