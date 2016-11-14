/**
 * Copyright (c) 2016 by amphorainc.com. All rights reserved.
 * created on Nov 10, 2016
 */
package com.tc.app.exchangemonitor.controller;

import java.util.Objects;

import com.tc.app.exchangemonitor.model.ExternalTradeSource;

import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * @author Saravana Kumar M
 */
public class ExternalTradeSourceRadioCellForPositionsTab extends ListCell<ExternalTradeSource>
{
	private final RadioButton radioButton = new RadioButton();
	//private static final ToggleGroup toggleGroup = new ToggleGroup();
	public static final ToggleGroup toggleGroup = new ToggleGroup();
	private static String selectedRadioButtonName;

	public ExternalTradeSourceRadioCellForPositionsTab()
	{
		this.radioButton.setToggleGroup(toggleGroup);
		this.radioButton.selectedProperty().addListener((observableValue, wasSelected, isSelected) -> {
			if(isSelected)
			{
				selectedRadioButtonName = this.getItem().getExternalTradeSrcName();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @see javafx.scene.control.Cell#updateItem(java.lang.Object, boolean)
	 */
	@Override
	protected void updateItem(final ExternalTradeSource item, final boolean empty)
	{
		super.updateItem(item, empty);
		if(empty || (item == null))
		{
			this.setText(null);
			this.setGraphic(null);
		}
		else
		{
			this.radioButton.setText(item.getExternalTradeSrcName());
			this.radioButton.setSelected(Objects.equals(item.getExternalTradeSrcName(), selectedRadioButtonName));
			this.setGraphic(this.radioButton);
		}
	}
}