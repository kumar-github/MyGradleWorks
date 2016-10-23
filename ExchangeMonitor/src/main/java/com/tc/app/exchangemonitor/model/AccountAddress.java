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
import javax.persistence.FetchType;
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
 * @author smurugabushanam
 */
@Entity
@Table(name = "account_address", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountAddress.findAll", query = "SELECT a FROM AccountAddress a"),
    @NamedQuery(name = "AccountAddress.findByAcctNum", query = "SELECT a FROM AccountAddress a WHERE a.accountAddressPK.acctNum = :acctNum"),
    @NamedQuery(name = "AccountAddress.findByAcctAddrNum", query = "SELECT a FROM AccountAddress a WHERE a.accountAddressPK.acctAddrNum = :acctAddrNum"),
    @NamedQuery(name = "AccountAddress.findByAcctAddrLine1", query = "SELECT a FROM AccountAddress a WHERE a.acctAddrLine1 = :acctAddrLine1"),
    @NamedQuery(name = "AccountAddress.findByAcctAddrLine2", query = "SELECT a FROM AccountAddress a WHERE a.acctAddrLine2 = :acctAddrLine2"),
    @NamedQuery(name = "AccountAddress.findByAcctAddrLine3", query = "SELECT a FROM AccountAddress a WHERE a.acctAddrLine3 = :acctAddrLine3"),
    @NamedQuery(name = "AccountAddress.findByAcctAddrLine4", query = "SELECT a FROM AccountAddress a WHERE a.acctAddrLine4 = :acctAddrLine4"),
    @NamedQuery(name = "AccountAddress.findByAcctAddrCity", query = "SELECT a FROM AccountAddress a WHERE a.acctAddrCity = :acctAddrCity"),
    @NamedQuery(name = "AccountAddress.findByStateCode", query = "SELECT a FROM AccountAddress a WHERE a.stateCode = :stateCode"),
    @NamedQuery(name = "AccountAddress.findByCountryCode", query = "SELECT a FROM AccountAddress a WHERE a.countryCode = :countryCode"),
    @NamedQuery(name = "AccountAddress.findByAcctAddrZipCode", query = "SELECT a FROM AccountAddress a WHERE a.acctAddrZipCode = :acctAddrZipCode"),
    @NamedQuery(name = "AccountAddress.findByAcctAddrPhNum", query = "SELECT a FROM AccountAddress a WHERE a.acctAddrPhNum = :acctAddrPhNum"),
    @NamedQuery(name = "AccountAddress.findByAcctAddrTelexNum", query = "SELECT a FROM AccountAddress a WHERE a.acctAddrTelexNum = :acctAddrTelexNum"),
    @NamedQuery(name = "AccountAddress.findByAcctAddrFaxNum", query = "SELECT a FROM AccountAddress a WHERE a.acctAddrFaxNum = :acctAddrFaxNum"),
    @NamedQuery(name = "AccountAddress.findByAcctAddrTelexAnsback", query = "SELECT a FROM AccountAddress a WHERE a.acctAddrTelexAnsback = :acctAddrTelexAnsback"),
    @NamedQuery(name = "AccountAddress.findByAcctAddrFaxAnsback", query = "SELECT a FROM AccountAddress a WHERE a.acctAddrFaxAnsback = :acctAddrFaxAnsback"),
    @NamedQuery(name = "AccountAddress.findByAcctAddrStatus", query = "SELECT a FROM AccountAddress a WHERE a.acctAddrStatus = :acctAddrStatus"),
    @NamedQuery(name = "AccountAddress.findByAcctAddrEmail", query = "SELECT a FROM AccountAddress a WHERE a.acctAddrEmail = :acctAddrEmail"),
    @NamedQuery(name = "AccountAddress.findByAccountingSystemId", query = "SELECT a FROM AccountAddress a WHERE a.accountingSystemId = :accountingSystemId"),
    @NamedQuery(name = "AccountAddress.findByTransId", query = "SELECT a FROM AccountAddress a WHERE a.transId = :transId")})
public class AccountAddress implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccountAddressPK accountAddressPK;
    
    @Basic(optional = false)
    @Column(name = "acct_addr_line_1", nullable = false, length = 40, columnDefinition="NVARCHAR")
    private String acctAddrLine1;
    
    @Column(name = "acct_addr_line_2", length = 40, columnDefinition="NVARCHAR")
    private String acctAddrLine2;
    
    @Column(name = "acct_addr_line_3", length = 40, columnDefinition="NVARCHAR")
    private String acctAddrLine3;
    
    @Column(name = "acct_addr_line_4", length = 40, columnDefinition="NVARCHAR")
    private String acctAddrLine4;
    
    @Basic(optional = false)
    @Column(name = "acct_addr_city", nullable = false, length = 40, columnDefinition="NVARCHAR")
    private String acctAddrCity;
    
    @Column(name = "state_code", length = 2, columnDefinition="NCHAR")
    private String stateCode;
    
    @Basic(optional = false)
    @Column(name = "country_code", nullable = false, length = 8, columnDefinition="NCHAR")
    private String countryCode;
    
    @Column(name = "acct_addr_zip_code", length = 10, columnDefinition="NVARCHAR")
    private String acctAddrZipCode;
    
    @Column(name = "acct_addr_ph_num", length = 15)
    private String acctAddrPhNum;
    @Column(name = "acct_addr_telex_num", length = 15)
    private String acctAddrTelexNum;
    @Column(name = "acct_addr_fax_num", length = 15)
    private String acctAddrFaxNum;
    @Column(name = "acct_addr_telex_ansback", length = 15)
    private String acctAddrTelexAnsback;
    @Column(name = "acct_addr_fax_ansback", length = 15)
    private String acctAddrFaxAnsback;
    @Basic(optional = false)
    @Column(name = "acct_addr_status", nullable = false)
    private Character acctAddrStatus;
    @Column(name = "acct_addr_email", length = 40)
    private String acctAddrEmail;
    @Column(name = "accounting_system_id", length = 9)
    private String accountingSystemId;
    @Basic(optional = false)
    @Column(name = "trans_id", nullable = false)
    private int transId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountAddress", fetch = FetchType.LAZY)
    private Collection<AccountContact> accountContactCollection;
    @JoinColumn(name = "acct_num", referencedColumnName = "acct_num", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Account account;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountAddress", fetch = FetchType.LAZY)
    private Collection<AccountAlias> accountAliasCollection;

    public AccountAddress() {
    }

    public AccountAddress(AccountAddressPK accountAddressPK) {
        this.accountAddressPK = accountAddressPK;
    }

    public AccountAddress(AccountAddressPK accountAddressPK, String acctAddrLine1, String acctAddrCity, String countryCode, Character acctAddrStatus, int transId) {
        this.accountAddressPK = accountAddressPK;
        this.acctAddrLine1 = acctAddrLine1;
        this.acctAddrCity = acctAddrCity;
        this.countryCode = countryCode;
        this.acctAddrStatus = acctAddrStatus;
        this.transId = transId;
    }

    public AccountAddress(int acctNum, short acctAddrNum) {
        this.accountAddressPK = new AccountAddressPK(acctNum, acctAddrNum);
    }

    public AccountAddressPK getAccountAddressPK() {
        return accountAddressPK;
    }

    public void setAccountAddressPK(AccountAddressPK accountAddressPK) {
        this.accountAddressPK = accountAddressPK;
    }

    public String getAcctAddrLine1() {
        return acctAddrLine1;
    }

    public void setAcctAddrLine1(String acctAddrLine1) {
        this.acctAddrLine1 = acctAddrLine1;
    }

    public String getAcctAddrLine2() {
        return acctAddrLine2;
    }

    public void setAcctAddrLine2(String acctAddrLine2) {
        this.acctAddrLine2 = acctAddrLine2;
    }

    public String getAcctAddrLine3() {
        return acctAddrLine3;
    }

    public void setAcctAddrLine3(String acctAddrLine3) {
        this.acctAddrLine3 = acctAddrLine3;
    }

    public String getAcctAddrLine4() {
        return acctAddrLine4;
    }

    public void setAcctAddrLine4(String acctAddrLine4) {
        this.acctAddrLine4 = acctAddrLine4;
    }

    public String getAcctAddrCity() {
        return acctAddrCity;
    }

    public void setAcctAddrCity(String acctAddrCity) {
        this.acctAddrCity = acctAddrCity;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAcctAddrZipCode() {
        return acctAddrZipCode;
    }

    public void setAcctAddrZipCode(String acctAddrZipCode) {
        this.acctAddrZipCode = acctAddrZipCode;
    }

    public String getAcctAddrPhNum() {
        return acctAddrPhNum;
    }

    public void setAcctAddrPhNum(String acctAddrPhNum) {
        this.acctAddrPhNum = acctAddrPhNum;
    }

    public String getAcctAddrTelexNum() {
        return acctAddrTelexNum;
    }

    public void setAcctAddrTelexNum(String acctAddrTelexNum) {
        this.acctAddrTelexNum = acctAddrTelexNum;
    }

    public String getAcctAddrFaxNum() {
        return acctAddrFaxNum;
    }

    public void setAcctAddrFaxNum(String acctAddrFaxNum) {
        this.acctAddrFaxNum = acctAddrFaxNum;
    }

    public String getAcctAddrTelexAnsback() {
        return acctAddrTelexAnsback;
    }

    public void setAcctAddrTelexAnsback(String acctAddrTelexAnsback) {
        this.acctAddrTelexAnsback = acctAddrTelexAnsback;
    }

    public String getAcctAddrFaxAnsback() {
        return acctAddrFaxAnsback;
    }

    public void setAcctAddrFaxAnsback(String acctAddrFaxAnsback) {
        this.acctAddrFaxAnsback = acctAddrFaxAnsback;
    }

    public Character getAcctAddrStatus() {
        return acctAddrStatus;
    }

    public void setAcctAddrStatus(Character acctAddrStatus) {
        this.acctAddrStatus = acctAddrStatus;
    }

    public String getAcctAddrEmail() {
        return acctAddrEmail;
    }

    public void setAcctAddrEmail(String acctAddrEmail) {
        this.acctAddrEmail = acctAddrEmail;
    }

    public String getAccountingSystemId() {
        return accountingSystemId;
    }

    public void setAccountingSystemId(String accountingSystemId) {
        this.accountingSystemId = accountingSystemId;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<AccountContact> getAccountContactCollection() {
        return accountContactCollection;
    }

    public void setAccountContactCollection(Collection<AccountContact> accountContactCollection) {
        this.accountContactCollection = accountContactCollection;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @XmlTransient
    public Collection<AccountAlias> getAccountAliasCollection() {
        return accountAliasCollection;
    }

    public void setAccountAliasCollection(Collection<AccountAlias> accountAliasCollection) {
        this.accountAliasCollection = accountAliasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountAddressPK != null ? accountAddressPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountAddress)) {
            return false;
        }
        AccountAddress other = (AccountAddress) object;
        if ((this.accountAddressPK == null && other.accountAddressPK != null) || (this.accountAddressPK != null && !this.accountAddressPK.equals(other.accountAddressPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generated11.AccountAddress[ accountAddressPK=" + accountAddressPK + " ]";
    }
    
}
