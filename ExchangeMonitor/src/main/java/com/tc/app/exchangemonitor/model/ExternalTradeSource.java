package com.tc.app.exchangemonitor.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tc.app.exchangemonitor.entitybusiness.ExternalTradeSourceBusiness;

@Entity
@Table(name = "external_trade_source", catalog = "QA_30_trade_sep12", schema = "dbo")
public class ExternalTradeSource extends ExternalTradeSourceBusiness
{
	public ExternalTradeSource()
	{
	}
}