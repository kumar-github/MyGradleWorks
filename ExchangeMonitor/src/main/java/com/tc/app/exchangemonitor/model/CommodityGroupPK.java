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
public class CommodityGroupPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "parent_cmdty_code", columnDefinition="CHAR")
    private String parentCmdtyCode;
    
    @Basic(optional = false)
    @Column(name = "cmdty_code", columnDefinition="CHAR")
    private String cmdtyCode;
    
    @Basic(optional = false)
    @Column(name = "cmdty_group_type_code", columnDefinition="CHAR")
    private String cmdtyGroupTypeCode;

    public CommodityGroupPK() {
    }

    public CommodityGroupPK(String parentCmdtyCode, String cmdtyCode, String cmdtyGroupTypeCode) {
        this.parentCmdtyCode = parentCmdtyCode;
        this.cmdtyCode = cmdtyCode;
        this.cmdtyGroupTypeCode = cmdtyGroupTypeCode;
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

    public String getCmdtyGroupTypeCode() {
        return cmdtyGroupTypeCode;
    }

    public void setCmdtyGroupTypeCode(String cmdtyGroupTypeCode) {
        this.cmdtyGroupTypeCode = cmdtyGroupTypeCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parentCmdtyCode != null ? parentCmdtyCode.hashCode() : 0);
        hash += (cmdtyCode != null ? cmdtyCode.hashCode() : 0);
        hash += (cmdtyGroupTypeCode != null ? cmdtyGroupTypeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityGroupPK)) {
            return false;
        }
        CommodityGroupPK other = (CommodityGroupPK) object;
        if ((this.parentCmdtyCode == null && other.parentCmdtyCode != null) || (this.parentCmdtyCode != null && !this.parentCmdtyCode.equals(other.parentCmdtyCode))) {
            return false;
        }
        if ((this.cmdtyCode == null && other.cmdtyCode != null) || (this.cmdtyCode != null && !this.cmdtyCode.equals(other.cmdtyCode))) {
            return false;
        }
        if ((this.cmdtyGroupTypeCode == null && other.cmdtyGroupTypeCode != null) || (this.cmdtyGroupTypeCode != null && !this.cmdtyGroupTypeCode.equals(other.cmdtyGroupTypeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityGroupPK[ parentCmdtyCode=" + parentCmdtyCode + ", cmdtyCode=" + cmdtyCode + ", cmdtyGroupTypeCode=" + cmdtyGroupTypeCode + " ]";
    }
    
}
