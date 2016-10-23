package com.tc.app.exchangemonitor.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tc.app.exchangemonitor.entitybusiness.ExternalTradeSystemBusiness;

@Entity
@Table(name = "external_trade_system", catalog = "QA_30_trade_sep12", schema = "dbo")
public class ExternalTradeSystem extends ExternalTradeSystemBusiness
{
}