/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	@NamedQuery(name = "Gtc.findAll", query = "SELECT g FROM Gtc g"),
	@NamedQuery(name = "Gtc.findByGtcCode", query = "SELECT g FROM Gtc g WHERE g.gtcCode = :gtcCode"),
	@NamedQuery(name = "Gtc.findByGtcDesc", query = "SELECT g FROM Gtc g WHERE g.gtcDesc = :gtcDesc"),
	@NamedQuery(name = "Gtc.findByAgreementNum", query = "SELECT g FROM Gtc g WHERE g.agreementNum = :agreementNum"),
	@NamedQuery(name = "Gtc.findByAgreementDate", query = "SELECT g FROM Gtc g WHERE g.agreementDate = :agreementDate"),
	@NamedQuery(name = "Gtc.findByCreationDate", query = "SELECT g FROM Gtc g WHERE g.creationDate = :creationDate"),
	@NamedQuery(name = "Gtc.findByTransId", query = "SELECT g FROM Gtc g WHERE g.transId = :transId")})
public class Gtc implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "gtc_code")
	private String gtcCode;
	@Basic(optional = false)
	@Column(name = "gtc_desc")
	private String gtcDesc;
	@Column(name = "agreement_num")
	private Integer agreementNum;
	@Column(name = "agreement_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date agreementDate;
	@Basic(optional = false)
	@Column(name = "creation_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	@Basic(optional = false)
	@Column(name = "trans_id")
	private int transId;
	@JoinColumn(name = "creator_init", referencedColumnName = "user_init")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private IctsUser creatorInit;
	@OneToMany(mappedBy = "gtcCode", fetch = FetchType.LAZY)
	private Collection<TradeItem> tradeItemCollection;

	public Gtc() {
	}

	public Gtc(String gtcCode) {
		this.gtcCode = gtcCode;
	}

	public Gtc(String gtcCode, String gtcDesc, Date creationDate, int transId) {
		this.gtcCode = gtcCode;
		this.gtcDesc = gtcDesc;
		this.creationDate = creationDate;
		this.transId = transId;
	}

	public String getGtcCode() {
		return gtcCode;
	}

	public void setGtcCode(String gtcCode) {
		this.gtcCode = gtcCode;
	}

	public String getGtcDesc() {
		return gtcDesc;
	}

	public void setGtcDesc(String gtcDesc) {
		this.gtcDesc = gtcDesc;
	}

	public Integer getAgreementNum() {
		return agreementNum;
	}

	public void setAgreementNum(Integer agreementNum) {
		this.agreementNum = agreementNum;
	}

	public Date getAgreementDate() {
		return agreementDate;
	}

	public void setAgreementDate(Date agreementDate) {
		this.agreementDate = agreementDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public IctsUser getCreatorInit() {
		return creatorInit;
	}

	public void setCreatorInit(IctsUser creatorInit) {
		this.creatorInit = creatorInit;
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
		hash += (gtcCode != null ? gtcCode.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Gtc)) {
			return false;
		}
		Gtc other = (Gtc) object;
		if ((this.gtcCode == null && other.gtcCode != null) || (this.gtcCode != null && !this.gtcCode.equals(other.gtcCode))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Gtc[ gtcCode=" + gtcCode + " ]";
	}

}
