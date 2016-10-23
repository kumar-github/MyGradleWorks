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
@Table(name = "market", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Market.findAll", query = "SELECT m FROM Market m"),
    @NamedQuery(name = "Market.findByMktCode", query = "SELECT m FROM Market m WHERE m.mktCode = :mktCode"),
    @NamedQuery(name = "Market.findByMktType", query = "SELECT m FROM Market m WHERE m.mktType = :mktType"),
    @NamedQuery(name = "Market.findByMktStatus", query = "SELECT m FROM Market m WHERE m.mktStatus = :mktStatus"),
    @NamedQuery(name = "Market.findByMktShortName", query = "SELECT m FROM Market m WHERE m.mktShortName = :mktShortName"),
    @NamedQuery(name = "Market.findByMktFullName", query = "SELECT m FROM Market m WHERE m.mktFullName = :mktFullName"),
    @NamedQuery(name = "Market.findByTransId", query = "SELECT m FROM Market m WHERE m.transId = :transId")})
public class Market implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "mkt_code", columnDefinition="CHAR")
    private String mktCode;
    
    @Basic(optional = false)
    @Column(name = "mkt_type")
    private Character mktType;
    @Basic(optional = false)
    @Column(name = "mkt_status")
    private Character mktStatus;
    @Basic(optional = false)
    @Column(name = "mkt_short_name")
    private String mktShortName;
    @Basic(optional = false)
    @Column(name = "mkt_full_name")
    private String mktFullName;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mktCode")
    private Collection<CommodityMarket> commodityMarketCollection;

    public Market() {
    }

    public Market(String mktCode) {
        this.mktCode = mktCode;
    }

    public Market(String mktCode, Character mktType, Character mktStatus, String mktShortName, String mktFullName, int transId) {
        this.mktCode = mktCode;
        this.mktType = mktType;
        this.mktStatus = mktStatus;
        this.mktShortName = mktShortName;
        this.mktFullName = mktFullName;
        this.transId = transId;
    }

    public String getMktCode() {
        return mktCode;
    }

    public void setMktCode(String mktCode) {
        this.mktCode = mktCode;
    }

    public Character getMktType() {
        return mktType;
    }

    public void setMktType(Character mktType) {
        this.mktType = mktType;
    }

    public Character getMktStatus() {
        return mktStatus;
    }

    public void setMktStatus(Character mktStatus) {
        this.mktStatus = mktStatus;
    }

    public String getMktShortName() {
        return mktShortName;
    }

    public void setMktShortName(String mktShortName) {
        this.mktShortName = mktShortName;
    }

    public String getMktFullName() {
        return mktFullName;
    }

    public void setMktFullName(String mktFullName) {
        this.mktFullName = mktFullName;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<CommodityMarket> getCommodityMarketCollection() {
        return commodityMarketCollection;
    }

    public void setCommodityMarketCollection(Collection<CommodityMarket> commodityMarketCollection) {
        this.commodityMarketCollection = commodityMarketCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mktCode != null ? mktCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Market)) {
            return false;
        }
        Market other = (Market) object;
        if ((this.mktCode == null && other.mktCode != null) || (this.mktCode != null && !this.mktCode.equals(other.mktCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Market[ mktCode=" + mktCode + " ]";
    }
    
}
