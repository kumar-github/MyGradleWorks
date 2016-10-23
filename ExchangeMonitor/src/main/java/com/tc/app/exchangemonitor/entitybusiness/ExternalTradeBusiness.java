package com.tc.app.exchangemonitor.entitybusiness;

import com.tc.app.exchangemonitor.entity.ExternalTradeEntity;

@SuppressWarnings("serial")
public class ExternalTradeBusiness extends ExternalTradeEntity
{
	public String getCommodity()
	{
		return this.getExchToolsTrade().getCommodity();
	}

	/* so sad. PropertyValueFactory is looking exactly for the below format. */
	public String get_tradingPeriod()
	{
		return this.getExchToolsTrade().getTradingPeriod();
	}

	public String get_callPut()
	{
		return this.getExchToolsTrade().getCallPut();
	}

	public Double get_strikePrice()
	{
		return this.getExchToolsTrade().getStrikePrice();
	}

	public Double get_quantity()
	{
		return this.getExchToolsTrade().getQuantity();
	}

	public Double get_price()
	{
		return this.getExchToolsTrade().getPrice();
	}

	public String get_exchToolsTradeNum()
	{
		return this.getExchToolsTrade().getExchToolsTradeNum();
	}

	public String get_ictsTradeNum()
	{
		return this.getTradeNum() != null ? this.getTradeNum().toString() : null;
	}

	public String get_ictsPortNum()
	{
		return this.getPortNum() != null ? this.getPortNum().toString() : null;
	}

	public String get_buyingCompany()
	{
		if(this.getExchToolsTrade().getInputAction().trim().equals("BUY"))
			return this.getExchToolsTrade().getInputCompany();
		return this.getExchToolsTrade().getAcceptedCompany();
	}

	public String get_sellingCompany()
	{
		if(this.getExchToolsTrade().getInputAction().trim().equals("BUY"))
			return this.getExchToolsTrade().getAcceptedCompany();
		return this.getExchToolsTrade().getInputCompany();
	}

	public String get_buyingTrader()
	{
		if(this.getExchToolsTrade().getInputAction().trim().equals("BUY"))
			return this.getExchToolsTrade().getInputTrader();
		return this.getExchToolsTrade().getAcceptedTrader();
	}

	public String get_sellingTrader()
	{
		if(this.getExchToolsTrade().getInputAction().trim().equals("BUY"))
			return this.getExchToolsTrade().getAcceptedTrader();
		return this.getExchToolsTrade().getInputTrader();
	}

	public String get_inputBroker()
	{
		if(this.getExchToolsTrade().getInputAction().trim().equals("BUY"))
			return this.getExchToolsTrade().getInputBroker();
		return this.getExchToolsTrade().getAcceptedBroker();
	}

	public String get_buyerClearingBroker()
	{
		return this.getExchToolsTrade().getBuyerClrngBroker();
	}

	public String get_sellerClearingBroker()
	{
		return this.getExchToolsTrade().getSellerClrngBroker();
	}

	public String get_buyerAccount()
	{
		return this.getExchToolsTrade().getBuyerAccount();
	}

	public String get_tradeType()
	{
		return this.getExchToolsTrade().getTradeType();
	}

	public String get_comment()
	{
		return this.getExternalCommentOid() != null ? this.getExternalCommentOid().getCommentText() : null;
	}
}