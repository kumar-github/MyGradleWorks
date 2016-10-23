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
@Table(name = "commkt_source_roll_date", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommktSourceRollDate.findAll", query = "SELECT c FROM CommktSourceRollDate c"),
    @NamedQuery(name = "CommktSourceRollDate.findByCommktKey", query = "SELECT c FROM CommktSourceRollDate c WHERE c.commktSourceRollDatePK.commktKey = :commktKey"),
    @NamedQuery(name = "CommktSourceRollDate.findByPriceSourceCode", query = "SELECT c FROM CommktSourceRollDate c WHERE c.commktSourceRollDatePK.priceSourceCode = :priceSourceCode"),
    @NamedQuery(name = "CommktSourceRollDate.findByRollDateRuleNum", query = "SELECT c FROM CommktSourceRollDate c WHERE c.commktSourceRollDatePK.rollDateRuleNum = :rollDateRuleNum"),
    @NamedQuery(name = "CommktSourceRollDate.findByRollDateDays", query = "SELECT c FROM CommktSourceRollDate c WHERE c.rollDateDays = :rollDateDays"),
    @NamedQuery(name = "CommktSourceRollDate.findByRollDateBusCalInd", query = "SELECT c FROM CommktSourceRollDate c WHERE c.rollDateBusCalInd = :rollDateBusCalInd"),
    @NamedQuery(name = "CommktSourceRollDate.findByRollDateOnInd", query = "SELECT c FROM CommktSourceRollDate c WHERE c.rollDateOnInd = :rollDateOnInd"),
    @NamedQuery(name = "CommktSourceRollDate.findByRollDateEvent", query = "SELECT c FROM CommktSourceRollDate c WHERE c.rollDateEvent = :rollDateEvent"),
    @NamedQuery(name = "CommktSourceRollDate.findByTransId", query = "SELECT c FROM CommktSourceRollDate c WHERE c.transId = :transId")})
public class CommktSourceRollDate implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommktSourceRollDatePK commktSourceRollDatePK;
    
    @Column(name = "roll_date_days", columnDefinition="TINYINT")
    private Short rollDateDays;
    
    @Column(name = "roll_date_bus_cal_ind")
    private Character rollDateBusCalInd;
    @Column(name = "roll_date_on_ind")
    private Character rollDateOnInd;
    @Column(name = "roll_date_event")
    private String rollDateEvent;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumns({
        @JoinColumn(name = "commkt_key", referencedColumnName = "commkt_key", insertable = false, updatable = false),
        @JoinColumn(name = "price_source_code", referencedColumnName = "price_source_code", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CommodityMarketSource commodityMarketSource;

    public CommktSourceRollDate() {
    }

    public CommktSourceRollDate(CommktSourceRollDatePK commktSourceRollDatePK) {
        this.commktSourceRollDatePK = commktSourceRollDatePK;
    }

    public CommktSourceRollDate(CommktSourceRollDatePK commktSourceRollDatePK, int transId) {
        this.commktSourceRollDatePK = commktSourceRollDatePK;
        this.transId = transId;
    }

    public CommktSourceRollDate(int commktKey, String priceSourceCode, short rollDateRuleNum) {
        this.commktSourceRollDatePK = new CommktSourceRollDatePK(commktKey, priceSourceCode, rollDateRuleNum);
    }

    public CommktSourceRollDatePK getCommktSourceRollDatePK() {
        return commktSourceRollDatePK;
    }

    public void setCommktSourceRollDatePK(CommktSourceRollDatePK commktSourceRollDatePK) {
        this.commktSourceRollDatePK = commktSourceRollDatePK;
    }

    public Short getRollDateDays() {
        return rollDateDays;
    }

    public void setRollDateDays(Short rollDateDays) {
        this.rollDateDays = rollDateDays;
    }

    public Character getRollDateBusCalInd() {
        return rollDateBusCalInd;
    }

    public void setRollDateBusCalInd(Character rollDateBusCalInd) {
        this.rollDateBusCalInd = rollDateBusCalInd;
    }

    public Character getRollDateOnInd() {
        return rollDateOnInd;
    }

    public void setRollDateOnInd(Character rollDateOnInd) {
        this.rollDateOnInd = rollDateOnInd;
    }

    public String getRollDateEvent() {
        return rollDateEvent;
    }

    public void setRollDateEvent(String rollDateEvent) {
        this.rollDateEvent = rollDateEvent;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public CommodityMarketSource getCommodityMarketSource() {
        return commodityMarketSource;
    }

    public void setCommodityMarketSource(CommodityMarketSource commodityMarketSource) {
        this.commodityMarketSource = commodityMarketSource;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commktSourceRollDatePK != null ? commktSourceRollDatePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommktSourceRollDate)) {
            return false;
        }
        CommktSourceRollDate other = (CommktSourceRollDate) object;
        if ((this.commktSourceRollDatePK == null && other.commktSourceRollDatePK != null) || (this.commktSourceRollDatePK != null && !this.commktSourceRollDatePK.equals(other.commktSourceRollDatePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommktSourceRollDate[ commktSourceRollDatePK=" + commktSourceRollDatePK + " ]";
    }
    
}
