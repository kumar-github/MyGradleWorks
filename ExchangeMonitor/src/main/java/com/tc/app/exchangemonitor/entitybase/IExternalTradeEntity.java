package com.tc.app.exchangemonitor.entitybase;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.tc.app.exchangemonitor.model.ExchToolsTrade;
import com.tc.app.exchangemonitor.model.ExternalComment;
import com.tc.app.exchangemonitor.model.ExternalTradeSource;
import com.tc.app.exchangemonitor.model.ExternalTradeState;
import com.tc.app.exchangemonitor.model.ExternalTradeStatus;
import com.tc.app.exchangemonitor.model.ExternalTradeSystem;

public interface IExternalTradeEntity extends Serializable
{
	public abstract Integer getOid();
	public abstract void setOid(Integer oid);
	public abstract Date getEntryDate();
	public abstract void setEntryDate(Date entryDate);
	public abstract Integer getPortNum();
	public abstract void setPortNum(Integer portNum);
	public abstract Integer getTradeNum();
	public abstract void setTradeNum(Integer tradeNum);
	public abstract int getTransId();
	public abstract void setTransId(int transId);
	public abstract BigDecimal getSequence();
	public abstract void setSequence(BigDecimal sequence);
	public abstract ExternalComment getExternalCommentOid();
	public abstract void setExternalCommentOid(ExternalComment externalCommentOid);
	public abstract Integer getInhousePortNum();
	public abstract void setInhousePortNum(Integer inhousePortNum);
	public abstract Short getOrderNum();
	public abstract void setOrderNum(Short orderNum);
	public abstract Short getItemNum();
	public abstract void setItemNum(Short itemNum);
	public abstract ExchToolsTrade getExchToolsTrade();
	public abstract void setExchToolsTrade(ExchToolsTrade exchToolsTrade);
	public abstract Integer getExtPosNum();
	public abstract void setExtPosNum(Integer extPosNum);
	public abstract ExternalTradeSource getExternalTradeSourceOid();
	public abstract void setExternalTradeSourceOid(ExternalTradeSource externalTradeSourceOid);
	public abstract ExternalTradeState getExternalTradeStateOid();
	public abstract void setExternalTradeStateOid(ExternalTradeState externalTradeStateOid);
	public abstract ExternalTradeStatus getExternalTradeStatusOid();
	public abstract void setExternalTradeStatusOid(ExternalTradeStatus externalTradeStatusOid);
	public abstract ExternalTradeSystem getExternalTradeSystemOid();
	public abstract void setExternalTradeSystemOid(ExternalTradeSystem externalTradeSystemOid);
	//public abstract String getCommodity();
}