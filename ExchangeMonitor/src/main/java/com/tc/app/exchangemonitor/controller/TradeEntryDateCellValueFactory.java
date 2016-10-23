package com.tc.app.exchangemonitor.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.tc.app.exchangemonitor.entitybase.IExternalTradeEntity;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class TradeEntryDateCellValueFactory implements Callback<TableColumn.CellDataFeatures<IExternalTradeEntity, ZonedDateTime>, ObservableValue<ZonedDateTime>>
{
	@Override
	public ObservableValue<ZonedDateTime> call(CellDataFeatures<IExternalTradeEntity, ZonedDateTime> cellData)
	{
		//return param.getValue().getCreationDate().toInstant().atZone(ZoneId.systemDefault());
		return new ReadOnlyObjectWrapper<ZonedDateTime>(cellData.getValue().getEntryDate().toInstant().atZone(ZoneId.systemDefault()));
	}
}