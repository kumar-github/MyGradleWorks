package com.tc.app.exchangemonitor.controller;

import java.util.List;
import java.util.StringJoiner;

import com.tc.app.exchangemonitor.entitybase.IExternalTradeEntity;

import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.util.Callback;

public class CustomRowFactory<T> implements Callback<TableView<T>, TableRow<T>>
{
	private final Callback<TableView<T>, TableRow<T>> rowFactory;
	private final Callback<T, List<MenuItem>> menuItemFactory;

	PseudoClass pendingStyle = PseudoClass.getPseudoClass("pending");
	PseudoClass completedStyle = PseudoClass.getPseudoClass("completed");
	PseudoClass failedStyle = PseudoClass.getPseudoClass("failed");
	PseudoClass skippedStyle = PseudoClass.getPseudoClass("skipped");

	public CustomRowFactory()
	{
		this.rowFactory = null;
		this.menuItemFactory = null;
	}

	public CustomRowFactory(Callback<TableView<T>, TableRow<T>> rowFactory, Callback<T, List<MenuItem>> menuItemFactory)
	{
		this.rowFactory = rowFactory;
		this.menuItemFactory = menuItemFactory;
	}

	public CustomRowFactory(Callback<T, List<MenuItem>> menuItemFactory)
	{
		this(null, menuItemFactory);
	}

	/* if we create the row factory through fxml and not through code then we can pass the menu items from the fxml itself */
	private ContextMenu tableRowContextMenu;
	public void setTableRowContextMenu(ContextMenu tableRowContextMenu)
	{
		this.tableRowContextMenu = tableRowContextMenu;
	}

	public ContextMenu getTableRowContextMenu()
	{
		return tableRowContextMenu;
	}

	private ContextMenu  tableRowContextMenuTemp = new ContextMenu();
	public void setTableRowContextMenuItems(List<MenuItem> tableRowContextMenuItems)
	{
		//this.tableRowContextMenuItems = tableRowContextMenuItems;
		this.tableRowContextMenuTemp.getItems().addAll(tableRowContextMenuItems);
	}

	public List<MenuItem> getTableRowContextMenuItems()
	{
		//return this.tableRowContextMenuItems;
		return this.tableRowContextMenuTemp.getItems();
	}

	private List<String> styles;
	public void setStyles(List<String> styles)
	{
		this.styles = styles;
	}

	public List<String> getStyles()
	{
		return this.styles;
	}

	@Override
	public TableRow<T> call(TableView<T> theTable)
	{
		final TableRow<T> theRow;

		if(rowFactory == null)
			theRow = new TableRow<T>();
		else
			theRow = rowFactory.call(theTable);
		
		theRow.itemProperty().addListener((ObservableValue<? extends T> observable, T oldValue, T newValue) -> { 
			customizeThisRowAsNeeded(observable, oldValue, newValue, theRow);
		});

		/*
		theRow.itemProperty().addListener((ObservableValue<? extends T> observable, T oldValue, T newValue) -> {
			if(newValue == null)
			{
				// context menu part
				theRow.setContextMenu(null);

				// row color part
				theRow.pseudoClassStateChanged(pendingStyle, false);
				theRow.pseudoClassStateChanged(completedStyle, false);
				theRow.pseudoClassStateChanged(failedStyle, false);
				theRow.pseudoClassStateChanged(skippedStyle, false);

				// tooltip part
				theRow.setTooltip(null);
			}
			else
			{
				if(newValue instanceof ExternalTrade)
				{
					// context menu part
					ExternalTrade anExternalTrade = (ExternalTrade)newValue;
					theRow.setContextMenu(menuItemFactory != null ? createContextMenuFromMenuItemFactory(theRow) : null);
					theRow.setContextMenu(createContextMenuFromFXML());
					//theRow.contextMenuProperty().bind(Bindings.when(Bindings.isNotNull(theRow.itemProperty())).then(createContextMenu(theRow)).otherwise((ContextMenu) null));
					//theRow.contextMenuProperty().bind(Bindings.when(theRow.emptyProperty()).then((ContextMenu) null).otherwise(contextMenu));

					// row color part
					theRow.pseudoClassStateChanged(pendingStyle, anExternalTrade.getExternalTradeStatusOid().getExternalTradeStatusName().equals("Pending"));
					theRow.pseudoClassStateChanged(completedStyle, anExternalTrade.getExternalTradeStatusOid().getExternalTradeStatusName().equals("Completed"));
					theRow.pseudoClassStateChanged(failedStyle, anExternalTrade.getExternalTradeStatusOid().getExternalTradeStatusName().equals("Failed"));
					theRow.pseudoClassStateChanged(skippedStyle, anExternalTrade.getExternalTradeStatusOid().getExternalTradeStatusName().equals("Skipped"));

					// tooltip part
					theRow.setTooltip(new Tooltip(anExternalTrade.getOid().toString()));
				}
			}
		});
		 */

		theRow.hoverProperty().addListener((observable) -> { doThisWhileHoveringOnTheRow(theRow); });	

		return theRow;
	}

	private ContextMenu createContextMenuFromMenuItemFactory(final TableRow<T> theRow)
	{
		ContextMenu contextMenu = new ContextMenu();
		ContextMenu tableMenu = theRow.getTableView().getContextMenu();
		if(tableMenu != null)
		{
			contextMenu.getItems().addAll(tableMenu.getItems());
			contextMenu.getItems().add(new SeparatorMenuItem());
		}
		contextMenu.getItems().addAll(menuItemFactory.call(theRow.getItem()));
		return contextMenu;
	}

	private ContextMenu createContextMenuFromFXML()
	{
		ContextMenu tableRowContextMenu = getTableRowContextMenu();
		List<MenuItem> tableRowContextMenuItems = getTableRowContextMenuItems();
		if(tableRowContextMenu != null)
		{
			tableRowContextMenu.getItems().addAll(tableRowContextMenuItems);
		}
		return tableRowContextMenu;
	}

	private void customizeThisRowAsNeeded(ObservableValue<? extends T> observable, T oldValue, T newValue, final TableRow<T> theRow)
	{
		
		configureRowContextMenu(observable, oldValue, newValue, theRow);
		configureRowColor(observable, oldValue, newValue, theRow);
		configureRowToolTip(observable, oldValue, newValue, theRow);
	}

	private void configureRowContextMenu(ObservableValue<? extends T> observable, T oldValue, T newValue, final TableRow<T> theRow)
	{
		if(newValue == null)
			theRow.setContextMenu(null);
		else
		{
			theRow.setContextMenu(menuItemFactory != null ? createContextMenuFromMenuItemFactory(theRow) : null);
			theRow.setContextMenu(createContextMenuFromFXML());
			//theRow.contextMenuProperty().bind(Bindings.when(Bindings.isNotNull(theRow.itemProperty())).then(createContextMenu(theRow)).otherwise((ContextMenu) null));
		}
	}
	
	private void configureRowColor(ObservableValue<? extends T> observable, T oldValue, T newValue, final TableRow<T> theRow)
	{
		if(newValue == null)
		{
			theRow.pseudoClassStateChanged(pendingStyle, false);
			theRow.pseudoClassStateChanged(completedStyle, false);
			theRow.pseudoClassStateChanged(failedStyle, false);
			theRow.pseudoClassStateChanged(skippedStyle, false);
		}
		else
		{
			if(newValue instanceof IExternalTradeEntity)
			{
				IExternalTradeEntity anExternalTrade = (IExternalTradeEntity)newValue;
				theRow.pseudoClassStateChanged(pendingStyle, anExternalTrade.getExternalTradeStatusOid().getExternalTradeStatusName().equals("Pending"));
				theRow.pseudoClassStateChanged(completedStyle, anExternalTrade.getExternalTradeStatusOid().getExternalTradeStatusName().equals("Completed"));
				theRow.pseudoClassStateChanged(failedStyle, anExternalTrade.getExternalTradeStatusOid().getExternalTradeStatusName().equals("Failed"));
				theRow.pseudoClassStateChanged(skippedStyle, anExternalTrade.getExternalTradeStatusOid().getExternalTradeStatusName().equals("Skipped"));
			}
		}
	}

	private void configureRowToolTip(ObservableValue<? extends T> observable, T oldValue, T newValue, final TableRow<T> theRow)
	{
		if(newValue == null)
			theRow.setTooltip(null);
		else
		{
			if(newValue instanceof IExternalTradeEntity)
			{
				//theRow.setTooltip(new Tooltip(((ExternalTrade)newValue).getOid().toString()));
				theRow.setTooltip(new Tooltip(getToolTipString(newValue)));
			}
		}
	}
	
	private void doThisWhileHoveringOnTheRow(TableRow<T> theRow)
	{
		if(theRow.isHover() && theRow.getItem() != null)
		{
			/* do whatever you want to do when hovering. If our intention is just to highlight or change color then we can do that in CSS not here. This is something business. */
			//theRow.setTooltip(new Tooltip(((ExternalTrade)theRow.getItem()).getOid().toString()));
		}
	}
	
	private String getToolTipString(T newValue)
	{
		IExternalTradeEntity anExternalTrade = (IExternalTradeEntity)newValue;
		
		//1st Method
		//return String.format("External Trade: %s\nStatus: %s\nState: %s", anExternalTrade.getOid(), anExternalTrade.getExternalTradeStatusOid().getExternalTradeStatusName(), anExternalTrade.getExternalTradeStateOid().getExternalTradeStateName());
		
		//2nd Method
		StringJoiner newLineJoiner = new StringJoiner("\n") ;
		newLineJoiner.add(String.format("External Trade OID : %s", anExternalTrade.getOid()));
		newLineJoiner.add(String.format("External Trade State : %s", anExternalTrade.getExternalTradeStateOid().getExternalTradeStateName()));
		newLineJoiner.add(String.format("External Trade Status : %s", anExternalTrade.getExternalTradeStatusOid().getExternalTradeStatusName()));
		newLineJoiner.add(String.format("Exch Tools Trade Num  : %s", anExternalTrade.getExchToolsTrade().getExchToolsTradeNum()));
		return newLineJoiner.toString();
	}
}