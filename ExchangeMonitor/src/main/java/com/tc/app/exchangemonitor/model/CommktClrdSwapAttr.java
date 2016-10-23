/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "commkt_clrd_swap_attr", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommktClrdSwapAttr.findAll", query = "SELECT c FROM CommktClrdSwapAttr c"),
    @NamedQuery(name = "CommktClrdSwapAttr.findByCommktKey", query = "SELECT c FROM CommktClrdSwapAttr c WHERE c.commktKey = :commktKey"),
    @NamedQuery(name = "CommktClrdSwapAttr.findByStatus", query = "SELECT c FROM CommktClrdSwapAttr c WHERE c.status = :status"),
    @NamedQuery(name = "CommktClrdSwapAttr.findByCommktLotSize", query = "SELECT c FROM CommktClrdSwapAttr c WHERE c.commktLotSize = :commktLotSize"),
    @NamedQuery(name = "CommktClrdSwapAttr.findByCommktSettlementInd", query = "SELECT c FROM CommktClrdSwapAttr c WHERE c.commktSettlementInd = :commktSettlementInd"),
    @NamedQuery(name = "CommktClrdSwapAttr.findByCommktTradingMthInd", query = "SELECT c FROM CommktClrdSwapAttr c WHERE c.commktTradingMthInd = :commktTradingMthInd"),
    @NamedQuery(name = "CommktClrdSwapAttr.findByCommktNearbyMask", query = "SELECT c FROM CommktClrdSwapAttr c WHERE c.commktNearbyMask = :commktNearbyMask"),
    @NamedQuery(name = "CommktClrdSwapAttr.findByCommktNumMthOut", query = "SELECT c FROM CommktClrdSwapAttr c WHERE c.commktNumMthOut = :commktNumMthOut"),
    @NamedQuery(name = "CommktClrdSwapAttr.findByTradingPrdOffset", query = "SELECT c FROM CommktClrdSwapAttr c WHERE c.tradingPrdOffset = :tradingPrdOffset"),
    @NamedQuery(name = "CommktClrdSwapAttr.findByLongShortInd", query = "SELECT c FROM CommktClrdSwapAttr c WHERE c.longShortInd = :longShortInd"),
    @NamedQuery(name = "CommktClrdSwapAttr.findBySpreadQtyFactor", query = "SELECT c FROM CommktClrdSwapAttr c WHERE c.spreadQtyFactor = :spreadQtyFactor"),
    @NamedQuery(name = "CommktClrdSwapAttr.findByTransId", query = "SELECT c FROM CommktClrdSwapAttr c WHERE c.transId = :transId"),
    @NamedQuery(name = "CommktClrdSwapAttr.findByMarginType", query = "SELECT c FROM CommktClrdSwapAttr c WHERE c.marginType = :marginType")})
public class CommktClrdSwapAttr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "commkt_key")
    private Integer commktKey;
    @Basic(optional = false)
    @Column(name = "status")
    private Character status;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "commkt_lot_size", columnDefinition="DECIMAL")
    private BigDecimal commktLotSize;
    
    @Basic(optional = false)
    @Column(name = "commkt_settlement_ind")
    private Character commktSettlementInd;
    
    @Column(name = "commkt_trading_mth_ind", columnDefinition="CHAR")
    private String commktTradingMthInd;
    
    @Column(name = "commkt_nearby_mask", columnDefinition="CHAR")
    private String commktNearbyMask;
    
    @Basic(optional = false)
    @Column(name = "commkt_num_mth_out")
    private short commktNumMthOut;
    @Basic(optional = false)
    @Column(name = "trading_prd_offset")
    private int tradingPrdOffset;
    @Basic(optional = false)
    @Column(name = "long_short_ind")
    private Character longShortInd;
    @Basic(optional = false)
    @Column(name = "spread_qty_factor")
    private int spreadQtyFactor;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @Basic(optional = false)
    @Column(name = "margin_type")
    private Character marginType;
    
    @JoinColumn(name = "commkt_curr_code", referencedColumnName = "cmdty_code", columnDefinition="CHAR")
    @ManyToOne(optional = false)
    private Commodity commktCurrCode;
    
    @JoinColumn(name = "comp_cmdty_code", referencedColumnName = "cmdty_code", columnDefinition="CHAR")
    @ManyToOne(optional = false)
    private Commodity compCmdtyCode;
    @JoinColumn(name = "commkt_key", referencedColumnName = "commkt_key", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private CommodityMarket commodityMarket;
    
    @JoinColumn(name = "commkt_lot_uom_code", referencedColumnName = "uom_code", columnDefinition="CHAR")
    @ManyToOne(optional = false)
    private Uom commktLotUomCode;
    
    @JoinColumn(name = "commkt_price_uom_code", referencedColumnName = "uom_code", columnDefinition="CHAR")
    @ManyToOne(optional = false)
    private Uom commktPriceUomCode;

    public CommktClrdSwapAttr() {
    }

    public CommktClrdSwapAttr(Integer commktKey) {
        this.commktKey = commktKey;
    }

    public CommktClrdSwapAttr(Integer commktKey, Character status, BigDecimal commktLotSize, Character commktSettlementInd, short commktNumMthOut, int tradingPrdOffset, Character longShortInd, int spreadQtyFactor, int transId, Character marginType) {
        this.commktKey = commktKey;
        this.status = status;
        this.commktLotSize = commktLotSize;
        this.commktSettlementInd = commktSettlementInd;
        this.commktNumMthOut = commktNumMthOut;
        this.tradingPrdOffset = tradingPrdOffset;
        this.longShortInd = longShortInd;
        this.spreadQtyFactor = spreadQtyFactor;
        this.transId = transId;
        this.marginType = marginType;
    }

    public Integer getCommktKey() {
        return commktKey;
    }

    public void setCommktKey(Integer commktKey) {
        this.commktKey = commktKey;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public BigDecimal getCommktLotSize() {
        return commktLotSize;
    }

    public void setCommktLotSize(BigDecimal commktLotSize) {
        this.commktLotSize = commktLotSize;
    }

    public Character getCommktSettlementInd() {
        return commktSettlementInd;
    }

    public void setCommktSettlementInd(Character commktSettlementInd) {
        this.commktSettlementInd = commktSettlementInd;
    }

    public String getCommktTradingMthInd() {
        return commktTradingMthInd;
    }

    public void setCommktTradingMthInd(String commktTradingMthInd) {
        this.commktTradingMthInd = commktTradingMthInd;
    }

    public String getCommktNearbyMask() {
        return commktNearbyMask;
    }

    public void setCommktNearbyMask(String commktNearbyMask) {
        this.commktNearbyMask = commktNearbyMask;
    }

    public short getCommktNumMthOut() {
        return commktNumMthOut;
    }

    public void setCommktNumMthOut(short commktNumMthOut) {
        this.commktNumMthOut = commktNumMthOut;
    }

    public int getTradingPrdOffset() {
        return tradingPrdOffset;
    }

    public void setTradingPrdOffset(int tradingPrdOffset) {
        this.tradingPrdOffset = tradingPrdOffset;
    }

    public Character getLongShortInd() {
        return longShortInd;
    }

    public void setLongShortInd(Character longShortInd) {
        this.longShortInd = longShortInd;
    }

    public int getSpreadQtyFactor() {
        return spreadQtyFactor;
    }

    public void setSpreadQtyFactor(int spreadQtyFactor) {
        this.spreadQtyFactor = spreadQtyFactor;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public Character getMarginType() {
        return marginType;
    }

    public void setMarginType(Character marginType) {
        this.marginType = marginType;
    }

    public Commodity getCommktCurrCode() {
        return commktCurrCode;
    }

    public void setCommktCurrCode(Commodity commktCurrCode) {
        this.commktCurrCode = commktCurrCode;
    }

    public Commodity getCompCmdtyCode() {
        return compCmdtyCode;
    }

    public void setCompCmdtyCode(Commodity compCmdtyCode) {
        this.compCmdtyCode = compCmdtyCode;
    }

    public CommodityMarket getCommodityMarket() {
        return commodityMarket;
    }

    public void setCommodityMarket(CommodityMarket commodityMarket) {
        this.commodityMarket = commodityMarket;
    }

    public Uom getCommktLotUomCode() {
        return commktLotUomCode;
    }

    public void setCommktLotUomCode(Uom commktLotUomCode) {
        this.commktLotUomCode = commktLotUomCode;
    }

    public Uom getCommktPriceUomCode() {
        return commktPriceUomCode;
    }

    public void setCommktPriceUomCode(Uom commktPriceUomCode) {
        this.commktPriceUomCode = commktPriceUomCode;
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
        if (!(object instanceof CommktClrdSwapAttr)) {
            return false;
        }
        CommktClrdSwapAttr other = (CommktClrdSwapAttr) object;
        if ((this.commktKey == null && other.commktKey != null) || (this.commktKey != null && !this.commktKey.equals(other.commktKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommktClrdSwapAttr[ commktKey=" + commktKey + " ]";
    }
    
}
