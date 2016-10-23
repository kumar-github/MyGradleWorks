package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.tc.app.exchangemonitor.model.ExternalTradeSource;
import com.tc.app.exchangemonitor.util.ApplicationHelper;
import com.tc.app.exchangemonitor.util.ReferenceDataCache;
import com.tc.app.exchangemonitor.view.java.ExternalMappingAccountsView;
import com.tc.app.exchangemonitor.view.java.ExternalMappingBrokersView;
import com.tc.app.exchangemonitor.view.java.ExternalMappingCompaniesView;
import com.tc.app.exchangemonitor.view.java.ExternalMappingCurrenciesView;
import com.tc.app.exchangemonitor.view.java.ExternalMappingPortfoliosView;
import com.tc.app.exchangemonitor.view.java.ExternalMappingTradersView;
import com.tc.app.exchangemonitor.view.java.ExternalMappingTradesView;
import com.tc.app.exchangemonitor.view.java.ExternalMappingTradingPeriodsView;
import com.tc.app.exchangemonitor.view.java.ExternalMappingUOMConversionsView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class MainApplicationMappingsTabController implements Initializable
{
	@FXML
	private ListView<ExternalTradeSource> externalTradeSourcesListView;
	//private ListView<IExternalTradeSourceEntity> externalTradeSourcesListView;
	
	@FXML
	private BorderPane mappingsWindowBorderPane;
	
	private ObservableList<ExternalTradeSource> observableExternalTradeSourceList = FXCollections.observableArrayList();
	//private ObservableList<IExternalTradeSourceEntity> observableExternalTradeSourceList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		/*externalTradeSourcesListView.getSelectionModel().selectedItemProperty().addListener(
		            new ChangeListener<IExternalTradeSourceEntity>() {
		                public void changed(ObservableValue<? extends IExternalTradeSourceEntity> ov, IExternalTradeSourceEntity old_val, IExternalTradeSourceEntity new_val) {
		                        System.out.println(new_val);
		                }
		        });*/
		externalTradeSourcesListView.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<ExternalTradeSource>() {
					public void changed(ObservableValue<? extends ExternalTradeSource> ov, ExternalTradeSource old_val, ExternalTradeSource new_val) {
						System.out.println(new_val);
					}
				});
		
		/*externalTradeSourcesListView.getSelectionModel().getSelectedItems().addListener(
				new ListChangeListener<IExternalTradeSourceEntity>() {
					@Override
					public void onChanged(
							javafx.collections.ListChangeListener.Change<? extends IExternalTradeSourceEntity> c) {
						System.out.println(c);
					}
				});*/
		
				externalTradeSourcesListView.getSelectionModel().getSelectedItems().addListener(
				new ListChangeListener<ExternalTradeSource>() {
					@Override
					public void onChanged(
							javafx.collections.ListChangeListener.Change<? extends ExternalTradeSource> c) {
						System.out.println(c);
					}
				});
		
		addThisControllerToControllersMap();
		doAssertion();
		doInitialDataBinding();
		initializeGUI();
		setAnyUIComponentStateIfNeeded();
		setComponentToolTipIfNeeded();
		initializeListeners();
	}
	
	private void addThisControllerToControllersMap()
	{
		ApplicationHelper.controllersMap.putInstance(MainApplicationMappingsTabController.class, this);
	}

	private void doAssertion()
	{
		assert externalTradeSourcesListView != null : "fx:id=\"externalTradeSourcesListView\" was not injected. Check your FXML file MainApplicationMappingsTabView.fxml";
	}

	private void doInitialDataBinding()
	{
		externalTradeSourcesListView.setItems(observableExternalTradeSourceList);
	}

	private void initializeGUI()
	{
		fetchExternalTradeSources();
	}
	
	private void setAnyUIComponentStateIfNeeded()
	{
		//exchangesListView.setCellFactory((param) -> new ExternalTradeSourceRadioButtonCellFactory());
		externalTradeSourcesListView.setCellFactory(new ExternalTradeSourceRadioButtonCellFactory());
	}

	private void setComponentToolTipIfNeeded()
	{
	}
	
	private void initializeListeners()
	{
	}
	
	private void fetchExternalTradeSources()
	{
		observableExternalTradeSourceList.addAll(ReferenceDataCache.fetchExternalTradeSources().values());
	}
	
	@FXML
	private void handleTradersMappingButtonClick()
	{
		mappingsWindowBorderPane.setCenter(null);
		mappingsWindowBorderPane.setCenter(new ExternalMappingTradersView().getView());
	}
	
	@FXML
	private void handleBrokersMappingButtonClick()
	{
		mappingsWindowBorderPane.setCenter(null);
		mappingsWindowBorderPane.setCenter(new ExternalMappingBrokersView().getView());
	}

	@FXML
	private void handleCompaniesMappingButtonClick()
	{
		mappingsWindowBorderPane.setCenter(null);
		mappingsWindowBorderPane.setCenter(new ExternalMappingCompaniesView().getView());
	}

	@FXML
	private void handleCurrenciesMappingButtonClick()
	{
		mappingsWindowBorderPane.setCenter(null);
		mappingsWindowBorderPane.setCenter(new ExternalMappingCurrenciesView().getView());
	}

	@FXML
	private void handlePortfoliosMappingButtonClick()
	{
		mappingsWindowBorderPane.setCenter(null);
		mappingsWindowBorderPane.setCenter(new ExternalMappingPortfoliosView().getView());
	}

	@FXML
	private void handleTradesMappingButtonClick()
	{
		mappingsWindowBorderPane.setCenter(null);
		mappingsWindowBorderPane.setCenter(new ExternalMappingTradesView().getView());
	}

	@FXML
	private void handleAccountsMappingButtonClick()
	{
		mappingsWindowBorderPane.setCenter(null);
		mappingsWindowBorderPane.setCenter(new ExternalMappingAccountsView().getView());
	}

	@FXML
	private void handleUOMConversionsMappingButtonClick()
	{
		mappingsWindowBorderPane.setCenter(null);
		mappingsWindowBorderPane.setCenter(new ExternalMappingUOMConversionsView().getView());
	}

	@FXML
	private void handleTradingPeriodsMappingButtonClick()
	{
		mappingsWindowBorderPane.setCenter(null);
		mappingsWindowBorderPane.setCenter(new ExternalMappingTradingPeriodsView().getView());
	}
}