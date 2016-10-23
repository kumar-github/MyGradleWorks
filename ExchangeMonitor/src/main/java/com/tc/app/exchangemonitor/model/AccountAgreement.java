package com.tc.app.exchangemonitor.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account_agreement", catalog = "QA_30_trade_sep12", schema = "dbo")
public class AccountAgreement implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "agreement_num", nullable = false)
    private Integer agreementNum;
    @Basic(optional = false)
    @Column(name = "product_type", nullable = false)
    private Character productType;
    
    @Basic(optional = false)
    @Column(name = "agreement_code", nullable = false, length = 20, columnDefinition="CHAR")
    private String agreementCode;
    
    @Column(name = "ext_agreement_code", length = 20, columnDefinition="CHAR")
    private String extAgreementCode;
    
    @Column(name = "confirm_by")
    private Character confirmBy;
    @Basic(optional = false)
    @Column(name = "forward_netting_ind", nullable = false)
    private Character forwardNettingInd;
    @Basic(optional = false)
    @Column(name = "voucher_netting_ind", nullable = false)
    private Character voucherNettingInd;
    @Basic(optional = false)
    @Column(name = "trans_id", nullable = false)
    private int transId;
    @JoinColumn(name = "acct_num", referencedColumnName = "acct_num", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Account acctNum;
    @JoinColumn(name = "target_book_comp_num", referencedColumnName = "acct_num")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account targetBookCompNum;
    
    @JoinColumn(name = "trade_group_num", referencedColumnName = "trade_group_num", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TradeGroup tradeGroupNum;

    public AccountAgreement() {
    }

    public AccountAgreement(Integer agreementNum) {
        this.agreementNum = agreementNum;
    }

    public AccountAgreement(Integer agreementNum, Character productType, String agreementCode, Character forwardNettingInd, Character voucherNettingInd, int transId) {
        this.agreementNum = agreementNum;
        this.productType = productType;
        this.agreementCode = agreementCode;
        this.forwardNettingInd = forwardNettingInd;
        this.voucherNettingInd = voucherNettingInd;
        this.transId = transId;
    }

    public Integer getAgreementNum() {
        return agreementNum;
    }

    public void setAgreementNum(Integer agreementNum) {
        this.agreementNum = agreementNum;
    }

    public Character getProductType() {
        return productType;
    }

    public void setProductType(Character productType) {
        this.productType = productType;
    }

    public String getAgreementCode() {
        return agreementCode;
    }

    public void setAgreementCode(String agreementCode) {
        this.agreementCode = agreementCode;
    }

    public String getExtAgreementCode() {
        return extAgreementCode;
    }

    public void setExtAgreementCode(String extAgreementCode) {
        this.extAgreementCode = extAgreementCode;
    }

    public Character getConfirmBy() {
        return confirmBy;
    }

    public void setConfirmBy(Character confirmBy) {
        this.confirmBy = confirmBy;
    }

    public Character getForwardNettingInd() {
        return forwardNettingInd;
    }

    public void setForwardNettingInd(Character forwardNettingInd) {
        this.forwardNettingInd = forwardNettingInd;
    }

    public Character getVoucherNettingInd() {
        return voucherNettingInd;
    }

    public void setVoucherNettingInd(Character voucherNettingInd) {
        this.voucherNettingInd = voucherNettingInd;
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

    public Account getTargetBookCompNum() {
        return targetBookCompNum;
    }

    public void setTargetBookCompNum(Account targetBookCompNum) {
        this.targetBookCompNum = targetBookCompNum;
    }

    public TradeGroup getTradeGroupNum() {
        return tradeGroupNum;
    }

    public void setTradeGroupNum(TradeGroup tradeGroupNum) {
        this.tradeGroupNum = tradeGroupNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (agreementNum != null ? agreementNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountAgreement)) {
            return false;
        }
        AccountAgreement other = (AccountAgreement) object;
        if ((this.agreementNum == null && other.agreementNum != null) || (this.agreementNum != null && !this.agreementNum.equals(other.agreementNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generated11.AccountAgreement[ agreementNum=" + agreementNum + " ]";
    }
    
}
