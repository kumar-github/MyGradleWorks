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
@Table(name = "price_source", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PriceSource.findAll", query = "SELECT p FROM PriceSource p"),
    @NamedQuery(name = "PriceSource.findByPriceSourceCode", query = "SELECT p FROM PriceSource p WHERE p.priceSourceCode = :priceSourceCode"),
    @NamedQuery(name = "PriceSource.findByPriceSourceName", query = "SELECT p FROM PriceSource p WHERE p.priceSourceName = :priceSourceName"),
    @NamedQuery(name = "PriceSource.findByPriceSourceType", query = "SELECT p FROM PriceSource p WHERE p.priceSourceType = :priceSourceType"),
    @NamedQuery(name = "PriceSource.findByTransId", query = "SELECT p FROM PriceSource p WHERE p.transId = :transId")})
public class PriceSource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "price_source_code", columnDefinition="CHAR")
    private String priceSourceCode;
    
    @Basic(optional = false)
    @Column(name = "price_source_name")
    private String priceSourceName;
    @Column(name = "price_source_type")
    private Character priceSourceType;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(mappedBy = "secPriceSourceCode")
    private Collection<CommktOptionAttr> commktOptionAttrCollection;
    @OneToMany(mappedBy = "mtmPriceSourceCode")
    private Collection<CommodityMarket> commodityMarketCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "priceSource")
    private Collection<CommodityMarketFormula> commodityMarketFormulaCollection;
    @OneToMany(mappedBy = "secPriceSourceCode")
    private Collection<CommktPhysicalAttr> commktPhysicalAttrCollection;
    @OneToMany(mappedBy = "secPriceSourceCode")
    private Collection<CommktFutureAttr> commktFutureAttrCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "priceSource")
    private Collection<CommodityMarketSource> commodityMarketSourceCollection;

    public PriceSource() {
    }

    public PriceSource(String priceSourceCode) {
        this.priceSourceCode = priceSourceCode;
    }

    public PriceSource(String priceSourceCode, String priceSourceName, int transId) {
        this.priceSourceCode = priceSourceCode;
        this.priceSourceName = priceSourceName;
        this.transId = transId;
    }

    public String getPriceSourceCode() {
        return priceSourceCode;
    }

    public void setPriceSourceCode(String priceSourceCode) {
        this.priceSourceCode = priceSourceCode;
    }

    public String getPriceSourceName() {
        return priceSourceName;
    }

    public void setPriceSourceName(String priceSourceName) {
        this.priceSourceName = priceSourceName;
    }

    public Character getPriceSourceType() {
        return priceSourceType;
    }

    public void setPriceSourceType(Character priceSourceType) {
        this.priceSourceType = priceSourceType;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<CommktOptionAttr> getCommktOptionAttrCollection() {
        return commktOptionAttrCollection;
    }

    public void setCommktOptionAttrCollection(Collection<CommktOptionAttr> commktOptionAttrCollection) {
        this.commktOptionAttrCollection = commktOptionAttrCollection;
    }

    @XmlTransient
    public Collection<CommodityMarket> getCommodityMarketCollection() {
        return commodityMarketCollection;
    }

    public void setCommodityMarketCollection(Collection<CommodityMarket> commodityMarketCollection) {
        this.commodityMarketCollection = commodityMarketCollection;
    }

    @XmlTransient
    public Collection<CommodityMarketFormula> getCommodityMarketFormulaCollection() {
        return commodityMarketFormulaCollection;
    }

    public void setCommodityMarketFormulaCollection(Collection<CommodityMarketFormula> commodityMarketFormulaCollection) {
        this.commodityMarketFormulaCollection = commodityMarketFormulaCollection;
    }

    @XmlTransient
    public Collection<CommktPhysicalAttr> getCommktPhysicalAttrCollection() {
        return commktPhysicalAttrCollection;
    }

    public void setCommktPhysicalAttrCollection(Collection<CommktPhysicalAttr> commktPhysicalAttrCollection) {
        this.commktPhysicalAttrCollection = commktPhysicalAttrCollection;
    }

    @XmlTransient
    public Collection<CommktFutureAttr> getCommktFutureAttrCollection() {
        return commktFutureAttrCollection;
    }

    public void setCommktFutureAttrCollection(Collection<CommktFutureAttr> commktFutureAttrCollection) {
        this.commktFutureAttrCollection = commktFutureAttrCollection;
    }

    @XmlTransient
    public Collection<CommodityMarketSource> getCommodityMarketSourceCollection() {
        return commodityMarketSourceCollection;
    }

    public void setCommodityMarketSourceCollection(Collection<CommodityMarketSource> commodityMarketSourceCollection) {
        this.commodityMarketSourceCollection = commodityMarketSourceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (priceSourceCode != null ? priceSourceCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PriceSource)) {
            return false;
        }
        PriceSource other = (PriceSource) object;
        if ((this.priceSourceCode == null && other.priceSourceCode != null) || (this.priceSourceCode != null && !this.priceSourceCode.equals(other.priceSourceCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PriceSource[ priceSourceCode=" + priceSourceCode + " ]";
    }
    
}
