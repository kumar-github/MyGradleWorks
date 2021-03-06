package com.tc.app.exchangemonitor.controller;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class DecimalCellFactory implements Callback<TableColumn<DummyPosition, Double>, TableCell<DummyPosition, Double>>
{
	//2nd way of implementing
	@Override
	public TableCell<DummyPosition, Double> call(TableColumn<DummyPosition, Double> param)
	{
		final TableCell<DummyPosition, Double> aTableCell = new TableCell<DummyPosition, Double>(){
			@Override
			protected void updateItem(Double item, boolean empty)
			{
				super.updateItem(item, empty);
				if(empty || item == null)
				{
					setText(null);
				}
				else
				{
					setText(String.format("%.4f", item.doubleValue()));
					//setText(Bindings.format("%.4f", item.doubleValue()).getValue());
				}
			}
		};
		return aTableCell;
	}
}