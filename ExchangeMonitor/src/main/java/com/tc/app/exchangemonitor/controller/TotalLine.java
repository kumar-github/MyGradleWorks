package com.tc.app.exchangemonitor.controller;

import java.util.stream.Collectors;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;

//public static class TotalLine extends DummyPosition
public class TotalLine extends DummyPosition
{
	private final ReadOnlyObjectWrapper<Double> total = new ReadOnlyObjectWrapper<>();
	private final ReadOnlyObjectWrapper<Double> buyPosition = new ReadOnlyObjectWrapper<>();
	private final ReadOnlyObjectWrapper<Double> sellPosition = new ReadOnlyObjectWrapper<>();
	private final ReadOnlyObjectWrapper<Double> netQuantity = new ReadOnlyObjectWrapper<>();

	public TotalLine(ObservableList<? extends DummyPosition> items)
	{
		super("Total", null, null, null);

		// Bind total to the sum of the totals of all the other line items:
		//total.bind(Bindings.createObjectBinding(() -> items.stream().collect(Collectors.summingDouble(DummyPosition::getTotal)), items));
		total.bind(Bindings.createObjectBinding(() -> items.stream().collect(Collectors.summingDouble(DummyPosition::getTotal)), items));
		buyPosition.bind(Bindings.createObjectBinding(() -> items.stream().collect(Collectors.summingDouble(DummyPosition::getBuyPosition)), items));
		sellPosition.bind(Bindings.createObjectBinding(() -> items.stream().collect(Collectors.summingDouble(DummyPosition::getSellPosition)), items));
		netQuantity.bind(Bindings.createObjectBinding(() -> items.stream().collect(Collectors.summingDouble(DummyPosition::getNetQuantity)), items));
	}

	@Override
	public ReadOnlyObjectProperty<Double> totalProperty()
	{
		return total;
	}
	
	@Override
	public ReadOnlyObjectProperty<Double> buyPositionProperty()
	{
		return buyPosition;
	}
	
	@Override
	public ReadOnlyObjectProperty<Double> sellPositionProperty()
	{
		return sellPosition;
	}
	
	@Override
	public ReadOnlyObjectProperty<Double> netQuantityProperty()
	{
		return netQuantity;
	}
}