package com.tc.app.exchangemonitor.controller;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class PLCellFactory implements Callback<TableColumn<DummyPosition, Double>, TableCell<DummyPosition, Double>>
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
					setStyle(null);
				}
				else
				{
					if(item < 0)
					{
						setText(item.toString());
						setTextFill(Color.RED);
						//setStyle("-fx-background-color: red");
					}
					else
					{
						setText(item.toString());
						setTextFill(Color.BLACK);
						//setStyle(null);
					}
				}
			}
		};
		return aTableCell;
	}
}