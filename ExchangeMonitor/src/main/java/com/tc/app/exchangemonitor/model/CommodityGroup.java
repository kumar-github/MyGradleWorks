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
@Table(name = "commodity_group", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommodityGroup.findAll", query = "SELECT c FROM CommodityGroup c"),
    @NamedQuery(name = "CommodityGroup.findByParentCmdtyCode", query = "SELECT c FROM CommodityGroup c WHERE c.commodityGroupPK.parentCmdtyCode = :parentCmdtyCode"),
    @NamedQuery(name = "CommodityGroup.findByCmdtyCode", query = "SELECT c FROM CommodityGroup c WHERE c.commodityGroupPK.cmdtyCode = :cmdtyCode"),
    @NamedQuery(name = "CommodityGroup.findByCmdtyGroupTypeCode", query = "SELECT c FROM CommodityGroup c WHERE c.commodityGroupPK.cmdtyGroupTypeCode = :cmdtyGroupTypeCode"),
    @NamedQuery(name = "CommodityGroup.findByTransId", query = "SELECT c FROM CommodityGroup c WHERE c.transId = :transId")})
public class CommodityGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommodityGroupPK commodityGroupPK;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumn(name = "parent_cmdty_code", referencedColumnName = "cmdty_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Commodity commodity;
    @JoinColumn(name = "cmdty_code", referencedColumnName = "cmdty_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Commodity commodity1;
    @JoinColumn(name = "cmdty_group_type_code", referencedColumnName = "cmdty_group_type_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CommodityGroupType commodityGroupType;

    public CommodityGroup() {
    }

    public CommodityGroup(CommodityGroupPK commodityGroupPK) {
        this.commodityGroupPK = commodityGroupPK;
    }

    public CommodityGroup(CommodityGroupPK commodityGroupPK, int transId) {
        this.commodityGroupPK = commodityGroupPK;
        this.transId = transId;
    }

    public CommodityGroup(String parentCmdtyCode, String cmdtyCode, String cmdtyGroupTypeCode) {
        this.commodityGroupPK = new CommodityGroupPK(parentCmdtyCode, cmdtyCode, cmdtyGroupTypeCode);
    }

    public CommodityGroupPK getCommodityGroupPK() {
        return commodityGroupPK;
    }

    public void setCommodityGroupPK(CommodityGroupPK commodityGroupPK) {
        this.commodityGroupPK = commodityGroupPK;
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

    public CommodityGroupType getCommodityGroupType() {
        return commodityGroupType;
    }

    public void setCommodityGroupType(CommodityGroupType commodityGroupType) {
        this.commodityGroupType = commodityGroupType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commodityGroupPK != null ? commodityGroupPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityGroup)) {
            return false;
        }
        CommodityGroup other = (CommodityGroup) object;
        if ((this.commodityGroupPK == null && other.commodityGroupPK != null) || (this.commodityGroupPK != null && !this.commodityGroupPK.equals(other.commodityGroupPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityGroup[ commodityGroupPK=" + commodityGroupPK + " ]";
    }
    
}
