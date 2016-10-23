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
import javax.persistence.JoinColumns;
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
@Table(name = "commodity_market_formula", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommodityMarketFormula.findAll", query = "SELECT c FROM CommodityMarketFormula c"),
    @NamedQuery(name = "CommodityMarketFormula.findByCommktKey", query = "SELECT c FROM CommodityMarketFormula c WHERE c.commodityMarketFormulaPK.commktKey = :commktKey"),
    @NamedQuery(name = "CommodityMarketFormula.findByTradingPrd", query = "SELECT c FROM CommodityMarketFormula c WHERE c.commodityMarketFormulaPK.tradingPrd = :tradingPrd"),
    @NamedQuery(name = "CommodityMarketFormula.findByPriceSourceCode", query = "SELECT c FROM CommodityMarketFormula c WHERE c.commodityMarketFormulaPK.priceSourceCode = :priceSourceCode"),
    @NamedQuery(name = "CommodityMarketFormula.findByLowBidFormulaNum", query = "SELECT c FROM CommodityMarketFormula c WHERE c.lowBidFormulaNum = :lowBidFormulaNum"),
    @NamedQuery(name = "CommodityMarketFormula.findByHighAskedFormulaNum", query = "SELECT c FROM CommodityMarketFormula c WHERE c.highAskedFormulaNum = :highAskedFormulaNum"),
    @NamedQuery(name = "CommodityMarketFormula.findByAvgClosedFormulaNum", query = "SELECT c FROM CommodityMarketFormula c WHERE c.avgClosedFormulaNum = :avgClosedFormulaNum"),
    @NamedQuery(name = "CommodityMarketFormula.findByLowBidSimpleFormulaNum", query = "SELECT c FROM CommodityMarketFormula c WHERE c.lowBidSimpleFormulaNum = :lowBidSimpleFormulaNum"),
    @NamedQuery(name = "CommodityMarketFormula.findByHighAskedSimpleFormulaNum", query = "SELECT c FROM CommodityMarketFormula c WHERE c.highAskedSimpleFormulaNum = :highAskedSimpleFormulaNum"),
    @NamedQuery(name = "CommodityMarketFormula.findByAvgClosedSimpleFormulaNum", query = "SELECT c FROM CommodityMarketFormula c WHERE c.avgClosedSimpleFormulaNum = :avgClosedSimpleFormulaNum"),
    @NamedQuery(name = "CommodityMarketFormula.findByCmfNum", query = "SELECT c FROM CommodityMarketFormula c WHERE c.cmfNum = :cmfNum"),
    @NamedQuery(name = "CommodityMarketFormula.findByMptNum", query = "SELECT c FROM CommodityMarketFormula c WHERE c.mptNum = :mptNum"),
    @NamedQuery(name = "CommodityMarketFormula.findByTransId", query = "SELECT c FROM CommodityMarketFormula c WHERE c.transId = :transId")})
public class CommodityMarketFormula implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommodityMarketFormulaPK commodityMarketFormulaPK;
    @Column(name = "low_bid_formula_num")
    private Integer lowBidFormulaNum;
    @Column(name = "high_asked_formula_num")
    private Integer highAskedFormulaNum;
    @Column(name = "avg_closed_formula_num")
    private Integer avgClosedFormulaNum;
    @Column(name = "low_bid_simple_formula_num")
    private Integer lowBidSimpleFormulaNum;
    @Column(name = "high_asked_simple_formula_num")
    private Integer highAskedSimpleFormulaNum;
    @Column(name = "avg_closed_simple_formula_num")
    private Integer avgClosedSimpleFormulaNum;
    @Basic(optional = false)
    @Column(name = "cmf_num")
    private int cmfNum;
    @Column(name = "mpt_num")
    private Integer mptNum;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumn(name = "price_source_code", referencedColumnName = "price_source_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PriceSource priceSource;
    @JoinColumns({
        @JoinColumn(name = "commkt_key", referencedColumnName = "commkt_key", insertable = false, updatable = false),
        @JoinColumn(name = "trading_prd", referencedColumnName = "trading_prd", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private TradingPeriod tradingPeriod;

    public CommodityMarketFormula() {
    }

    public CommodityMarketFormula(CommodityMarketFormulaPK commodityMarketFormulaPK) {
        this.commodityMarketFormulaPK = commodityMarketFormulaPK;
    }

    public CommodityMarketFormula(CommodityMarketFormulaPK commodityMarketFormulaPK, int cmfNum, int transId) {
        this.commodityMarketFormulaPK = commodityMarketFormulaPK;
        this.cmfNum = cmfNum;
        this.transId = transId;
    }

    public CommodityMarketFormula(int commktKey, String tradingPrd, String priceSourceCode) {
        this.commodityMarketFormulaPK = new CommodityMarketFormulaPK(commktKey, tradingPrd, priceSourceCode);
    }

    public CommodityMarketFormulaPK getCommodityMarketFormulaPK() {
        return commodityMarketFormulaPK;
    }

    public void setCommodityMarketFormulaPK(CommodityMarketFormulaPK commodityMarketFormulaPK) {
        this.commodityMarketFormulaPK = commodityMarketFormulaPK;
    }

    public Integer getLowBidFormulaNum() {
        return lowBidFormulaNum;
    }

    public void setLowBidFormulaNum(Integer lowBidFormulaNum) {
        this.lowBidFormulaNum = lowBidFormulaNum;
    }

    public Integer getHighAskedFormulaNum() {
        return highAskedFormulaNum;
    }

    public void setHighAskedFormulaNum(Integer highAskedFormulaNum) {
        this.highAskedFormulaNum = highAskedFormulaNum;
    }

    public Integer getAvgClosedFormulaNum() {
        return avgClosedFormulaNum;
    }

    public void setAvgClosedFormulaNum(Integer avgClosedFormulaNum) {
        this.avgClosedFormulaNum = avgClosedFormulaNum;
    }

    public Integer getLowBidSimpleFormulaNum() {
        return lowBidSimpleFormulaNum;
    }

    public void setLowBidSimpleFormulaNum(Integer lowBidSimpleFormulaNum) {
        this.lowBidSimpleFormulaNum = lowBidSimpleFormulaNum;
    }

    public Integer getHighAskedSimpleFormulaNum() {
        return highAskedSimpleFormulaNum;
    }

    public void setHighAskedSimpleFormulaNum(Integer highAskedSimpleFormulaNum) {
        this.highAskedSimpleFormulaNum = highAskedSimpleFormulaNum;
    }

    public Integer getAvgClosedSimpleFormulaNum() {
        return avgClosedSimpleFormulaNum;
    }

    public void setAvgClosedSimpleFormulaNum(Integer avgClosedSimpleFormulaNum) {
        this.avgClosedSimpleFormulaNum = avgClosedSimpleFormulaNum;
    }

    public int getCmfNum() {
        return cmfNum;
    }

    public void setCmfNum(int cmfNum) {
        this.cmfNum = cmfNum;
    }

    public Integer getMptNum() {
        return mptNum;
    }

    public void setMptNum(Integer mptNum) {
        this.mptNum = mptNum;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public PriceSource getPriceSource() {
        return priceSource;
    }

    public void setPriceSource(PriceSource priceSource) {
        this.priceSource = priceSource;
    }

    public TradingPeriod getTradingPeriod() {
        return tradingPeriod;
    }

    public void setTradingPeriod(TradingPeriod tradingPeriod) {
        this.tradingPeriod = tradingPeriod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commodityMarketFormulaPK != null ? commodityMarketFormulaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityMarketFormula)) {
            return false;
        }
        CommodityMarketFormula other = (CommodityMarketFormula) object;
        if ((this.commodityMarketFormulaPK == null && other.commodityMarketFormulaPK != null) || (this.commodityMarketFormulaPK != null && !this.commodityMarketFormulaPK.equals(other.commodityMarketFormulaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityMarketFormula[ commodityMarketFormulaPK=" + commodityMarketFormulaPK + " ]";
    }
    
}
