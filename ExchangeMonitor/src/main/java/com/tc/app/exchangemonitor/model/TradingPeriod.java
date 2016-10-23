/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "trading_period", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TradingPeriod.findAll", query = "SELECT t FROM TradingPeriod t"),
    @NamedQuery(name = "TradingPeriod.findByCommktKey", query = "SELECT t FROM TradingPeriod t WHERE t.tradingPeriodPK.commktKey = :commktKey"),
    @NamedQuery(name = "TradingPeriod.findByTradingPrd", query = "SELECT t FROM TradingPeriod t WHERE t.tradingPeriodPK.tradingPrd = :tradingPrd"),
    @NamedQuery(name = "TradingPeriod.findByLastTradeDate", query = "SELECT t FROM TradingPeriod t WHERE t.lastTradeDate = :lastTradeDate"),
    @NamedQuery(name = "TradingPeriod.findByOptExpDate", query = "SELECT t FROM TradingPeriod t WHERE t.optExpDate = :optExpDate"),
    @NamedQuery(name = "TradingPeriod.findByFirstDelDate", query = "SELECT t FROM TradingPeriod t WHERE t.firstDelDate = :firstDelDate"),
    @NamedQuery(name = "TradingPeriod.findByLastDelDate", query = "SELECT t FROM TradingPeriod t WHERE t.lastDelDate = :lastDelDate"),
    @NamedQuery(name = "TradingPeriod.findByFirstIssueDate", query = "SELECT t FROM TradingPeriod t WHERE t.firstIssueDate = :firstIssueDate"),
    @NamedQuery(name = "TradingPeriod.findByLastIssueDate", query = "SELECT t FROM TradingPeriod t WHERE t.lastIssueDate = :lastIssueDate"),
    @NamedQuery(name = "TradingPeriod.findByLastQuoteDate", query = "SELECT t FROM TradingPeriod t WHERE t.lastQuoteDate = :lastQuoteDate"),
    @NamedQuery(name = "TradingPeriod.findByTradingPrdDesc", query = "SELECT t FROM TradingPeriod t WHERE t.tradingPrdDesc = :tradingPrdDesc"),
    @NamedQuery(name = "TradingPeriod.findByOptEvalMethod", query = "SELECT t FROM TradingPeriod t WHERE t.optEvalMethod = :optEvalMethod"),
    @NamedQuery(name = "TradingPeriod.findByTransId", query = "SELECT t FROM TradingPeriod t WHERE t.transId = :transId")})
public class TradingPeriod implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TradingPeriodPK tradingPeriodPK;
    @Column(name = "last_trade_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastTradeDate;
    @Column(name = "opt_exp_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date optExpDate;
    @Column(name = "first_del_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstDelDate;
    @Column(name = "last_del_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastDelDate;
    @Column(name = "first_issue_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstIssueDate;
    @Column(name = "last_issue_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastIssueDate;
    @Column(name = "last_quote_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastQuoteDate;
    @Column(name = "trading_prd_desc")
    private String tradingPrdDesc;
    @Column(name = "opt_eval_method")
    private Character optEvalMethod;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tradingPeriod")
    private Collection<CommodityMarketFormula> commodityMarketFormulaCollection;
    @JoinColumn(name = "commkt_key", referencedColumnName = "commkt_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CommodityMarket commodityMarket;

    public TradingPeriod() {
    }

    public TradingPeriod(TradingPeriodPK tradingPeriodPK) {
        this.tradingPeriodPK = tradingPeriodPK;
    }

    public TradingPeriod(TradingPeriodPK tradingPeriodPK, int transId) {
        this.tradingPeriodPK = tradingPeriodPK;
        this.transId = transId;
    }

    public TradingPeriod(int commktKey, String tradingPrd) {
        this.tradingPeriodPK = new TradingPeriodPK(commktKey, tradingPrd);
    }

    public TradingPeriodPK getTradingPeriodPK() {
        return tradingPeriodPK;
    }

    public void setTradingPeriodPK(TradingPeriodPK tradingPeriodPK) {
        this.tradingPeriodPK = tradingPeriodPK;
    }

    public Date getLastTradeDate() {
        return lastTradeDate;
    }

    public void setLastTradeDate(Date lastTradeDate) {
        this.lastTradeDate = lastTradeDate;
    }

    public Date getOptExpDate() {
        return optExpDate;
    }

    public void setOptExpDate(Date optExpDate) {
        this.optExpDate = optExpDate;
    }

    public Date getFirstDelDate() {
        return firstDelDate;
    }

    public void setFirstDelDate(Date firstDelDate) {
        this.firstDelDate = firstDelDate;
    }

    public Date getLastDelDate() {
        return lastDelDate;
    }

    public void setLastDelDate(Date lastDelDate) {
        this.lastDelDate = lastDelDate;
    }

    public Date getFirstIssueDate() {
        return firstIssueDate;
    }

    public void setFirstIssueDate(Date firstIssueDate) {
        this.firstIssueDate = firstIssueDate;
    }

    public Date getLastIssueDate() {
        return lastIssueDate;
    }

    public void setLastIssueDate(Date lastIssueDate) {
        this.lastIssueDate = lastIssueDate;
    }

    public Date getLastQuoteDate() {
        return lastQuoteDate;
    }

    public void setLastQuoteDate(Date lastQuoteDate) {
        this.lastQuoteDate = lastQuoteDate;
    }

    public String getTradingPrdDesc() {
        return tradingPrdDesc;
    }

    public void setTradingPrdDesc(String tradingPrdDesc) {
        this.tradingPrdDesc = tradingPrdDesc;
    }

    public Character getOptEvalMethod() {
        return optEvalMethod;
    }

    public void setOptEvalMethod(Character optEvalMethod) {
        this.optEvalMethod = optEvalMethod;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<CommodityMarketFormula> getCommodityMarketFormulaCollection() {
        return commodityMarketFormulaCollection;
    }

    public void setCommodityMarketFormulaCollection(Collection<CommodityMarketFormula> commodityMarketFormulaCollection) {
        this.commodityMarketFormulaCollection = commodityMarketFormulaCollection;
    }

    public CommodityMarket getCommodityMarket() {
        return commodityMarket;
    }

    public void setCommodityMarket(CommodityMarket commodityMarket) {
        this.commodityMarket = commodityMarket;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tradingPeriodPK != null ? tradingPeriodPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TradingPeriod)) {
            return false;
        }
        TradingPeriod other = (TradingPeriod) object;
        if ((this.tradingPeriodPK == null && other.tradingPeriodPK != null) || (this.tradingPeriodPK != null && !this.tradingPeriodPK.equals(other.tradingPeriodPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TradingPeriod[ tradingPeriodPK=" + tradingPeriodPK + " ]";
    }
    
}
