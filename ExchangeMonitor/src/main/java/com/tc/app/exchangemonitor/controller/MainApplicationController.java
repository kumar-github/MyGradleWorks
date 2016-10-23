package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.tc.app.exchangemonitor.util.ApplicationHelper;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class MainApplicationController implements Initializable
{
	@FXML
	private BorderPane mainApplicationBorderPane;
	
	@FXML
	private MainApplicationTitleBarController mainApplicationTitleBarViewController;
	
	@FXML
	private MainApplicationMenuBarController mainApplicationMenuBarViewController;
	
	@FXML
	private MainApplicationMonitorTabController mainApplicationMonitorTabViewController;
	
	@FXML
	private MainApplicationStatusBarController mainApplicationStatusBarViewController;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		addThisControllerToControllersMap();
		doAssertion();
		doInitialDataBinding();
		initializeGUI();
	}

	private void addThisControllerToControllersMap()
	{
		ApplicationHelper.controllersMap.putInstance(MainApplicationController.class, this);
	}

	private void doAssertion()
	{
		//assert externalTradeTableViewDataFilterTextField != null : "fx:id=\"externalTradeTableViewDataFilterTextField\" was not injected. Check your FXML file MainApplicationViewNew.fxml";
	}

	private void doInitialDataBinding()
	{
	}

	private void initializeGUI()
	{
	}
	
	/* This is the worsttttttttttttttttttttttt way to do this. */
	public BorderPane getMainApplicationBorderPane()
	{
		return mainApplicationBorderPane;
	}
	
	public MainApplicationTitleBarController getMainApplicationTitleBarViewController()
	{
		return mainApplicationTitleBarViewController;
	}
	
	public MainApplicationMenuBarController getMainApplicationMenuBarViewController()
	{
		return mainApplicationMenuBarViewController;
	}
	
	public MainApplicationMonitorTabController getMainApplicationMonitorTabViewController()
	{
		return mainApplicationMonitorTabViewController;
	}
	
	public MainApplicationStatusBarController getMainApplicationStatusBarViewController()
	{
		return mainApplicationStatusBarViewController;
	}

	/* ******************************************************************************************************************************************************************************************************* */

	/**
	 * ============================================================================================================================================================================
	 * 																																							All temporarily commented code starts here. We may need in future for reference
	 * ============================================================================================================================================================================
	 */

	/*tradeAccountListView.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
    @Override
    public BooleanProperty call(String item) {
    //public ObservableValue<Boolean> call(String item) {
        /*BooleanProperty observable = new SimpleBooleanProperty();
        observable.addListener((obs, wasSelected, isNowSelected) -> System.out.println("Check box for "+item+" changed from "+wasSelected+" to "+isNowSelected));
        return observable ;
    }
    }));*/

	// set the cell factory
	/*Callback<String, ObservableValue<Boolean>> getProperty = new Callback<String, ObservableValue<Boolean>>() {
	@Override
    public BooleanProperty call(String item) {
        // given a person, we return the property that represents
        // whether or not they are invited. We can then bind to this
        // bidirectionally.
        //return item;
    	System.out.println(item + " is clicked");
    	return null;
    }};

    tradeAccountListView.setCellFactory(CheckBoxListCell.forListView(getProperty));*/

	/*
	public void handleSearchByKey2(String oldVal, String newVal)
	{
		// If the number of characters in the text box is less than last time it must be because the user pressed delete
		if(oldVal != null && (newVal.length() < oldVal.length()))
		{
			// Restore the lists original set of entries and start from the beginning
			tradeAccountListView.setItems(FXCollections.observableArrayList(externalTradeAccounts));
		}

		// Break out all of the parts of the search text by splitting on white space
		String[] parts = newVal.toUpperCase().split(" ");

		// Filter out the entries that don't contain the entered text
		ObservableList<String> subentries = FXCollections.observableArrayList();
		//for (Object entry: tradeAccountListView.getItems())
		for(String entry: tradeAccountListView.getItems())
		{
			boolean match = true;
			for(String part: parts)
			{
				// The entry needs to contain all portions of the search string *but* in any order
				if(!entry.toUpperCase().contains(part))
				{
					match = false;
					break;
				}
			}

			if (match)
			{
				subentries.add(entry);
			}
		}
		tradeAccountListView.setItems(subentries);
	}
	 */

	/**
	 * ============================================================================================================================================================================
	 * 																																							All temporarily commented code ends here. We may need in future for reference
	 * ============================================================================================================================================================================
	 */
}