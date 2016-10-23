package com.tc.app.exchangemonitor.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tc.app.exchangemonitor.entitybusiness.ExternalMappingBusiness;

@Entity
@Table(name = "external_mapping", catalog = "QA_30_trade_sep12", schema = "dbo")
public class ExternalMapping extends ExternalMappingBusiness 
{
	public ExternalMapping()
	{
	}
	
	public ExternalMapping(String anyString)
	{
		this.externalValue1 = anyString;
	}
}