/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Saravana Kumar M
 */
@Embeddable
public class CreditTermGroupPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "group_num")
    private int groupNum;
    
    @Basic(optional = false)
    @Column(name = "credit_term_code", columnDefinition="CHAR")
    private String creditTermCode;

    public CreditTermGroupPK() {
    }

    public CreditTermGroupPK(int groupNum, String creditTermCode) {
        this.groupNum = groupNum;
        this.creditTermCode = creditTermCode;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    public String getCreditTermCode() {
        return creditTermCode;
    }

    public void setCreditTermCode(String creditTermCode) {
        this.creditTermCode = creditTermCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) groupNum;
        hash += (creditTermCode != null ? creditTermCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditTermGroupPK)) {
            return false;
        }
        CreditTermGroupPK other = (CreditTermGroupPK) object;
        if (this.groupNum != other.groupNum) {
            return false;
        }
        if ((this.creditTermCode == null && other.creditTermCode != null) || (this.creditTermCode != null && !this.creditTermCode.equals(other.creditTermCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CreditTermGroupPK[ groupNum=" + groupNum + ", creditTermCode=" + creditTermCode + " ]";
    }
    
}
