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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "commodity_market", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommodityMarket.findAll", query = "SELECT c FROM CommodityMarket c"),
    @NamedQuery(name = "CommodityMarket.findByCommktKey", query = "SELECT c FROM CommodityMarket c WHERE c.commktKey = :commktKey"),
    @NamedQuery(name = "CommodityMarket.findByDfltOptEvalMethod", query = "SELECT c FROM CommodityMarket c WHERE c.dfltOptEvalMethod = :dfltOptEvalMethod"),
    @NamedQuery(name = "CommodityMarket.findByTransId", query = "SELECT c FROM CommodityMarket c WHERE c.transId = :transId"),
    @NamedQuery(name = "CommodityMarket.findByManInputSecQtyRequired", query = "SELECT c FROM CommodityMarket c WHERE c.manInputSecQtyRequired = :manInputSecQtyRequired")})
public class CommodityMarket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "commkt_key")
    private Integer commktKey;
    @Column(name = "dflt_opt_eval_method")
    private Character dfltOptEvalMethod;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @Column(name = "man_input_sec_qty_required")
    private Character manInputSecQtyRequired;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "commodityMarket")
    private CommktOptionAttr commktOptionAttr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commktKey")
    private Collection<ExternalPosition> externalPositionCollection;
    @JoinColumn(name = "cmdty_code", referencedColumnName = "cmdty_code")
    @ManyToOne(optional = false)
    private Commodity cmdtyCode;
    
    @JoinColumn(name = "mkt_code", referencedColumnName = "mkt_code", columnDefinition="CHAR")
    @ManyToOne(optional = false)
    private Market mktCode;
    
    @JoinColumn(name = "mtm_price_source_code", referencedColumnName = "price_source_code", columnDefinition="CHAR")
    @ManyToOne
    private PriceSource mtmPriceSourceCode;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "commodityMarket")
    private CommktClrdSwapAttr commktClrdSwapAttr;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "commodityMarket")
    private CommktPhysicalAttr commktPhysicalAttr;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "commodityMarket")
    private CommktFutureAttr commktFutureAttr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commodityMarket")
    private Collection<CommodityMarketAlias> commodityMarketAliasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commodityMarket")
    private Collection<TradingPeriod> tradingPeriodCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commodityMarket")
    private Collection<CommodityMarketSource> commodityMarketSourceCollection;

    public CommodityMarket() {
    }

    public CommodityMarket(Integer commktKey) {
        this.commktKey = commktKey;
    }

    public CommodityMarket(Integer commktKey, int transId) {
        this.commktKey = commktKey;
        this.transId = transId;
    }

    public Integer getCommktKey() {
        return commktKey;
    }

    public void setCommktKey(Integer commktKey) {
        this.commktKey = commktKey;
    }

    public Character getDfltOptEvalMethod() {
        return dfltOptEvalMethod;
    }

    public void setDfltOptEvalMethod(Character dfltOptEvalMethod) {
        this.dfltOptEvalMethod = dfltOptEvalMethod;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public Character getManInputSecQtyRequired() {
        return manInputSecQtyRequired;
    }

    public void setManInputSecQtyRequired(Character manInputSecQtyRequired) {
        this.manInputSecQtyRequired = manInputSecQtyRequired;
    }

    public CommktOptionAttr getCommktOptionAttr() {
        return commktOptionAttr;
    }

    public void setCommktOptionAttr(CommktOptionAttr commktOptionAttr) {
        this.commktOptionAttr = commktOptionAttr;
    }

    @XmlTransient
    public Collection<ExternalPosition> getExternalPositionCollection() {
        return externalPositionCollection;
    }

    public void setExternalPositionCollection(Collection<ExternalPosition> externalPositionCollection) {
        this.externalPositionCollection = externalPositionCollection;
    }

    public Commodity getCmdtyCode() {
        return cmdtyCode;
    }

    public void setCmdtyCode(Commodity cmdtyCode) {
        this.cmdtyCode = cmdtyCode;
    }

    public Market getMktCode() {
        return mktCode;
    }

    public void setMktCode(Market mktCode) {
        this.mktCode = mktCode;
    }

    public PriceSource getMtmPriceSourceCode() {
        return mtmPriceSourceCode;
    }

    public void setMtmPriceSourceCode(PriceSource mtmPriceSourceCode) {
        this.mtmPriceSourceCode = mtmPriceSourceCode;
    }

    public CommktClrdSwapAttr getCommktClrdSwapAttr() {
        return commktClrdSwapAttr;
    }

    public void setCommktClrdSwapAttr(CommktClrdSwapAttr commktClrdSwapAttr) {
        this.commktClrdSwapAttr = commktClrdSwapAttr;
    }

    public CommktPhysicalAttr getCommktPhysicalAttr() {
        return commktPhysicalAttr;
    }

    public void setCommktPhysicalAttr(CommktPhysicalAttr commktPhysicalAttr) {
        this.commktPhysicalAttr = commktPhysicalAttr;
    }

    public CommktFutureAttr getCommktFutureAttr() {
        return commktFutureAttr;
    }

    public void setCommktFutureAttr(CommktFutureAttr commktFutureAttr) {
        this.commktFutureAttr = commktFutureAttr;
    }

    @XmlTransient
    public Collection<CommodityMarketAlias> getCommodityMarketAliasCollection() {
        return commodityMarketAliasCollection;
    }

    public void setCommodityMarketAliasCollection(Collection<CommodityMarketAlias> commodityMarketAliasCollection) {
        this.commodityMarketAliasCollection = commodityMarketAliasCollection;
    }

    @XmlTransient
    public Collection<TradingPeriod> getTradingPeriodCollection() {
        return tradingPeriodCollection;
    }

    public void setTradingPeriodCollection(Collection<TradingPeriod> tradingPeriodCollection) {
        this.tradingPeriodCollection = tradingPeriodCollection;
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
        hash += (commktKey != null ? commktKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityMarket)) {
            return false;
        }
        CommodityMarket other = (CommodityMarket) object;
        if ((this.commktKey == null && other.commktKey != null) || (this.commktKey != null && !this.commktKey.equals(other.commktKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityMarket[ commktKey=" + commktKey + " ]";
    }
    
}
