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
 * @author smurugabushanam
 */
@Embeddable
public class AccountAliasPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "acct_num", nullable = false)
    private int acctNum;
    @Basic(optional = false)
    @Column(name = "acct_addr_num", nullable = false)
    private short acctAddrNum;
    @Basic(optional = false)
    @Column(name = "alias_source_code", nullable = false, length = 8, columnDefinition="CHAR")
    private String aliasSourceCode;

    public AccountAliasPK() {
    }

    public AccountAliasPK(int acctNum, short acctAddrNum, String aliasSourceCode) {
        this.acctNum = acctNum;
        this.acctAddrNum = acctAddrNum;
        this.aliasSourceCode = aliasSourceCode;
    }

    public int getAcctNum() {
        return acctNum;
    }

    public void setAcctNum(int acctNum) {
        this.acctNum = acctNum;
    }

    public short getAcctAddrNum() {
        return acctAddrNum;
    }

    public void setAcctAddrNum(short acctAddrNum) {
        this.acctAddrNum = acctAddrNum;
    }

    public String getAliasSourceCode() {
        return aliasSourceCode;
    }

    public void setAliasSourceCode(String aliasSourceCode) {
        this.aliasSourceCode = aliasSourceCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) acctNum;
        hash += (int) acctAddrNum;
        hash += (aliasSourceCode != null ? aliasSourceCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountAliasPK)) {
            return false;
        }
        AccountAliasPK other = (AccountAliasPK) object;
        if (this.acctNum != other.acctNum) {
            return false;
        }
        if (this.acctAddrNum != other.acctAddrNum) {
            return false;
        }
        if ((this.aliasSourceCode == null && other.aliasSourceCode != null) || (this.aliasSourceCode != null && !this.aliasSourceCode.equals(other.aliasSourceCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generated11.AccountAliasPK[ acctNum=" + acctNum + ", acctAddrNum=" + acctAddrNum + ", aliasSourceCode=" + aliasSourceCode + " ]";
    }
    
}
