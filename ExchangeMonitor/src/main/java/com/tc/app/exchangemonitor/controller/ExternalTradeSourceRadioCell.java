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
public class ExternalTradeSourceRadioCell extends ListCell<ExternalTradeSource>
{
	private final RadioButton radioButton = new RadioButton();
	private static final ToggleGroup toggleGroup = new ToggleGroup();
	private static String selectedRadioButtonName;

	public ExternalTradeSourceRadioCell()
	{
		radioButton.setToggleGroup(toggleGroup);
		radioButton.selectedProperty().addListener((observableValue, wasSelected, isSelected) -> {
			if(isSelected)
			{
				selectedRadioButtonName = getItem().getExternalTradeSrcName();
			}
		});
	}

	/* (non-Javadoc)
	 * @see javafx.scene.control.Cell#updateItem(java.lang.Object, boolean)
	 */
	@Override
	protected void updateItem(ExternalTradeSource item, boolean empty)
	{
		super.updateItem(item, empty);
		if(empty || item == null)
		{
			setText(null);
			setGraphic(null);
		}
		else
		{
			radioButton.setText(item.getExternalTradeSrcName());
			radioButton.setSelected(Objects.equals(item.getExternalTradeSrcName(), selectedRadioButtonName));
			setGraphic(radioButton);
		}
	}
}