package com.tc.app.exchangemonitor.entitybase;

import java.io.Serializable;

import com.tc.app.exchangemonitor.model.ExternalTradeSource;

public interface IExternalMappingEntity extends Serializable
{
	public abstract Integer getOid();
	public abstract void setOid(Integer oid);
	public abstract Character getMappingType();
	public abstract void setMappingType(Character mappingType);
	public abstract String getExternalValue1();
	public abstract void setExternalValue1(String externalValue1);
	public abstract String getExternalValue2();
	public abstract void setExternalValue2(String externalValue2);
	public abstract String getExternalValue3();
	public abstract void setExternalValue3(String externalValue3);
	public abstract String getExternalValue4();
	public abstract void setExternalValue4(String externalValue4);
	public abstract String getAliasValue();
	public abstract void setAliasValue(String aliasValue);
	public abstract int getTransId();
	public abstract void setTransId(int transId);
	public abstract ExternalTradeSource getExternalTradeSourceOid();
	public abstract void setExternalTradeSourceOid(ExternalTradeSource externalTradeSourceOid);
}