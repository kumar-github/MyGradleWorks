/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Saravana Kumar M
 */
@Embeddable
public class CommodityMarketFormulaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "commkt_key")
    private int commktKey;
    
    @Basic(optional = false)
    @Column(name = "trading_prd", columnDefinition="CHAR")
    private String tradingPrd;
    
    @Basic(optional = false)
    @Column(name = "price_source_code", columnDefinition="CHAR")
    private String priceSourceCode;

    public CommodityMarketFormulaPK() {
    }

    public CommodityMarketFormulaPK(int commktKey, String tradingPrd, String priceSourceCode) {
        this.commktKey = commktKey;
        this.tradingPrd = tradingPrd;
        this.priceSourceCode = priceSourceCode;
    }

    public int getCommktKey() {
        return commktKey;
    }

    public void setCommktKey(int commktKey) {
        this.commktKey = commktKey;
    }

    public String getTradingPrd() {
        return tradingPrd;
    }

    public void setTradingPrd(String tradingPrd) {
        this.tradingPrd = tradingPrd;
    }

    public String getPriceSourceCode() {
        return priceSourceCode;
    }

    public void setPriceSourceCode(String priceSourceCode) {
        this.priceSourceCode = priceSourceCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) commktKey;
        hash += (tradingPrd != null ? tradingPrd.hashCode() : 0);
        hash += (priceSourceCode != null ? priceSourceCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityMarketFormulaPK)) {
            return false;
        }
        CommodityMarketFormulaPK other = (CommodityMarketFormulaPK) object;
        if (this.commktKey != other.commktKey) {
            return false;
        }
        if ((this.tradingPrd == null && other.tradingPrd != null) || (this.tradingPrd != null && !this.tradingPrd.equals(other.tradingPrd))) {
            return false;
        }
        if ((this.priceSourceCode == null && other.priceSourceCode != null) || (this.priceSourceCode != null && !this.priceSourceCode.equals(other.priceSourceCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityMarketFormulaPK[ commktKey=" + commktKey + ", tradingPrd=" + tradingPrd + ", priceSourceCode=" + priceSourceCode + " ]";
    }
    
}
