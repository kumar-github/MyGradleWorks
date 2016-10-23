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
public class TradingPeriodPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "commkt_key")
    private int commktKey;
    
    @Basic(optional = false)
    @Column(name = "trading_prd", columnDefinition="CHAR")
    private String tradingPrd;

    public TradingPeriodPK() {
    }

    public TradingPeriodPK(int commktKey, String tradingPrd) {
        this.commktKey = commktKey;
        this.tradingPrd = tradingPrd;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) commktKey;
        hash += (tradingPrd != null ? tradingPrd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TradingPeriodPK)) {
            return false;
        }
        TradingPeriodPK other = (TradingPeriodPK) object;
        if (this.commktKey != other.commktKey) {
            return false;
        }
        if ((this.tradingPrd == null && other.tradingPrd != null) || (this.tradingPrd != null && !this.tradingPrd.equals(other.tradingPrd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TradingPeriodPK[ commktKey=" + commktKey + ", tradingPrd=" + tradingPrd + " ]";
    }
    
}
