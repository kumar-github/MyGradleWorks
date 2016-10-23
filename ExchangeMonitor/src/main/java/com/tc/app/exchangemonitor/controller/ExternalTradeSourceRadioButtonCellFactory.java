package com.tc.app.exchangemonitor.controller;

import com.tc.app.exchangemonitor.entitybase.IExternalTradeSourceEntity;
import com.tc.app.exchangemonitor.model.ExternalTradeSource;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.util.Callback;

public class ExternalTradeSourceRadioButtonCellFactory implements Callback<ListView<ExternalTradeSource>, ListCell<ExternalTradeSource>>
//public class ExternalTradeSourceRadioButtonCellFactory implements Callback<ListView<IExternalTradeSourceEntity>, ListCell<IExternalTradeSourceEntity>>
{
	private static final ToggleGroup toggleGroup = new ToggleGroup();
	
	@Override
	public ListCell<ExternalTradeSource> call(ListView<ExternalTradeSource> param)
	//public ListCell<IExternalTradeSourceEntity> call(ListView<IExternalTradeSourceEntity> param)
	{
		final ListCell<ExternalTradeSource> cell = new ListCell<ExternalTradeSource>(){
		//final ListCell<IExternalTradeSourceEntity> cell = new ListCell<IExternalTradeSourceEntity>(){
			@Override
			//protected void updateItem(IExternalTradeSourceEntity item, boolean empty)
			protected void updateItem(ExternalTradeSource item, boolean empty)
			{
				super.updateItem(item, empty);
				if(item == null || empty)
				{
					this.setText(null);
					this.setGraphic(null);
				}
				else
				{
					RadioButton radioButton = new RadioButton(item.getExternalTradeSrcName());
					radioButton.setToggleGroup(toggleGroup);
					this.setGraphic(radioButton);
					//Add Listeners if any
					/*toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
						@Override
						public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue)
						{
							if (toggleGroup.getSelectedToggle() != null)
							{
								System.out.println(toggleGroup.getSelectedToggle().getUserData());
							}
						}
					});*/
					//this.setText(item.getExternalTradeSrcName());
				}
			}
		};
		return cell;
	}
}