package com.tc.app.exchangemonitor.controller;

import java.util.Date;

public class DummySettlePrice
{
	private Integer tradeNum;
	private int orderNum;
	private int itemNum;
	private int itemFillNum;
	private Date fillDate;
	private String cmdtyCode;
	private String riskMktCode;
	private String tradingPrd;
	private char psInd;
	private Double fillQty;
	private Double fillPrice;
	private String firstName;
	private String lastName;
	private Integer realPortNum;
	private String portFullName;
	private String priceSourceCode;
	private String orderInstrCode;
	private Double orderPrice;
	private Integer cmntNum;
	private String externalTradeNum;
	private Integer commktKey;
	
	public DummySettlePrice()
	{
	}
	
	public Integer getTradeNum()
	{
		return tradeNum;
	}
	
	public void setTradeNum(Integer tradeNum)
	{
		this.tradeNum = tradeNum;
	}

	public int getOrderNum()
	{
		return orderNum;
	}

	public void setOrderNum(int orderNum)
	{
		this.orderNum = orderNum;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	public int getItemFillNum() {
		return itemFillNum;
	}

	public void setItemFillNum(int itemFillNum) {
		this.itemFillNum = itemFillNum;
	}

	public Date getFillDate() {
		return fillDate;
	}

	public void setFillDate(Date fillDate) {
		this.fillDate = fillDate;
	}

	public String getCmdtyCode() {
		return cmdtyCode;
	}

	public void setCmdtyCode(String cmdtyCode) {
		this.cmdtyCode = cmdtyCode;
	}

	public String getRiskMktCode() {
		return riskMktCode;
	}

	public void setRiskMktCode(String riskMktCode) {
		this.riskMktCode = riskMktCode;
	}

	public String getTradingPrd() {
		return tradingPrd;
	}

	public void setTradingPrd(String tradingPrd) {
		this.tradingPrd = tradingPrd;
	}

	public char getPsInd() {
		return psInd;
	}

	public void setPsInd(char psInd) {
		this.psInd = psInd;
	}

	public Double getFillQty() {
		return fillQty;
	}

	public void setFillQty(Double fillQty) {
		this.fillQty = fillQty;
	}

	public Double getFillPrice() {
		return fillPrice;
	}

	public void setFillPrice(Double fillPrice) {
		this.fillPrice = fillPrice;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getRealPortNum() {
		return realPortNum;
	}

	public void setRealPortNum(Integer realPortNum) {
		this.realPortNum = realPortNum;
	}

	public String getPortFullName() {
		return portFullName;
	}

	public void setPortFullName(String portFullName) {
		this.portFullName = portFullName;
	}

	public String getPriceSourceCode() {
		return priceSourceCode;
	}

	public void setPriceSourceCode(String priceSourceCode) {
		this.priceSourceCode = priceSourceCode;
	}

	public String getOrderInstrCode() {
		return orderInstrCode;
	}

	public void setOrderInstrCode(String orderInstrCode) {
		this.orderInstrCode = orderInstrCode;
	}

	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Integer getCmntNum() {
		return cmntNum;
	}

	public void setCmntNum(Integer cmntNum) {
		this.cmntNum = cmntNum;
	}

	public String getExternalTradeNum() {
		return externalTradeNum;
	}

	public void setExternalTradeNum(String externalTradeNum) {
		this.externalTradeNum = externalTradeNum;
	}

	public Integer getCommktKey() {
		return commktKey;
	}

	public void setCommktKey(Integer commktKey) {
		this.commktKey = commktKey;
	}
	
	public String getTradeOrderItemFillNumString()
	{
		return this.getTradeNum() + "/" + this.getOrderNum() + "/" + this.getItemNum() + "/" + this.getItemFillNum(); 
	}
	
	public String getTraderFullName()
	{
		return this.getFirstName() + " " + this.getLastName(); 
	}
	
	public String getBuySellString()
	{
		return this.getPsInd() == 'P' ? "BUY" : "SELL";
	}
	
	public String getPortNumNameString()
	{
		return this.getRealPortNum() + "-" + this.getPortFullName();
	}
}