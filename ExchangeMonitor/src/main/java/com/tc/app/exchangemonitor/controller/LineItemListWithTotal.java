package com.tc.app.exchangemonitor.controller;

import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.collections.transformation.TransformationList;

public class LineItemListWithTotal extends TransformationList<DummyPosition, DummyPosition>
{
	private final TotalLine totalLine;

	protected LineItemListWithTotal(ObservableList<? extends DummyPosition> source)
	{
		super(source);
		totalLine = new TotalLine(source);
	}

	@Override
	protected void sourceChanged(Change<? extends DummyPosition> c)
	{

		// no need to modify change:
		// indexes generated by the source list will match indexes in this
		// list

		fireChange(c);
	}

	// if index is in range for source list, just return that index
	// otherwise return -1, indicating index is not represented in source
	@Override
	public int getSourceIndex(int index)
	{
		if(index < getSource().size()){ return index; }
		return -1;
	}

	// if index is in range for source list, return corresponding
	// item from source list.
	// if index is one after the last element in the source list,
	// return total line.
	@Override
	public DummyPosition get(int index)
	{
		if(index < getSource().size())
		{
			return getSource().get(index);
		}
		else if(index == getSource().size())
		{
			return totalLine;
		}
		else
			throw new ArrayIndexOutOfBoundsException(index);
	}

	// size of transformation list is one bigger than size of source list:
	@Override
	public int size()
	{
		return getSource().size() + 1;
	}
}