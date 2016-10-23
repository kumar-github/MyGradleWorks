/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "credit_term_group", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CreditTermGroup.findAll", query = "SELECT c FROM CreditTermGroup c"),
    @NamedQuery(name = "CreditTermGroup.findByGroupNum", query = "SELECT c FROM CreditTermGroup c WHERE c.creditTermGroupPK.groupNum = :groupNum"),
    @NamedQuery(name = "CreditTermGroup.findByCreditTermCode", query = "SELECT c FROM CreditTermGroup c WHERE c.creditTermGroupPK.creditTermCode = :creditTermCode"),
    @NamedQuery(name = "CreditTermGroup.findByTransId", query = "SELECT c FROM CreditTermGroup c WHERE c.transId = :transId")})
public class CreditTermGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CreditTermGroupPK creditTermGroupPK;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumn(name = "group_num", referencedColumnName = "group_num", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CreditGroup creditGroup;
    @JoinColumn(name = "credit_term_code", referencedColumnName = "credit_term_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CreditTerm creditTerm;

    public CreditTermGroup() {
    }

    public CreditTermGroup(CreditTermGroupPK creditTermGroupPK) {
        this.creditTermGroupPK = creditTermGroupPK;
    }

    public CreditTermGroup(CreditTermGroupPK creditTermGroupPK, int transId) {
        this.creditTermGroupPK = creditTermGroupPK;
        this.transId = transId;
    }

    public CreditTermGroup(int groupNum, String creditTermCode) {
        this.creditTermGroupPK = new CreditTermGroupPK(groupNum, creditTermCode);
    }

    public CreditTermGroupPK getCreditTermGroupPK() {
        return creditTermGroupPK;
    }

    public void setCreditTermGroupPK(CreditTermGroupPK creditTermGroupPK) {
        this.creditTermGroupPK = creditTermGroupPK;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public CreditGroup getCreditGroup() {
        return creditGroup;
    }

    public void setCreditGroup(CreditGroup creditGroup) {
        this.creditGroup = creditGroup;
    }

    public CreditTerm getCreditTerm() {
        return creditTerm;
    }

    public void setCreditTerm(CreditTerm creditTerm) {
        this.creditTerm = creditTerm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (creditTermGroupPK != null ? creditTermGroupPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditTermGroup)) {
            return false;
        }
        CreditTermGroup other = (CreditTermGroup) object;
        if ((this.creditTermGroupPK == null && other.creditTermGroupPK != null) || (this.creditTermGroupPK != null && !this.creditTermGroupPK.equals(other.creditTermGroupPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CreditTermGroup[ creditTermGroupPK=" + creditTermGroupPK + " ]";
    }
    
}
