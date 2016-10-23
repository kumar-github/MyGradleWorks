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
 * @author smurugabushanam
 */
@Entity
@Table(name = "risk_transfer_indicator", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RiskTransferIndicator.findAll", query = "SELECT r FROM RiskTransferIndicator r"),
    @NamedQuery(name = "RiskTransferIndicator.findByRiskTransferIndCode", query = "SELECT r FROM RiskTransferIndicator r WHERE r.riskTransferIndCode = :riskTransferIndCode"),
    @NamedQuery(name = "RiskTransferIndicator.findByRiskTransferIndDesc", query = "SELECT r FROM RiskTransferIndicator r WHERE r.riskTransferIndDesc = :riskTransferIndDesc"),
    @NamedQuery(name = "RiskTransferIndicator.findByTransId", query = "SELECT r FROM RiskTransferIndicator r WHERE r.transId = :transId")})
public class RiskTransferIndicator implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "risk_transfer_ind_code", nullable = false, length = 2, columnDefinition="CHAR")
    private String riskTransferIndCode;
    
    @Basic(optional = false)
    @Column(name = "risk_transfer_ind_desc", nullable = false, length = 30)
    private String riskTransferIndDesc;
    @Basic(optional = false)
    @Column(name = "trans_id", nullable = false)
    private int transId;
    @OneToMany(mappedBy = "riskTransferIndCode", fetch = FetchType.LAZY)
    private Collection<Account> accountCollection;

    public RiskTransferIndicator() {
    }

    public RiskTransferIndicator(String riskTransferIndCode) {
        this.riskTransferIndCode = riskTransferIndCode;
    }

    public RiskTransferIndicator(String riskTransferIndCode, String riskTransferIndDesc, int transId) {
        this.riskTransferIndCode = riskTransferIndCode;
        this.riskTransferIndDesc = riskTransferIndDesc;
        this.transId = transId;
    }

    public String getRiskTransferIndCode() {
        return riskTransferIndCode;
    }

    public void setRiskTransferIndCode(String riskTransferIndCode) {
        this.riskTransferIndCode = riskTransferIndCode;
    }

    public String getRiskTransferIndDesc() {
        return riskTransferIndDesc;
    }

    public void setRiskTransferIndDesc(String riskTransferIndDesc) {
        this.riskTransferIndDesc = riskTransferIndDesc;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<Account> getAccountCollection() {
        return accountCollection;
    }

    public void setAccountCollection(Collection<Account> accountCollection) {
        this.accountCollection = accountCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (riskTransferIndCode != null ? riskTransferIndCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RiskTransferIndicator)) {
            return false;
        }
        RiskTransferIndicator other = (RiskTransferIndicator) object;
        if ((this.riskTransferIndCode == null && other.riskTransferIndCode != null) || (this.riskTransferIndCode != null && !this.riskTransferIndCode.equals(other.riskTransferIndCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generated11.RiskTransferIndicator[ riskTransferIndCode=" + riskTransferIndCode + " ]";
    }
    
}
