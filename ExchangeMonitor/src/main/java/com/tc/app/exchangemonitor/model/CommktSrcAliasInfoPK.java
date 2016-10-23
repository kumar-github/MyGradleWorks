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
public class CommktSrcAliasInfoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "commkt_key")
    private int commktKey;
    
    @Basic(optional = false)
    @Column(name = "price_source_code", columnDefinition="CHAR")
    private String priceSourceCode;
    
    @Basic(optional = false)
    @Column(name = "alias_source_code", columnDefinition="CHAR")
    private String aliasSourceCode;

    public CommktSrcAliasInfoPK() {
    }

    public CommktSrcAliasInfoPK(int commktKey, String priceSourceCode, String aliasSourceCode) {
        this.commktKey = commktKey;
        this.priceSourceCode = priceSourceCode;
        this.aliasSourceCode = aliasSourceCode;
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

    public String getAliasSourceCode() {
        return aliasSourceCode;
    }

    public void setAliasSourceCode(String aliasSourceCode) {
        this.aliasSourceCode = aliasSourceCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) commktKey;
        hash += (priceSourceCode != null ? priceSourceCode.hashCode() : 0);
        hash += (aliasSourceCode != null ? aliasSourceCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommktSrcAliasInfoPK)) {
            return false;
        }
        CommktSrcAliasInfoPK other = (CommktSrcAliasInfoPK) object;
        if (this.commktKey != other.commktKey) {
            return false;
        }
        if ((this.priceSourceCode == null && other.priceSourceCode != null) || (this.priceSourceCode != null && !this.priceSourceCode.equals(other.priceSourceCode))) {
            return false;
        }
        if ((this.aliasSourceCode == null && other.aliasSourceCode != null) || (this.aliasSourceCode != null && !this.aliasSourceCode.equals(other.aliasSourceCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommktSrcAliasInfoPK[ commktKey=" + commktKey + ", priceSourceCode=" + priceSourceCode + ", aliasSourceCode=" + aliasSourceCode + " ]";
    }
    
}
