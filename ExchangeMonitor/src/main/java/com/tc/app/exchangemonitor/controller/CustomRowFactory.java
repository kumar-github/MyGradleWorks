package com.tc.app.exchangemonitor.controller;

import java.util.List;
import java.util.StringJoiner;

import com.tc.app.exchangemonitor.entitybase.IExternalTradeEntity;
import com.tc.app.exchangemonitor.util.StaticConstantsHelper;

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

	public CustomRowFactory(final Callback<TableView<T>, TableRow<T>> rowFactory, final Callback<T, List<MenuItem>> menuItemFactory)
	{
		this.rowFactory = rowFactory;
		this.menuItemFactory = menuItemFactory;
	}

	public CustomRowFactory(final Callback<T, List<MenuItem>> menuItemFactory)
	{
		this(null, menuItemFactory);
	}

	/* if we create the row factory through fxml and not through code then we can pass the menu items from the fxml itself */
	private ContextMenu tableRowContextMenu;

	public void setTableRowContextMenu(final ContextMenu tableRowContextMenu)
	{
		this.tableRowContextMenu = tableRowContextMenu;
	}

	public ContextMenu getTableRowContextMenu()
	{
		return this.tableRowContextMenu;
	}

	private final ContextMenu tableRowContextMenuTemp = new ContextMenu();
	public void setTableRowContextMenuItems(final List<MenuItem> tableRowContextMenuItems)
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
	public void setStyles(final List<String> styles)
	{
		this.styles = styles;
	}

	public List<String> getStyles()
	{
		return this.styles;
	}

	@Override
	public TableRow<T> call(final TableView<T> theTable)
	{
		final TableRow<T> theRow;

		if(this.rowFactory == null)
		{
			theRow = new TableRow<>();
		}
		else
		{
			theRow = this.rowFactory.call(theTable);
		}

		theRow.itemProperty().addListener((final ObservableValue<? extends T> observable, final T oldValue, final T newValue) -> {
			this.customizeThisRowAsNeeded(observable, oldValue, newValue, theRow);
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

		theRow.hoverProperty().addListener((observable) -> { this.doThisWhileHoveringOnTheRow(theRow); });

		return theRow;
	}

	private ContextMenu createContextMenuFromMenuItemFactory(final TableRow<T> theRow)
	{
		final ContextMenu contextMenu = new ContextMenu();
		final ContextMenu tableMenu = theRow.getTableView().getContextMenu();
		if(tableMenu != null)
		{
			contextMenu.getItems().addAll(tableMenu.getItems());
			contextMenu.getItems().add(new SeparatorMenuItem());
		}
		contextMenu.getItems().addAll(this.menuItemFactory.call(theRow.getItem()));
		return contextMenu;
	}

	private ContextMenu createContextMenuFromFXML()
	{
		final ContextMenu tableRowContextMenu = this.getTableRowContextMenu();
		final List<MenuItem> tableRowContextMenuItems = this.getTableRowContextMenuItems();
		if(tableRowContextMenu != null)
		{
			tableRowContextMenu.getItems().addAll(tableRowContextMenuItems);
		}
		return tableRowContextMenu;
	}

	private void customizeThisRowAsNeeded(final ObservableValue<? extends T> observable, final T oldValue, final T newValue, final TableRow<T> theRow)
	{
		if(PreferencesHelper.getUserPreferences().getBoolean(StaticConstantsHelper.SHOULD_ENABLE_ROW_CONTEXT_MENU, false))
		{
			this.configureRowContextMenu(observable, oldValue, newValue, theRow);
		}

		if(PreferencesHelper.getUserPreferences().getBoolean(StaticConstantsHelper.SHOULD_ENABLE_ROW_COLORS, false))
		{
			this.configureRowColor(observable, oldValue, newValue, theRow);
		}

		if(PreferencesHelper.getUserPreferences().getBoolean(StaticConstantsHelper.SHOULD_ENABLE_ROW_TOOLTIPS, false))
		{
			this.configureRowToolTip(observable, oldValue, newValue, theRow);
		}
	}

	private void configureRowContextMenu(final ObservableValue<? extends T> observable, final T oldValue, final T newValue, final TableRow<T> theRow)
	{
		if(newValue == null)
		{
			theRow.setContextMenu(null);
		}
		else
		{
			theRow.setContextMenu(this.menuItemFactory != null ? this.createContextMenuFromMenuItemFactory(theRow) : null);
			theRow.setContextMenu(this.createContextMenuFromFXML());
			//theRow.contextMenuProperty().bind(Bindings.when(Bindings.isNotNull(theRow.itemProperty())).then(createContextMenu(theRow)).otherwise((ContextMenu) null));
		}
	}

	private void configureRowColor(final ObservableValue<? extends T> observable, final T oldValue, final T newValue, final TableRow<T> theRow)
	{
		if(newValue == null)
		{
			theRow.pseudoClassStateChanged(this.pendingStyle, false);
			theRow.pseudoClassStateChanged(this.completedStyle, false);
			theRow.pseudoClassStateChanged(this.failedStyle, false);
			theRow.pseudoClassStateChanged(this.skippedStyle, false);
		}
		else
		{
			if(newValue instanceof IExternalTradeEntity)
			{
				final IExternalTradeEntity anExternalTrade = (IExternalTradeEntity) newValue;
				theRow.pseudoClassStateChanged(this.pendingStyle, anExternalTrade.getExternalTradeStatusOid().getExternalTradeStatusName().equals("Pending"));
				theRow.pseudoClassStateChanged(this.completedStyle, anExternalTrade.getExternalTradeStatusOid().getExternalTradeStatusName().equals("Completed"));
				theRow.pseudoClassStateChanged(this.failedStyle, anExternalTrade.getExternalTradeStatusOid().getExternalTradeStatusName().equals("Failed"));
				theRow.pseudoClassStateChanged(this.skippedStyle, anExternalTrade.getExternalTradeStatusOid().getExternalTradeStatusName().equals("Skipped"));
			}
		}
	}

	private void configureRowToolTip(final ObservableValue<? extends T> observable, final T oldValue, final T newValue, final TableRow<T> theRow)
	{
		if(newValue == null)
		{
			theRow.setTooltip(null);
		}
		else
		{
			if(newValue instanceof IExternalTradeEntity)
			{
				//theRow.setTooltip(new Tooltip(((ExternalTrade)newValue).getOid().toString()));
				theRow.setTooltip(new Tooltip(this.getToolTipString(newValue)));
			}
		}
	}

	private void doThisWhileHoveringOnTheRow(final TableRow<T> theRow)
	{
		if(theRow.isHover() && (theRow.getItem() != null))
		{
			/* do whatever you want to do when hovering. If our intention is just to highlight or change color then we can do that in CSS not here. This is something business. */
			//theRow.setTooltip(new Tooltip(((ExternalTrade)theRow.getItem()).getOid().toString()));
		}
	}

	private String getToolTipString(final T newValue)
	{
		final IExternalTradeEntity anExternalTrade = (IExternalTradeEntity) newValue;

		//1st Method
		//return String.format("External Trade: %s\nStatus: %s\nState: %s", anExternalTrade.getOid(), anExternalTrade.getExternalTradeStatusOid().getExternalTradeStatusName(), anExternalTrade.getExternalTradeStateOid().getExternalTradeStateName());

		//2nd Method
		final StringJoiner newLineJoiner = new StringJoiner("\n");
		newLineJoiner.add(String.format("External Trade OID : %s", anExternalTrade.getOid()));
		newLineJoiner.add(String.format("External Trade State : %s", anExternalTrade.getExternalTradeStateOid().getExternalTradeStateName()));
		//newLineJoiner.add(String.format("External Trade Status : %s", anExternalTrade.getExternalTradeStatusOid().getExternalTradeStatusName()));
		newLineJoiner.add(String.format("External Trade Status : %s", anExternalTrade.getExternalTradeStatusOid()));
		newLineJoiner.add(String.format("Exch Tools Trade Num  : %s", anExternalTrade.getExchToolsTrade().getExchToolsTradeNum()));
		return newLineJoiner.toString();
	}
}