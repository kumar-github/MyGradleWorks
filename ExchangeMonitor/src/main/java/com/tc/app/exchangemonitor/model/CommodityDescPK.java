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
public class CommodityDescPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "cmdty_code", columnDefinition="CHAR")
    private String cmdtyCode;
    
    @Basic(optional = false)
    @Column(name = "cmdty_desc_lang")
    private String cmdtyDescLang;
    @Basic(optional = false)
    @Column(name = "cmdty_desc_for")
    private String cmdtyDescFor;

    public CommodityDescPK() {
    }

    public CommodityDescPK(String cmdtyCode, String cmdtyDescLang, String cmdtyDescFor) {
        this.cmdtyCode = cmdtyCode;
        this.cmdtyDescLang = cmdtyDescLang;
        this.cmdtyDescFor = cmdtyDescFor;
    }

    public String getCmdtyCode() {
        return cmdtyCode;
    }

    public void setCmdtyCode(String cmdtyCode) {
        this.cmdtyCode = cmdtyCode;
    }

    public String getCmdtyDescLang() {
        return cmdtyDescLang;
    }

    public void setCmdtyDescLang(String cmdtyDescLang) {
        this.cmdtyDescLang = cmdtyDescLang;
    }

    public String getCmdtyDescFor() {
        return cmdtyDescFor;
    }

    public void setCmdtyDescFor(String cmdtyDescFor) {
        this.cmdtyDescFor = cmdtyDescFor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cmdtyCode != null ? cmdtyCode.hashCode() : 0);
        hash += (cmdtyDescLang != null ? cmdtyDescLang.hashCode() : 0);
        hash += (cmdtyDescFor != null ? cmdtyDescFor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityDescPK)) {
            return false;
        }
        CommodityDescPK other = (CommodityDescPK) object;
        if ((this.cmdtyCode == null && other.cmdtyCode != null) || (this.cmdtyCode != null && !this.cmdtyCode.equals(other.cmdtyCode))) {
            return false;
        }
        if ((this.cmdtyDescLang == null && other.cmdtyDescLang != null) || (this.cmdtyDescLang != null && !this.cmdtyDescLang.equals(other.cmdtyDescLang))) {
            return false;
        }
        if ((this.cmdtyDescFor == null && other.cmdtyDescFor != null) || (this.cmdtyDescFor != null && !this.cmdtyDescFor.equals(other.cmdtyDescFor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityDescPK[ cmdtyCode=" + cmdtyCode + ", cmdtyDescLang=" + cmdtyDescLang + ", cmdtyDescFor=" + cmdtyDescFor + " ]";
    }
    
}
