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
public class CommodityMarketAliasPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "commkt_key")
    private int commktKey;
    
    @Basic(optional = false)
    @Column(name = "alias_source_code", columnDefinition="CHAR")
    private String aliasSourceCode;
    
    @Basic(optional = false)
    @Column(name = "type")
    private Character type;

    public CommodityMarketAliasPK() {
    }

    public CommodityMarketAliasPK(int commktKey, String aliasSourceCode, Character type) {
        this.commktKey = commktKey;
        this.aliasSourceCode = aliasSourceCode;
        this.type = type;
    }

    public int getCommktKey() {
        return commktKey;
    }

    public void setCommktKey(int commktKey) {
        this.commktKey = commktKey;
    }

    public String getAliasSourceCode() {
        return aliasSourceCode;
    }

    public void setAliasSourceCode(String aliasSourceCode) {
        this.aliasSourceCode = aliasSourceCode;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) commktKey;
        hash += (aliasSourceCode != null ? aliasSourceCode.hashCode() : 0);
        hash += (type != null ? type.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityMarketAliasPK)) {
            return false;
        }
        CommodityMarketAliasPK other = (CommodityMarketAliasPK) object;
        if (this.commktKey != other.commktKey) {
            return false;
        }
        if ((this.aliasSourceCode == null && other.aliasSourceCode != null) || (this.aliasSourceCode != null && !this.aliasSourceCode.equals(other.aliasSourceCode))) {
            return false;
        }
        if ((this.type == null && other.type != null) || (this.type != null && !this.type.equals(other.type))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityMarketAliasPK[ commktKey=" + commktKey + ", aliasSourceCode=" + aliasSourceCode + ", type=" + type + " ]";
    }
    
}
