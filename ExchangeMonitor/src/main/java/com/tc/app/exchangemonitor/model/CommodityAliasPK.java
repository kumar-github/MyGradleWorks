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
public class CommodityAliasPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "cmdty_code", columnDefinition="CHAR")
    private String cmdtyCode;
    
    @Basic(optional = false)
    @Column(name = "alias_source_code", columnDefinition="CHAR")
    private String aliasSourceCode;

    public CommodityAliasPK() {
    }

    public CommodityAliasPK(String cmdtyCode, String aliasSourceCode) {
        this.cmdtyCode = cmdtyCode;
        this.aliasSourceCode = aliasSourceCode;
    }

    public String getCmdtyCode() {
        return cmdtyCode;
    }

    public void setCmdtyCode(String cmdtyCode) {
        this.cmdtyCode = cmdtyCode;
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
        hash += (cmdtyCode != null ? cmdtyCode.hashCode() : 0);
        hash += (aliasSourceCode != null ? aliasSourceCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityAliasPK)) {
            return false;
        }
        CommodityAliasPK other = (CommodityAliasPK) object;
        if ((this.cmdtyCode == null && other.cmdtyCode != null) || (this.cmdtyCode != null && !this.cmdtyCode.equals(other.cmdtyCode))) {
            return false;
        }
        if ((this.aliasSourceCode == null && other.aliasSourceCode != null) || (this.aliasSourceCode != null && !this.aliasSourceCode.equals(other.aliasSourceCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityAliasPK[ cmdtyCode=" + cmdtyCode + ", aliasSourceCode=" + aliasSourceCode + " ]";
    }
    
}
