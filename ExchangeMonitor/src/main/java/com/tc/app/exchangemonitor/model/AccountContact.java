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
import javax.persistence.Table;

@Entity
@Table(name = "account_contact", catalog = "QA_30_trade_sep12", schema = "dbo")
public class AccountContact implements Serializable
{
	private static final long serialVersionUID = 1L;
    
	@EmbeddedId
    protected AccountContactPK accountContactPK;
    
	@Basic(optional = false)
    @Column(name = "acct_cont_last_name", nullable = false, length = 20, columnDefinition="NVARCHAR")
    private String acctContLastName;
    
	@Basic(optional = false)
    @Column(name = "acct_cont_first_name", nullable = false, length = 20, columnDefinition="NVARCHAR")
    private String acctContFirstName;
    
	@Column(name = "acct_cont_nick_name", length = 20, columnDefinition="NVARCHAR")
    private String acctContNickName;
    
	@Column(name = "acct_cont_title", length = 30, columnDefinition="NVARCHAR")
    private String acctContTitle;
    
	@Column(name = "acct_cont_addr_line_1", length = 40, columnDefinition="NVARCHAR")
    private String acctContAddrLine1;
    
	@Column(name = "acct_cont_addr_line_2", length = 40, columnDefinition="NVARCHAR")
    private String acctContAddrLine2;
    
	@Column(name = "acct_cont_addr_line_3", length = 40, columnDefinition="NVARCHAR")
    private String acctContAddrLine3;
    
	@Column(name = "acct_cont_addr_line_4", length = 40, columnDefinition="NVARCHAR")
    private String acctContAddrLine4;
    
	@Column(name = "acct_cont_addr_city", length = 40, columnDefinition="NVARCHAR")
    private String acctContAddrCity;
    
	@Column(name = "state_code", length = 2, columnDefinition="NCHAR")
    private String stateCode;
    
	@Column(name = "country_code", length = 8, columnDefinition="NCHAR")
    private String countryCode;
    
	@Column(name = "acct_cont_addr_zip_code", length = 10, columnDefinition="NVARCHAR")
    private String acctContAddrZipCode;
    
	@Column(name = "acct_cont_home_ph_num", length = 15)
    private String acctContHomePhNum;
    
	@Column(name = "acct_cont_off_ph_num", length = 15)
    private String acctContOffPhNum;
    
	@Column(name = "acct_cont_oth_ph_num", length = 15)
    private String acctContOthPhNum;
    
	@Column(name = "acct_cont_telex_num", length = 15)
    private String acctContTelexNum;
    
	@Column(name = "acct_cont_fax_num", length = 15)
    private String acctContFaxNum;
    
	@Column(name = "acct_cont_email", length = 40)
    private String acctContEmail;
    
	@Column(name = "acct_cont_function", length = 15)
    private String acctContFunction;
    
	@Basic(optional = false)
    @Column(name = "acct_cont_status", nullable = false)
    private Character acctContStatus;
    
	@Basic(optional = false)
    @Column(name = "trans_id", nullable = false)
    private int transId;
    
	@JoinColumn(name = "acct_num", referencedColumnName = "acct_num", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Account account;
    
	@JoinColumns({
        @JoinColumn(name = "acct_num", referencedColumnName = "acct_num", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "acct_addr_num", referencedColumnName = "acct_addr_num", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AccountAddress accountAddress;

    public AccountContact()
    {
    }

    public AccountContact(AccountContactPK accountContactPK) {
        this.accountContactPK = accountContactPK;
    }

    public AccountContact(AccountContactPK accountContactPK, String acctContLastName, String acctContFirstName, Character acctContStatus, int transId) {
        this.accountContactPK = accountContactPK;
        this.acctContLastName = acctContLastName;
        this.acctContFirstName = acctContFirstName;
        this.acctContStatus = acctContStatus;
        this.transId = transId;
    }

    public AccountContact(int acctNum, int acctContNum) {
        this.accountContactPK = new AccountContactPK(acctNum, acctContNum);
    }

    public AccountContactPK getAccountContactPK() {
        return accountContactPK;
    }

    public void setAccountContactPK(AccountContactPK accountContactPK) {
        this.accountContactPK = accountContactPK;
    }

    public String getAcctContLastName() {
        return acctContLastName;
    }

    public void setAcctContLastName(String acctContLastName) {
        this.acctContLastName = acctContLastName;
    }

    public String getAcctContFirstName() {
        return acctContFirstName;
    }

    public void setAcctContFirstName(String acctContFirstName) {
        this.acctContFirstName = acctContFirstName;
    }

    public String getAcctContNickName() {
        return acctContNickName;
    }

    public void setAcctContNickName(String acctContNickName) {
        this.acctContNickName = acctContNickName;
    }

    public String getAcctContTitle() {
        return acctContTitle;
    }

    public void setAcctContTitle(String acctContTitle) {
        this.acctContTitle = acctContTitle;
    }

    public String getAcctContAddrLine1() {
        return acctContAddrLine1;
    }

    public void setAcctContAddrLine1(String acctContAddrLine1) {
        this.acctContAddrLine1 = acctContAddrLine1;
    }

    public String getAcctContAddrLine2() {
        return acctContAddrLine2;
    }

    public void setAcctContAddrLine2(String acctContAddrLine2) {
        this.acctContAddrLine2 = acctContAddrLine2;
    }

    public String getAcctContAddrLine3() {
        return acctContAddrLine3;
    }

    public void setAcctContAddrLine3(String acctContAddrLine3) {
        this.acctContAddrLine3 = acctContAddrLine3;
    }

    public String getAcctContAddrLine4() {
        return acctContAddrLine4;
    }

    public void setAcctContAddrLine4(String acctContAddrLine4) {
        this.acctContAddrLine4 = acctContAddrLine4;
    }

    public String getAcctContAddrCity() {
        return acctContAddrCity;
    }

    public void setAcctContAddrCity(String acctContAddrCity) {
        this.acctContAddrCity = acctContAddrCity;
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

    public String getAcctContAddrZipCode() {
        return acctContAddrZipCode;
    }

    public void setAcctContAddrZipCode(String acctContAddrZipCode) {
        this.acctContAddrZipCode = acctContAddrZipCode;
    }

    public String getAcctContHomePhNum() {
        return acctContHomePhNum;
    }

    public void setAcctContHomePhNum(String acctContHomePhNum) {
        this.acctContHomePhNum = acctContHomePhNum;
    }

    public String getAcctContOffPhNum() {
        return acctContOffPhNum;
    }

    public void setAcctContOffPhNum(String acctContOffPhNum) {
        this.acctContOffPhNum = acctContOffPhNum;
    }

    public String getAcctContOthPhNum() {
        return acctContOthPhNum;
    }

    public void setAcctContOthPhNum(String acctContOthPhNum) {
        this.acctContOthPhNum = acctContOthPhNum;
    }

    public String getAcctContTelexNum() {
        return acctContTelexNum;
    }

    public void setAcctContTelexNum(String acctContTelexNum) {
        this.acctContTelexNum = acctContTelexNum;
    }

    public String getAcctContFaxNum() {
        return acctContFaxNum;
    }

    public void setAcctContFaxNum(String acctContFaxNum) {
        this.acctContFaxNum = acctContFaxNum;
    }

    public String getAcctContEmail() {
        return acctContEmail;
    }

    public void setAcctContEmail(String acctContEmail) {
        this.acctContEmail = acctContEmail;
    }

    public String getAcctContFunction() {
        return acctContFunction;
    }

    public void setAcctContFunction(String acctContFunction) {
        this.acctContFunction = acctContFunction;
    }

    public Character getAcctContStatus() {
        return acctContStatus;
    }

    public void setAcctContStatus(Character acctContStatus) {
        this.acctContStatus = acctContStatus;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AccountAddress getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(AccountAddress accountAddress) {
        this.accountAddress = accountAddress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountContactPK != null ? accountContactPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountContact)) {
            return false;
        }
        AccountContact other = (AccountContact) object;
        if ((this.accountContactPK == null && other.accountContactPK != null) || (this.accountContactPK != null && !this.accountContactPK.equals(other.accountContactPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generated11.AccountContact[ accountContactPK=" + accountContactPK + " ]";
    }
    
}
