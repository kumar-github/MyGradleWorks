package com.tc.app.exchangemonitor.model;

import java.util.stream.Collectors;

import application.LineItemTable.LineItem;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

// Model class. Note this is carefully created to allow subclassing for the "total" line.
// To do this, we want to allow nullable values for quantity and unit price (as they make
// no sense in the total; hence we use ObjectProperty<Integer> instead of IntegerProperty.
// Note we also allow the xxxProperty() methods to be overriden, but not the getXxx and 
// setXxx. This still enforces the xxxProperty().get() == getXxx() rule (etc), while allowing
// us to replace the total property in a subclass.
public class PositionLineItem
{
	private final StringProperty name = new SimpleStringProperty();
	private final ObjectProperty<Integer> quantity = new SimpleObjectProperty<>();
	private final ObjectProperty<Double> unitPrice = new SimpleObjectProperty<>();
	private final ReadOnlyObjectWrapper<Double> total = new ReadOnlyObjectWrapper<>();

	public PositionLineItem(String name, Integer quantity, Double unitPrice) {
		setName(name);
		setQuantity(quantity);
		setUnitPrice(unitPrice);

		// Obvious binding for the total of this line item: 
		// total = quantity * unit price
		total.bind(Bindings.createObjectBinding(() -> {
			if (quantityProperty().get() == null
					|| unitPriceProperty().get() == null) {
				return 0.0;
			}
			return quantityProperty().get() * unitPriceProperty().get();
		}, quantityProperty(), unitPriceProperty()));
	}

	public ObjectProperty<Integer> quantityProperty() {
		return this.quantity;
	}

	public final Integer getQuantity() {
		return this.quantityProperty().get();
	}

	public final void setQuantity(final Integer quantity) {
		this.quantityProperty().set(quantity);
	}

	public ObjectProperty<Double> unitPriceProperty() {
		return this.unitPrice;
	}

	public final Double getUnitPrice() {
		return this.unitPriceProperty().get();
	}

	public final void setUnitPrice(final Double unitPrice) {
		this.unitPriceProperty().set(unitPrice);
	}

	public ReadOnlyObjectProperty<Double> totalProperty() {
		return this.total.getReadOnlyProperty();
	}

	public final java.lang.Double getTotal() {
		return this.totalProperty().get();
	}

	public final StringProperty nameProperty() {
		return this.name;
	}

	public final String getName() {
		return this.nameProperty().get();
	}

	public final void setName(final String name) {
		this.nameProperty().set(name);
	}
}

// Special subclass to represent the total of all the line items.
// Just sets quantity and unit price to null.
// Overrides totalProperty() to return our own property, that is bound to
// the data list.
class TotalPositionLineItem extends PositionLineItem
{

	private final ReadOnlyObjectWrapper<Double> total = new ReadOnlyObjectWrapper<>();

	public TotalPositionLineItem(ObservableList<? extends PositionLineItem> items)
	{
		super("Total", null, null);

		// Bind total to the sum of the totals of all the other line items:
		total.bind(Bindings.createObjectBinding(() -> items.stream()
				.collect(Collectors.summingDouble(PositionLineItem::getTotal)),
				items));
	}

	@Override
	public ReadOnlyObjectProperty<Double> totalProperty() {
		return total;
	}
}