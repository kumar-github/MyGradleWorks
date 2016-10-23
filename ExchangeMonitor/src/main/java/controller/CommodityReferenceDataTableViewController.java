package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.tc.app.exchangemonitor.model.Commodity;
import com.tc.app.exchangemonitor.util.HibernateReferenceDataFetchUtil;

import entitypredicates.ICommodityPredicates;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CommodityReferenceDataTableViewController implements IGenericReferenceDataController
{
	@FXML private TableView<Commodity> commodityReferenceDataTableView;
	@FXML private TableColumn<Commodity, String> commodityCodeTableColumn;
	@FXML private TableColumn<Commodity, String> commodityShortNameTableColumn;
	@FXML private TableColumn<Commodity, String> commodityFullNameTableColumn;

	private ObservableList<Commodity> commoditiesObservableList = FXCollections.observableArrayList();
	private FilteredList<Commodity> commoditiesFilteredList = new FilteredList<Commodity>(commoditiesObservableList, p->true);
	private SortedList<Commodity> commoditiesSortedList = new SortedList<Commodity>(commoditiesFilteredList);

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		commoditiesSortedList.comparatorProperty().bind(commodityReferenceDataTableView.comparatorProperty());

		//commoditiesObservableList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("FetchAllCommodities");
		long startTime = System.currentTimeMillis();
		commoditiesObservableList.addAll(HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("FetchAllCommodities"));
		long endTime = System.currentTimeMillis();
		commodityReferenceDataTableView.setItems(commoditiesSortedList);
		System.out.println(endTime - startTime);
	}

	@Override
	public FilteredList<Commodity> getInnerTableViewControlDataSource()
	{
		return commoditiesFilteredList;
	}

	@Override
	public void filter(String filterText)
	{
		commoditiesFilteredList.setPredicate(ICommodityPredicates.applyCommodityPredicate(filterText));
	}
}