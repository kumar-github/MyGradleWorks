package com.tc.app.exchangemonitor.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "account", catalog = "QA_30_trade_sep12", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"acct_short_name", "acct_type_code"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByAcctNum", query = "SELECT a FROM Account a WHERE a.acctNum = :acctNum"),
    @NamedQuery(name = "Account.findByAcctShortName", query = "SELECT a FROM Account a WHERE a.acctShortName = :acctShortName"),
    @NamedQuery(name = "Account.findByAcctFullName", query = "SELECT a FROM Account a WHERE a.acctFullName = :acctFullName"),
    @NamedQuery(name = "Account.findByAcctStatus", query = "SELECT a FROM Account a WHERE a.acctStatus = :acctStatus"),
    @NamedQuery(name = "Account.findByAcctParentInd", query = "SELECT a FROM Account a WHERE a.acctParentInd = :acctParentInd"),
    @NamedQuery(name = "Account.findByAcctSubInd", query = "SELECT a FROM Account a WHERE a.acctSubInd = :acctSubInd"),
    @NamedQuery(name = "Account.findByAcctVatCode", query = "SELECT a FROM Account a WHERE a.acctVatCode = :acctVatCode"),
    @NamedQuery(name = "Account.findByAcctFiscalCode", query = "SELECT a FROM Account a WHERE a.acctFiscalCode = :acctFiscalCode"),
    @NamedQuery(name = "Account.findByAcctTypeCode", query = "SELECT a FROM Account a WHERE a.acctStatus = 'A' and a.acctTypeCode = :acctTypeCode"),
    @NamedQuery(name = "Account.findByAcctSubTypeCode", query = "SELECT a FROM Account a WHERE a.acctSubTypeCode = :acctSubTypeCode"),
    @NamedQuery(name = "Account.findByTransId", query = "SELECT a FROM Account a WHERE a.transId = :transId"),
    @NamedQuery(name = "Account.findByManInputSecQtyRequired", query = "SELECT a FROM Account a WHERE a.manInputSecQtyRequired = :manInputSecQtyRequired"),
    @NamedQuery(name = "Account.findByLegalEntityNum", query = "SELECT a FROM Account a WHERE a.legalEntityNum = :legalEntityNum"),
    @NamedQuery(name = "Account.findByGovtCode", query = "SELECT a FROM Account a WHERE a.govtCode = :govtCode"),
    @NamedQuery(name = "Account.findByAllowsNetout", query = "SELECT a FROM Account a WHERE a.allowsNetout = :allowsNetout"),
    @NamedQuery(name = "Account.findByAllowsBookout", query = "SELECT a FROM Account a WHERE a.allowsBookout = :allowsBookout"),
    @NamedQuery(name = "Account.findByMasterAgreementDate", query = "SELECT a FROM Account a WHERE a.masterAgreementDate = :masterAgreementDate")})

public class Account implements Serializable
{
	private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "acct_num", nullable = false)
    private Integer acctNum;
    
    @Basic(optional = false)
    @Column(name = "acct_short_name", nullable = false, length = 15, columnDefinition="NVARCHAR")
    private String acctShortName;
    
    @Basic(optional = false)
    @Column(name = "acct_full_name", nullable = false, length = 255, columnDefinition="NVARCHAR")
    private String acctFullName;
    @Basic(optional = false)
    @Column(name = "acct_status", nullable = false)
    private Character acctStatus;
    @Basic(optional = false)
    @Column(name = "acct_parent_ind", nullable = false)
    private Character acctParentInd;
    @Basic(optional = false)
    @Column(name = "acct_sub_ind", nullable = false)
    private Character acctSubInd;
    @Column(name = "acct_vat_code", length = 11)
    private String acctVatCode;
    @Column(name = "acct_fiscal_code", length = 16)
    private String acctFiscalCode;
    
    @Column(name = "acct_sub_type_code", length = 8, columnDefinition="CHAR")
    private String acctSubTypeCode;
    
    @Basic(optional = false)
    @Column(name = "trans_id", nullable = false)
    private int transId;
    @Column(name = "man_input_sec_qty_required")
    private Character manInputSecQtyRequired;
    @Column(name = "legal_entity_num")
    private Integer legalEntityNum;
    @Column(name = "govt_code", length = 50)
    private String govtCode;
    @Basic(optional = false)
    @Column(name = "allows_netout", nullable = false)
    private boolean allowsNetout;
    @Basic(optional = false)
    @Column(name = "allows_bookout", nullable = false)
    private boolean allowsBookout;
    @Column(name = "master_agreement_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date masterAgreementDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acctNum", fetch = FetchType.LAZY)
    private Collection<AccountAgreement> accountAgreementCollection;
    @OneToMany(mappedBy = "targetBookCompNum", fetch = FetchType.LAZY)
    private Collection<AccountAgreement> accountAgreementCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account", fetch = FetchType.LAZY)
    private Collection<AccountContact> accountContactCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account", fetch = FetchType.LAZY)
    private Collection<AccountAddress> accountAddressCollection;
    
    @JoinColumn(name = "acct_type_code", referencedColumnName = "acct_type_code", nullable = false, columnDefinition="CHAR")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AccountType acctTypeCode;
    
    @JoinColumn(name = "contract_cmnt_num", referencedColumnName = "cmnt_num")
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment contractCmntNum;
    
    @JoinColumn(name = "risk_transfer_ind_code", referencedColumnName = "risk_transfer_ind_code", columnDefinition="CHAR")
    @ManyToOne(fetch = FetchType.LAZY)
    private RiskTransferIndicator riskTransferIndCode;

    public Account() {
    }

    public Account(Integer acctNum) {
        this.acctNum = acctNum;
    }

    public Account(Integer acctNum, String acctShortName, String acctFullName, Character acctStatus, Character acctParentInd, Character acctSubInd, int transId, boolean allowsNetout, boolean allowsBookout) {
        this.acctNum = acctNum;
        this.acctShortName = acctShortName;
        this.acctFullName = acctFullName;
        this.acctStatus = acctStatus;
        this.acctParentInd = acctParentInd;
        this.acctSubInd = acctSubInd;
        this.transId = transId;
        this.allowsNetout = allowsNetout;
        this.allowsBookout = allowsBookout;
    }

    public Integer getAcctNum() {
        return acctNum;
    }

    public void setAcctNum(Integer acctNum) {
        this.acctNum = acctNum;
    }

    public String getAcctShortName() {
        return acctShortName;
    }

    public void setAcctShortName(String acctShortName) {
        this.acctShortName = acctShortName;
    }

    public String getAcctFullName() {
        return acctFullName;
    }

    public void setAcctFullName(String acctFullName) {
        this.acctFullName = acctFullName;
    }

    public Character getAcctStatus() {
        return acctStatus;
    }

    public void setAcctStatus(Character acctStatus) {
        this.acctStatus = acctStatus;
    }

    public Character getAcctParentInd() {
        return acctParentInd;
    }
    
    public void setAcctParentInd(Character acctParentInd) {
        this.acctParentInd = acctParentInd;
    }

    public Character getAcctSubInd() {
        return acctSubInd;
    }

    public void setAcctSubInd(Character acctSubInd) {
        this.acctSubInd = acctSubInd;
    }

    public String getAcctVatCode() {
        return acctVatCode;
    }

    public void setAcctVatCode(String acctVatCode) {
        this.acctVatCode = acctVatCode;
    }

    public String getAcctFiscalCode() {
        return acctFiscalCode;
    }

    public void setAcctFiscalCode(String acctFiscalCode) {
        this.acctFiscalCode = acctFiscalCode;
    }

    public String getAcctSubTypeCode() {
        return acctSubTypeCode;
    }

    public void setAcctSubTypeCode(String acctSubTypeCode) {
        this.acctSubTypeCode = acctSubTypeCode;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public Character getManInputSecQtyRequired() {
        return manInputSecQtyRequired;
    }

    public void setManInputSecQtyRequired(Character manInputSecQtyRequired) {
        this.manInputSecQtyRequired = manInputSecQtyRequired;
    }

    public Integer getLegalEntityNum() {
        return legalEntityNum;
    }

    public void setLegalEntityNum(Integer legalEntityNum) {
        this.legalEntityNum = legalEntityNum;
    }

    public String getGovtCode() {
        return govtCode;
    }

    public void setGovtCode(String govtCode) {
        this.govtCode = govtCode;
    }

    public boolean getAllowsNetout() {
        return allowsNetout;
    }

    public void setAllowsNetout(boolean allowsNetout) {
        this.allowsNetout = allowsNetout;
    }

    public boolean getAllowsBookout() {
        return allowsBookout;
    }

    public void setAllowsBookout(boolean allowsBookout) {
        this.allowsBookout = allowsBookout;
    }

    public Date getMasterAgreementDate() {
        return masterAgreementDate;
    }

    public void setMasterAgreementDate(Date masterAgreementDate) {
        this.masterAgreementDate = masterAgreementDate;
    }

    @XmlTransient
    public Collection<AccountAgreement> getAccountAgreementCollection() {
        return accountAgreementCollection;
    }

    public void setAccountAgreementCollection(Collection<AccountAgreement> accountAgreementCollection) {
        this.accountAgreementCollection = accountAgreementCollection;
    }

    @XmlTransient
    public Collection<AccountAgreement> getAccountAgreementCollection1() {
        return accountAgreementCollection1;
    }

    public void setAccountAgreementCollection1(Collection<AccountAgreement> accountAgreementCollection1) {
        this.accountAgreementCollection1 = accountAgreementCollection1;
    }

    @XmlTransient
    public Collection<AccountContact> getAccountContactCollection() {
        return accountContactCollection;
    }

    public void setAccountContactCollection(Collection<AccountContact> accountContactCollection) {
        this.accountContactCollection = accountContactCollection;
    }

    @XmlTransient
    public Collection<AccountAddress> getAccountAddressCollection() {
        return accountAddressCollection;
    }

    public void setAccountAddressCollection(Collection<AccountAddress> accountAddressCollection) {
        this.accountAddressCollection = accountAddressCollection;
    }

    public AccountType getAcctTypeCode() {
        return acctTypeCode;
    }

    public void setAcctTypeCode(AccountType acctTypeCode) {
        this.acctTypeCode = acctTypeCode;
    }

    public Comment getContractCmntNum() {
        return contractCmntNum;
    }

    public void setContractCmntNum(Comment contractCmntNum) {
        this.contractCmntNum = contractCmntNum;
    }

    public RiskTransferIndicator getRiskTransferIndCode() {
        return riskTransferIndCode;
    }

    public void setRiskTransferIndCode(RiskTransferIndicator riskTransferIndCode) {
        this.riskTransferIndCode = riskTransferIndCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acctNum != null ? acctNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.acctNum == null && other.acctNum != null) || (this.acctNum != null && !this.acctNum.equals(other.acctNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Account[ acctNum=" + acctNum + " ]";
    }
}