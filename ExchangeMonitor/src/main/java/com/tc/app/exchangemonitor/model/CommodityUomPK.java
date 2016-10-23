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
public class CommodityUomPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "cmdty_code", columnDefinition="CHAR")
    private String cmdtyCode;
    
    @Basic(optional = false)
    @Column(name = "cmdty_uom_for", columnDefinition="CHAR")
    private String cmdtyUomFor;
    
    @Basic(optional = false)
    @Column(name = "uom_code", columnDefinition="CHAR")
    private String uomCode;

    public CommodityUomPK() {
    }

    public CommodityUomPK(String cmdtyCode, String cmdtyUomFor, String uomCode) {
        this.cmdtyCode = cmdtyCode;
        this.cmdtyUomFor = cmdtyUomFor;
        this.uomCode = uomCode;
    }

    public String getCmdtyCode() {
        return cmdtyCode;
    }

    public void setCmdtyCode(String cmdtyCode) {
        this.cmdtyCode = cmdtyCode;
    }

    public String getCmdtyUomFor() {
        return cmdtyUomFor;
    }

    public void setCmdtyUomFor(String cmdtyUomFor) {
        this.cmdtyUomFor = cmdtyUomFor;
    }

    public String getUomCode() {
        return uomCode;
    }

    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cmdtyCode != null ? cmdtyCode.hashCode() : 0);
        hash += (cmdtyUomFor != null ? cmdtyUomFor.hashCode() : 0);
        hash += (uomCode != null ? uomCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityUomPK)) {
            return false;
        }
        CommodityUomPK other = (CommodityUomPK) object;
        if ((this.cmdtyCode == null && other.cmdtyCode != null) || (this.cmdtyCode != null && !this.cmdtyCode.equals(other.cmdtyCode))) {
            return false;
        }
        if ((this.cmdtyUomFor == null && other.cmdtyUomFor != null) || (this.cmdtyUomFor != null && !this.cmdtyUomFor.equals(other.cmdtyUomFor))) {
            return false;
        }
        if ((this.uomCode == null && other.uomCode != null) || (this.uomCode != null && !this.uomCode.equals(other.uomCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityUomPK[ cmdtyCode=" + cmdtyCode + ", cmdtyUomFor=" + cmdtyUomFor + ", uomCode=" + uomCode + " ]";
    }
    
}
