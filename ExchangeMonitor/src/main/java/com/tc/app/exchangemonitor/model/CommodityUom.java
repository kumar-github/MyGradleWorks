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
@Table(name = "commodity_uom", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommodityUom.findAll", query = "SELECT c FROM CommodityUom c"),
    @NamedQuery(name = "CommodityUom.findByCmdtyCode", query = "SELECT c FROM CommodityUom c WHERE c.commodityUomPK.cmdtyCode = :cmdtyCode"),
    @NamedQuery(name = "CommodityUom.findByCmdtyUomFor", query = "SELECT c FROM CommodityUom c WHERE c.commodityUomPK.cmdtyUomFor = :cmdtyUomFor"),
    @NamedQuery(name = "CommodityUom.findByUomCode", query = "SELECT c FROM CommodityUom c WHERE c.commodityUomPK.uomCode = :uomCode"),
    @NamedQuery(name = "CommodityUom.findByTransId", query = "SELECT c FROM CommodityUom c WHERE c.transId = :transId")})
public class CommodityUom implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommodityUomPK commodityUomPK;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumn(name = "cmdty_code", referencedColumnName = "cmdty_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Commodity commodity;
    @JoinColumn(name = "uom_code", referencedColumnName = "uom_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Uom uom;

    public CommodityUom() {
    }

    public CommodityUom(CommodityUomPK commodityUomPK) {
        this.commodityUomPK = commodityUomPK;
    }

    public CommodityUom(CommodityUomPK commodityUomPK, int transId) {
        this.commodityUomPK = commodityUomPK;
        this.transId = transId;
    }

    public CommodityUom(String cmdtyCode, String cmdtyUomFor, String uomCode) {
        this.commodityUomPK = new CommodityUomPK(cmdtyCode, cmdtyUomFor, uomCode);
    }

    public CommodityUomPK getCommodityUomPK() {
        return commodityUomPK;
    }

    public void setCommodityUomPK(CommodityUomPK commodityUomPK) {
        this.commodityUomPK = commodityUomPK;
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

    public Uom getUom() {
        return uom;
    }

    public void setUom(Uom uom) {
        this.uom = uom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commodityUomPK != null ? commodityUomPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityUom)) {
            return false;
        }
        CommodityUom other = (CommodityUom) object;
        if ((this.commodityUomPK == null && other.commodityUomPK != null) || (this.commodityUomPK != null && !this.commodityUomPK.equals(other.commodityUomPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityUom[ commodityUomPK=" + commodityUomPK + " ]";
    }
    
}
