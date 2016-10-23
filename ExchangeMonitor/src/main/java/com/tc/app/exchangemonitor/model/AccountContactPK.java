package com.tc.app.exchangemonitor.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AccountContactPK implements Serializable
{
	@Basic(optional = false)
    @Column(name = "acct_num", nullable = false)
    private int acctNum;
    
	@Basic(optional = false)
    @Column(name = "acct_cont_num", nullable = false)
    private int acctContNum;

    public AccountContactPK()
    {
    }

    public AccountContactPK(int acctNum, int acctContNum)
    {
        this.acctNum = acctNum;
        this.acctContNum = acctContNum;
    }

    public int getAcctNum() {
        return acctNum;
    }

    public void setAcctNum(int acctNum) {
        this.acctNum = acctNum;
    }

    public int getAcctContNum() {
        return acctContNum;
    }

    public void setAcctContNum(int acctContNum) {
        this.acctContNum = acctContNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) acctNum;
        hash += (int) acctContNum;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountContactPK)) {
            return false;
        }
        AccountContactPK other = (AccountContactPK) object;
        if (this.acctNum != other.acctNum) {
            return false;
        }
        if (this.acctContNum != other.acctContNum) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generated11.AccountContactPK[ acctNum=" + acctNum + ", acctContNum=" + acctContNum + " ]";
    }
    
}