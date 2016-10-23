package com.tc.app.exchangemonitor.controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.tc.app.exchangemonitor.entitybase.IExternalTradeEntity;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class DateCellFactory implements Callback<TableColumn<IExternalTradeEntity, ZonedDateTime>, TableCell<IExternalTradeEntity, ZonedDateTime>>
{
	@Override
	public TableCell<IExternalTradeEntity, ZonedDateTime> call(TableColumn<IExternalTradeEntity, ZonedDateTime> param)
	{
		return new TableCell<IExternalTradeEntity, ZonedDateTime>(){
			@Override
			protected void updateItem(ZonedDateTime item, boolean empty)
			{
				super.updateItem(item, empty);
				if(empty || item == null)
				{
					setText(null);
				}
				else
				{
					setText(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss z").format(item));
				}
			}
		};
	}
}