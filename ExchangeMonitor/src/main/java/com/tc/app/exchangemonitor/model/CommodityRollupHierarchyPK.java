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
public class CommodityRollupHierarchyPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "parent_cmdty_code", columnDefinition="CHAR")
    private String parentCmdtyCode;
    
    @Basic(optional = false)
    @Column(name = "cmdty_code", columnDefinition="CHAR")
    private String cmdtyCode;
    
    @Basic(optional = false)
    @Column(name = "rollup_type_code", columnDefinition="CHAR")
    private String rollupTypeCode;

    public CommodityRollupHierarchyPK() {
    }

    public CommodityRollupHierarchyPK(String parentCmdtyCode, String cmdtyCode, String rollupTypeCode) {
        this.parentCmdtyCode = parentCmdtyCode;
        this.cmdtyCode = cmdtyCode;
        this.rollupTypeCode = rollupTypeCode;
    }

    public String getParentCmdtyCode() {
        return parentCmdtyCode;
    }

    public void setParentCmdtyCode(String parentCmdtyCode) {
        this.parentCmdtyCode = parentCmdtyCode;
    }

    public String getCmdtyCode() {
        return cmdtyCode;
    }

    public void setCmdtyCode(String cmdtyCode) {
        this.cmdtyCode = cmdtyCode;
    }

    public String getRollupTypeCode() {
        return rollupTypeCode;
    }

    public void setRollupTypeCode(String rollupTypeCode) {
        this.rollupTypeCode = rollupTypeCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parentCmdtyCode != null ? parentCmdtyCode.hashCode() : 0);
        hash += (cmdtyCode != null ? cmdtyCode.hashCode() : 0);
        hash += (rollupTypeCode != null ? rollupTypeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityRollupHierarchyPK)) {
            return false;
        }
        CommodityRollupHierarchyPK other = (CommodityRollupHierarchyPK) object;
        if ((this.parentCmdtyCode == null && other.parentCmdtyCode != null) || (this.parentCmdtyCode != null && !this.parentCmdtyCode.equals(other.parentCmdtyCode))) {
            return false;
        }
        if ((this.cmdtyCode == null && other.cmdtyCode != null) || (this.cmdtyCode != null && !this.cmdtyCode.equals(other.cmdtyCode))) {
            return false;
        }
        if ((this.rollupTypeCode == null && other.rollupTypeCode != null) || (this.rollupTypeCode != null && !this.rollupTypeCode.equals(other.rollupTypeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityRollupHierarchyPK[ parentCmdtyCode=" + parentCmdtyCode + ", cmdtyCode=" + cmdtyCode + ", rollupTypeCode=" + rollupTypeCode + " ]";
    }
    
}
