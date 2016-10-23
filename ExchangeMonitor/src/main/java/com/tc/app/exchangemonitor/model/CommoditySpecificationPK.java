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
public class CommoditySpecificationPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "cmdty_code", columnDefinition="CHAR")
    private String cmdtyCode;
    
    @Basic(optional = false)
    @Column(name = "spec_code", columnDefinition="CHAR")
    private String specCode;

    public CommoditySpecificationPK() {
    }

    public CommoditySpecificationPK(String cmdtyCode, String specCode) {
        this.cmdtyCode = cmdtyCode;
        this.specCode = specCode;
    }

    public String getCmdtyCode() {
        return cmdtyCode;
    }

    public void setCmdtyCode(String cmdtyCode) {
        this.cmdtyCode = cmdtyCode;
    }

    public String getSpecCode() {
        return specCode;
    }

    public void setSpecCode(String specCode) {
        this.specCode = specCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cmdtyCode != null ? cmdtyCode.hashCode() : 0);
        hash += (specCode != null ? specCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommoditySpecificationPK)) {
            return false;
        }
        CommoditySpecificationPK other = (CommoditySpecificationPK) object;
        if ((this.cmdtyCode == null && other.cmdtyCode != null) || (this.cmdtyCode != null && !this.cmdtyCode.equals(other.cmdtyCode))) {
            return false;
        }
        if ((this.specCode == null && other.specCode != null) || (this.specCode != null && !this.specCode.equals(other.specCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommoditySpecificationPK[ cmdtyCode=" + cmdtyCode + ", specCode=" + specCode + " ]";
    }
    
}
