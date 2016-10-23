package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.tc.app.exchangemonitor.model.IctsUser;
import com.tc.app.exchangemonitor.util.HibernateReferenceDataFetchUtil;

import entitypredicates.ITraderPredicates;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TraderReferenceDataTableViewController implements IGenericReferenceDataController
{
	@FXML private TableView<IctsUser> traderReferenceDataTableView;
	@FXML private TableColumn<IctsUser, String> userInitTableColumn;
	@FXML private TableColumn<IctsUser, String> userFirstNameTableColumn;
	@FXML private TableColumn<IctsUser, String> userLastNameTableColumn;
	@FXML private TableColumn<IctsUser, String> userLogonIdTableColumn;
	
	private ObservableList<IctsUser> tradersObservableList = FXCollections.observableArrayList();
	private FilteredList<IctsUser> tradersFilteredList = new FilteredList<IctsUser>(tradersObservableList, p->true);
	private SortedList<IctsUser> tradersSortedList = new SortedList<IctsUser>(tradersFilteredList);

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		tradersSortedList.comparatorProperty().bind(traderReferenceDataTableView.comparatorProperty());

		//commoditiesObservableList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("FetchAllCommodities");
		tradersObservableList.addAll(HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("FetchAllTraders"));
		traderReferenceDataTableView.setItems(tradersSortedList);
	}
	
	@Override
	public FilteredList<IctsUser> getInnerTableViewControlDataSource()
	{
		return tradersFilteredList;
	}
	
	@Override
	public void filter(String filterText)
	{
		tradersFilteredList.setPredicate(ITraderPredicates.applyTraderPredicate(filterText));
	}
}