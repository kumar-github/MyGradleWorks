package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.tc.app.exchangemonitor.util.ApplicationHelper;
import com.tc.app.exchangemonitor.util.DatePickerConverter;
import com.tc.app.exchangemonitor.util.HibernateUtil;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MainApplicationSettlePricesTabController implements IMainApplicationMonitorTabController
{
	private static final Logger LOGGER = LogManager.getLogger(MainApplicationSettlePricesTabController.class);

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

	@FXML private Text startDateFilterKeyText;
	@FXML private Text startDateFilterValueText;

	@FXML private Text endDateFilterKeyText;
	@FXML private Text endDateFilterValueText;

	@FXML private TitledPane actionTitledPane;

	@FXML
	private Accordion queryFilterAccordion;

	@FXML
	private DatePicker startDateDatePicker;

	@FXML
	private DatePicker endDateDatePicker;

	@FXML
	private TableView<DummySettlePrice> settlePricesTableView;

	@FXML
	private TextField settlePricesTableViewDataFilterTextField;

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

	private InvalidationListener settlePricesTableViewDataFilterTextFieldKeyListener = null;

	private ObservableList<DummySettlePrice> settlePricesObservableList = FXCollections.observableArrayList();
	private FilteredList<DummySettlePrice> settlePricesFilteredList = new FilteredList<DummySettlePrice>(settlePricesObservableList, p->true);
	private SortedList<DummySettlePrice> settlePricesSortedList = new SortedList<DummySettlePrice>(settlePricesFilteredList);

	private FetchSettlePricesScheduledService fetchSettlePricesScheduledService = new FetchSettlePricesScheduledService();

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
		ApplicationHelper.controllersMap.putInstance(MainApplicationSettlePricesTabController.class, this);
	}

	@Override
	public void doAssertion()
	{
		assert settlePricesTableViewDataFilterTextField != null : "fx:id=\"settlePricesTableViewDataFilterTextField\" was not injected. Check your FXML file MainWindowSettlePricesTabView.fxml";
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

		startDateFilterKeyText.visibleProperty().bind(startDateFilterValueText.textProperty().isNotEqualTo("null"));
		startDateFilterKeyText.managedProperty().bind(startDateFilterKeyText.visibleProperty());
		startDateFilterValueText.managedProperty().bind(startDateFilterValueText.visibleProperty());

		endDateFilterKeyText.visibleProperty().bind(endDateFilterValueText.textProperty().isNotEqualTo("null"));
		endDateFilterKeyText.managedProperty().bind(endDateFilterKeyText.visibleProperty());
		endDateFilterValueText.managedProperty().bind(endDateFilterValueText.visibleProperty());

		settlePricesTableView.setItems(settlePricesSortedList);

		startDateFilterValueText.textProperty().bind(startDateDatePicker.valueProperty().asString());
		endDateFilterValueText.textProperty().bind(endDateDatePicker.valueProperty().asString());

		startMonitorButton.disableProperty().bind(fetchSettlePricesScheduledService.runningProperty());
		pauseMonitorButton.disableProperty().bind(fetchSettlePricesScheduledService.runningProperty().not());
		stopMonitorButton.disableProperty().bind(fetchSettlePricesScheduledService.runningProperty().not());

		//applicationMainWindowCurrentFilterToolBar.visibleProperty().bind(exchangesFilterText.textProperty().isNotEmpty().or(statesFilterText.textProperty().isNotEmpty()).or(typesFilterText.textProperty().isNotEmpty()).or(accountsFilterText.textProperty().isNotEmpty()).or(startDateFilterText.textProperty().isNotEqualTo("null")).or(endDateFilterText.textProperty().isNotEqualTo("null")));
		/* We are hiding the entire toolbar if no text in any of the Text control. */
		applicationMainWindowCurrentFilterToolBar.visibleProperty().bind
		(
				(startDateFilterKeyText.visibleProperty())
				.or(endDateFilterKeyText.visibleProperty())
				);

		settlePricesSortedList.comparatorProperty().bind(settlePricesTableView.comparatorProperty());
	}

	@Override
	public void initializeGUI()
	{
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

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Listener creation starts here
	 * ============================================================================================================================================================================
	 */

	public void createListeners()
	{
		settlePricesTableViewDataFilterTextFieldKeyListener = (observable) -> { handleSettlePricesTableViewFilterByKey(); };
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
		settlePricesTableViewDataFilterTextField.textProperty().addListener(settlePricesTableViewDataFilterTextFieldKeyListener);
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

	private void handleSettlePricesTableViewFilterByKey()
	{
		//settlePricesFilteredList.setPredicate(settlePricesTableViewFilterPredicate(settlePricesTableViewDataFilterTextField.getText().trim().toLowerCase()));

		Platform.runLater(new Runnable() {
			@Override
			public void run()
			{
				// we don't want repeated selections
				settlePricesTableView.getSelectionModel().clearSelection();
				//get the focus
				settlePricesTableView.requestFocus();

				//select first item in TableView model
				settlePricesTableView.getSelectionModel().selectFirst();

				//set the focus on the first element
				settlePricesTableView.getFocusModel().focus(0);

				//render the selected item in the TableView
				//tableClickHandler(null);
			}
		});

		Platform.runLater(new Runnable() {
			@Override
			public void run()
			{
				settlePricesTableViewDataFilterTextField.requestFocus();
				settlePricesTableViewDataFilterTextField.end();
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
		startMonitoringSettlePrices();
	}

	@FXML
	private void handlePauseMonitorButtonClick()
	{
		pauseMonitoringSettlePrices();
	}

	@FXML
	private void handleStopMonitorButtonClick()
	{
		stopMonitoringSettlePrices();
	}

	private void startMonitoringSettlePrices()
	{
		//acc.setExpandedPane(externalTradeSourcesTitledPane);
		fetchSettlePricesFromDBForTableView();
	}

	private void pauseMonitoringSettlePrices()
	{
		if(fetchSettlePricesScheduledService != null)
		{
			if(fetchSettlePricesScheduledService.isRunning())
			{
				fetchSettlePricesScheduledService.cancel();
				//statusMessagesProperty().set("Task Stopped.");
				//progressStatusesProperty().set(0.0);
			}
		}
	}

	private void stopMonitoringSettlePrices()
	{
		pauseMonitoringSettlePrices();
		settlePricesObservableList.clear();

	}

	private void fetchSettlePricesFromDBForTableView()
	{
		Query sqlQueryToFetchSettlePrices = null;
		String selectedStartDate = null;
		String selectedEndDate = null;
		String s = "SELECT tif.trade_num as tradeNum, tif.order_num as orderNum, tif.item_num as itemNum, tif.item_fill_num as itemFillNum, tif.fill_date as fillDate, ti.cmdty_code as cmdtyCode, ti.risk_mkt_code as riskMktCode, ti.trading_prd as tradingPrd, ti.p_s_ind as psInd, tif.fill_qty as fillQty, tif.fill_price as fillPrice, iu.user_first_name as firstName, iu.user_last_name as lastName, ti.real_port_num as realPortNum, p.port_full_name as portFullName, tifut.price_source_code as priceSourceCode, toexch.order_instr_code as orderInstrCode, toexch.order_price as orderPrice, ti.cmnt_num as cmntNum, tif.external_trade_num as externalTradeNum, cm.commkt_key as commktKey FROM trade_item_fill tif INNER JOIN trade_item ti ON tif.trade_num = ti.trade_num AND tif.order_num = ti.order_num AND tif.item_num = ti.item_num INNER JOIN trade t ON tif.trade_num = t.trade_num INNER JOIN trade_order tor ON tif.trade_num = tor.trade_num AND tif.order_num = tor.order_num INNER JOIN trade_item_fut tifut ON tif.trade_num = tifut.trade_num AND tif.order_num = tifut.order_num AND tif.item_num = tifut.item_num INNER JOIN trade_order_on_exch toexch ON tif.trade_num = toexch.trade_num AND tif.order_num = toexch.order_num INNER JOIN comment c ON ti.cmnt_num = c.cmnt_num INNER JOIN commodity_market cm ON ti.cmdty_code = cm.cmdty_code AND toexch.order_instr_code = cm.mkt_code left outer join icts_user iu ON iu.user_init = t.trader_init left outer join portfolio p ON p.port_num = ti.real_port_num where (tor.strip_summary_ind != 'Y' AND t.conclusion_type = 'C') AND (tif.fill_date >= '2011-09-05 00:00:00.000' AND tif.fill_date <= '2015-01-09 00:00:00.000') AND (UPPER(RTRIM(LTRIM(c.tiny_cmnt))) = 'NOT PRICED' OR UPPER(RTRIM(LTRIM(c.tiny_cmnt))) = 'NOTPRICED') ORDER BY tif.fill_date DESC";

		if(startDateDatePicker.getValue() != null)
			selectedStartDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(startDateDatePicker.getValue());
		if(endDateDatePicker.getValue() != null)
			selectedEndDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(endDateDatePicker.getValue());
		
		//sqlQueryToFetchSettlePrices.setParameter("startDate", selectedStartDate);
		//sqlQueryToFetchSettlePrices.setParameter("endDate", selectedEndDate);
		
		Session session = HibernateUtil.beginTransaction();
		sqlQueryToFetchSettlePrices = session.createSQLQuery(s);
		sqlQueryToFetchSettlePrices.setResultTransformer(Transformers.aliasToBean(com.tc.app.exchangemonitor.controller.DummySettlePrice.class));

		/* This will fetch the data in a background thread, so UI will not be freezed and user can interact with the UI. Here we use a scheduled service which will invoke the task recurringly. */
		fetchSettlePricesScheduledService.setSQLQuery(sqlQueryToFetchSettlePrices);
		fetchSettlePricesScheduledService.setDelay(Duration.seconds(1));
		fetchSettlePricesScheduledService.setPeriod(Duration.seconds(10));

		/*
		 *  modified the above 2 lines as below. previously statusMessagesProperty and progressStatusesProperty are available in the same class but now moved to a different controller.
		 *  Eventually the below code has to be modified to access those properties from the respective controller class.
		 *  Currently accessing the statusMessagesProperty and progressStatusesProperty through the controller whose reference is injected while loading. this may not be the perfect approach,
		 *  need to find out a better way.
		 */
		fetchSettlePricesScheduledService.messageProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> { ApplicationHelper.controllersMap.getInstance(MainWindowController.class).statusMessagesProperty().set(newValue); });
		fetchSettlePricesScheduledService.progressProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) -> { ApplicationHelper.controllersMap.getInstance(MainWindowController.class).progressStatusesProperty().set(newValue.doubleValue()); });

		fetchSettlePricesScheduledService.restart();

		fetchSettlePricesScheduledService.setOnSucceeded((WorkerStateEvent workerStateEvent) -> { doThisIfFetchSucceeded(); });
	}

	private void doThisIfFetchSucceeded()
	{
		settlePricesObservableList.clear();
		settlePricesObservableList.addAll(fetchSettlePricesScheduledService.getValue());
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

			RotateTransition r = new RotateTransition(Duration.seconds(2), settlePricesTableView);
			r.setFromAngle(0);
			r.setByAngle(360);
			r.play();

			FadeTransition ft = new FadeTransition(Duration.seconds(2), settlePricesTableView);
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