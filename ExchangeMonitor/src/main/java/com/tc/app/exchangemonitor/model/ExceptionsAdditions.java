/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "trade_item", catalog = "DEV_CS25_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ExceptionsAdditions.findAll", query = "SELECT e FROM ExceptionsAdditions e"),
	@NamedQuery(name = "ExceptionsAdditions.findByExcpAddnsCode", query = "SELECT e FROM ExceptionsAdditions e WHERE e.excpAddnsCode = :excpAddnsCode"),
	@NamedQuery(name = "ExceptionsAdditions.findByExcpAddnsDesc", query = "SELECT e FROM ExceptionsAdditions e WHERE e.excpAddnsDesc = :excpAddnsDesc"),
	@NamedQuery(name = "ExceptionsAdditions.findByTransId", query = "SELECT e FROM ExceptionsAdditions e WHERE e.transId = :transId")})
public class ExceptionsAdditions implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "excp_addns_code")
	private String excpAddnsCode;
	@Column(name = "excp_addns_desc")
	private String excpAddnsDesc;
	@Basic(optional = false)
	@Column(name = "trans_id")
	private int transId;
	@OneToMany(mappedBy = "excpAddnsCode", fetch = FetchType.LAZY)
	private Collection<TradeItem> tradeItemCollection;

	public ExceptionsAdditions() {
	}

	public ExceptionsAdditions(String excpAddnsCode) {
		this.excpAddnsCode = excpAddnsCode;
	}

	public ExceptionsAdditions(String excpAddnsCode, int transId) {
		this.excpAddnsCode = excpAddnsCode;
		this.transId = transId;
	}

	public String getExcpAddnsCode() {
		return excpAddnsCode;
	}

	public void setExcpAddnsCode(String excpAddnsCode) {
		this.excpAddnsCode = excpAddnsCode;
	}

	public String getExcpAddnsDesc() {
		return excpAddnsDesc;
	}

	public void setExcpAddnsDesc(String excpAddnsDesc) {
		this.excpAddnsDesc = excpAddnsDesc;
	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	@XmlTransient
	public Collection<TradeItem> getTradeItemCollection() {
		return tradeItemCollection;
	}

	public void setTradeItemCollection(Collection<TradeItem> tradeItemCollection) {
		this.tradeItemCollection = tradeItemCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (excpAddnsCode != null ? excpAddnsCode.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ExceptionsAdditions)) {
			return false;
		}
		ExceptionsAdditions other = (ExceptionsAdditions) object;
		if ((this.excpAddnsCode == null && other.excpAddnsCode != null) || (this.excpAddnsCode != null && !this.excpAddnsCode.equals(other.excpAddnsCode))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ExceptionsAdditions[ excpAddnsCode=" + excpAddnsCode + " ]";
	}

}
