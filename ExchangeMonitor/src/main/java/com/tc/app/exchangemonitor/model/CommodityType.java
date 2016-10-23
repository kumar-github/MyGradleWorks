/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "commodity_type", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommodityType.findAll", query = "SELECT c FROM CommodityType c"),
    @NamedQuery(name = "CommodityType.findByCmdtyTypeCode", query = "SELECT c FROM CommodityType c WHERE c.cmdtyTypeCode = :cmdtyTypeCode"),
    @NamedQuery(name = "CommodityType.findByCmdtyTypeDesc", query = "SELECT c FROM CommodityType c WHERE c.cmdtyTypeDesc = :cmdtyTypeDesc"),
    @NamedQuery(name = "CommodityType.findByTransId", query = "SELECT c FROM CommodityType c WHERE c.transId = :transId")})
public class CommodityType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cmdty_type_code", columnDefinition="CHAR")
    private String cmdtyTypeCode;
    
    @Basic(optional = false)
    @Column(name = "cmdty_type_desc")
    private String cmdtyTypeDesc;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmdtyType")
    private Collection<Commodity> commodityCollection;

    public CommodityType() {
    }

    public CommodityType(String cmdtyTypeCode) {
        this.cmdtyTypeCode = cmdtyTypeCode;
    }

    public CommodityType(String cmdtyTypeCode, String cmdtyTypeDesc, int transId) {
        this.cmdtyTypeCode = cmdtyTypeCode;
        this.cmdtyTypeDesc = cmdtyTypeDesc;
        this.transId = transId;
    }

    public String getCmdtyTypeCode() {
        return cmdtyTypeCode;
    }

    public void setCmdtyTypeCode(String cmdtyTypeCode) {
        this.cmdtyTypeCode = cmdtyTypeCode;
    }

    public String getCmdtyTypeDesc() {
        return cmdtyTypeDesc;
    }

    public void setCmdtyTypeDesc(String cmdtyTypeDesc) {
        this.cmdtyTypeDesc = cmdtyTypeDesc;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<Commodity> getCommodityCollection() {
        return commodityCollection;
    }

    public void setCommodityCollection(Collection<Commodity> commodityCollection) {
        this.commodityCollection = commodityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cmdtyTypeCode != null ? cmdtyTypeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityType)) {
            return false;
        }
        CommodityType other = (CommodityType) object;
        if ((this.cmdtyTypeCode == null && other.cmdtyTypeCode != null) || (this.cmdtyTypeCode != null && !this.cmdtyTypeCode.equals(other.cmdtyTypeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityType[ cmdtyTypeCode=" + cmdtyTypeCode + " ]";
    }
    
}
