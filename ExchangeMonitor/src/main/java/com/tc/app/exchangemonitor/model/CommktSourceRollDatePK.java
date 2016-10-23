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
public class CommktSourceRollDatePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "commkt_key")
    private int commktKey;
    
    @Basic(optional = false)
    @Column(name = "price_source_code", columnDefinition="CHAR")
    private String priceSourceCode;
    
    @Basic(optional = false)
    @Column(name = "roll_date_rule_num", columnDefinition="TINYINT")
    private short rollDateRuleNum;

    public CommktSourceRollDatePK() {
    }

    public CommktSourceRollDatePK(int commktKey, String priceSourceCode, short rollDateRuleNum) {
        this.commktKey = commktKey;
        this.priceSourceCode = priceSourceCode;
        this.rollDateRuleNum = rollDateRuleNum;
    }

    public int getCommktKey() {
        return commktKey;
    }

    public void setCommktKey(int commktKey) {
        this.commktKey = commktKey;
    }

    public String getPriceSourceCode() {
        return priceSourceCode;
    }

    public void setPriceSourceCode(String priceSourceCode) {
        this.priceSourceCode = priceSourceCode;
    }

    public short getRollDateRuleNum() {
        return rollDateRuleNum;
    }

    public void setRollDateRuleNum(short rollDateRuleNum) {
        this.rollDateRuleNum = rollDateRuleNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) commktKey;
        hash += (priceSourceCode != null ? priceSourceCode.hashCode() : 0);
        hash += (int) rollDateRuleNum;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommktSourceRollDatePK)) {
            return false;
        }
        CommktSourceRollDatePK other = (CommktSourceRollDatePK) object;
        if (this.commktKey != other.commktKey) {
            return false;
        }
        if ((this.priceSourceCode == null && other.priceSourceCode != null) || (this.priceSourceCode != null && !this.priceSourceCode.equals(other.priceSourceCode))) {
            return false;
        }
        if (this.rollDateRuleNum != other.rollDateRuleNum) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommktSourceRollDatePK[ commktKey=" + commktKey + ", priceSourceCode=" + priceSourceCode + ", rollDateRuleNum=" + rollDateRuleNum + " ]";
    }
    
}
