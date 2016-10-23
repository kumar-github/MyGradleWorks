package com.tc.app.exchangemonitor.entitybase;

import java.io.Serializable;
import java.util.Collection;

import com.tc.app.exchangemonitor.model.ExternalMapping;
import com.tc.app.exchangemonitor.model.ExternalTrade;
import com.tc.app.exchangemonitor.model.ExternalTradeSystem;
import com.tc.app.exchangemonitor.model.ExternalTradeType;

public interface IExternalTradeSourceEntity extends Serializable
{
	public abstract Integer getOid();
	public abstract void setOid(Integer oid);
	public abstract String getExternalTradeSrcName();
	public abstract void setExternalTradeSrcName(String externalTradeSrcName);
	public abstract int getTransId();
	public abstract void setTransId(int transId);
	public abstract String getAliasSourceCode();
	public abstract void setAliasSourceCode(String aliasSourceCode);
	public abstract ExternalTradeSystem getExternalTradeSystemOid();
	public abstract void setExternalTradeSystemOid(ExternalTradeSystem externalTradeSystemOid);
	public abstract Collection<ExternalTrade> getExternalTradeCollection();
	public abstract void setExternalTradeCollection(Collection<ExternalTrade> externalTradeCollection);
	public abstract Collection<ExternalTradeType> getExternalTradeTypeCollection();
	public abstract void setExternalTradeTypeCollection(Collection<ExternalTradeType> externalTradeTypeCollection);
	public abstract Collection<ExternalMapping> getExternalMappingCollection();
	public abstract void setExternalMappingCollection(Collection<ExternalMapping> externalMappingCollection);
}