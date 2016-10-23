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
public class CommodityLocationPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "cmdty_code", columnDefinition="CHAR")
    private String cmdtyCode;
    
    @Basic(optional = false)
    @Column(name = "loc_code", columnDefinition="CHAR")
    private String locCode;

    public CommodityLocationPK() {
    }

    public CommodityLocationPK(String cmdtyCode, String locCode) {
        this.cmdtyCode = cmdtyCode;
        this.locCode = locCode;
    }

    public String getCmdtyCode() {
        return cmdtyCode;
    }

    public void setCmdtyCode(String cmdtyCode) {
        this.cmdtyCode = cmdtyCode;
    }

    public String getLocCode() {
        return locCode;
    }

    public void setLocCode(String locCode) {
        this.locCode = locCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cmdtyCode != null ? cmdtyCode.hashCode() : 0);
        hash += (locCode != null ? locCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityLocationPK)) {
            return false;
        }
        CommodityLocationPK other = (CommodityLocationPK) object;
        if ((this.cmdtyCode == null && other.cmdtyCode != null) || (this.cmdtyCode != null && !this.cmdtyCode.equals(other.cmdtyCode))) {
            return false;
        }
        if ((this.locCode == null && other.locCode != null) || (this.locCode != null && !this.locCode.equals(other.locCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityLocationPK[ cmdtyCode=" + cmdtyCode + ", locCode=" + locCode + " ]";
    }
    
}
