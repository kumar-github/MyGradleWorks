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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author smurugabushanam
 */
@Entity
@Table(name = "account_type", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountType.findAll", query = "SELECT a FROM AccountType a"),
    @NamedQuery(name = "AccountType.findByAcctTypeCode", query = "SELECT a FROM AccountType a WHERE a.acctTypeCode = :acctTypeCode"),
    @NamedQuery(name = "AccountType.findByAcctTypeDesc", query = "SELECT a FROM AccountType a WHERE a.acctTypeDesc = :acctTypeDesc"),
    @NamedQuery(name = "AccountType.findByAcctTypeNum", query = "SELECT a FROM AccountType a WHERE a.acctTypeNum = :acctTypeNum"),
    @NamedQuery(name = "AccountType.findByTransId", query = "SELECT a FROM AccountType a WHERE a.transId = :transId")})
public class AccountType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "acct_type_code", nullable = false, length = 8, columnDefinition="CHAR")
    private String acctTypeCode;
    
    @Basic(optional = false)
    @Column(name = "acct_type_desc", nullable = false, length = 40)
    private String acctTypeDesc;
    @Basic(optional = false)
    @Column(name = "acct_type_num", nullable = false)
    private short acctTypeNum;
    @Basic(optional = false)
    @Column(name = "trans_id", nullable = false)
    private int transId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acctTypeCode", fetch = FetchType.LAZY)
    private Collection<Account> accountCollection;

    public AccountType() {
    }

    public AccountType(String acctTypeCode) {
        this.acctTypeCode = acctTypeCode;
    }

    public AccountType(String acctTypeCode, String acctTypeDesc, short acctTypeNum, int transId) {
        this.acctTypeCode = acctTypeCode;
        this.acctTypeDesc = acctTypeDesc;
        this.acctTypeNum = acctTypeNum;
        this.transId = transId;
    }

    public String getAcctTypeCode() {
        return acctTypeCode;
    }

    public void setAcctTypeCode(String acctTypeCode) {
        this.acctTypeCode = acctTypeCode;
    }

    public String getAcctTypeDesc() {
        return acctTypeDesc;
    }

    public void setAcctTypeDesc(String acctTypeDesc) {
        this.acctTypeDesc = acctTypeDesc;
    }

    public short getAcctTypeNum() {
        return acctTypeNum;
    }

    public void setAcctTypeNum(short acctTypeNum) {
        this.acctTypeNum = acctTypeNum;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<Account> getAccountCollection() {
        return accountCollection;
    }

    public void setAccountCollection(Collection<Account> accountCollection) {
        this.accountCollection = accountCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acctTypeCode != null ? acctTypeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountType)) {
            return false;
        }
        AccountType other = (AccountType) object;
        if ((this.acctTypeCode == null && other.acctTypeCode != null) || (this.acctTypeCode != null && !this.acctTypeCode.equals(other.acctTypeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generated11.AccountType[ acctTypeCode=" + acctTypeCode + " ]";
    }
    
}
