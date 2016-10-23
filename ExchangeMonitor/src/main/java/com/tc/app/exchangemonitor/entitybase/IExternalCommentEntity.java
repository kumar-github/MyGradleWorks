package com.tc.app.exchangemonitor.entitybase;

import java.io.Serializable;
import java.util.Collection;

import com.tc.app.exchangemonitor.model.ExchToolsTrade;

public interface IExternalCommentEntity extends Serializable
{
	public abstract Integer getOid();
	public abstract void setOid(Integer oid);
	public abstract String getCommentText();
	public abstract void setCommentText(String commentText);
	public abstract int getTransId();
	public abstract void setTransId(int transId);
	public abstract Collection<ExchToolsTrade> getExchToolsTradeCollection();
	public abstract void setExchToolsTradeCollection(Collection<ExchToolsTrade> exchToolsTradeCollection);
}