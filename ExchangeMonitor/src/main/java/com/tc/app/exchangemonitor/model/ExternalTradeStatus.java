package com.tc.app.exchangemonitor.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tc.app.exchangemonitor.entitybusiness.ExternalTradeStatusBusiness;

@Entity
@Table(name = "external_trade_status", catalog = "QA_30_trade_sep12", schema = "dbo")
public class ExternalTradeStatus extends ExternalTradeStatusBusiness 
{
}