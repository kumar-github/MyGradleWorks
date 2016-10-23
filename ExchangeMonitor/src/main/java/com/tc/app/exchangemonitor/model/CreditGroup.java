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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "credit_group", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CreditGroup.findAll", query = "SELECT c FROM CreditGroup c"),
    @NamedQuery(name = "CreditGroup.findByGroupNum", query = "SELECT c FROM CreditGroup c WHERE c.groupNum = :groupNum"),
    @NamedQuery(name = "CreditGroup.findByGroupName", query = "SELECT c FROM CreditGroup c WHERE c.groupName = :groupName"),
    @NamedQuery(name = "CreditGroup.findByTransId", query = "SELECT c FROM CreditGroup c WHERE c.transId = :transId")})
public class CreditGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "group_num")
    private Integer groupNum;
    
    @Basic(optional = false)
    @Column(name = "group_name", columnDefinition="CHAR")
    private String groupName;
    
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumn(name = "acct_num", referencedColumnName = "acct_num")
    @ManyToOne
    private Account acctNum;
    @JoinColumn(name = "country_code", referencedColumnName = "country_code")
    @ManyToOne
    private Country countryCode;
    @JoinColumn(name = "user_init", referencedColumnName = "user_init")
    @ManyToOne
    private IctsUser userInit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditGroup")
    private Collection<CreditTermGroup> creditTermGroupCollection;

    public CreditGroup() {
    }

    public CreditGroup(Integer groupNum) {
        this.groupNum = groupNum;
    }

    public CreditGroup(Integer groupNum, String groupName, int transId) {
        this.groupNum = groupNum;
        this.groupName = groupName;
        this.transId = transId;
    }

    public Integer getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public Account getAcctNum() {
        return acctNum;
    }

    public void setAcctNum(Account acctNum) {
        this.acctNum = acctNum;
    }

    public Country getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Country countryCode) {
        this.countryCode = countryCode;
    }

    public IctsUser getUserInit() {
        return userInit;
    }

    public void setUserInit(IctsUser userInit) {
        this.userInit = userInit;
    }

    @XmlTransient
    public Collection<CreditTermGroup> getCreditTermGroupCollection() {
        return creditTermGroupCollection;
    }

    public void setCreditTermGroupCollection(Collection<CreditTermGroup> creditTermGroupCollection) {
        this.creditTermGroupCollection = creditTermGroupCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupNum != null ? groupNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditGroup)) {
            return false;
        }
        CreditGroup other = (CreditGroup) object;
        if ((this.groupNum == null && other.groupNum != null) || (this.groupNum != null && !this.groupNum.equals(other.groupNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CreditGroup[ groupNum=" + groupNum + " ]";
    }
    
}
