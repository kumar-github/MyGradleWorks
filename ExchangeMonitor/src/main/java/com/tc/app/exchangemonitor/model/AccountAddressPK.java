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
public class AccountAddressPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "acct_num", nullable = false)
    private int acctNum;
    @Basic(optional = false)
    @Column(name = "acct_addr_num", nullable = false)
    private short acctAddrNum;

    public AccountAddressPK() {
    }

    public AccountAddressPK(int acctNum, short acctAddrNum) {
        this.acctNum = acctNum;
        this.acctAddrNum = acctAddrNum;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) acctNum;
        hash += (int) acctAddrNum;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountAddressPK)) {
            return false;
        }
        AccountAddressPK other = (AccountAddressPK) object;
        if (this.acctNum != other.acctNum) {
            return false;
        }
        if (this.acctAddrNum != other.acctAddrNum) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generated11.AccountAddressPK[ acctNum=" + acctNum + ", acctAddrNum=" + acctAddrNum + " ]";
    }
    
}
