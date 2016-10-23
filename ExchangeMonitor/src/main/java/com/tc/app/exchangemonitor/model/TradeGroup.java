package com.tc.app.exchangemonitor.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
 * @author smurugabushanam
 */
@Entity
@Table(name = "trade_group", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TradeGroup.findAll", query = "SELECT t FROM TradeGroup t"),
    @NamedQuery(name = "TradeGroup.findByTradeGroupNum", query = "SELECT t FROM TradeGroup t WHERE t.tradeGroupNum = :tradeGroupNum"),
    @NamedQuery(name = "TradeGroup.findByTradeGroupCode", query = "SELECT t FROM TradeGroup t WHERE t.tradeGroupCode = :tradeGroupCode"),
    @NamedQuery(name = "TradeGroup.findByTradeGroupDesc", query = "SELECT t FROM TradeGroup t WHERE t.tradeGroupDesc = :tradeGroupDesc"),
    @NamedQuery(name = "TradeGroup.findByStatus", query = "SELECT t FROM TradeGroup t WHERE t.status = :status"),
    @NamedQuery(name = "TradeGroup.findByTransId", query = "SELECT t FROM TradeGroup t WHERE t.transId = :transId")})
public class TradeGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "trade_group_num", nullable = false)
    private Integer tradeGroupNum;
    
    @Basic(optional = false)
    @Column(name = "trade_group_code", nullable = false, length = 15, columnDefinition="CHAR")
    private String tradeGroupCode;
    
    @Basic(optional = false)
    @Column(name = "trade_group_desc", nullable = false, length = 30)
    private String tradeGroupDesc;
    @Basic(optional = false)
    @Column(nullable = false)
    private Character status;
    @Basic(optional = false)
    @Column(name = "trans_id", nullable = false)
    private int transId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tradeGroupNum", fetch = FetchType.LAZY)
    private Collection<AccountAgreement> accountAgreementCollection;

    public TradeGroup() {
    }

    public TradeGroup(Integer tradeGroupNum) {
        this.tradeGroupNum = tradeGroupNum;
    }

    public TradeGroup(Integer tradeGroupNum, String tradeGroupCode, String tradeGroupDesc, Character status, int transId) {
        this.tradeGroupNum = tradeGroupNum;
        this.tradeGroupCode = tradeGroupCode;
        this.tradeGroupDesc = tradeGroupDesc;
        this.status = status;
        this.transId = transId;
    }

    public Integer getTradeGroupNum() {
        return tradeGroupNum;
    }

    public void setTradeGroupNum(Integer tradeGroupNum) {
        this.tradeGroupNum = tradeGroupNum;
    }

    public String getTradeGroupCode() {
        return tradeGroupCode;
    }

    public void setTradeGroupCode(String tradeGroupCode) {
        this.tradeGroupCode = tradeGroupCode;
    }

    public String getTradeGroupDesc() {
        return tradeGroupDesc;
    }

    public void setTradeGroupDesc(String tradeGroupDesc) {
        this.tradeGroupDesc = tradeGroupDesc;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<AccountAgreement> getAccountAgreementCollection() {
        return accountAgreementCollection;
    }

    public void setAccountAgreementCollection(Collection<AccountAgreement> accountAgreementCollection) {
        this.accountAgreementCollection = accountAgreementCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tradeGroupNum != null ? tradeGroupNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TradeGroup)) {
            return false;
        }
        TradeGroup other = (TradeGroup) object;
        if ((this.tradeGroupNum == null && other.tradeGroupNum != null) || (this.tradeGroupNum != null && !this.tradeGroupNum.equals(other.tradeGroupNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generated11.TradeGroup[ tradeGroupNum=" + tradeGroupNum + " ]";
    }
    
}
