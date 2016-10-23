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
@Table(name = "commodity_location", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommodityLocation.findAll", query = "SELECT c FROM CommodityLocation c"),
    @NamedQuery(name = "CommodityLocation.findByCmdtyCode", query = "SELECT c FROM CommodityLocation c WHERE c.commodityLocationPK.cmdtyCode = :cmdtyCode"),
    @NamedQuery(name = "CommodityLocation.findByLocCode", query = "SELECT c FROM CommodityLocation c WHERE c.commodityLocationPK.locCode = :locCode"),
    @NamedQuery(name = "CommodityLocation.findByTypicalGravity", query = "SELECT c FROM CommodityLocation c WHERE c.typicalGravity = :typicalGravity"),
    @NamedQuery(name = "CommodityLocation.findByTransId", query = "SELECT c FROM CommodityLocation c WHERE c.transId = :transId")})
public class CommodityLocation implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommodityLocationPK commodityLocationPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "typical_gravity")
    private Double typicalGravity;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumn(name = "cmdty_code", referencedColumnName = "cmdty_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Commodity commodity;
    @JoinColumn(name = "cmdty_delivered_scheduled", referencedColumnName = "cmdty_code")
    @ManyToOne
    private Commodity cmdtyDeliveredScheduled;
    @JoinColumn(name = "cmdty_received_scheduled", referencedColumnName = "cmdty_code")
    @ManyToOne
    private Commodity cmdtyReceivedScheduled;
    @JoinColumn(name = "loc_code", referencedColumnName = "loc_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Location location;

    public CommodityLocation() {
    }

    public CommodityLocation(CommodityLocationPK commodityLocationPK) {
        this.commodityLocationPK = commodityLocationPK;
    }

    public CommodityLocation(CommodityLocationPK commodityLocationPK, int transId) {
        this.commodityLocationPK = commodityLocationPK;
        this.transId = transId;
    }

    public CommodityLocation(String cmdtyCode, String locCode) {
        this.commodityLocationPK = new CommodityLocationPK(cmdtyCode, locCode);
    }

    public CommodityLocationPK getCommodityLocationPK() {
        return commodityLocationPK;
    }

    public void setCommodityLocationPK(CommodityLocationPK commodityLocationPK) {
        this.commodityLocationPK = commodityLocationPK;
    }

    public Double getTypicalGravity() {
        return typicalGravity;
    }

    public void setTypicalGravity(Double typicalGravity) {
        this.typicalGravity = typicalGravity;
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

    public Commodity getCmdtyDeliveredScheduled() {
        return cmdtyDeliveredScheduled;
    }

    public void setCmdtyDeliveredScheduled(Commodity cmdtyDeliveredScheduled) {
        this.cmdtyDeliveredScheduled = cmdtyDeliveredScheduled;
    }

    public Commodity getCmdtyReceivedScheduled() {
        return cmdtyReceivedScheduled;
    }

    public void setCmdtyReceivedScheduled(Commodity cmdtyReceivedScheduled) {
        this.cmdtyReceivedScheduled = cmdtyReceivedScheduled;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commodityLocationPK != null ? commodityLocationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityLocation)) {
            return false;
        }
        CommodityLocation other = (CommodityLocation) object;
        if ((this.commodityLocationPK == null && other.commodityLocationPK != null) || (this.commodityLocationPK != null && !this.commodityLocationPK.equals(other.commodityLocationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityLocation[ commodityLocationPK=" + commodityLocationPK + " ]";
    }
    
}
