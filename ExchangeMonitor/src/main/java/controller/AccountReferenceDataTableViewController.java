package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.tc.app.exchangemonitor.model.Account;
import com.tc.app.exchangemonitor.util.HibernateReferenceDataFetchUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AccountReferenceDataTableViewController implements IGenericReferenceDataController
{
	@FXML private TableView<Account> accountReferenceDataTableView;
	@FXML private TableColumn<Account, Integer> accountNumTableColumn;
	@FXML private TableColumn<Account, String> accountShortNameTableColumn;
	@FXML private TableColumn<Account, String> accountFullNameTableColumn;
	@FXML private TableColumn<Account, String> accountTypeCodeTableColumn;

	private ObservableList<Account> accountsObservableList = FXCollections.observableArrayList();
	private FilteredList<Account> accountsFilteredList = new FilteredList<Account>(accountsObservableList, p->true);
	private SortedList<Account> accountsSortedList = new SortedList<Account>(accountsFilteredList);
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		accountsSortedList.comparatorProperty().bind(accountReferenceDataTableView.comparatorProperty());

		//commoditiesObservableList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("FetchAllCommodities");
		accountsObservableList.addAll(HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("FetchAllAccounts"));
		accountReferenceDataTableView.setItems(accountsSortedList);
	}

	@Override
	//public FilteredList<Account> getInnerTableViewControlDataSource()
	public FilteredList<Account> getInnerTableViewControlDataSource()
	{
		return accountsFilteredList;
	}

	@Override
	public void filter(String filterText)
	{
	}
}