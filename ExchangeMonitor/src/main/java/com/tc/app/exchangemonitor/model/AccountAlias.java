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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author smurugabushanam
 */
@Entity
@Table(name = "account_alias", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountAlias.findAll", query = "SELECT a FROM AccountAlias a"),
    @NamedQuery(name = "AccountAlias.findByAcctNum", query = "SELECT a FROM AccountAlias a WHERE a.accountAliasPK.acctNum = :acctNum"),
    @NamedQuery(name = "AccountAlias.findByAcctAddrNum", query = "SELECT a FROM AccountAlias a WHERE a.accountAliasPK.acctAddrNum = :acctAddrNum"),
    @NamedQuery(name = "AccountAlias.findByAliasSourceCode", query = "SELECT a FROM AccountAlias a WHERE a.accountAliasPK.aliasSourceCode = :aliasSourceCode"),
    @NamedQuery(name = "AccountAlias.findByAcctAliasName", query = "SELECT a FROM AccountAlias a WHERE a.acctAliasName = :acctAliasName"),
    @NamedQuery(name = "AccountAlias.findByTransId", query = "SELECT a FROM AccountAlias a WHERE a.transId = :transId")})
public class AccountAlias implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccountAliasPK accountAliasPK;
    @Basic(optional = false)
    @Column(name = "acct_alias_name", nullable = false, length = 15)
    private String acctAliasName;
    @Basic(optional = false)
    @Column(name = "trans_id", nullable = false)
    private int transId;
    @JoinColumns({
        @JoinColumn(name = "acct_num", referencedColumnName = "acct_num", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "acct_addr_num", referencedColumnName = "acct_addr_num", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AccountAddress accountAddress;
    
    @JoinColumn(name = "alias_source_code", referencedColumnName = "alias_source_code", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AliasSource aliasSource;

    public AccountAlias() {
    }

    public AccountAlias(AccountAliasPK accountAliasPK) {
        this.accountAliasPK = accountAliasPK;
    }

    public AccountAlias(AccountAliasPK accountAliasPK, String acctAliasName, int transId) {
        this.accountAliasPK = accountAliasPK;
        this.acctAliasName = acctAliasName;
        this.transId = transId;
    }

    public AccountAlias(int acctNum, short acctAddrNum, String aliasSourceCode) {
        this.accountAliasPK = new AccountAliasPK(acctNum, acctAddrNum, aliasSourceCode);
    }

    public AccountAliasPK getAccountAliasPK() {
        return accountAliasPK;
    }

    public void setAccountAliasPK(AccountAliasPK accountAliasPK) {
        this.accountAliasPK = accountAliasPK;
    }

    public String getAcctAliasName() {
        return acctAliasName;
    }

    public void setAcctAliasName(String acctAliasName) {
        this.acctAliasName = acctAliasName;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public AccountAddress getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(AccountAddress accountAddress) {
        this.accountAddress = accountAddress;
    }

    public AliasSource getAliasSource() {
        return aliasSource;
    }

    public void setAliasSource(AliasSource aliasSource) {
        this.aliasSource = aliasSource;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountAliasPK != null ? accountAliasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountAlias)) {
            return false;
        }
        AccountAlias other = (AccountAlias) object;
        if ((this.accountAliasPK == null && other.accountAliasPK != null) || (this.accountAliasPK != null && !this.accountAliasPK.equals(other.accountAliasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generated11.AccountAlias[ accountAliasPK=" + accountAliasPK + " ]";
    }
    
}
