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
@Table(name = "commodity_rollup_type", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommodityRollupType.findAll", query = "SELECT c FROM CommodityRollupType c"),
    @NamedQuery(name = "CommodityRollupType.findByRollupTypeCode", query = "SELECT c FROM CommodityRollupType c WHERE c.rollupTypeCode = :rollupTypeCode"),
    @NamedQuery(name = "CommodityRollupType.findByRollupTypeDesc", query = "SELECT c FROM CommodityRollupType c WHERE c.rollupTypeDesc = :rollupTypeDesc"),
    @NamedQuery(name = "CommodityRollupType.findByTransId", query = "SELECT c FROM CommodityRollupType c WHERE c.transId = :transId")})
public class CommodityRollupType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "rollup_type_code", columnDefinition="CHAR")
    private String rollupTypeCode;
    
    @Column(name = "rollup_type_desc")
    private String rollupTypeDesc;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commodityRollupType")
    private Collection<CommodityRollupHierarchy> commodityRollupHierarchyCollection;

    public CommodityRollupType() {
    }

    public CommodityRollupType(String rollupTypeCode) {
        this.rollupTypeCode = rollupTypeCode;
    }

    public CommodityRollupType(String rollupTypeCode, int transId) {
        this.rollupTypeCode = rollupTypeCode;
        this.transId = transId;
    }

    public String getRollupTypeCode() {
        return rollupTypeCode;
    }

    public void setRollupTypeCode(String rollupTypeCode) {
        this.rollupTypeCode = rollupTypeCode;
    }

    public String getRollupTypeDesc() {
        return rollupTypeDesc;
    }

    public void setRollupTypeDesc(String rollupTypeDesc) {
        this.rollupTypeDesc = rollupTypeDesc;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<CommodityRollupHierarchy> getCommodityRollupHierarchyCollection() {
        return commodityRollupHierarchyCollection;
    }

    public void setCommodityRollupHierarchyCollection(Collection<CommodityRollupHierarchy> commodityRollupHierarchyCollection) {
        this.commodityRollupHierarchyCollection = commodityRollupHierarchyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rollupTypeCode != null ? rollupTypeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityRollupType)) {
            return false;
        }
        CommodityRollupType other = (CommodityRollupType) object;
        if ((this.rollupTypeCode == null && other.rollupTypeCode != null) || (this.rollupTypeCode != null && !this.rollupTypeCode.equals(other.rollupTypeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityRollupType[ rollupTypeCode=" + rollupTypeCode + " ]";
    }
    
}
