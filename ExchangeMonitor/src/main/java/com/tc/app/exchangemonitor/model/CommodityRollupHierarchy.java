/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "commodity_rollup_hierarchy", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommodityRollupHierarchy.findAll", query = "SELECT c FROM CommodityRollupHierarchy c"),
    @NamedQuery(name = "CommodityRollupHierarchy.findByParentCmdtyCode", query = "SELECT c FROM CommodityRollupHierarchy c WHERE c.commodityRollupHierarchyPK.parentCmdtyCode = :parentCmdtyCode"),
    @NamedQuery(name = "CommodityRollupHierarchy.findByCmdtyCode", query = "SELECT c FROM CommodityRollupHierarchy c WHERE c.commodityRollupHierarchyPK.cmdtyCode = :cmdtyCode"),
    @NamedQuery(name = "CommodityRollupHierarchy.findByRollupTypeCode", query = "SELECT c FROM CommodityRollupHierarchy c WHERE c.commodityRollupHierarchyPK.rollupTypeCode = :rollupTypeCode"),
    @NamedQuery(name = "CommodityRollupHierarchy.findByTransId", query = "SELECT c FROM CommodityRollupHierarchy c WHERE c.transId = :transId")})
public class CommodityRollupHierarchy implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommodityRollupHierarchyPK commodityRollupHierarchyPK;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumn(name = "parent_cmdty_code", referencedColumnName = "cmdty_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Commodity commodity;
    @JoinColumn(name = "cmdty_code", referencedColumnName = "cmdty_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Commodity commodity1;
    @JoinColumn(name = "rollup_type_code", referencedColumnName = "rollup_type_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CommodityRollupType commodityRollupType;

    public CommodityRollupHierarchy() {
    }

    public CommodityRollupHierarchy(CommodityRollupHierarchyPK commodityRollupHierarchyPK) {
        this.commodityRollupHierarchyPK = commodityRollupHierarchyPK;
    }

    public CommodityRollupHierarchy(CommodityRollupHierarchyPK commodityRollupHierarchyPK, int transId) {
        this.commodityRollupHierarchyPK = commodityRollupHierarchyPK;
        this.transId = transId;
    }

    public CommodityRollupHierarchy(String parentCmdtyCode, String cmdtyCode, String rollupTypeCode) {
        this.commodityRollupHierarchyPK = new CommodityRollupHierarchyPK(parentCmdtyCode, cmdtyCode, rollupTypeCode);
    }

    public CommodityRollupHierarchyPK getCommodityRollupHierarchyPK() {
        return commodityRollupHierarchyPK;
    }

    public void setCommodityRollupHierarchyPK(CommodityRollupHierarchyPK commodityRollupHierarchyPK) {
        this.commodityRollupHierarchyPK = commodityRollupHierarchyPK;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Commodity getCommodity1() {
        return commodity1;
    }

    public void setCommodity1(Commodity commodity1) {
        this.commodity1 = commodity1;
    }

    public CommodityRollupType getCommodityRollupType() {
        return commodityRollupType;
    }

    public void setCommodityRollupType(CommodityRollupType commodityRollupType) {
        this.commodityRollupType = commodityRollupType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commodityRollupHierarchyPK != null ? commodityRollupHierarchyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityRollupHierarchy)) {
            return false;
        }
        CommodityRollupHierarchy other = (CommodityRollupHierarchy) object;
        if ((this.commodityRollupHierarchyPK == null && other.commodityRollupHierarchyPK != null) || (this.commodityRollupHierarchyPK != null && !this.commodityRollupHierarchyPK.equals(other.commodityRollupHierarchyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityRollupHierarchy[ commodityRollupHierarchyPK=" + commodityRollupHierarchyPK + " ]";
    }
    
}
