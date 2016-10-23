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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "commodity_market_source", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommodityMarketSource.findAll", query = "SELECT c FROM CommodityMarketSource c"),
    @NamedQuery(name = "CommodityMarketSource.findByCommktKey", query = "SELECT c FROM CommodityMarketSource c WHERE c.commodityMarketSourcePK.commktKey = :commktKey"),
    @NamedQuery(name = "CommodityMarketSource.findByPriceSourceCode", query = "SELECT c FROM CommodityMarketSource c WHERE c.commodityMarketSourcePK.priceSourceCode = :priceSourceCode"),
    @NamedQuery(name = "CommodityMarketSource.findByTvmUseInd", query = "SELECT c FROM CommodityMarketSource c WHERE c.tvmUseInd = :tvmUseInd"),
    @NamedQuery(name = "CommodityMarketSource.findByOptionEvalUseInd", query = "SELECT c FROM CommodityMarketSource c WHERE c.optionEvalUseInd = :optionEvalUseInd"),
    @NamedQuery(name = "CommodityMarketSource.findByFinancialBorrowUseInd", query = "SELECT c FROM CommodityMarketSource c WHERE c.financialBorrowUseInd = :financialBorrowUseInd"),
    @NamedQuery(name = "CommodityMarketSource.findByFinancialLendUseInd", query = "SELECT c FROM CommodityMarketSource c WHERE c.financialLendUseInd = :financialLendUseInd"),
    @NamedQuery(name = "CommodityMarketSource.findByQuotePricePrecision", query = "SELECT c FROM CommodityMarketSource c WHERE c.quotePricePrecision = :quotePricePrecision"),
    @NamedQuery(name = "CommodityMarketSource.findByTransId", query = "SELECT c FROM CommodityMarketSource c WHERE c.transId = :transId")})
public class CommodityMarketSource implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommodityMarketSourcePK commodityMarketSourcePK;
    @Column(name = "tvm_use_ind")
    private Character tvmUseInd;
    @Column(name = "option_eval_use_ind")
    private Character optionEvalUseInd;
    @Column(name = "financial_borrow_use_ind")
    private Character financialBorrowUseInd;
    @Column(name = "financial_lend_use_ind")
    private Character financialLendUseInd;
    
    @Column(name = "quote_price_precision", columnDefinition="TINYINT")
    private Short quotePricePrecision;
    
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commodityMarketSource")
    private Collection<CommktSourceRollDate> commktSourceRollDateCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commodityMarketSource")
    private Collection<CommktSourceAlias> commktSourceAliasCollection;
    @JoinColumn(name = "dflt_alias_source_code", referencedColumnName = "alias_source_code")
    @ManyToOne
    private AliasSource dfltAliasSourceCode;
    
    /*@JoinColumn(name = "calendar_code", referencedColumnName = "calendar_code")
    @ManyToOne
    private Calendar calendarCode;*/
    
    @JoinColumn(name = "commkt_key", referencedColumnName = "commkt_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CommodityMarket commodityMarket;
    @JoinColumn(name = "price_source_code", referencedColumnName = "price_source_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PriceSource priceSource;

    public CommodityMarketSource() {
    }

    public CommodityMarketSource(CommodityMarketSourcePK commodityMarketSourcePK) {
        this.commodityMarketSourcePK = commodityMarketSourcePK;
    }

    public CommodityMarketSource(CommodityMarketSourcePK commodityMarketSourcePK, int transId) {
        this.commodityMarketSourcePK = commodityMarketSourcePK;
        this.transId = transId;
    }

    public CommodityMarketSource(int commktKey, String priceSourceCode) {
        this.commodityMarketSourcePK = new CommodityMarketSourcePK(commktKey, priceSourceCode);
    }

    public CommodityMarketSourcePK getCommodityMarketSourcePK() {
        return commodityMarketSourcePK;
    }

    public void setCommodityMarketSourcePK(CommodityMarketSourcePK commodityMarketSourcePK) {
        this.commodityMarketSourcePK = commodityMarketSourcePK;
    }

    public Character getTvmUseInd() {
        return tvmUseInd;
    }

    public void setTvmUseInd(Character tvmUseInd) {
        this.tvmUseInd = tvmUseInd;
    }

    public Character getOptionEvalUseInd() {
        return optionEvalUseInd;
    }

    public void setOptionEvalUseInd(Character optionEvalUseInd) {
        this.optionEvalUseInd = optionEvalUseInd;
    }

    public Character getFinancialBorrowUseInd() {
        return financialBorrowUseInd;
    }

    public void setFinancialBorrowUseInd(Character financialBorrowUseInd) {
        this.financialBorrowUseInd = financialBorrowUseInd;
    }

    public Character getFinancialLendUseInd() {
        return financialLendUseInd;
    }

    public void setFinancialLendUseInd(Character financialLendUseInd) {
        this.financialLendUseInd = financialLendUseInd;
    }

    public Short getQuotePricePrecision() {
        return quotePricePrecision;
    }

    public void setQuotePricePrecision(Short quotePricePrecision) {
        this.quotePricePrecision = quotePricePrecision;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<CommktSourceRollDate> getCommktSourceRollDateCollection() {
        return commktSourceRollDateCollection;
    }

    public void setCommktSourceRollDateCollection(Collection<CommktSourceRollDate> commktSourceRollDateCollection) {
        this.commktSourceRollDateCollection = commktSourceRollDateCollection;
    }

    @XmlTransient
    public Collection<CommktSourceAlias> getCommktSourceAliasCollection() {
        return commktSourceAliasCollection;
    }

    public void setCommktSourceAliasCollection(Collection<CommktSourceAlias> commktSourceAliasCollection) {
        this.commktSourceAliasCollection = commktSourceAliasCollection;
    }

    public AliasSource getDfltAliasSourceCode() {
        return dfltAliasSourceCode;
    }

    public void setDfltAliasSourceCode(AliasSource dfltAliasSourceCode) {
        this.dfltAliasSourceCode = dfltAliasSourceCode;
    }

    /*public Calendar getCalendarCode() {
        return calendarCode;
    }

    public void setCalendarCode(Calendar calendarCode) {
        this.calendarCode = calendarCode;
    }*/

    public CommodityMarket getCommodityMarket() {
        return commodityMarket;
    }

    public void setCommodityMarket(CommodityMarket commodityMarket) {
        this.commodityMarket = commodityMarket;
    }

    public PriceSource getPriceSource() {
        return priceSource;
    }

    public void setPriceSource(PriceSource priceSource) {
        this.priceSource = priceSource;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commodityMarketSourcePK != null ? commodityMarketSourcePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityMarketSource)) {
            return false;
        }
        CommodityMarketSource other = (CommodityMarketSource) object;
        if ((this.commodityMarketSourcePK == null && other.commodityMarketSourcePK != null) || (this.commodityMarketSourcePK != null && !this.commodityMarketSourcePK.equals(other.commodityMarketSourcePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityMarketSource[ commodityMarketSourcePK=" + commodityMarketSourcePK + " ]";
    }
    
}
