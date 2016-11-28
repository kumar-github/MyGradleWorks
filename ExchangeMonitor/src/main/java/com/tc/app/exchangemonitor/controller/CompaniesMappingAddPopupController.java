/**
 * Copyright (c) 2016 by amphorainc.com. All rights reserved.
 * created on Nov 17, 2016
 */
package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tc.app.exchangemonitor.model.Account;
import com.tc.app.exchangemonitor.model.Country;
import com.tc.app.exchangemonitor.util.ApplicationHelper;
import com.tc.app.exchangemonitor.util.ReferenceDataCache;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Saravana Kumar M
 *
 */
public class CompaniesMappingAddPopupController implements Initializable
{
	private static final Logger LOGGER = LogManager.getLogger(CompaniesMappingAddPopupController.class);
	private static final String COMPANY_MAPPING_TYPE = "C";

	@FXML
	private Label titleLabel;
	@FXML
	private TextField externalSourceCompanyTextField;
	@FXML
	private ComboBox<String> companyTypeComboBox;
	@FXML
	private ComboBox<Country> companyCountryComboBox;
	@FXML
	private ComboBox<Account> ictsCompanyComboBox;
	@FXML
	private Button saveButton;
	@FXML
	private Button cancelButton;

	private final ObservableList<Country> observableCompanyCountriesList = FXCollections.observableArrayList();

	private final ObservableList<Account> observableIctsCompaniesList = FXCollections.observableArrayList();
	private final FilteredList<Account> filteredIctsCompaniesList = new FilteredList<>(this.observableIctsCompaniesList, null);
	private final SortedList<Account> sortedIctsCompaniesList = new SortedList<>(this.filteredIctsCompaniesList);


	/* Listener Variables */
	private ChangeListener<String> companyTypeComboBoxChangeListener = null;

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
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
		ApplicationHelper.controllersMap.putInstance(CompaniesMappingAddPopupController.class, this);
		System.out.println(COMPANY_MAPPING_TYPE);
	}

	private void doAssertion()
	{
		assert this.externalSourceCompanyTextField != null : "fx:id=\"externalSourceCompanyTextField\" was not injected. Check your FXML file CompaniesMappingAddPopupView.fxml";
	}

	private void doInitialDataBinding()
	{
		this.companyCountryComboBox.setItems(this.observableCompanyCountriesList);
		this.ictsCompanyComboBox.setItems(this.sortedIctsCompaniesList);
		this.saveButton.disableProperty().bind(this.externalSourceCompanyTextField.textProperty().isEmpty().or(this.companyTypeComboBox.valueProperty().isNull()).or(this.companyCountryComboBox.valueProperty().isNull()).or(this.ictsCompanyComboBox.valueProperty().isNull()));
	}

	private void initializeGUI()
	{
		this.fetchCountries();

		/* By companies we mean Accounts of type "'CUSTOMER" or "PEICOMP". */
		this.fetchCompanies();
	}

	private void setAnyUIComponentStateIfNeeded()
	{
		Platform.runLater(() -> this.titleLabel.requestFocus());
	}

	private void createListeners()
	{
		this.companyTypeComboBoxChangeListener = (observableValue, oldValue, newValue) -> {
			this.handleCompanyTypeComboBoxSelectionChange(newValue);
		};
	}

	private void attachListeners()
	{
		this.companyTypeComboBox.valueProperty().addListener(this.companyTypeComboBoxChangeListener);
	}

	private void handleCompanyTypeComboBoxSelectionChange(final String newValue)
	{
		if(newValue.equals("CUSTOMER"))
		{
			LOGGER.debug("Before Applying any Predicate : " + this.filteredIctsCompaniesList.size());
			this.filteredIctsCompaniesList.setPredicate(null);
			LOGGER.debug("After Clearing Existing Predicate : " + this.filteredIctsCompaniesList.size());
			this.filteredIctsCompaniesList.setPredicate((anAccount) -> anAccount.getAcctTypeCode().getAcctTypeCode().equals("CUSTOMER"));
			LOGGER.debug("After Applying new Predicate : " + this.filteredIctsCompaniesList.size());
		}
		else if(newValue.equals("BOOKING COMPANY"))
		{
			LOGGER.debug("Before Applying any Predicate : " + this.filteredIctsCompaniesList.size());
			this.filteredIctsCompaniesList.setPredicate(null);
			LOGGER.debug("After Clearing Existing Predicate : " + this.filteredIctsCompaniesList.size());
			this.filteredIctsCompaniesList.setPredicate((anAccount) -> anAccount.getAcctTypeCode().getAcctTypeCode().trim().equals("PEICOMP"));
			LOGGER.debug("After Applying new Predicate : " + this.filteredIctsCompaniesList.size());
		}
	}

	private void fetchCountries()
	{
		this.observableCompanyCountriesList.clear();
		this.observableCompanyCountriesList.addAll(ReferenceDataCache.fetchAllActiveCountries().values());
		/*
		final Session session = HibernateUtil.beginTransaction();
		final Criteria criteria = session.createCriteria(com.tc.app.exchangemonitor.model.Country.class);
		criteria.add(Restrictions.isNotNull("isoCountryCode"));
		criteria.setProjection(Projections.groupProperty("isoCountryCode"));
		criteria.setProjection(Projections.groupProperty("countryName"));
		this.observableCompanyCountriesList.clear();
		final long startTime = System.currentTimeMillis();
		this.observableCompanyCountriesList.addAll(criteria.list());
		final long endTime = System.currentTimeMillis();
		session.close();
		LOGGER.info("It took " + (endTime - startTime) + " millsecs to fetch " + this.observableCompanyCountriesList.size() + " Countries.");
		 */
	}

	private void fetchCompanies()
	{
		this.observableIctsCompaniesList.clear();
		this.observableIctsCompaniesList.addAll(ReferenceDataCache.fetchAllActiveAccounts().values());
		/*
		final Session session = HibernateUtil.beginTransaction();
		final Criteria criteria = session.createCriteria(com.tc.app.exchangemonitor.model.Account.class);
		criteria.add(Restrictions.eq("acctStatus", 'A'));
		criteria.setFetchMode("acctTypeCode", FetchMode.JOIN);
		this.observableIctsCompaniesList.clear();
		final long startTime = System.currentTimeMillis();
		this.observableIctsCompaniesList.addAll(criteria.list());
		final long endTime = System.currentTimeMillis();
		session.close();
		LOGGER.info("It took " + (endTime - startTime) + " millsecs to fetch " + this.observableIctsCompaniesList.size() + " Companies.");
		 */
	}

	/*
	private void saveBrokerMapping()
	{
		final String externalTradeSourceName = ((RadioButton) ExternalTradeSourceRadioCellForMappingsTab.toggleGroup.getSelectedToggle()).getText();
		final Integer externalTradeSourceOid = this.getOidForExternalSourceName(externalTradeSourceName);

		final String externalSourceBroker = this.externalSourceBrokerTextField.getText().isEmpty() ? null : this.externalSourceBrokerTextField.getText().trim().toUpperCase();
		final String brokerType = this.brokerTypeComboBox.getSelectionModel().getSelectedItem();
		final String externalSourceTrader = this.externalSourceTraderTextField.getText().isEmpty() ? null : this.externalSourceTraderTextField.getText().trim().toUpperCase();
		final String externalSourceAccount = this.externalSourceAccountTextField.getText().isEmpty() ? null : this.externalSourceAccountTextField.getText().trim().toUpperCase();
		final String ictsBroker = this.ictsBrokerComboBox.getSelectionModel().getSelectedItem().getAcctShortName();
		Session session = null;

		//final boolean doesBrokerMappingExistsAlready = this.doesBrokerMappingExistsAlready(externalSourceBroker, brokerType, externalSourceTrader, externalSourceAccount);
		final boolean doesBrokerMappingExistsAlready = false;

		try
		{
			if(!doesBrokerMappingExistsAlready)
			{
				session = HibernateUtil.beginTransaction();
				final Integer transid = HibernateReferenceDataFetchUtil.generateNewTransaction();
				final Integer newNum = HibernateReferenceDataFetchUtil.generateNewNum();
				session.getNamedQuery("InsertNewMapping").setParameter("oidParam", newNum).setParameter("externalTradeSourceOidParam", externalTradeSourceOid).setParameter("mappingTypeParam", COMPANY_MAPPING_TYPE).setParameter("externalValue1Param", externalSourceBroker).setParameter("externalValue2Param", brokerType).setParameter("externalValue3Param", externalSourceTrader).setParameter("externalValue4Param", externalSourceAccount).setParameter("aliasValueParam", ictsBroker).setParameter("transIdParam", transid).executeUpdate();
				session.getTransaction().commit();
				LOGGER.info("Mapping Saved Successfully.");
			}
			else
			{
				LOGGER.error("Mapping Already Exists!");
			}
		}
		catch(final Exception exception)
		{
			LOGGER.error("Save Failed." + exception);
			session.getTransaction().rollback();
			throw new RuntimeException("Save Failed.", exception);
		}
		finally
		{
			if((session != null) && session.isOpen())
			{
				if((session.getTransaction() != null) && (session.getTransaction().getStatus() == TransactionStatus.ACTIVE))
				{
					session.getTransaction().commit();//This is mandatory - to avoid DB locking
					//session.close();
				}
			}
		}
	}
	 */

	/*
	private boolean doesBrokerMappingExistsAlready(final String externalSourceBroker, final String brokerType, final String externalSourceTrader, final String externalSourceAccount)
	{
		boolean doesExists = false;
		Session session = null;
		List aMapping = null;

		try
		{
			session = HibernateUtil.beginTransaction();
			aMapping = session.getNamedQuery("DoesMappingExists").setParameter("externalTradeSourceOidParam", 1).setParameter("mappingTypeParam", BROKER_MAPPING_TYPE).setParameter("externalValue1Param", externalSourceBroker).setParameter("externalValue2Param", brokerType).setParameter("externalValue3Param", externalSourceTrader).setParameter("externalValue4Param", externalSourceAccount).list();
			System.out.println(aMapping);
			doesExists = (aMapping == null) ? false : true;
		}
		catch(final Exception exception)
		{
		}
		finally
		{
			session.close();
		}

		return doesExists;
	}
	 */

	@FXML
	private void handleSaveButtonClick()
	{
		//this.saveBrokerMapping();
	}

	@FXML
	private void handleCancelButtonClick()
	{
		((Stage) this.cancelButton.getScene().getWindow()).close();
	}
}