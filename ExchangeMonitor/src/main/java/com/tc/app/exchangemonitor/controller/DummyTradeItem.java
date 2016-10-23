package com.tc.app.exchangemonitor.controller;

public class DummyTradeItem
{
	private Number tradeNum;
	private Number orderNum;
	private Number itemNum;
	private Character itemType;
	private Character psInd;
    private String cmdtyCode;
    private Number bookingCompNum;
    private Number avgPrice;
    private String priceUomCode;
    private String priceCurrCode;
    private String contrQtyUomCode;
    private String titleMktCode;
    private String riskMktCode;
    private String tradingPrd;
	
    public DummyTradeItem()
	{
	}
	
    public Number getTradeNum()
	{
		return tradeNum;
	}

	public void setTradeNum(Number tradeNum)
	{
		this.tradeNum = tradeNum;
	}

	public Number getOrderNum()
	{
		return orderNum;
	}

	public void setOrderNum(Number orderNum)
	{
		this.orderNum = orderNum;
	}

	public Number getItemNum()
	{
		return itemNum;
	}

	public void setItemNum(Number itemNum)
	{
		this.itemNum = itemNum;
	}

	public Character getItemType()
	{
		return itemType;
	}

	public void setItemType(Character itemType)
	{
		this.itemType = itemType;
	}

	public Character getpsInd()
	{
		return psInd;
	}

	public void setpSInd(Character psInd)
	{
		this.psInd = psInd;
	}

	public String getCmdtyCode()
	{
		return cmdtyCode;
	}

	public void setCmdtyCode(String cmdtyCode)
	{
		this.cmdtyCode = cmdtyCode;
	}

	public Number getBookingCompNum()
	{
		return bookingCompNum;
	}

	public void setBookingCompNum(Number bookingCompNum)
	{
		this.bookingCompNum = bookingCompNum;
	}

	public Number getAvgPrice()
	{
		return avgPrice;
	}

	public void setAvgPrice(Number avgPrice)
	{
		this.avgPrice = avgPrice;
	}

	public String getPriceUomCode()
	{
		return priceUomCode;
	}

	public void setPriceUomCode(String priceUomCode)
	{
		this.priceUomCode = priceUomCode;
	}

	public String getPriceCurrCode()
	{
		return priceCurrCode;
	}

	public void setPriceCurrCode(String priceCurrCode)
	{
		this.priceCurrCode = priceCurrCode;
	}

	public String getContrQtyUomCode()
	{
		return contrQtyUomCode;
	}

	public void setContrQtyUomCode(String contrQtyUomCode)
	{
		this.contrQtyUomCode = contrQtyUomCode;
	}

	public String getTitleMktCode()
	{
		return titleMktCode;
	}

	public void setTitleMktCode(String titleMktCode)
	{
		this.titleMktCode = titleMktCode;
	}

	public String getRiskMktCode()
	{
		return riskMktCode;
	}

	public void setRiskMktCode(String riskMktCode)
	{
		this.riskMktCode = riskMktCode;
	}

	public String getTradingPrd()
	{
		return tradingPrd;
	}

	public void setTradingPrd(String tradingPrd)
	{
		this.tradingPrd = tradingPrd;
	}
}