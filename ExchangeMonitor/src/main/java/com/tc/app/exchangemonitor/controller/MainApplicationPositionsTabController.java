package com.tc.app.exchangemonitor.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.control.CheckListView;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.tc.app.exchangemonitor.entitybase.IExternalMappingEntity;
import com.tc.app.exchangemonitor.entitybase.IExternalTradeStateEntity;
import com.tc.app.exchangemonitor.entitybase.IExternalTradeStatusEntity;
import com.tc.app.exchangemonitor.model.ExternalMapping;
import com.tc.app.exchangemonitor.model.ExternalTradeSource;
import com.tc.app.exchangemonitor.model.predicates.ExternalMappingPredicates;
import com.tc.app.exchangemonitor.util.ApplicationHelper;
import com.tc.app.exchangemonitor.util.DatePickerConverter;
import com.tc.app.exchangemonitor.util.HibernateUtil;
import com.tc.app.exchangemonitor.util.ReferenceDataCache;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MainApplicationPositionsTabController implements IMainApplicationMonitorTabController
{
	private static final Logger LOGGER = LogManager.getLogger(MainApplicationPositionsTabController.class);

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Variables injected through FXML starts here
	 * ============================================================================================================================================================================
	 */

	@FXML
	private ToolBar applicationMainWindowCurrentFilterToolBar;

	@FXML
	private Button startMonitorButton;

	@FXML
	private ImageView startMonitorButtonImageView;

	@FXML
	private Button pauseMonitorButton;

	@FXML
	private Button stopMonitorButton;

	@FXML private Text exchangesFilterKeyText;
	@FXML private Text exchangesFilterValueText;

	@FXML private Text statesFilterKeyText;
	@FXML private Text statesFilterValueText;

	@FXML private Text typesFilterKeyText;
	@FXML private Text typesFilterValueText;

	@FXML private Text accountsFilterKeyText;
	@FXML private Text accountsFilterValueText;

	@FXML private Text startDateFilterKeyText;
	@FXML private Text startDateFilterValueText;

	@FXML private Text endDateFilterKeyText;
	@FXML private Text endDateFilterValueText;

	@FXML private TitledPane actionTitledPane;

	@FXML
	private Accordion queryFilterAccordion;

	@FXML
	//private CheckListView<IExternalTradeSourceEntity> externalTradeSourcesListView;
	private CheckListView<ExternalTradeSource> externalTradeSourcesListView;

	@FXML
	private CheckListView<IExternalTradeStateEntity> externalTradeStatesListView;

	@FXML
	private CheckListView<IExternalTradeStatusEntity> externalTradeStatusesListView;

	@FXML
	private CheckListView<IExternalMappingEntity> externalTradeAccountsListView;

	@FXML
	private TextField externalTradeAccountsFilterTextField;

	@FXML
	private TitledPane externalTradeSourcesTitledPane;

	@FXML
	private TitledPane externalTradeStatesTitledPane;

	@FXML
	private TitledPane externalTradeStatusesTitledPane;

	@FXML
	private TitledPane externalTradeAccountsTitledPane;

	@FXML
	private DatePicker startDateDatePicker;

	@FXML
	private DatePicker endDateDatePicker;

	@FXML
	private TableView<DummyPosition> dummyPositionsTableView;

	/**
	 * ============================================================================================================================================================================
	 * 																																							All FXML Variables ends here
	 * ============================================================================================================================================================================
	 */

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Variables injected through @Inject starts here
	 * ============================================================================================================================================================================
	 */

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Variables injected through @Inject ends here
	 * ============================================================================================================================================================================
	 */

	/**
	 * ============================================================================================================================================================================
	 * 																																							All other variable declaration starts here
	 * ============================================================================================================================================================================
	 */

	private ListChangeListener<ExternalTradeSource> externalTradeSourcesCheckBoxClickListener = null;
	private ListChangeListener<IExternalTradeStateEntity> externalTradeStatesCheckBoxClickListener = null;
	private ListChangeListener<IExternalTradeStatusEntity> externalTradeStatusesCheckBoxClickListener = null;
	private ListChangeListener<IExternalMappingEntity> externalTradeAccountsCheckBoxClickListener = null;
	private ChangeListener<String> externalTradeAccountsFilterTextFieldKeyListener = null;

	private List<IExternalMappingEntity> externalTradeAccounts = new ArrayList<IExternalMappingEntity>();

	private List<IExternalMappingEntity> checkedExternalTradeAccounts = new ArrayList<IExternalMappingEntity>();

	private ObservableList<ExternalTradeSource> externalTradeSourceObservableList = FXCollections.observableArrayList();

	private ObservableList<IExternalTradeStateEntity> externalTradeStateObservableList = FXCollections.observableArrayList();

	private ObservableList<IExternalTradeStatusEntity> externalTradeStatusObservableList = FXCollections.observableArrayList();

	private ObservableList<IExternalMappingEntity> externalTradeAccountObservableList = FXCollections.observableArrayList();

	//private ObservableList<DummyPosition> dummyPositionsObservableList = FXCollections.observableArrayList();
	private ObservableList<DummyPosition> dummyPositionsObservableList = FXCollections.observableArrayList(aPosition -> new Observable[] { aPosition.totalProperty() });

	private FetchPositionsScheduledService fetchPositionsScheduledService = new FetchPositionsScheduledService();

	/**
	 * ============================================================================================================================================================================
	 * 																																							All other variable declaration ends here
	 * ============================================================================================================================================================================
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		/* add the instantiated controller object to map, so that we can use in the future. */
		addThisControllerToControllersMap();

		/* so that we can track if any fxml variables are not injected. */
		doAssertion();

		/* This is to bind the observable variables to the actual UI control. Once bound, the data in the observable variable will automatically displayed on the UI control. Note: Initially the variables 
		 * value may be null, so nothing appears on the UI control but whenever the value gets changed on the variable either directly or indirectly that will reflect on the UI control automatically.
		 */
		doInitialDataBinding();

		/* This will initialize the user interface ensuring all UI controls are loaded with the proper data. We need to fetch data from DB and construct checkboxes, buttons etc... and display on the UI. */
		initializeGUI();

		/* This will create the listeners but wont attach it to any components */
		createListeners();

		/* This will initialize bind the listeners to the respective UI controls so that when app is launched, everything is ready for user interaction. */
		attachListeners();

		/* This will initialize the tables with the columns and bind the cell value factories for the columns. */
		initializeTableViews();
	}

	public void addThisControllerToControllersMap()
	{
		ApplicationHelper.controllersMap.putInstance(MainApplicationPositionsTabController.class, this);
	}

	@Override
	public void doAssertion()
	{
	}

	@Override
	public void doInitialDataBinding()
	{
		/*
		Callback<ExternalTrade, List<MenuItem>> rowMenuItemFactory = new Callback<ExternalTrade, List<MenuItem>>() {
			@Override
			public List<MenuItem> call(ExternalTrade param) {
				final MenuItem addMenuItem = new MenuItem("Add");
				final MenuItem updateMenuItem = new MenuItem("Update");
				final MenuItem deleteMenuItem = new MenuItem("Delete");

				//return Collections.singletonList(addMenuItem);
				return Arrays.asList(addMenuItem, updateMenuItem, deleteMenuItem);
			}
		};
		 */

		//externalTradesTableView.setRowFactory(new CustomRowFactory<ExternalTrade>(null));
		//externalTradesTableView.setRowFactory(new CustomRowFactory<ExternalTrade>(rowMenuItemFactory));

		/* Since startDate and endDate are set as NULL initially, "null" is appearing in the startDateFilterText and endDateFilterText and bcoz of that the Text control is appearing in the UI. To get rid 
		 * of that we are hiding the Text control if it contains text equals to "null"
		 */
		startDateFilterValueText.visibleProperty().bind(startDateFilterValueText.textProperty().isNotEqualTo("null"));
		endDateFilterValueText.visibleProperty().bind(endDateFilterValueText.textProperty().isNotEqualTo("null"));

		exchangesFilterKeyText.visibleProperty().bind(exchangesFilterValueText.textProperty().isNotEmpty());
		exchangesFilterKeyText.managedProperty().bind(exchangesFilterKeyText.visibleProperty());
		exchangesFilterValueText.managedProperty().bind(exchangesFilterValueText.visibleProperty());

		statesFilterKeyText.visibleProperty().bind(statesFilterValueText.textProperty().isNotEmpty());
		statesFilterKeyText.managedProperty().bind(statesFilterKeyText.visibleProperty());
		statesFilterValueText.managedProperty().bind(statesFilterValueText.visibleProperty());

		typesFilterKeyText.visibleProperty().bind(typesFilterValueText.textProperty().isNotEmpty());
		typesFilterKeyText.managedProperty().bind(typesFilterKeyText.visibleProperty());
		typesFilterValueText.managedProperty().bind(typesFilterValueText.visibleProperty());

		accountsFilterKeyText.visibleProperty().bind(accountsFilterValueText.textProperty().isNotEmpty());
		accountsFilterKeyText.managedProperty().bind(accountsFilterKeyText.visibleProperty());
		accountsFilterValueText.managedProperty().bind(accountsFilterValueText.visibleProperty());

		startDateFilterKeyText.visibleProperty().bind(startDateFilterValueText.textProperty().isNotEqualTo("null"));
		startDateFilterKeyText.managedProperty().bind(startDateFilterKeyText.visibleProperty());
		startDateFilterValueText.managedProperty().bind(startDateFilterValueText.visibleProperty());

		endDateFilterKeyText.visibleProperty().bind(endDateFilterValueText.textProperty().isNotEqualTo("null"));
		endDateFilterKeyText.managedProperty().bind(endDateFilterKeyText.visibleProperty());
		endDateFilterValueText.managedProperty().bind(endDateFilterValueText.visibleProperty());

		//dummyPositionsTableView.setItems(dummyPositionsSortedList);
		dummyPositionsTableView.setItems(new LineItemListWithTotal(dummyPositionsObservableList));

		startDateFilterValueText.textProperty().bind(startDateDatePicker.valueProperty().asString());
		endDateFilterValueText.textProperty().bind(endDateDatePicker.valueProperty().asString());

		startMonitorButton.disableProperty().bind(fetchPositionsScheduledService.runningProperty());
		pauseMonitorButton.disableProperty().bind(fetchPositionsScheduledService.runningProperty().not());
		stopMonitorButton.disableProperty().bind(fetchPositionsScheduledService.runningProperty().not());

		//actionTitledPane.disableProperty().bind(fetchExternalTradesScheduledService.runningProperty());
		//queryFilterAccordion.disableProperty().bind(fetchExternalTradesScheduledService.runningProperty());
		//externalTradeTableViewDataFilterTextField.disableProperty().bind(fetchExternalTradesScheduledService.runningProperty());

		//applicationMainWindowCurrentFilterToolBar.visibleProperty().bind(exchangesFilterText.textProperty().isNotEmpty().or(statesFilterText.textProperty().isNotEmpty()).or(typesFilterText.textProperty().isNotEmpty()).or(accountsFilterText.textProperty().isNotEmpty()).or(startDateFilterText.textProperty().isNotEqualTo("null")).or(endDateFilterText.textProperty().isNotEqualTo("null")));
		/* We are hiding the entire toolbar if no text in any of the Text control. */
		applicationMainWindowCurrentFilterToolBar.visibleProperty().bind
		(
				exchangesFilterKeyText.visibleProperty()
				.or(statesFilterKeyText.visibleProperty())
				.or(typesFilterKeyText.visibleProperty())
				.or(accountsFilterKeyText.visibleProperty())
				.or(startDateFilterKeyText.visibleProperty())
				.or(endDateFilterKeyText.visibleProperty())
				);

		externalTradeSourcesListView.setItems(externalTradeSourceObservableList);
		externalTradeStatesListView.setItems(externalTradeStateObservableList);
		externalTradeStatusesListView.setItems(externalTradeStatusObservableList);
		externalTradeAccountsListView.setItems(externalTradeAccountObservableList);
	}

	@Override
	public void initializeGUI()
	{
		/**
		 * fetch exchanges from external_trade_source table and construct checkbox for each exchange and set it on the UI
		 */
		fetchExternalTradeSources();

		/**
		 * fetch external trades states from external_trade_state table and construct checkbox for each trade state and set it on the UI
		 */
		fetchExternalTradeStates();

		/**
		 * fetch external trades statuses from external_trade_status table and construct checkbox for each trade status and set it on the UI
		 */
		fetchExternalTradeStatuses();

		/**
		 * fetch trade accounts from external_mapping table and with mapping_type 'K' and construct checkbox for trade account and set it on the UI
		 */
		fetchExternalTradeAccounts();

		/**
		 * set yesterday's date as default start date
		 */
		//startDateDatePicker.setValue(LocalDate.now().minusDays(1));
		startDateDatePicker.setValue(null);
		startDateDatePicker.setConverter(new DatePickerConverter("dd-MMM-yyyy"));
		//startDateDatePicker.setPromptText("dd-MMM-yyyy");

		/**
		 * set today's date as default end date
		 */
		//endDateDatePicker.setValue(LocalDate.now());
		endDateDatePicker.setValue(null);
		endDateDatePicker.setConverter(new DatePickerConverter("dd-MMM-yyyy"));

		/**
		 * fetch external trade types from external_trade_type table so that we can use when we display data in the TableView, since we need to display the 
		 * trade_type_name in the UI
		 */
	}

	private void fetchExternalTradeSources()
	{
		externalTradeSourceObservableList.addAll(ReferenceDataCache.fetchExternalTradeSources().values());
	}

	private void fetchExternalTradeStates()
	{
		externalTradeStateObservableList.addAll(ReferenceDataCache.fetchExternalTradeStates().values());
	}

	private void fetchExternalTradeStatuses()
	{
		externalTradeStatusObservableList.addAll(ReferenceDataCache.fetchExternalTradeStatuses().values());
	}

	private void fetchExternalTradeAccounts()
	{
		externalTradeAccounts.addAll(ReferenceDataCache.fetchExternalTradeAccounts().values());
		// the below line is creating a dummy external mapping record with name "Any". not a better way.
		externalTradeAccounts.add(0, new ExternalMapping("Any"));
		externalTradeAccountObservableList.addAll(externalTradeAccounts);
	}

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Listener creation starts here
	 * ============================================================================================================================================================================
	 */

	public void createListeners()
	{
		externalTradeSourcesCheckBoxClickListener = (change) -> { handleExternalTradeSourcesCheckBoxClick(change); };
		externalTradeStatesCheckBoxClickListener = (change) -> { handleExternalTradeStatesCheckBoxClick(change); };
		externalTradeStatusesCheckBoxClickListener = (change) -> { handleExternalTradeStatusesCheckBoxClick(change); };
		externalTradeAccountsCheckBoxClickListener = (change) -> { handleExternalTradeAccountsCheckBoxClick(change); };
		externalTradeAccountsFilterTextFieldKeyListener = (observavleValue, oldValue, newValue) -> { handleExternalTradeAccountsFilterByKey(oldValue, newValue); };
	}

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Listener creation ends here
	 * ============================================================================================================================================================================
	 */

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Listener registration starts here
	 * ============================================================================================================================================================================
	 */

	@Override
	public  void attachListeners()
	{
		/*externalTradeSourcesListView.getCheckModel().getCheckedItems().addListener((Change<? extends ExternalTradeSource> change) ->
		{
			handleExternalTradeSourcesCheckBoxClick(change);
		});*/
		// the above code is commented and implemented as below.
		externalTradeSourcesListView.getCheckModel().getCheckedItems().addListener(externalTradeSourcesCheckBoxClickListener);	
		externalTradeStatesListView.getCheckModel().getCheckedItems().addListener(externalTradeStatesCheckBoxClickListener);
		externalTradeStatusesListView.getCheckModel().getCheckedItems().addListener(externalTradeStatusesCheckBoxClickListener);
		externalTradeAccountsListView.getCheckModel().getCheckedItems().addListener(externalTradeAccountsCheckBoxClickListener);
		externalTradeAccountsFilterTextField.textProperty().addListener(externalTradeAccountsFilterTextFieldKeyListener);
	}

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Listener registration ends here
	 * ============================================================================================================================================================================
	 */

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Listeners methods starts here
	 * ============================================================================================================================================================================
	 */

	private void handleExternalTradeAccountsFilterByKey(String oldValue, String newValue)
	{
		// If the number of characters in the text box is less than last time it must be because the user pressed delete
		if(oldValue != null && (newValue.length() < oldValue.length()))
		{
			// Restore the lists original set of entries and start from the beginning
			externalTradeAccountsListView.setItems(FXCollections.observableArrayList(externalTradeAccounts));
		}

		// Change to upper case so that case is not an issue
		newValue = newValue.toUpperCase();

		// Filter out the entries that don't contain the entered text
		ObservableList<IExternalMappingEntity> subentries = FXCollections.observableArrayList();

		for (IExternalMappingEntity entry: externalTradeAccountsListView.getItems() )
		{
			if(entry.getExternalValue1().toUpperCase().contains(newValue))
			{
				subentries.add(entry);
			}
		}
		externalTradeAccountsListView.setItems(subentries);

		for(IExternalMappingEntity string : checkedExternalTradeAccounts)
		{
			if(subentries.contains(string))
			{
				externalTradeAccountsListView.getCheckModel().check(string);
			}
		}
		externalTradeAccountsListView.getCheckModel().getCheckedItems().addListener(externalTradeAccountsCheckBoxClickListener);
	};

	private void handleExternalTradeSourcesCheckBoxClick(Change<? extends ExternalTradeSource> change)
	{
		if(externalTradeSourcesListView.getCheckModel().getCheckedItems().size() == 0)
			externalTradeSourcesTitledPane.setText(ApplicationConstants.EXTERNAL_TRADE_SOURCES_TITLEDPANE_TEXT);
		else
			externalTradeSourcesTitledPane.setText(ApplicationConstants.EXTERNAL_TRADE_SOURCES_TITLEDPANE_TEXT + "(" + externalTradeSourcesListView.getCheckModel().getCheckedItems().size() + ")");

		if(externalTradeSourcesListView.getCheckModel().getCheckedItems().size() > 0)
			exchangesFilterValueText.setText(externalTradeSourcesListView.getCheckModel().getCheckedItems().toString());
		else
			exchangesFilterValueText.setText(null);
	}

	private void handleExternalTradeStatesCheckBoxClick(Change<? extends IExternalTradeStateEntity> change)
	{
		if(externalTradeStatesListView.getCheckModel().getCheckedItems().size() == 0)
			externalTradeStatesTitledPane.setText(ApplicationConstants.EXTERNAL_TRADE_STATES_TITLEDPANE_TEXT);
		else
			externalTradeStatesTitledPane.setText(ApplicationConstants.EXTERNAL_TRADE_STATES_TITLEDPANE_TEXT + "(" + externalTradeStatesListView.getCheckModel().getCheckedItems().size() + ")");

		if(externalTradeStatesListView.getCheckModel().getCheckedItems().size() > 0)
			statesFilterValueText.setText(externalTradeStatesListView.getCheckModel().getCheckedItems().toString());
		else
			statesFilterValueText.setText(null);
	};

	private void handleExternalTradeStatusesCheckBoxClick(Change<? extends IExternalTradeStatusEntity> change)
	{
		if(externalTradeStatusesListView.getCheckModel().getCheckedItems().size() == 0)
			externalTradeStatusesTitledPane.setText(ApplicationConstants.EXTERNAL_TRADE_STATUSES_TITLEDPANE_TEXT);
		else
			externalTradeStatusesTitledPane.setText(ApplicationConstants.EXTERNAL_TRADE_STATUSES_TITLEDPANE_TEXT + "(" + externalTradeStatusesListView.getCheckModel().getCheckedItems().size() + ")");

		if(externalTradeStatusesListView.getCheckModel().getCheckedItems().size() > 0)
			typesFilterValueText.setText(externalTradeStatusesListView.getCheckModel().getCheckedItems().toString());
		else
			typesFilterValueText.setText(null);
	};

	private void handleExternalTradeAccountsCheckBoxClick(Change<? extends IExternalMappingEntity> change)
	{
		change.next();
		if(change.wasAdded())
		{
			checkedExternalTradeAccounts.add(change.getAddedSubList().get(0));
		}
		else if(change.wasRemoved())
		{
			checkedExternalTradeAccounts.remove(change.getRemoved().get(0));
		}
		if(checkedExternalTradeAccounts.size() == 0)
			externalTradeAccountsTitledPane.setText(ApplicationConstants.EXTERNAL_TRADE_ACCOUNTS_TITLEDPANE_TEXT);
		else
			externalTradeAccountsTitledPane.setText(ApplicationConstants.EXTERNAL_TRADE_ACCOUNTS_TITLEDPANE_TEXT + "(" + checkedExternalTradeAccounts.size() + ")");

		if(checkedExternalTradeAccounts.size() > 0)
			accountsFilterValueText.setText(checkedExternalTradeAccounts.toString());
		else
			accountsFilterValueText.setText(null);
	};

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Listeners methods ends here
	 * ============================================================================================================================================================================
	 */

	private void initializeTableViews()
	{
		initializeDummyPositionsTableView();
	}

	private void initializeDummyPositionsTableView()
	{
		//externalTradesTableView.getSelectionModel().setCellSelectionEnabled(true);
		//externalTradesTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}


	/**
	 * ============================================================================================================================================================================
	 * 																																							All Event Handling logic starts here
	 * ============================================================================================================================================================================
	 */

	@FXML
	private void handleStartMonitorButtonClick()
	{
		startMonitoringPositions();
	}

	@FXML
	private void handlePauseMonitorButtonClick()
	{
		pauseMonitoringPositions();
	}

	@FXML
	private void handleStopMonitorButtonClick()
	{
		stopMonitoringPositions();
	}

	private void startMonitoringPositions()
	{
		//acc.setExpandedPane(externalTradeSourcesTitledPane);
		fetchPositionsFromDBForTableView();
	}

	private void pauseMonitoringPositions()
	{
		if(fetchPositionsScheduledService != null)
		{
			if(fetchPositionsScheduledService.isRunning())
			{
				fetchPositionsScheduledService.cancel();
				//statusMessagesProperty().set("Task Stopped.");
				//progressStatusesProperty().set(0.0);
			}
		}
	}

	private void stopMonitoringPositions()
	{
		pauseMonitoringPositions();
		dummyPositionsObservableList.clear();

	}

	private void fetchPositionsFromDBForTableView()
	{
		Query sqlQueryToFetchDummyPositions = null;
		String selectedStartDate = null;
		String selectedEndDate = null;

		List<ExternalTradeSource> externalTradeSourceObjectsSelectedByUserFromUI = getExternalTradeSourcesSelectedByUserFromUI();
		List<IExternalTradeStateEntity> externalTradeStateObjectsSelectedByUserFromUI = getExternalTradeStatesSelectedByUserFromUI();
		List<IExternalTradeStatusEntity> externalTradeStatusObjectsSelectedByUserFromUI = getExternalTradeStatusesSelectedByUserFromUI();
		List<IExternalMappingEntity> externalTradeAccountObjectsSelectedByUserFromUI = getExternalTradeAccountsSelectedByUserFromUI();

		if(startDateDatePicker.getValue() != null)
			selectedStartDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(startDateDatePicker.getValue());
		if(endDateDatePicker.getValue() != null)
			selectedEndDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(endDateDatePicker.getValue());

		List<String> selectedExternalTradeSourceNames = new ArrayList<String>();
		externalTradeSourceObjectsSelectedByUserFromUI.forEach((anExternalTradeSourceEntity) -> selectedExternalTradeSourceNames.add(anExternalTradeSourceEntity.getOid().toString()));

		List<String> selectedExternalTradeStateNames = new ArrayList<String>();
		externalTradeStateObjectsSelectedByUserFromUI.forEach((anExternalTradeState) -> selectedExternalTradeStateNames.add(anExternalTradeState.getOid().toString()));

		List<String> selectedExternalTradeStatusNames = new ArrayList<String>();
		externalTradeStatusObjectsSelectedByUserFromUI.forEach((anExternalTradeStatus) -> selectedExternalTradeStatusNames.add(anExternalTradeStatus.getOid().toString()));

		Session session = HibernateUtil.beginTransaction();
		/*
		List<String> selectedExternalTradeAccountNames = new ArrayList<String>();
		if(externalTradeAccountsListView.getCheckModel().getCheckedIndices().contains(0))
		{
			sqlQueryToFetchDummyPositions = session.getNamedQuery("externalTradesWithoutBuyerAccount");
			sqlQueryToFetchDummyPositions.setParameter("buyerAccountsParam", "" );
		}
		else
		{
			externalTradeAccountObjectsSelectedByUserFromUI.forEach((anExternalMapping) -> selectedExternalTradeAccountNames.add(anExternalMapping.getExternalValue1()));
			sqlQueryToFetchDummyPositions = session.getNamedQuery("externalTradesWithBuyerAccount");
			sqlQueryToFetchDummyPositions.setParameterList("buyerAccountsParam", selectedExternalTradeAccountNames);
		}

		if(selectedExternalTradeSourceNames.size() == 0)
			sqlQueryToFetchDummyPositions.setParameter("externalTradeSourcesParam", null);
		else
			sqlQueryToFetchDummyPositions.setParameterList("externalTradeSourcesParam", selectedExternalTradeSourceNames);

		if(selectedExternalTradeStateNames.size() == 0)
			sqlQueryToFetchDummyPositions.setParameter("externalTradeStatesParam", null);
		else
			sqlQueryToFetchDummyPositions.setParameterList("externalTradeStatesParam", selectedExternalTradeStateNames);

		if(selectedExternalTradeStatusNames.size() == 0)
			sqlQueryToFetchDummyPositions.setParameter("externalTradeStatusesParam", null);
		else
			sqlQueryToFetchDummyPositions.setParameterList("externalTradeStatusesParam", selectedExternalTradeStatusNames);

		sqlQueryToFetchDummyPositions.setParameter("startDate", selectedStartDate);
		sqlQueryToFetchDummyPositions.setParameter("endDate", selectedEndDate);
		 */

		/*Dont forget to add the buyer account qualifier. presently commented.*/
		//String s = "SELECT ets.external_trade_state_name as externalTradeStateName, ett.creation_date as creationDate, et.entry_date as entryDate, ett.exch_tools_trade_num as exchToolsTradeNum, ett.commodity as commodity, ett.trading_period as tradingPeriod, ett.call_put as callPut, ett.strike_price as strikePrice, ett.quantity as quantity, ett.price as price, ett.input_action as inputAction, ett.input_company as inputCompany, ett.accepted_action as acceptedAction, ett.accepted_company as acceptedCompany, ett.buyer_account as buyerAccount FROM external_trade et, exch_tools_trade ett,external_trade_state ets WHERE et.external_trade_system_oid in (1) AND et.external_trade_source_oid in (3) AND et.external_trade_status_oid in (1,2 ,3, 4) AND et.external_trade_state_oid in (1, 2, 3) AND ett.creation_date >= '2016-09-27 00:00:00' AND ett.creation_date <= '2016-09-30 00:00:00' AND NOT EXISTS (SELECT 1 FROM exch_tools_trade ett1 JOIN external_trade et1 ON et1.oid = ett1.external_trade_oid  JOIN external_trade_state ets1 ON et1.external_trade_state_oid = ets1.oid WHERE ett.commodity = ett1.commodity AND ett.exch_tools_trade_num  = ett1.exch_tools_trade_num AND ett.trading_period = ett1.trading_period AND ett.buyer_account = ett1.buyer_account AND convert(datetime,convert(varchar,ett.creation_date,109)) = convert(datetime,convert(varchar,ett1.creation_date,109)) AND ISNULL(ett.call_put,'NULL') = ISNULL(ett1.call_put,'NULL') AND ISNULL(ett.strike_price,0) = ISNULL(ett1.strike_price,0) AND (((ets1.external_trade_state_name = 'Update' or ets1.external_trade_state_name = 'Delete') AND (ets.external_trade_state_name = 'Add')) OR (ets1.external_trade_state_name = 'Delete' AND ets.external_trade_state_name = 'Update'))) AND ets.external_trade_state_name != 'Delete'  AND et.oid = ett.external_trade_oid AND et.external_trade_state_oid = ets.oid";
		String s = "SELECT ets.external_trade_state_name as externalTradeStateName, ett.creation_date as creationDate, et.entry_date as entryDate, ett.exch_tools_trade_num as exchToolsTradeNum, ett.commodity as commodity, ett.trading_period as tradingPeriod, ett.call_put as callPut, ett.strike_price as strikePrice, ett.quantity as quantity, ett.price as price, ett.input_action as inputAction, ett.input_company as inputCompany, ett.accepted_action as acceptedAction, ett.accepted_company as acceptedCompany, ett.buyer_account as buyerAccount FROM external_trade et, exch_tools_trade ett,external_trade_state ets WHERE et.external_trade_system_oid in (1) AND et.external_trade_source_oid in (3) AND et.external_trade_status_oid in (1,2 ,3, 4) AND et.external_trade_state_oid in (1, 2, 3) AND ett.creation_date >= '27-SEP-2016' AND ett.creation_date <= '30-SEP-2016' AND NOT EXISTS (SELECT 1 FROM exch_tools_trade ett1 JOIN external_trade et1 ON et1.oid = ett1.external_trade_oid  JOIN external_trade_state ets1 ON et1.external_trade_state_oid = ets1.oid WHERE ett.commodity = ett1.commodity AND ett.exch_tools_trade_num  = ett1.exch_tools_trade_num AND ett.trading_period = ett1.trading_period AND ett.buyer_account = ett1.buyer_account AND convert(datetime,convert(varchar,ett.creation_date,109)) = convert(datetime,convert(varchar,ett1.creation_date,109)) AND ISNULL(ett.call_put,'NULL') = ISNULL(ett1.call_put,'NULL') AND ISNULL(ett.strike_price,0) = ISNULL(ett1.strike_price,0) AND (((ets1.external_trade_state_name = 'Update' or ets1.external_trade_state_name = 'Delete') AND (ets.external_trade_state_name = 'Add')) OR (ets1.external_trade_state_name = 'Delete' AND ets.external_trade_state_name = 'Update'))) AND ets.external_trade_state_name != 'Delete'  AND et.oid = ett.external_trade_oid AND et.external_trade_state_oid = ets.oid";
		sqlQueryToFetchDummyPositions = session.createSQLQuery(s);
		sqlQueryToFetchDummyPositions.setResultTransformer(Transformers.aliasToBean(com.tc.app.exchangemonitor.controller.DummyPosition.class));


		/* This will fetch the data in a background thread, so UI will not be freezed and user can interact with the UI. Here we use a scheduled service which will invoke the task recurringly. */
		fetchPositionsScheduledService.setSQLQuery(sqlQueryToFetchDummyPositions);
		fetchPositionsScheduledService.setDelay(Duration.seconds(1));
		fetchPositionsScheduledService.setPeriod(Duration.seconds(10));

		/*
		 *  modified the above 2 lines as below. previously statusMessagesProperty and progressStatusesProperty are available in the same class but now moved to a different controller.
		 *  Eventually the below code has to be modified to access those properties from the respective controller class.
		 *  Currently accessing the statusMessagesProperty and progressStatusesProperty through the controller whose reference is injected while loading. this may not be the perfect approach,
		 *  need to find out a better way.
		 */
		fetchPositionsScheduledService.messageProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> { ApplicationHelper.controllersMap.getInstance(MainWindowController.class).statusMessagesProperty().set(newValue); });
		fetchPositionsScheduledService.progressProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) -> { ApplicationHelper.controllersMap.getInstance(MainWindowController.class).progressStatusesProperty().set(newValue.doubleValue()); });

		fetchPositionsScheduledService.restart();

		fetchPositionsScheduledService.setOnSucceeded((WorkerStateEvent workerStateEvent) -> { doThisIfFetchSucceeded(); });
	}

	//public ObservableList<IExternalTradeSourceEntity> getExternalTradeSourcesSelectedByUserFromUI()
	public ObservableList<ExternalTradeSource> getExternalTradeSourcesSelectedByUserFromUI()
	{
		return externalTradeSourcesListView.getCheckModel().getCheckedItems();
	}

	public List<IExternalTradeStateEntity> getExternalTradeStatesSelectedByUserFromUI()
	{
		return externalTradeStatesListView.getCheckModel().getCheckedItems();
	}

	public List<IExternalTradeStatusEntity> getExternalTradeStatusesSelectedByUserFromUI()
	{
		return externalTradeStatusesListView.getCheckModel().getCheckedItems();
	}

	public List<IExternalMappingEntity> getExternalTradeAccountsSelectedByUserFromUI()
	{
		return externalTradeAccountsListView.getCheckModel().getCheckedItems();
	}

	private void doThisIfFetchSucceeded()
	{
		List<String> homeCompanies = Arrays.asList("MERCURIA ENERGY TRADING SA", "MERCURIA ENERGY", "MERCURIA ENERGY TRADING INC.", "MERCURIA ENERGY TRADING PTE LTD", "MERCURIA ENERGY CANADA INC", "MERCURIA ENERGY AMERICA INC", "UBS - MERCURIA TT", "MERCURIA_ENERGY_TRADING_INC", "MERCTRINC", "MERCURIA ENERGY TRADING MIDDLE EAST DMCC", "ICE");
		//List<DummyPosition> listOfUniquePositions = fetchPositionsScheduledService.getValue().stream().distinct().collect(Collectors.toList());
		List<DummyPosition> listOfUniquePositions = fetchPositionsScheduledService.getValue().stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());

		ExternalMappingPredicates.filterExternalMappings(ReferenceDataCache.fetchExternalMappings(), ExternalMappingPredicates.applyIceBookingCompaniesPredicate);

		//List<String> homeCompaniesForSource = ReferenceDataCache.fetchExternalMappings().stream().filter(ExternalMappingPredicates.applyIceBookingCompaniesPredicate).;
		//deals.stream().filter(deal -> deal.provider().equalsIgnoreCase("Apple")).forEach(System.out::println);

		Stream<IExternalMappingEntity> x = ReferenceDataCache.fetchExternalMappings().stream().filter(ExternalMappingPredicates.applyIceBookingCompaniesPredicate);

		listOfUniquePositions.forEach((aPosition) -> {
			if (homeCompanies.contains(aPosition.getInputCompany()))
			{
				aPosition.setBuySell(aPosition.getInputAction());
			}
			else if (homeCompanies.contains(aPosition.getAcceptedCompany()))
			{
				aPosition.setBuySell(aPosition.getAcceptedAction());
			}
		});

		/*
		Map<String, List<DummyPosition>> positionByCommodityMap = aDummyPositionList.stream().collect(Collectors.groupingBy(DummyPosition::getCommodity));
		Map<String, Collection<List<DummyPosition>>> positionByCommodityAndTradingPeriodMap = aDummyPositionList.stream().collect(Collectors.groupingBy(DummyPosition::getCommodity, Collectors.collectingAndThen(Collectors.groupingBy(DummyPosition::getTradingPeriod), Map::values)));
		Map<String, List<List<DummyPosition>>> positionByCommodityAndTradingPeriodList = aDummyPositionList.stream().collect(Collectors.groupingBy(DummyPosition::getCommodity, Collectors.collectingAndThen(Collectors.groupingBy(DummyPosition::getTradingPeriod), m -> new ArrayList<>(m.values()))));
		Map<String, Collection<List<DummyPosition>>> positionByCommodityAndTradingPeriodCollection = aDummyPositionList.stream().collect(Collectors.groupingBy(DummyPosition::getCommodity, Collectors.collectingAndThen(Collectors.groupingBy(DummyPosition::getTradingPeriod), m -> new ArrayList<>(m.values()))));
		Map<String, Map<String, List<DummyPosition>>> map1 = aDummyPositionList.stream().collect(Collectors.groupingBy(DummyPosition::getCommodity, Collectors.groupingBy(DummyPosition::getTradingPeriod)));
		Map<String, Map<String, Map<String, List<DummyPosition>>>> map2 = aDummyPositionList.stream().collect(Collectors.groupingBy(DummyPosition::getCommodity, Collectors.groupingBy(DummyPosition::getTradingPeriod, Collectors.groupingBy(DummyPosition::getCallPut))));
		List<Collector<DummyPosition, ?, ?>> collectors = Arrays.asList(Collectors.groupingBy(DummyPosition::getCommodity), Collectors.groupingBy(DummyPosition::getTradingPeriod));
		 */
		//map3.values().forEach(a -> a.values().forEach(b -> b.values().forEach(c -> c.values().forEach(d -> System.out.println(d)))));

		dummyPositionsObservableList.clear();
		//addTradePosition(listOfUniquePositions);
		Map<String, Map<String, Map<String, Map<Double, List<DummyPosition>>>>> mapOfGroupedPostions = listOfUniquePositions.stream().collect(Collectors.groupingBy(DummyPosition::getCommodity, Collectors.groupingBy(DummyPosition::getTradingPeriod, Collectors.groupingBy(DummyPosition::getCallPut, Collectors.groupingBy(DummyPosition::getStrikePrice)))));
		mapOfGroupedPostions.values().forEach(a -> a.values().forEach(b -> b.values().forEach(theMap -> {
			dummyPositionsObservableList.add(doThis(theMap));
		})));

		/*
		dummyPositionsObservableList.clear();
		dummyPositionsObservableList.addAll(fetchPositionsScheduledService.getValue());
		 */
		//dummyExternalTrades.addAll(fetchExternalTradesScheduledService.getLastValue());
		//dummyExternalTrades.addAll(fetchExternalTradesScheduledService.getLastValue() != null ? fetchExternalTradesScheduledService.getLastValue() : fetchExternalTradesScheduledService.getValue());
	}

	//private void doThis(Map<Double, List<DummyPosition>> theMap)
	private DummyPosition doThis(Map<Double, List<DummyPosition>> theMap)
	{
		DummyPosition aTempPosition = new DummyPosition();
		Collection<List<DummyPosition>> tempCollection = theMap.values();
		tempCollection.forEach(aPositionList -> {
			for(DummyPosition aPosition : aPositionList)
			{
				/* This will set again and again but seems no other way */
				aTempPosition.setCommodity(aPosition.getCommodity());
				aTempPosition.setTradingPeriod(aPosition.getTradingPeriod());
				aTempPosition.setCallPut(aPosition.getCallPut());
				aTempPosition.setStrikePrice(aPosition.getStrikePrice());

				if(aPosition.getBuySell().equals("BUY"))
				{
					if(aPosition.getExternalTradeStateName().equals("Add") || aPosition.getExternalTradeStateName().equals("Update"))
					{
						aTempPosition.setBuyPosition(aTempPosition.getBuyPosition() + aPosition.getQuantity());
						aTempPosition.setBuyPositionValue(aTempPosition.getBuyPositionValue() + (aPosition.getQuantity() * aPosition.getPrice()));
					}
					else if(aPosition.getExternalTradeStateName().equals("Delete"))
					{
						aTempPosition.setBuyPosition(aTempPosition.getBuyPosition() - aPosition.getQuantity());
						aTempPosition.setBuyPositionValue(aTempPosition.getBuyPositionValue() - (aPosition.getQuantity() * aPosition.getPrice()));
					}

					aTempPosition.setLastPrice(aPosition.getPrice());
					aTempPosition.setBuyPositionPrice(0.0);
					//aTempPosition.setBuyPosition(aTempPosition.getBuyPosition() + aPosition.getQuantity());
					//aTempPosition.setBuyPositionValue(aTempPosition.getBuyPositionValue() + (aPosition.getQuantity() * aPosition.getPrice()));

					//aTempPosition.setSellPosition(0.0);
					//aTempPosition.setSellPositionPrice(0.0);
					//aTempPosition.setSellPositionValue(0.0);					
				}
				else if(aPosition.getBuySell().equals("SELL"))
				{
					aTempPosition.setLastPrice(aPosition.getPrice());
					aTempPosition.setSellPositionPrice(0.0);
					//aTempPosition.setBuyPosition(0.0);
					//aTempPosition.setBuyPositionPrice(0.0);
					//aTempPosition.setBuyPositionValue(0.0);
					if(aPosition.getExternalTradeStateName().equals("Add") || aPosition.getExternalTradeStateName().equals("Update"))
					{
						aTempPosition.setSellPosition(aTempPosition.getSellPosition() + aPosition.getQuantity());
						aTempPosition.setSellPositionValue(aTempPosition.getSellPositionValue() + (aPosition.getQuantity() * aPosition.getPrice()));	
					}
					else if (aPosition.getExternalTradeStateName().equals("Delete"))
					{
						aTempPosition.setSellPosition(aTempPosition.getSellPosition() - aPosition.getQuantity());
						aTempPosition.setSellPositionValue(aTempPosition.getSellPositionValue() - (aPosition.getQuantity() * aPosition.getPrice()));	
					}
				}
			}
		});
		aTempPosition.setAverageBuyPrice((aTempPosition.getBuyPositionValue() / aTempPosition.getBuyPosition()));
		aTempPosition.setAverageSellPrice((aTempPosition.getSellPositionValue() / aTempPosition.getSellPosition()));
		aTempPosition.setNetQuantity(aTempPosition.getBuyPosition() - aTempPosition.getSellPosition());
		//aTempPosition.setPL(aTempPosition.getSellPositionValue() - aTempPosition.getBuyPositionValue() + (aTempPosition.getLastPrice() * aTempPosition.getBuyPosition()) - (aTempPosition.getLastPrice() * aTempPosition.getSellPosition()));
		aTempPosition.setTotal((aTempPosition.getSellPositionValue() - aTempPosition.getBuyPositionValue() + (aTempPosition.getLastPrice() * aTempPosition.getBuyPosition()) - (aTempPosition.getLastPrice() * aTempPosition.getSellPosition())) * 1000);
		aTempPosition.setTotal(Double.parseDouble(new DecimalFormat("##.##").format(aTempPosition.getTotal())));

		//System.out.println(Double.parseDouble(new DecimalFormat("##.##").format(aTempPosition.getSellPositionValue() - aTempPosition.getBuyPositionValue() + (aTempPosition.getLastPrice() * aTempPosition.getBuyPosition()) - (aTempPosition.getLastPrice() * aTempPosition.getSellPosition()))));
		//System.out.println((aTempPosition.getSellPositionValue() - aTempPosition.getBuyPositionValue() + (aTempPosition.getLastPrice() * aTempPosition.getBuyPosition()) - (aTempPosition.getLastPrice() * aTempPosition.getSellPosition()) * 100)/100);
		System.out.println(aTempPosition.getTotal());
		System.out.println(Math.round(aTempPosition.getTotal() * 100.0)/100.0);
		System.out.println(Double.parseDouble(new DecimalFormat("##.##").format(aTempPosition.getTotal())));
		System.out.println(new BigDecimal(aTempPosition.getTotal()).round(new MathContext(2)).doubleValue());

		//System.out.println(aTempPosition.getCommodity() + " -- " + aTempPosition.getTradingPeriod() + " -- " + aTempPosition.getCallPut() + " -- " + aTempPosition.getStrikePrice() + " -- " + aTempPosition.getBuyPosition() + " -- " + aTempPosition.getAverageBuyPrice() + " -- " + aTempPosition.getSellPosition() + " -- " + aTempPosition.getAverageSellPrice() + " -- " + aTempPosition.getNetQuantity() + " -- " + aTempPosition.getLastPrice() + " -- " + aTempPosition.getPL());
		System.out.println(aTempPosition.getCommodity() + " -- " + aTempPosition.getTradingPeriod() + " -- " + aTempPosition.getCallPut() + " -- " + aTempPosition.getStrikePrice() + " -- " + aTempPosition.getBuyPosition() + " -- " + aTempPosition.getAverageBuyPrice() + " -- " + aTempPosition.getSellPosition() + " -- " + aTempPosition.getAverageSellPrice() + " -- " + aTempPosition.getNetQuantity() + " -- " + aTempPosition.getLastPrice() + " -- " + aTempPosition.getTotal());
		return aTempPosition;
	}
}

/**
 * ============================================================================================================================================================================
 * 																																							All Event Handling logic ends here
 * ============================================================================================================================================================================
 */