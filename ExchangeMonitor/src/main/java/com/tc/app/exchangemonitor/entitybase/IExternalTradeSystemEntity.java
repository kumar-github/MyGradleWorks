package com.tc.app.exchangemonitor.entitybase;

import java.io.Serializable;
import java.util.Collection;

import com.tc.app.exchangemonitor.model.ExternalTrade;
import com.tc.app.exchangemonitor.model.ExternalTradeSource;

public interface IExternalTradeSystemEntity extends Serializable
{
	public abstract Integer getOid();
	public abstract void setOid(Integer oid);
	public abstract String getExternalTradeSystemName();
	public abstract void setExternalTradeSystemName(String externalTradeSystemName);
	public abstract int getTransId();
	public abstract void setTransId(int transId);
	public abstract Collection<ExternalTradeSource> getExternalTradeSourceCollection();
	public abstract void setExternalTradeSourceCollection(Collection<ExternalTradeSource> externalTradeSourceCollection);
	public abstract Collection<ExternalTrade> getExternalTradeCollection();
	public abstract void setExternalTradeCollection(Collection<ExternalTrade> externalTradeCollection);
}