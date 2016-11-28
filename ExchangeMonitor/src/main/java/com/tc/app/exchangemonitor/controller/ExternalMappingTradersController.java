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

public class ExternalMappingTradersController implements Initializable
{
	private static final Logger LOGGER = LogManager.getLogger(ExternalMappingTradersController.class);

	@FXML
	private TableView<IExternalMappingEntity> externalMappingTradersTableView;

	@FXML
	private TableColumn<IExternalMappingEntity, String> externalSourceTraderTableColumn;

	@FXML
	private TableColumn<IExternalMappingEntity, String> ictsTraderTableColumn;

	@FXML
	private Button addMappingButton;
	@FXML
	private Button deleteMappingButton;
	@FXML
	private Button updateMappingButton;
	@FXML
	private Button refreshMappingButton;

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

		//this.externalMappingsViewModel.isAnyRowSelectedInTradersViewProperty().bind(this.externalMappingTradersTableView.getSelectionModel().selectedItemProperty().isNotNull());
		this.deleteMappingButton.disableProperty().bind(this.externalMappingTradersTableView.getSelectionModel().selectedItemProperty().isNull());
		this.updateMappingButton.disableProperty().bind(this.externalMappingTradersTableView.getSelectionModel().selectedItemProperty().isNull());
	}

	private void initializeGUI()
	{
		this.fetchExternalMapping();
		//this.fetchTradersExternalMapping();
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

	/*
	private void fetchTradersExternalMapping()
	{
		final String selectedExternalTradeSource = ((RadioButton)ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Predicate<IExternalMappingEntity> predicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(selectedExternalTradeSource);
		this.externalMappingTradersObservableList.addAll(ExternalMappingPredicates.filterExternalMappings(ReferenceDataCache.fetchExternalMappings(), predicate.and(ExternalMappingPredicates.isTraderPredicate)));
		LOGGER.info("Fetched Mappings Count : " + this.externalMappingTradersObservableList.size());
	}
	 */

	private void fetchExternalMapping()
	{
		final String selectedExternalTradeSource = ((RadioButton) ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Predicate<IExternalMappingEntity> predicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(selectedExternalTradeSource);
		//this.externalMappingTradersObservableList.addAll(ExternalMappingPredicates.filterExternalMappings(ReferenceDataCache.fetchExternalMappings(), predicate.and(ExternalMappingPredicates.isTraderPredicate)));

		/* We are loading all the external mappings and setting it to the tableview. After that we update the filter with required predicates. Is this better or load only respective mappings.? */
		this.externalMappingTradersObservableList.addAll(ReferenceDataCache.fetchExternalMappings());
		this.updateFilter(predicate);
	}

	public void updateFilter(final Predicate<IExternalMappingEntity> predicate)
	{
		this.externalMappingTradersFilteredList.setPredicate(predicate.and(ExternalMappingPredicates.isTraderPredicate));
	}

	@FXML
	private void handleAddMapingButtonClick()
	{
		/*
		final Dialog dialog = new Dialog<>();
		final ButtonType loginButtonType = new ButtonType("Test", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(loginButtonType);

		dialog.showAndWait();

		final Alert alert = new Alert(AlertType.NONE);
		alert.getDialogPane().getButtonTypes().add(loginButtonType);
		alert.getDialogPane().setContent(new VBox(new TextField("welcome")));
		alert.showAndWait();
		 */
		this.showAddTradersMappingView();
	}

	private void showAddTradersMappingView()
	{
		final Stage tempStage = new Stage(StageStyle.TRANSPARENT);
		/* To make this stage appears on top of the application window. Else, if the application is displayed in the secondary monitor the child stage will still visible on the primary monitor. */
		tempStage.initOwner(this.addMappingButton.getScene().getWindow());
		tempStage.initModality(Modality.APPLICATION_MODAL);
		tempStage.setScene(new Scene(new TradersMappingAddPopupView().getView()));
		tempStage.showAndWait();

		/* We will come back here once the user pressed cancel or login. Do we need to do anything here?. */
		System.out.println("Stage Operation Completed.");

		//final Optional<String> x = tradersMappingAddPopup.showAndWait();
		// Convert the result to a username-password-pair when the login button is clicked.
		/*
		tradersMappingAddPopup.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return new Pair<>(username.getText(), password.getText());
		    }
		    return null;
		});
		 */

		/*x.ifPresent(data -> {
			System.out.println(data);
		});*/
	}

	@FXML
	private void handleRefreshMappingButtonClick()
	{
	}
}