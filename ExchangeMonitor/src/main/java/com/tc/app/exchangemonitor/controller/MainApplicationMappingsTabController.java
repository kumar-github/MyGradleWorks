package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.control.CheckListView;
import org.hibernate.Session;

import com.tc.app.exchangemonitor.entitybase.IExternalMappingEntity;
import com.tc.app.exchangemonitor.model.Account;
import com.tc.app.exchangemonitor.model.ExternalTradeSource;
import com.tc.app.exchangemonitor.model.predicates.ExternalMappingPredicates;
import com.tc.app.exchangemonitor.util.ApplicationHelper;
import com.tc.app.exchangemonitor.util.HibernateUtil;
import com.tc.app.exchangemonitor.util.ReferenceDataCache;
import com.tc.app.exchangemonitor.view.java.BrokersMappingAddPopupView;
import com.tc.app.exchangemonitor.view.java.ExternalMappingAccountsView;
import com.tc.app.exchangemonitor.view.java.ExternalMappingBrokersView;
import com.tc.app.exchangemonitor.view.java.ExternalMappingCompaniesView;
import com.tc.app.exchangemonitor.view.java.ExternalMappingCurrenciesView;
import com.tc.app.exchangemonitor.view.java.ExternalMappingPortfoliosView;
import com.tc.app.exchangemonitor.view.java.ExternalMappingTradersView;
import com.tc.app.exchangemonitor.view.java.ExternalMappingTradesView;
import com.tc.app.exchangemonitor.view.java.ExternalMappingTradingPeriodsView;
import com.tc.app.exchangemonitor.view.java.ExternalMappingUOMConversionsView;
import com.tc.app.exchangemonitor.view.java.TradersMappingAddPopupView;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

public class MainApplicationMappingsTabController implements Initializable
{
	private static final Logger LOGGER = LogManager.getLogger(MainApplicationMonitorTabController.class);

	@FXML
	private CheckListView<ExternalTradeSource> externalTradeSourcesListView;
	@FXML
	private BorderPane mappingsWindowBorderPane;
	@FXML
	private Button tradersMappingButton;
	@FXML
	private Button brokersMappingButton;
	@FXML
	private Button companiesMappingButton;
	@FXML
	private Button currenciesMappingButton;
	@FXML
	private Button portfoliosMappingButton;
	@FXML
	private Button tradesMappingButton;
	@FXML
	private Button accountsMappingButton;
	@FXML
	private Button uomConversionsMappingButton;
	@FXML
	private Button tradingPeriodsMappingButton;
	@FXML
	private Button addMappingButton;
	@FXML
	private Button deleteMappingButton;
	@FXML
	private Button updateMappingButton;

	/*
	 * This is a listener variable which is initialized to null. Later we will bind this listener variable to some property. For eg:(RadioButton's selection property and a TextField's text property etc...) so that when those properties change
	 * this will get fired. But we did not attach any handler method do this variable. We need to attach some kind of handler method which contains the logic to be executed when the corressponding property changes.
	 */
	private ChangeListener<Toggle> externalTradeSourcesRadioButtonClickListener = null;

	private final ObservableList<ExternalTradeSource> observableExternalTradeSourceList = FXCollections.observableArrayList();


	@Override
	public void initialize(final URL location, final ResourceBundle resources)
	{
		this.addThisControllerToControllersMap();
		this.doAssertion();
		this.doInitialDataBinding();
		this.initializeGUI();
		this.setAnyUIComponentStateIfNeeded();
		this.createListeners();
		this.attachListeners();
	}

	private void addThisControllerToControllersMap()
	{
		ApplicationHelper.controllersMap.putInstance(MainApplicationMappingsTabController.class, this);
	}

	private void doAssertion()
	{
		assert this.externalTradeSourcesListView != null : "fx:id=\"externalTradeSourcesListView\" was not injected. Check your FXML file MainApplicationMappingsTabView.fxml";
	}

	private void doInitialDataBinding()
	{
		this.externalTradeSourcesListView.setItems(this.observableExternalTradeSourceList);

		/* Disable the mapping buttons if external trade source is not selected. */
		this.tradersMappingButton.disableProperty().bind(ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.selectedToggleProperty().isNull());
		this.brokersMappingButton.disableProperty().bind(ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.selectedToggleProperty().isNull());
		this.companiesMappingButton.disableProperty().bind(ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.selectedToggleProperty().isNull());
		this.currenciesMappingButton.disableProperty().bind(ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.selectedToggleProperty().isNull());
		this.portfoliosMappingButton.disableProperty().bind(ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.selectedToggleProperty().isNull());
		this.tradesMappingButton.disableProperty().bind(ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.selectedToggleProperty().isNull());
		this.accountsMappingButton.disableProperty().bind(ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.selectedToggleProperty().isNull());
		this.uomConversionsMappingButton.disableProperty().bind(ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.selectedToggleProperty().isNull());
		this.tradingPeriodsMappingButton.disableProperty().bind(ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.selectedToggleProperty().isNull());

		/* Enable the "Add" button only if any mapping table is visible. */
		this.addMappingButton.disableProperty().bind(Bindings.createBooleanBinding(() -> this.mappingsWindowBorderPane.getCenter() == null, this.mappingsWindowBorderPane.centerProperty()));
		//this.deleteMappingButton.disableProperty().bind(this.externalMappingsViewModel.isAnyRowSelectedInTradersViewProperty().not());
		//this.deleteMappingButton.disableProperty().bind(this.externalMappingsViewModel.isAnyRowSelectedInTradersViewProperty().not().or(this.externalMappingsViewModel.isAnyRowSelectedInBrokersViewProperty().not()));
	}

	private void initializeGUI()
	{
		this.fetchExternalTradeSources();
	}

	private void setAnyUIComponentStateIfNeeded()
	{
		//exchangesListView.setCellFactory((param) -> new ExternalTradeSourceRadioButtonCellFactory());
		//externalTradeSourcesListView.setCellFactory(new ExternalTradeSourceRadioButtonCellFactory());
		this.externalTradeSourcesListView.setCellFactory((param) -> new ExternalTradeSourceRadioCellForMappingsTab());
	}

	private void fetchExternalTradeSources()
	{
		this.observableExternalTradeSourceList.addAll(ReferenceDataCache.fetchExternalTradeSources().values());
	}

	public void createListeners()
	{
		this.externalTradeSourcesRadioButtonClickListener = (observavleValue, oldValue, newValue) -> {
			this.handleExternalTradeSourcesRadioButtonClick(newValue);
		};
	}

	private void handleExternalTradeSourcesRadioButtonClick(final Toggle newValue)
	{
		if(this.mappingsWindowBorderPane.getCenter() != null)
		{
			final Predicate<IExternalMappingEntity> aPredicate = ExternalMappingPredicates.getPredicateForExternalTradeSource(((RadioButton) newValue).getText());
			switch(this.mappingsWindowBorderPane.getCenter().getId())
			{
				case "tradersMappingVBox":
					ApplicationHelper.controllersMap.getInstance(ExternalMappingTradersController.class).updateFilter(aPredicate);
					break;

				case "brokersMappingVBox":
					ApplicationHelper.controllersMap.getInstance(ExternalMappingBrokersController.class).updateFilter(aPredicate);
					break;

				case "companiesMappingVBox":
					ApplicationHelper.controllersMap.getInstance(ExternalMappingCompaniesController.class).updateFilter(aPredicate);
					break;

				case "currenciesMappingVBox":
					ApplicationHelper.controllersMap.getInstance(ExternalMappingCurrenciesController.class).updateFilter(aPredicate);
					break;

				case "portfoliosMappingVBox":
					ApplicationHelper.controllersMap.getInstance(ExternalMappingPortfoliosController.class).updateFilter(aPredicate);
					break;

				case "tradesMappingVBox":
					ApplicationHelper.controllersMap.getInstance(ExternalMappingTradesController.class).updateFilter(aPredicate);
					break;

				case "accountsMappingVBox":
					ApplicationHelper.controllersMap.getInstance(ExternalMappingAccountsController.class).updateFilter(aPredicate);
					break;

				case "uomConversionsMappingVBox":
					ApplicationHelper.controllersMap.getInstance(ExternalMappingUOMConversionsController.class).updateFilter(aPredicate);
					break;

				case "tradingPeriodsMappingVBox":
					ApplicationHelper.controllersMap.getInstance(ExternalMappingTradingPeriodsController.class).updateFilter(aPredicate);
					break;

				default:
					break;
			}
		}
	}

	public void attachListeners()
	{
		ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.selectedToggleProperty().addListener(this.externalTradeSourcesRadioButtonClickListener);
	}

	private static String CURRENT_VISIBLE_MAPPING_VIEW = null;
	@FXML
	private void handleTradersMappingButtonClick()
	{
		this.mappingsWindowBorderPane.setCenter(null);
		this.mappingsWindowBorderPane.setCenter(new ExternalMappingTradersView().getView());
		CURRENT_VISIBLE_MAPPING_VIEW = this.mappingsWindowBorderPane.getCenter().getId();
	}

	@FXML
	private void handleBrokersMappingButtonClick()
	{
		this.mappingsWindowBorderPane.setCenter(null);
		this.mappingsWindowBorderPane.setCenter(new ExternalMappingBrokersView().getView());
		CURRENT_VISIBLE_MAPPING_VIEW = this.mappingsWindowBorderPane.getCenter().getId();
	}

	@FXML
	private void handleCompaniesMappingButtonClick()
	{
		this.mappingsWindowBorderPane.setCenter(null);
		this.mappingsWindowBorderPane.setCenter(new ExternalMappingCompaniesView().getView());
		CURRENT_VISIBLE_MAPPING_VIEW = this.mappingsWindowBorderPane.getCenter().getId();
	}

	@FXML
	private void handleCurrenciesMappingButtonClick()
	{
		this.mappingsWindowBorderPane.setCenter(null);
		this.mappingsWindowBorderPane.setCenter(new ExternalMappingCurrenciesView().getView());
		CURRENT_VISIBLE_MAPPING_VIEW = this.mappingsWindowBorderPane.getCenter().getId();
	}

	@FXML
	private void handlePortfoliosMappingButtonClick()
	{
		this.mappingsWindowBorderPane.setCenter(null);
		this.mappingsWindowBorderPane.setCenter(new ExternalMappingPortfoliosView().getView());
		CURRENT_VISIBLE_MAPPING_VIEW = this.mappingsWindowBorderPane.getCenter().getId();
	}

	@FXML
	private void handleTradesMappingButtonClick()
	{
		this.mappingsWindowBorderPane.setCenter(null);
		this.mappingsWindowBorderPane.setCenter(new ExternalMappingTradesView().getView());
		CURRENT_VISIBLE_MAPPING_VIEW = this.mappingsWindowBorderPane.getCenter().getId();
	}

	@FXML
	private void handleAccountsMappingButtonClick()
	{
		this.mappingsWindowBorderPane.setCenter(null);
		this.mappingsWindowBorderPane.setCenter(new ExternalMappingAccountsView().getView());
		CURRENT_VISIBLE_MAPPING_VIEW = this.mappingsWindowBorderPane.getCenter().getId();
	}

	@FXML
	private void handleUOMConversionsMappingButtonClick()
	{
		this.mappingsWindowBorderPane.setCenter(null);
		this.mappingsWindowBorderPane.setCenter(new ExternalMappingUOMConversionsView().getView());
		CURRENT_VISIBLE_MAPPING_VIEW = this.mappingsWindowBorderPane.getCenter().getId();
	}

	@FXML
	private void handleTradingPeriodsMappingButtonClick()
	{
		this.mappingsWindowBorderPane.setCenter(null);
		this.mappingsWindowBorderPane.setCenter(new ExternalMappingTradingPeriodsView().getView());
		CURRENT_VISIBLE_MAPPING_VIEW = this.mappingsWindowBorderPane.getCenter().getId();
	}

	@FXML
	private void handleAddMapingButtonClick()
	{
		final Session session = HibernateUtil.beginTransaction();

		final long startTime = System.currentTimeMillis();
		final List x = session.createCriteria(Account.class).list();
		final long endTime = System.currentTimeMillis();
		System.out.println("It took " + (endTime - startTime) + " millisecs to fetch " + x.size() + " records");
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
		switch(CURRENT_VISIBLE_MAPPING_VIEW)
		{
			case "tradersMappingVBox":
				this.showTradersMappingAddPopup();
				break;

			case "brokersMappingVBox":
				this.showBrokersMappingAddPopup();
				break;

			default:
				break;
		}
	}

	private void showTradersMappingAddPopup()
	{
		final Alert tradersMappingAddPopup = this.getAddMappingsPopupTemplate();
		tradersMappingAddPopup.setHeaderText("Add Traders Mapping");
		tradersMappingAddPopup.setGraphic(new ImageView(this.getClass().getResource("/icons/dollar-1-512.png").toString()));
		tradersMappingAddPopup.getDialogPane().setContent(new TradersMappingAddPopupView().getView());
		final Optional<ButtonType> x = tradersMappingAddPopup.showAndWait();

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

		x.ifPresent(data -> {
			System.out.println(data);
		});
	}

	private void showBrokersMappingAddPopup()
	{
		final Alert brokersMappingAddPopup = this.getAddMappingsPopupTemplate();
		brokersMappingAddPopup.setHeaderText("Add Brokers Mapping");
		brokersMappingAddPopup.setGraphic(new ImageView(this.getClass().getResource("/icons/dollar-1-512.png").toString()));
		brokersMappingAddPopup.getDialogPane().setContent(new BrokersMappingAddPopupView().getView());
		brokersMappingAddPopup.showAndWait();
	}

	private Alert getAddMappingsPopupTemplate()
	{
		final Alert alert = new Alert(AlertType.NONE);
		final ButtonType saveButtonType = new ButtonType("Save", ButtonData.OK_DONE);
		final ButtonType cancelButtonType = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		alert.getDialogPane().getButtonTypes().addAll(saveButtonType, cancelButtonType);
		alert.getDialogPane().setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)));
		alert.initStyle(StageStyle.TRANSPARENT);
		return alert;
	}
}