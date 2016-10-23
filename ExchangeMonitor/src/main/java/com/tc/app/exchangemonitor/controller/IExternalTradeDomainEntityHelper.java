package com.tc.app.exchangemonitor.controller;

public interface IExternalTradeDomainEntityHelper extends DomainEntity
{
	String externalComment = null;

	default public String getExternalComment()
	{
		return externalComment;
	}
}