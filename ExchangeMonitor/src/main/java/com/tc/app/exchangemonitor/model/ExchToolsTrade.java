package com.tc.app.exchangemonitor.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tc.app.exchangemonitor.entitybusiness.ExchToolsTradeBusiness;

@Entity
@Table(name = "exch_tools_trade", catalog = "QA_30_trade_sep12", schema = "dbo")
public class ExchToolsTrade extends ExchToolsTradeBusiness 
{
}