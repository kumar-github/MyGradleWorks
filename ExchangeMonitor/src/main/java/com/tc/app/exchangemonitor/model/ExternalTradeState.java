package com.tc.app.exchangemonitor.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tc.app.exchangemonitor.entitybusiness.ExternalTradeStateBusiness;

@Entity
@Table(name = "external_trade_state", catalog = "QA_30_trade_sep12", schema = "dbo")
public class ExternalTradeState extends ExternalTradeStateBusiness
{
}