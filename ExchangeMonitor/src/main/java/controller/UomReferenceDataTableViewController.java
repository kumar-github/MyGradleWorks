package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.tc.app.exchangemonitor.model.Uom;
import com.tc.app.exchangemonitor.util.HibernateReferenceDataFetchUtil;

import entitypredicates.IUomPredicates;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class UomReferenceDataTableViewController implements IGenericReferenceDataController
{
	@FXML private TableView<Uom> uomReferenceDataTableView;
	@FXML private TableColumn<Uom, String> uomCodeTableColumn;
	@FXML private TableColumn<Uom, String> uomShortNameTableColumn;
	@FXML private TableColumn<Uom, String> uomFullNameTableColumn;

	private ObservableList<Uom> uomObservableList = FXCollections.observableArrayList();
	private FilteredList<Uom> uomFilteredList = new FilteredList<Uom>(uomObservableList, p->true);
	private SortedList<Uom> uomSortedList = new SortedList<Uom>(uomFilteredList);
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		uomSortedList.comparatorProperty().bind(uomReferenceDataTableView.comparatorProperty());

		//commoditiesObservableList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("FetchAllCommodities");
		uomObservableList.addAll(HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("FetchAllUoms"));
		uomReferenceDataTableView.setItems(uomSortedList);
	}

	@Override
	public FilteredList<Uom> getInnerTableViewControlDataSource()
	{
		return uomFilteredList;
	}

	@Override
	public void filter(String filterText)
	{
		uomFilteredList.setPredicate(IUomPredicates.applyUomPredicate(filterText));
	}
}