/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "external_position", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExternalPosition.findAll", query = "SELECT e FROM ExternalPosition e"),
    @NamedQuery(name = "ExternalPosition.findByExtPosNum", query = "SELECT e FROM ExternalPosition e WHERE e.extPosNum = :extPosNum"),
    @NamedQuery(name = "ExternalPosition.findByTradingPrd", query = "SELECT e FROM ExternalPosition e WHERE e.tradingPrd = :tradingPrd"),
    @NamedQuery(name = "ExternalPosition.findByItemType", query = "SELECT e FROM ExternalPosition e WHERE e.itemType = :itemType"),
    @NamedQuery(name = "ExternalPosition.findByPutCallInd", query = "SELECT e FROM ExternalPosition e WHERE e.putCallInd = :putCallInd"),
    @NamedQuery(name = "ExternalPosition.findByStrikePrice", query = "SELECT e FROM ExternalPosition e WHERE e.strikePrice = :strikePrice"),
    @NamedQuery(name = "ExternalPosition.findByQuantity", query = "SELECT e FROM ExternalPosition e WHERE e.quantity = :quantity"),
    @NamedQuery(name = "ExternalPosition.findByTransId", query = "SELECT e FROM ExternalPosition e WHERE e.transId = :transId")})
public class ExternalPosition implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ext_pos_num")
    private Integer extPosNum;
    
    @Column(name = "trading_prd", columnDefinition="CHAR")
    private String tradingPrd;
    
    @Column(name = "item_type")
    private Character itemType;
    @Basic(optional = false)
    @Column(name = "put_call_ind")
    private Character putCallInd;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "strike_price", columnDefinition="DECIMAL")
    private BigDecimal strikePrice;
    
    @Basic(optional = false)
    @Column(name = "quantity", columnDefinition="DECIMAL")
    private BigDecimal quantity;
    
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumn(name = "clr_brkr_num", referencedColumnName = "acct_num")
    @ManyToOne
    private Account clrBrkrNum;
    @JoinColumn(name = "commkt_key", referencedColumnName = "commkt_key")
    @ManyToOne(optional = false)
    private CommodityMarket commktKey;
    
    @JoinColumn(name = "qty_uom_code", referencedColumnName = "uom_code", columnDefinition="CHAR")
    @ManyToOne
    private Uom qtyUomCode;
    
    @OneToMany(mappedBy = "extPosNum")
    private Collection<ExternalTrade> externalTradeCollection;

    public ExternalPosition() {
    }

    public ExternalPosition(Integer extPosNum) {
        this.extPosNum = extPosNum;
    }

    public ExternalPosition(Integer extPosNum, Character putCallInd, BigDecimal quantity, int transId) {
        this.extPosNum = extPosNum;
        this.putCallInd = putCallInd;
        this.quantity = quantity;
        this.transId = transId;
    }

    public Integer getExtPosNum() {
        return extPosNum;
    }

    public void setExtPosNum(Integer extPosNum) {
        this.extPosNum = extPosNum;
    }

    public String getTradingPrd() {
        return tradingPrd;
    }

    public void setTradingPrd(String tradingPrd) {
        this.tradingPrd = tradingPrd;
    }

    public Character getItemType() {
        return itemType;
    }

    public void setItemType(Character itemType) {
        this.itemType = itemType;
    }

    public Character getPutCallInd() {
        return putCallInd;
    }

    public void setPutCallInd(Character putCallInd) {
        this.putCallInd = putCallInd;
    }

    public BigDecimal getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(BigDecimal strikePrice) {
        this.strikePrice = strikePrice;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public Account getClrBrkrNum() {
        return clrBrkrNum;
    }

    public void setClrBrkrNum(Account clrBrkrNum) {
        this.clrBrkrNum = clrBrkrNum;
    }

    public CommodityMarket getCommktKey() {
        return commktKey;
    }

    public void setCommktKey(CommodityMarket commktKey) {
        this.commktKey = commktKey;
    }

    public Uom getQtyUomCode() {
        return qtyUomCode;
    }

    public void setQtyUomCode(Uom qtyUomCode) {
        this.qtyUomCode = qtyUomCode;
    }

    @XmlTransient
    public Collection<ExternalTrade> getExternalTradeCollection() {
        return externalTradeCollection;
    }

    public void setExternalTradeCollection(Collection<ExternalTrade> externalTradeCollection) {
        this.externalTradeCollection = externalTradeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (extPosNum != null ? extPosNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExternalPosition)) {
            return false;
        }
        ExternalPosition other = (ExternalPosition) object;
        if ((this.extPosNum == null && other.extPosNum != null) || (this.extPosNum != null && !this.extPosNum.equals(other.extPosNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExternalPosition[ extPosNum=" + extPosNum + " ]";
    }
    
}
