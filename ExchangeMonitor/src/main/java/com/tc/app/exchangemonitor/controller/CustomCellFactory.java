package com.tc.app.exchangemonitor.controller;

import java.util.List;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class CustomCellFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>>
{
	private final Callback<TableColumn<S, T>, TableCell<S, T>> cellFactory;
	private final Callback<TableCell<S, T>, List<MenuItem>> menuItemFactory;

	public CustomCellFactory()
	{
		this.cellFactory = null;
		this.menuItemFactory = null;
	}

	public CustomCellFactory(Callback<TableCell<S,T>, List<MenuItem>> menuItemFactory)
	{
		this(null, menuItemFactory);
	}

	public CustomCellFactory(Callback<TableColumn<S, T>, TableCell<S, T>> cellFactory, Callback<TableCell<S,T>, List<MenuItem>> menuItemFactory)
	{
		this.cellFactory = cellFactory ;
		this.menuItemFactory = menuItemFactory ;
	}	

	@Override
	public TableCell<S, T> call(TableColumn<S, T> theTableColumn)
	{
		final TableCell<S, T> theCell;

		if(cellFactory == null)
		{
			theCell = new TableCell<S, T>(){
				@Override
				protected void updateItem(T item, boolean empty)
				{
					super.updateItem((T)item, empty);
					if(empty || item == null)
						setText(null);
					else
						setText(item.toString());
				}
			};
		}
		else
		{
			theCell = cellFactory.call(theTableColumn);
		}

		theCell.itemProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue == null)
				theCell.setContextMenu(null);
			else
				theCell.setContextMenu(createContextMenu(theCell));
		});

		return theCell;
	}

	private ContextMenu createContextMenu(TableCell<S, T> theCell)
	{
		ContextMenu contextMenu = new ContextMenu();
		TableRow<?> theRow = theCell.getTableRow();
		if (theRow != null)
		{
			ContextMenu rowMenu = theRow.getContextMenu();
			if (rowMenu == null)
			{
				TableView<S> table = theCell.getTableView();
				ContextMenu tableMenu = table.getContextMenu();
				if (tableMenu != null)
				{
					contextMenu.getItems().addAll(tableMenu.getItems());
					contextMenu.getItems().add(new SeparatorMenuItem());
				}
			}
			else
			{
				contextMenu.getItems().addAll(rowMenu.getItems());
				contextMenu.getItems().add(new SeparatorMenuItem());
			}
		}
		contextMenu.getItems().addAll(menuItemFactory.call(theCell));
		return contextMenu ;
	}
}