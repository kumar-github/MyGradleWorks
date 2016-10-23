package com.tc.app.exchangemonitor.entitybase;

import java.io.Serializable;

import com.tc.app.exchangemonitor.model.ExternalTradeSource;

public interface IExternalTradeTypeEntity extends Serializable
{
	public abstract Integer getOid();
	public abstract void setOid(Integer oid);
	public abstract String getTradeTypeCode();
	public abstract void setTradeTypeCode(String tradeTypeCode);
	public abstract String getTradeTypeName();
	public abstract void setTradeTypeName(String tradeTypeName);
	public abstract int getTransId();
	public abstract void setTransId(int transId);
	public abstract ExternalTradeSource getExternalTradeSourceOid();
	public abstract void setExternalTradeSourceOid(ExternalTradeSource externalTradeSourceOid);
}