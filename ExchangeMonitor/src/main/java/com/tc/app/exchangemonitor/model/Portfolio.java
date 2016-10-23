/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "portfolio", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Portfolio.findAll", query = "SELECT p FROM Portfolio p"),
    @NamedQuery(name = "Portfolio.findByPortNum", query = "SELECT p FROM Portfolio p WHERE p.portNum = :portNum"),
    @NamedQuery(name = "Portfolio.findByPortType", query = "SELECT p FROM Portfolio p WHERE p.portType = :portType"),
    @NamedQuery(name = "Portfolio.findByPortShortName", query = "SELECT p FROM Portfolio p WHERE p.portShortName = :portShortName"),
    @NamedQuery(name = "Portfolio.findByPortFullName", query = "SELECT p FROM Portfolio p WHERE p.portFullName = :portFullName"),
    @NamedQuery(name = "Portfolio.findByPortClass", query = "SELECT p FROM Portfolio p WHERE p.portClass = :portClass"),
    @NamedQuery(name = "Portfolio.findByPortRefKey", query = "SELECT p FROM Portfolio p WHERE p.portRefKey = :portRefKey"),
    @NamedQuery(name = "Portfolio.findByCmntNum", query = "SELECT p FROM Portfolio p WHERE p.cmntNum = :cmntNum"),
    @NamedQuery(name = "Portfolio.findByNumHistoryDays", query = "SELECT p FROM Portfolio p WHERE p.numHistoryDays = :numHistoryDays"),
    @NamedQuery(name = "Portfolio.findByPortLocked", query = "SELECT p FROM Portfolio p WHERE p.portLocked = :portLocked"),
    @NamedQuery(name = "Portfolio.findByPortLockedAndPortType", query = "SELECT p FROM Portfolio p WHERE p.portLocked != 1 and p.portType = 'R'"),
    @NamedQuery(name = "Portfolio.findByTransId", query = "SELECT p FROM Portfolio p WHERE p.transId = :transId")})
public class Portfolio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "port_num")
    private Integer portNum;
    
    @Basic(optional = false)
    @Column(name = "port_type", columnDefinition="CHAR")
    private String portType;
    
    @Column(name = "port_short_name")
    private String portShortName;
    @Column(name = "port_full_name")
    private String portFullName;
    @Column(name = "port_class")
    private Character portClass;
    @Column(name = "port_ref_key")
    private String portRefKey;
    @Column(name = "cmnt_num")
    private Integer cmntNum;
    @Column(name = "num_history_days")
    private Integer numHistoryDays;
    @Column(name = "port_locked")
    private Short portLocked;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumn(name = "trading_entity_num", referencedColumnName = "acct_num")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account tradingEntityNum;
    @JoinColumn(name = "desired_pl_curr_code", referencedColumnName = "cmdty_code")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Commodity desiredPlCurrCode;
    @JoinColumn(name = "owner_init", referencedColumnName = "user_init")
    @ManyToOne(fetch = FetchType.LAZY)
    private IctsUser ownerInit;

    public Portfolio() {
    }

    public Portfolio(Integer portNum) {
        this.portNum = portNum;
    }

    public Portfolio(Integer portNum, String portType, int transId) {
        this.portNum = portNum;
        this.portType = portType;
        this.transId = transId;
    }

    public Integer getPortNum() {
        return portNum;
    }

    public void setPortNum(Integer portNum) {
        this.portNum = portNum;
    }

    public String getPortType() {
        return portType;
    }

    public void setPortType(String portType) {
        this.portType = portType;
    }

    public String getPortShortName() {
        return portShortName;
    }

    public void setPortShortName(String portShortName) {
        this.portShortName = portShortName;
    }

    public String getPortFullName() {
        return portFullName;
    }

    public void setPortFullName(String portFullName) {
        this.portFullName = portFullName;
    }

    public Character getPortClass() {
        return portClass;
    }

    public void setPortClass(Character portClass) {
        this.portClass = portClass;
    }

    public String getPortRefKey() {
        return portRefKey;
    }

    public void setPortRefKey(String portRefKey) {
        this.portRefKey = portRefKey;
    }

    public Integer getCmntNum() {
        return cmntNum;
    }

    public void setCmntNum(Integer cmntNum) {
        this.cmntNum = cmntNum;
    }

    public Integer getNumHistoryDays() {
        return numHistoryDays;
    }

    public void setNumHistoryDays(Integer numHistoryDays) {
        this.numHistoryDays = numHistoryDays;
    }

    public Short getPortLocked() {
        return portLocked;
    }

    public void setPortLocked(Short portLocked) {
        this.portLocked = portLocked;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public Account getTradingEntityNum() {
        return tradingEntityNum;
    }

    public void setTradingEntityNum(Account tradingEntityNum) {
        this.tradingEntityNum = tradingEntityNum;
    }

    public Commodity getDesiredPlCurrCode() {
        return desiredPlCurrCode;
    }

    public void setDesiredPlCurrCode(Commodity desiredPlCurrCode) {
        this.desiredPlCurrCode = desiredPlCurrCode;
    }

    public IctsUser getOwnerInit() {
        return ownerInit;
    }

    public void setOwnerInit(IctsUser ownerInit) {
        this.ownerInit = ownerInit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (portNum != null ? portNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Portfolio)) {
            return false;
        }
        Portfolio other = (Portfolio) object;
        if ((this.portNum == null && other.portNum != null) || (this.portNum != null && !this.portNum.equals(other.portNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Portfolio[ portNum=" + portNum + " ]";
    }
    
}
