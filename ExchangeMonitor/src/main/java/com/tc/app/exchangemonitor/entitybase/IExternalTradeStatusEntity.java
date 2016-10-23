package com.tc.app.exchangemonitor.entitybase;

import java.io.Serializable;
import java.util.Collection;

import com.tc.app.exchangemonitor.model.ExternalTrade;

public interface IExternalTradeStatusEntity extends Serializable
{
	public abstract Integer getOid();
	public abstract void setOid(Integer oid);
	public abstract String getExternalTradeStatusName();
	public abstract void setExternalTradeStatusName(String externalTradeStatusName);
	public abstract int getTransId();
	public abstract void setTransId(int transId);
	public abstract Collection<ExternalTrade> getExternalTradeCollection();
	public abstract void setExternalTradeCollection(Collection<ExternalTrade> externalTradeCollection);
}