package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.control.CheckListView;
import org.hibernate.Query;
import org.hibernate.Session;

import com.tc.app.exchangemonitor.entitybase.IExternalMappingEntity;
import com.tc.app.exchangemonitor.entitybase.IExternalTradeEntity;
import com.tc.app.exchangemonitor.entitybase.IExternalTradeStateEntity;
import com.tc.app.exchangemonitor.entitybase.IExternalTradeStatusEntity;
import com.tc.app.exchangemonitor.model.ExternalMapping;
import com.tc.app.exchangemonitor.model.ExternalTrade;
import com.tc.app.exchangemonitor.model.ExternalTradeSource;
import com.tc.app.exchangemonitor.model.ExternalTradeStatus;
import com.tc.app.exchangemonitor.util.ApplicationHelper;
import com.tc.app.exchangemonitor.util.DatePickerConverter;
import com.tc.app.exchangemonitor.util.HibernateUtil;
import com.tc.app.exchangemonitor.util.ReferenceDataCache;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MainApplicationMonitorTabController implements IMainApplicationMonitorTabController
{
	private static final Logger LOGGER = LogManager.getLogger(MainApplicationMonitorTabController.class);

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

	@FXML
	private TitledPane actionTitledPane;

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
	private TableView<IExternalTradeEntity> externalTradesTableView;

	@FXML
	private TextField externalTradeTableViewDataFilterTextField;

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

	//private ListChangeListener<IExternalTradeSourceEntity> externalTradeSourcesCheckBoxClickListener = null;
	private ListChangeListener<ExternalTradeSource> externalTradeSourcesCheckBoxClickListener = null;
	private ListChangeListener<IExternalTradeStateEntity> externalTradeStatesCheckBoxClickListener = null;
	private ListChangeListener<IExternalTradeStatusEntity> externalTradeStatusesCheckBoxClickListener = null;
	private ListChangeListener<IExternalMappingEntity> externalTradeAccountsCheckBoxClickListener = null;
	private ChangeListener<String> externalTradeAccountsFilterTextFieldKeyListener = null;
	private InvalidationListener externalTradeTableViewDataFilterTextFieldKeyListener = null;

	private List<IExternalMappingEntity> externalTradeAccounts = new ArrayList<IExternalMappingEntity>();

	private List<IExternalMappingEntity> checkedExternalTradeAccounts = new ArrayList<IExternalMappingEntity>();

	//private ObservableList<IExternalTradeSourceEntity> externalTradeSourceObservableList = FXCollections.observableArrayList();
	private ObservableList<ExternalTradeSource> externalTradeSourceObservableList = FXCollections.observableArrayList();
	
	private ObservableList<IExternalTradeStateEntity> externalTradeStateObservableList = FXCollections.observableArrayList();
	
	private ObservableList<IExternalTradeStatusEntity> externalTradeStatusObservableList = FXCollections.observableArrayList();
	
	private ObservableList<IExternalMappingEntity> externalTradeAccountObservableList = FXCollections.observableArrayList();
	
	private ObservableList<IExternalTradeEntity> externalTradesObservableList = FXCollections.observableArrayList();
	private FilteredList<IExternalTradeEntity> externalTradesFilteredList = new FilteredList<IExternalTradeEntity>(externalTradesObservableList, p->true);
	private SortedList<IExternalTradeEntity> externalTradesSortedList = new SortedList<IExternalTradeEntity>(externalTradesFilteredList);

	private FetchExternalTradesScheduledService fetchExternalTradesScheduledService = new FetchExternalTradesScheduledService();

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

		/* This will initialize the animations if needed so that, we see the table rotation or button fade effect etc... */
		initializeAnimationsIfNeeded();
	}

	public void addThisControllerToControllersMap()
	{
		ApplicationHelper.controllersMap.putInstance(MainApplicationMonitorTabController.class, this);
	}

	@Override
	public void doAssertion()
	{
		assert externalTradeTableViewDataFilterTextField != null : "fx:id=\"externalTradeTableViewDataFilterTextField\" was not injected. Check your FXML file MainApplicationViewNew.fxml";
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

		externalTradesTableView.setItems(externalTradesSortedList);

		startDateFilterValueText.textProperty().bind(startDateDatePicker.valueProperty().asString());
		endDateFilterValueText.textProperty().bind(endDateDatePicker.valueProperty().asString());

		startMonitorButton.disableProperty().bind(fetchExternalTradesScheduledService.runningProperty());
		pauseMonitorButton.disableProperty().bind(fetchExternalTradesScheduledService.runningProperty().not());
		stopMonitorButton.disableProperty().bind(fetchExternalTradesScheduledService.runningProperty().not());

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

		externalTradesSortedList.comparatorProperty().bind(externalTradesTableView.comparatorProperty());
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
		externalTradeTableViewDataFilterTextFieldKeyListener = (observable) -> { handleExternalTradeTableViewFilterByKey(); };
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
		/*externalTradeAccountsFilterTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
		{
			handleExternalTradeAccountsFilterByKey(oldValue, newValue);
		});*/

		externalTradeTableViewDataFilterTextField.textProperty().addListener(externalTradeTableViewDataFilterTextFieldKeyListener);
		/*externalTradeTableViewDataFilterTextField.textProperty().addListener((Observable observable) ->
		{
			externalTradesFilteredList.setPredicate(externalTradesTableViewFilterPredicate(externalTradeTableViewDataFilterTextField.getText().trim().toLowerCase()));
		});*/
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
		/*externalTradeAccountsListView.getCheckModel().getCheckedItems().addListener((Change<? extends ExternalMapping>change) ->
		{
			handleExternalTradeAccountsCheckBoxClick(change);
		});*/
		externalTradeAccountsListView.getCheckModel().getCheckedItems().addListener(externalTradeAccountsCheckBoxClickListener);
	};

	//private void handleExternalTradeSourcesCheckBoxClick(Change<? extends IExternalTradeSourceEntity> change)
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
		//System.out.println(change.getAddedSubList().get(0));
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

	private void handleExternalTradeTableViewFilterByKey()
	{
		externalTradesFilteredList.setPredicate(externalTradesTableViewFilterPredicate(externalTradeTableViewDataFilterTextField.getText().trim().toLowerCase()));
		
		Platform.runLater(new Runnable() {
			@Override
			public void run()
			{
				// we don't want repeated selections
				externalTradesTableView.getSelectionModel().clearSelection();
				//get the focus
				externalTradesTableView.requestFocus();
				
				//select first item in TableView model
				externalTradesTableView.getSelectionModel().selectFirst();
				
				//set the focus on the first element
				externalTradesTableView.getFocusModel().focus(0);
				
				//render the selected item in the TableView
				//tableClickHandler(null);
			}
		});
		
		Platform.runLater(new Runnable() {
			@Override
			public void run()
			{
				externalTradeTableViewDataFilterTextField.requestFocus();
				externalTradeTableViewDataFilterTextField.end();
				//externalTradeTableViewDataFilterTextField.positionCaret(externalTradeTableViewDataFilterTextField.getLength()+1);
			}
		});
	}

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Listeners methods ends here
	 * ============================================================================================================================================================================
	 */

	private void initializeTableViews()
	{
		initializeExternalTradeTableView();
	}

	private void initializeExternalTradeTableView()
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
		startMonitoringExternalTrades();
	}

	@FXML
	private void handlePauseMonitorButtonClick()
	{
		pauseMonitoringExternalTrades();
	}

	@FXML
	private void handleStopMonitorButtonClick()
	{
		stopMonitoringExternalTrades();
	}

	private void startMonitoringExternalTrades()
	{
		//acc.setExpandedPane(externalTradeSourcesTitledPane);
		fetchExternalTradesFromDBForTableView();
	}

	private void pauseMonitoringExternalTrades()
	{
		if(fetchExternalTradesScheduledService != null)
		{
			if(fetchExternalTradesScheduledService.isRunning())
			{
				fetchExternalTradesScheduledService.cancel();
				//statusMessagesProperty().set("Task Stopped.");
				//progressStatusesProperty().set(0.0);
			}
		}
	}

	private void stopMonitoringExternalTrades()
	{
		pauseMonitoringExternalTrades();
		externalTradesObservableList.clear();

	}

	@FXML
	private void handleReEnterFailedTradeButtonClick()
	{
		//Session tempSession = HibernateUtil.beginTransaction();
		//ExternalTrade anExternalTrade = tempSession.get(ExternalTrade.class, 18852944);
		//HibernateUtil.commitTransaction();
	}

	@FXML
	private void handleReEnterAllFailedTradesButtonClick()
	{
	}

	@FXML
	private ContextMenu tableRowContextMenu;

	@FXML
	private void handleReprocessThisTradeMenuItemClick()
	{
		System.out.println(externalTradesTableView.getSelectionModel().getSelectedItems());
		/*
		 TableRow aTableRow = (TableRow<T>)tableRowContextMenu.getOwnerNode();
		 aTableRow.getItem();
		 aTableRow.getTableView();
		 */
		Session tempSession = HibernateUtil.beginTransaction();
		ExternalTrade anExternalTrade = tempSession.get(ExternalTrade.class, 18852944);
		ExternalTradeStatus anExternalTradeStatus = tempSession.get(ExternalTradeStatus.class, 1);
		anExternalTrade.setExternalTradeStatusOid(anExternalTradeStatus);
		anExternalTrade.setTransId(989653645);
		HibernateUtil.commitTransaction();
	}

	@FXML
	private void handleReprocessAllFailedTradesMenuItemClick()
	{
	}

	private void fetchExternalTradesFromDBForTableView()
	{
		Query sqlQueryToFetchExternalTrades = null;
		String selectedStartDate = null;
		String selectedEndDate = null;

		//List<IExternalTradeSourceEntity> externalTradeSourceObjectsSelectedByUserFromUI = getExternalTradeSourcesSelectedByUserFromUI();
		List<ExternalTradeSource> externalTradeSourceObjectsSelectedByUserFromUI = getExternalTradeSourcesSelectedByUserFromUI();
		List<IExternalTradeStateEntity> externalTradeStateObjectsSelectedByUserFromUI = getExternalTradeStatesSelectedByUserFromUI();
		List<IExternalTradeStatusEntity> externalTradeStatusObjectsSelectedByUserFromUI = getExternalTradeStatusesSelectedByUserFromUI();
		List<IExternalMappingEntity> externalTradeAccountObjectsSelectedByUserFromUI = getExternalTradeAccountsSelectedByUserFromUI();

		if(startDateDatePicker.getValue() != null)
			//selectedStartDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss").format(startDateDatePicker.getValue());
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
		List<String> selectedExternalTradeAccountNames = new ArrayList<String>();
		if(externalTradeAccountsListView.getCheckModel().getCheckedIndices().contains(0))
		{
			sqlQueryToFetchExternalTrades = session.getNamedQuery("externalTradesWithoutBuyerAccount");
			sqlQueryToFetchExternalTrades.setParameter("buyerAccountsParam", "" );
		}
		else
		{
			externalTradeAccountObjectsSelectedByUserFromUI.forEach((anExternalMapping) -> selectedExternalTradeAccountNames.add(anExternalMapping.getExternalValue1()));
			sqlQueryToFetchExternalTrades = session.getNamedQuery("externalTradesWithBuyerAccount");
			sqlQueryToFetchExternalTrades.setParameterList("buyerAccountsParam", selectedExternalTradeAccountNames);
		}

		if(selectedExternalTradeSourceNames.size() == 0)
			sqlQueryToFetchExternalTrades.setParameter("externalTradeSourcesParam", null);
		else
			sqlQueryToFetchExternalTrades.setParameterList("externalTradeSourcesParam", selectedExternalTradeSourceNames);

		if(selectedExternalTradeStateNames.size() == 0)
			sqlQueryToFetchExternalTrades.setParameter("externalTradeStatesParam", null);
		else
			sqlQueryToFetchExternalTrades.setParameterList("externalTradeStatesParam", selectedExternalTradeStateNames);

		if(selectedExternalTradeStatusNames.size() == 0)
			sqlQueryToFetchExternalTrades.setParameter("externalTradeStatusesParam", null);
		else
			sqlQueryToFetchExternalTrades.setParameterList("externalTradeStatusesParam", selectedExternalTradeStatusNames);

		sqlQueryToFetchExternalTrades.setParameter("startDate", selectedStartDate);
		sqlQueryToFetchExternalTrades.setParameter("endDate", selectedEndDate);

		/* This will fetch the data in a background thread, so UI will not be freezed and user can interact with the UI. Here we use a scheduled service which will invoke the task recurringly. */
		fetchExternalTradesScheduledService.setSQLQuery(sqlQueryToFetchExternalTrades);
		fetchExternalTradesScheduledService.setDelay(Duration.seconds(1));
		fetchExternalTradesScheduledService.setPeriod(Duration.seconds(10));

		/*
		 *  modified the above 2 lines as below. previously statusMessagesProperty and progressStatusesProperty are available in the same class but now moved to a different controller.
		 *  Eventually the below code has to be modified to access those properties from the respective controller class.
		 *  Currently accessing the statusMessagesProperty and progressStatusesProperty through the controller whose reference is injected while loading. this may not be the perfect approach,
		 *  need to find out a better way.
		 */
		fetchExternalTradesScheduledService.messageProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> { ApplicationHelper.controllersMap.getInstance(MainWindowController.class).statusMessagesProperty().set(newValue); });
		fetchExternalTradesScheduledService.progressProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) -> { ApplicationHelper.controllersMap.getInstance(MainWindowController.class).progressStatusesProperty().set(newValue.doubleValue()); });

		fetchExternalTradesScheduledService.restart();

		fetchExternalTradesScheduledService.setOnSucceeded((WorkerStateEvent workerStateEvent) -> { doThisIfFetchSucceeded(); });
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
		externalTradesObservableList.clear();
		externalTradesObservableList.addAll(fetchExternalTradesScheduledService.getValue());
		//dummyExternalTrades.addAll(fetchExternalTradesScheduledService.getLastValue());
		//dummyExternalTrades.addAll(fetchExternalTradesScheduledService.getLastValue() != null ? fetchExternalTradesScheduledService.getLastValue() : fetchExternalTradesScheduledService.getValue());
	}

	private void doThisIfFetchFailed()
	{
	}

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Event Handling logic ends here
	 * ============================================================================================================================================================================
	 */

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Animation logic starts here
	 * ============================================================================================================================================================================
	 */

	private void initializeAnimationsIfNeeded()
	{
		if(isAnimationNeeded())
		{
			//give a glow effect to a button
			final Glow glow = new Glow();
			glow.setLevel(0.0);
			//startMonitorButton.setEffect(glow);

			final Timeline timeline = new Timeline();
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.setAutoReverse(true);
			final KeyValue kv = new KeyValue(glow.levelProperty(), 0.3);
			final KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
			timeline.getKeyFrames().add(kf);
			timeline.play();

			//to rotate a component
			final RotateTransition rotate = new RotateTransition(Duration.seconds(2), startMonitorButtonImageView);
			rotate.setFromAngle(0);
			rotate.setByAngle(360);
			rotate.setCycleCount(-1);
			/*rotate.setAutoReverse(true);
			rotate.setCycleCount(Animation.INDEFINITE);
			rotate.setInterpolator(Interpolator.LINEAR);*/
			rotate.play();

			RotateTransition r = new RotateTransition(Duration.seconds(2), externalTradesTableView);
			r.setFromAngle(0);
			r.setByAngle(360);
			r.play();

			FadeTransition ft = new FadeTransition(Duration.seconds(2), externalTradesTableView);
			ft.setFromValue(1.0);
			ft.setToValue(0.0);
			ft.setCycleCount(2);
			ft.setAutoReverse(true);
			ft.play();
		}
	}

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Animation logic ends here
	 * ============================================================================================================================================================================
	 */

	private boolean isAnimationNeeded()
	{
		return false;
	}
}

/**
 * ============================================================================================================================================================================
 * 																																							All temporarily commented logic.
 * ============================================================================================================================================================================
 */

/*
	private void initializeExternalTradeTableView()
	{
		tradeOidTableColumn.setCellValueFactory(new PropertyValueFactory<>("oid"));
		tradeOidTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DummyExternalTrade, Number>, ObservableValue<Number>>()
		{
			@Override
			public ObservableValue<Number> call(CellDataFeatures<DummyExternalTrade, Number> param)
			{
				return new SimpleIntegerProperty(param.getValue().getOid().intValue());
			}});

		 //commenting the above code, bcoz the same can be implemented as below using java 8 Lambda
		externalTradeOidTableColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOid()));
		tradeCreationDateTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Date>(cellData.getValue().getCreationDate()));

		//modified the above code as below. creationDate column in DB is Date or TimeStamp, so it is mandatory to define it as Date in the DummyExternalTrade bean class. 
		 //But to utilize the java 8 LocalDate concept, we declared the TableView's creation date column as LocalDate. The value returned by the DummyExternalTrade bean is Date but 
		 //the UI column is expecting a LocalDate. so we convert the date to LocalDate.    
		tradeCreationDateTableColumn.setCellValueFactory(new TradeCreationDateCellValueFactory());
		tradeEntryDateTableColumn.setCellValueFactory(new TradeEntryDateCellValueFactory());
		tradeEntryDateTableColumn.setCellFactory(new TradeEntryDateCellFactory());
		tradeStateTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalTradeStateOid().getExternalTradeStateName()));
		tradeStatusTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalTradeStatusOid().getExternalTradeStatusName()));
		exchangeTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalTradeSourceOid().getExternalTradeSrcName()));
		commodityTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExchToolsTrade().getCommodity()));
		tradingPeriodTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExchToolsTrade().getTradingPeriod()));
		callPutTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExchToolsTrade().getCallPut()));

		strikePriceTableColumn.setCellValueFactory(cellData -> {
			if(cellData.getValue().getExchToolsTrade().getStrikePrice() != null)
				return new ReadOnlyDoubleWrapper(cellData.getValue().getExchToolsTrade().getStrikePrice().doubleValue());
			return null;
		});	
		quantityTableColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getExchToolsTrade().getQuantity()));
		priceTableColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getExchToolsTrade().getPrice()));

		buyingCompanyTableColumn.setCellValueFactory(cellData -> {
			if(cellData.getValue().getExchToolsTrade().getInputAction().trim().equals("BUY"))
			{
				return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getInputCompany());
			}
			else
			{
				return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getAcceptedCompany());
			}
		});

		buyingTraderTableColumn.setCellValueFactory(cellData -> {
			if(cellData.getValue().getExchToolsTrade().getInputAction().trim().equals("BUY"))
				return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getInputTrader());
			return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getAcceptedTrader());
		});

		sellingCompanyTableColumn.setCellValueFactory(cellData -> {
			if(cellData.getValue().getExchToolsTrade().getInputAction().trim().equals("BUY"))
				return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getAcceptedCompany());
			return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getInputCompany());
		});

		sellingTraderTableColumn.setCellValueFactory(cellData -> {
			if(cellData.getValue().getExchToolsTrade().getInputAction().trim().equals("BUY"))
				return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getAcceptedTrader());
			return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getInputTrader());
		});

		exchangeTradeNumTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExchToolsTrade().getExchToolsTradeNum()));
		accountTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExchToolsTrade().getBuyerAccount()));

		ictsTradeNumTableColumn.setCellValueFactory(cellData -> {
			if(cellData.getValue().getTradeNum() != null)
				return new ReadOnlyStringWrapper(cellData.getValue().getTradeNum().toString());
			return null;
		});

		ictsPortNumTableColumn.setCellValueFactory(cellData -> {
			if(cellData.getValue().getPortNum() != null)
				return new ReadOnlyStringWrapper(cellData.getValue().getPortNum().toString());
			return null;
		});

		tradeTypeTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExchToolsTrade().getTradeType()));

		inputBrokerTableColumn.setCellValueFactory(cellData -> {
			if(cellData.getValue().getExchToolsTrade().getInputAction().trim().equals("BUY"))
				return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getInputBroker());
			return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getAcceptedBroker());
		});

		buyerClearingBrokerTableColumn.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getBuyerClrngBroker());
		});

		sellerClearingBrokerTableColumn.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getSellerClrngBroker());
		});

		commentTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalCommentOid() != null ? cellData.getValue().getExternalCommentOid().getCommentText() : null));
	}*/