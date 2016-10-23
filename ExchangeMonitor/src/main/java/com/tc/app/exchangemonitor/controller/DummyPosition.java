package com.tc.app.exchangemonitor.controller;

import java.util.Date;
import java.util.Objects;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;

public class DummyPosition
{
	private String externalTradeStateName;
	private Date creationDate;
	private Date entryDate;
	private String exchToolsTradeNum;
	private String commodity;
	private String tradingPeriod;
	private String callPut;
	private Double strikePrice;
	private Double quantity;
	private Double price;
	private String inputAction;
	private String inputCompany;
	private String acceptedAction;
	private String acceptedCompany;
	private String buyerAccount;

	public DummyPosition()
	{
	}

	public DummyPosition(String commodity, String tradingPeriod, String callPut, Double strikePrice)
	{
		this.commodity = commodity;
		this.tradingPeriod = tradingPeriod;
		this.callPut = callPut;
		this.strikePrice = strikePrice;
	}

	public String getExternalTradeStateName()
	{
		return externalTradeStateName;
	}

	public void setExternalTradeStateName(String externalTradeStateName)
	{
		this.externalTradeStateName = externalTradeStateName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getExchToolsTradeNum() {
		return exchToolsTradeNum;
	}

	public void setExchToolsTradeNum(String exchToolsTradeNum) {
		this.exchToolsTradeNum = exchToolsTradeNum;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getTradingPeriod() {
		return tradingPeriod;
	}

	public void setTradingPeriod(String tradingPeriod) {
		this.tradingPeriod = tradingPeriod;
	}

	public String getCallPut()
	{
		return callPut;
	}

	public void setCallPut(String callPut)
	{
		//this.callPut = callPut;
		//if(callPut == null)
		this.callPut = (callPut == null) ? "" : callPut;
	}

	public Double getStrikePrice()
	{
		return strikePrice;
	}

	public void setStrikePrice(Double strikePrice)
	{
		//this.strikePrice = strikePrice;
		this.strikePrice = (strikePrice == null) ? 0.0 : strikePrice;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getInputAction() {
		return inputAction;
	}

	public void setInputAction(String inputAction) {
		this.inputAction = inputAction;
	}

	public String getInputCompany() {
		return inputCompany;
	}

	public void setInputCompany(String inputCompany) {
		this.inputCompany = inputCompany;
	}

	public String getAcceptedAction() {
		return acceptedAction;
	}

	public void setAcceptedAction(String acceptedAction) {
		this.acceptedAction = acceptedAction;
	}

	public String getAcceptedCompany() {
		return acceptedCompany;
	}

	public void setAcceptedCompany(String acceptedCompany) {
		this.acceptedCompany = acceptedCompany;
	}

	public String getBuyerAccount() {
		return buyerAccount;
	}

	public void setBuyerAccount(String buyerAccount)
	{
		this.buyerAccount = buyerAccount;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;

		final DummyPosition other = (DummyPosition)obj;
		return Objects.equals(this.exchToolsTradeNum, other.exchToolsTradeNum) &&
				Objects.equals(this.commodity, other.commodity) &&
				Objects.equals(this.externalTradeStateName, other.externalTradeStateName) &&
				Objects.equals(this.buyerAccount, other.buyerAccount) &&
				Objects.equals(this.creationDate, other.creationDate);
		//(this.creationDate.compareTo(other.creationDate) == 0);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(this.exchToolsTradeNum, this.commodity, this.externalTradeStateName, this.buyerAccount, this.creationDate);
	}

	/* ====================================================================== */
	/* ========================TEMP VARIABLES =============================== */
	/* ====================================================================== */

	private String buySell;
	public String getBuySell()
	{
		return buySell;
	}

	public void setBuySell(String buySell)
	{
		this.buySell = buySell.trim();
	}

	/* Bought */
	/*private Double buyPosition;
	public Double getBuyPosition()
	{
		return buyPosition != null ? buyPosition : 0.0;
	}

	public void setBuyPosition(Double buyPosition)
	{
		this.buyPosition = buyPosition;
	}*/

	private Double buyPositionPrice;
	public Double getBuyPositionPrice()
	{
		return buyPositionPrice != null ? buyPositionPrice : 0.0;
	}

	public void setBuyPositionPrice(Double buyPositionPrice)
	{
		this.buyPositionPrice = buyPositionPrice;
	}

	private Double buyPositionValue;
	public Double getBuyPositionValue()
	{
		return buyPositionValue != null ? buyPositionValue : 0.0;
	}

	public void setBuyPositionValue(Double buyPositionValue)
	{
		this.buyPositionValue = buyPositionValue;
	}

	/* Sold */
	/*
	private Double sellPosition;
	public Double getSellPosition()
	{
		return sellPosition != null ? sellPosition : 0.0;
	}

	public void setSellPosition(Double sellPosition)
	{
		this.sellPosition = sellPosition;
	}*/

	private Double sellPositionPrice;
	public Double getSellPositionPrice()
	{
		return sellPositionPrice != null ? sellPositionPrice : 0.0;
	}

	public void setSellPositionPrice(Double sellPositionPrice)
	{
		this.sellPositionPrice = sellPositionPrice;
	}

	private Double sellPositionValue;
	public Double getSellPositionValue()
	{
		return sellPositionValue != null ? sellPositionValue : 0.0;
	}

	public void setSellPositionValue(Double sellPositionValue)
	{
		this.sellPositionValue = sellPositionValue;
	}

	private Double averageBuyPrice;
	public Double getAverageBuyPrice()
	{
		return averageBuyPrice;
	}

	public void setAverageBuyPrice(Double averageBuyPrice)
	{
		this.averageBuyPrice = averageBuyPrice;
	}

	private Double averageSellPrice;
	public Double getAverageSellPrice()
	{
		return averageSellPrice;
	}

	public void setAverageSellPrice(Double averageSellPrice)
	{
		this.averageSellPrice = averageSellPrice;
	}

	private Double lastPrice;
	public Double getLastPrice()
	{
		return lastPrice;
	}

	public void setLastPrice(Double lastPrice)
	{
		//this.lastPrice = lastPrice;
		if(this.lastPrice == null)
			this.lastPrice = lastPrice;
	}

	/*
	private Double netQuantity;
	public Double getNetQuantity()
	{
		return netQuantity;
	}

	public void setNetQuantity(Double netQuantity)
	{
		this.netQuantity = netQuantity;
	}
	*/

	/*
	private Double pl;
	public long getPL()
	{
		return Math.round(pl * 1000);
	}

	public void setPL(Double pl)
	{
		this.pl = pl;
	}
	 */

	/*
	private final ReadOnlyObjectWrapper<Double> total = new ReadOnlyObjectWrapper<>();
	public ReadOnlyObjectProperty<Double> totalProperty()
	{
		return this.total.getReadOnlyProperty();
	}

	public final java.lang.Double getTotal()
	{
		return this.totalProperty().get();
	}
	 */

	private final ReadOnlyObjectWrapper<Double> buyPosition = new ReadOnlyObjectWrapper<>(0.0);
	public ReadOnlyObjectProperty<Double> buyPositionProperty()
	{
		return this.buyPosition.getReadOnlyProperty();
	}

	public final double getBuyPosition()
	{
		return this.buyPositionProperty().get();
	}

	public void setBuyPosition(Double buyPosition)
	{
		this.buyPosition.set(buyPosition);
	}
	
	private final ReadOnlyObjectWrapper<Double> sellPosition = new ReadOnlyObjectWrapper<>(0.0);
	public ReadOnlyObjectProperty<Double> sellPositionProperty()
	{
		return this.sellPosition.getReadOnlyProperty();
	}

	public final double getSellPosition()
	{
		return this.sellPositionProperty().get();
	}

	public void setSellPosition(Double sellPosition)
	{
		this.sellPosition.set(sellPosition);
	}
	
	private final ReadOnlyObjectWrapper<Double> netQuantity = new ReadOnlyObjectWrapper<>(0.0);
	public ReadOnlyObjectProperty<Double> netQuantityProperty()
	{
		return this.netQuantity.getReadOnlyProperty();
	}

	public final double getNetQuantity()
	{
		return this.netQuantityProperty().get();
	}

	public void setNetQuantity(Double netQuantity)
	{
		this.netQuantity.set(netQuantity);
	}

	private final ReadOnlyObjectWrapper<Double> total = new ReadOnlyObjectWrapper<>();
	public ReadOnlyObjectProperty<Double> totalProperty()
	{
		return this.total.getReadOnlyProperty();
	}

	//public final java.lang.Double getTotal()
	public final double getTotal()
	{
		return this.totalProperty().get();
		//return Math.round(total.get() * 1000);
	}

	public void setTotal(Double total)
	{
		//this.total.set(total * 1000);
		//this.total.set(2.0);
		this.total.set(total);
	}
}

/*
//public static class TotalLine extends DummyPosition
class TotalLine extends DummyPosition
{
	private final ReadOnlyObjectWrapper<Double> total = new ReadOnlyObjectWrapper<>();

	public TotalLine(ObservableList<? extends DummyPosition> items)
	{
		super("Total", null, null, null);

		// Bind total to the sum of the totals of all the other line items:
		//total.bind(Bindings.createObjectBinding(() -> items.stream().collect(Collectors.summingDouble(DummyPosition::getTotal)), items));
		total.bind(Bindings.createObjectBinding(() -> items.stream().collect(Collectors.summingDouble(DummyPosition::getTotal)), items));
	}

	@Override
	public ReadOnlyObjectProperty<Double> totalProperty()
	{
		return total;
	}
}
 */

/*

// TransformationList implementation. This TransformationList just has
// one extra line at the end, displaying the total. We use a subclass of
// LineItem for that line:
class LineItemListWithTotal extends TransformationList<DummyPosition, DummyPosition>
{
	private final TotalLine totalLine;

	protected LineItemListWithTotal(	ObservableList<? extends DummyPosition> source)
	{
		super(source);
		totalLine = new TotalLine(source);
	}

	@Override
	protected void sourceChanged(Change<? extends DummyPosition> c) {

		// no need to modify change:
		// indexes generated by the source list will match indexes in this
		// list

		fireChange(c);
	}

	// if index is in range for source list, just return that index
	// otherwise return -1, indicating index is not represented in source
	@Override
	public int getSourceIndex(int index) {
		if (index < getSource().size()) {
			return index;
		}
		return -1;
	}

	// if index is in range for source list, return corresponding
	// item from source list.
	// if index is one after the last element in the source list,
	// return total line.
	@Override
	public DummyPosition get(int index)
	{
		if (index < getSource().size())
		{
			return getSource().get(index);
		}
		else if (index == getSource().size())
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
 */