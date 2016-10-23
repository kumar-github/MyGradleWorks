package com.tc.app.exchangemonitor.controller;

import com.tc.app.exchangemonitor.entitybase.IExternalTradeEntity;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ExternalTradeExchangesCellFactory implements Callback<TableColumn<IExternalTradeEntity, String>, TableCell<IExternalTradeEntity, String>>
{
	/*
	// 1st way of implementing
	@Override
	public TableCell<ExternalTrade, ZonedDateTime> call(TableColumn<ExternalTrade, ZonedDateTime> param)
	{
		return new TableCell<ExternalTrade, ZonedDateTime>(){
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
	*/
	
	//2nd way of implementing
	@Override
	public TableCell<IExternalTradeEntity, String> call(TableColumn<IExternalTradeEntity, String> param)
	{
		final TableCell<IExternalTradeEntity, String> aTableCell = new TableCell<IExternalTradeEntity, String>(){
			@Override
			protected void updateItem(String item, boolean empty)
			{
				super.updateItem(item, empty);
				if(empty || item == null)
				{
					setText(null);
				}
				else
				{
					switch(item)
					{
						case "1":
							setText("NYMEX");
							break;
						case "2":
							setText("IPE");
							break;
						case "3":
							setText("ICE");
							break;
						case "4":
							setText("ExchangeTools");
							break;
						case "5":
							setText("DashBoard");
							break;
						case "6":
							setText("Excel");
							break;
						case "7":
							setText("DME");
							break;
						case "8":
							setText("ECONFIRM");
							break;
						case "9":
							setText("CEE");
							break;
						case "10":
							setText("Olympus");
							break;
						case "11":
							setText("CBT");
							break;
						case "12":
							setText("NonDefined");
							break;
						default:
							setText(null);
					}
				}
			}
		};
		return aTableCell;
	}
}