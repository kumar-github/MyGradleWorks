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
 * @author smurugabushanam
 */
@Entity
@Table(name = "department", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
    @NamedQuery(name = "Department.findByDeptCode", query = "SELECT d FROM Department d WHERE d.deptCode = :deptCode"),
    @NamedQuery(name = "Department.findByDeptName", query = "SELECT d FROM Department d WHERE d.deptName = :deptName"),
    @NamedQuery(name = "Department.findByProfitCenterInd", query = "SELECT d FROM Department d WHERE d.profitCenterInd = :profitCenterInd"),
    @NamedQuery(name = "Department.findByManagerInit", query = "SELECT d FROM Department d WHERE d.managerInit = :managerInit"),
    @NamedQuery(name = "Department.findByUserContFld1Label", query = "SELECT d FROM Department d WHERE d.userContFld1Label = :userContFld1Label"),
    @NamedQuery(name = "Department.findByUserContFld2Label", query = "SELECT d FROM Department d WHERE d.userContFld2Label = :userContFld2Label"),
    @NamedQuery(name = "Department.findByUserContFld3Label", query = "SELECT d FROM Department d WHERE d.userContFld3Label = :userContFld3Label"),
    @NamedQuery(name = "Department.findByUserContFld4Label", query = "SELECT d FROM Department d WHERE d.userContFld4Label = :userContFld4Label"),
    @NamedQuery(name = "Department.findByUserContFld5Label", query = "SELECT d FROM Department d WHERE d.userContFld5Label = :userContFld5Label"),
    @NamedQuery(name = "Department.findByUserContFld6Label", query = "SELECT d FROM Department d WHERE d.userContFld6Label = :userContFld6Label"),
    @NamedQuery(name = "Department.findByUserAcctFld1Label", query = "SELECT d FROM Department d WHERE d.userAcctFld1Label = :userAcctFld1Label"),
    @NamedQuery(name = "Department.findByUserAcctFld2Label", query = "SELECT d FROM Department d WHERE d.userAcctFld2Label = :userAcctFld2Label"),
    @NamedQuery(name = "Department.findByUserAcctFld3Label", query = "SELECT d FROM Department d WHERE d.userAcctFld3Label = :userAcctFld3Label"),
    @NamedQuery(name = "Department.findByUserAcctFld4Label", query = "SELECT d FROM Department d WHERE d.userAcctFld4Label = :userAcctFld4Label"),
    @NamedQuery(name = "Department.findByUserAcctFld5Label", query = "SELECT d FROM Department d WHERE d.userAcctFld5Label = :userAcctFld5Label"),
    @NamedQuery(name = "Department.findByUserAcctFld6Label", query = "SELECT d FROM Department d WHERE d.userAcctFld6Label = :userAcctFld6Label"),
    @NamedQuery(name = "Department.findByDeptNum", query = "SELECT d FROM Department d WHERE d.deptNum = :deptNum"),
    @NamedQuery(name = "Department.findByTransId", query = "SELECT d FROM Department d WHERE d.transId = :transId")})
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "dept_code", columnDefinition="CHAR")
    private String deptCode;
    @Basic(optional = false)
    @Column(name = "dept_name")
    private String deptName;
    @Basic(optional = false)
    @Column(name = "profit_center_ind")
    private Character profitCenterInd;
    @Column(name = "manager_init")
    private String managerInit;
    @Column(name = "user_cont_fld1_label")
    private String userContFld1Label;
    @Column(name = "user_cont_fld2_label")
    private String userContFld2Label;
    @Column(name = "user_cont_fld3_label")
    private String userContFld3Label;
    @Column(name = "user_cont_fld4_label")
    private String userContFld4Label;
    @Column(name = "user_cont_fld5_label")
    private String userContFld5Label;
    @Column(name = "user_cont_fld6_label")
    private String userContFld6Label;
    @Column(name = "user_acct_fld1_label")
    private String userAcctFld1Label;
    @Column(name = "user_acct_fld2_label")
    private String userAcctFld2Label;
    @Column(name = "user_acct_fld3_label")
    private String userAcctFld3Label;
    @Column(name = "user_acct_fld4_label")
    private String userAcctFld4Label;
    @Column(name = "user_acct_fld5_label")
    private String userAcctFld5Label;
    @Column(name = "user_acct_fld6_label")
    private String userAcctFld6Label;
    @Column(name = "dept_num")
    private Short deptNum;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deptCode")
    private Collection<Desk> deskCollection;
    @JoinColumn(name = "trading_entity_num", referencedColumnName = "acct_num")
    @ManyToOne
    private Account tradingEntityNum;

    public Department() {
    }

    public Department(String deptCode) {
        this.deptCode = deptCode;
    }

    public Department(String deptCode, String deptName, Character profitCenterInd, int transId) {
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.profitCenterInd = profitCenterInd;
        this.transId = transId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Character getProfitCenterInd() {
        return profitCenterInd;
    }

    public void setProfitCenterInd(Character profitCenterInd) {
        this.profitCenterInd = profitCenterInd;
    }

    public String getManagerInit() {
        return managerInit;
    }

    public void setManagerInit(String managerInit) {
        this.managerInit = managerInit;
    }

    public String getUserContFld1Label() {
        return userContFld1Label;
    }

    public void setUserContFld1Label(String userContFld1Label) {
        this.userContFld1Label = userContFld1Label;
    }

    public String getUserContFld2Label() {
        return userContFld2Label;
    }

    public void setUserContFld2Label(String userContFld2Label) {
        this.userContFld2Label = userContFld2Label;
    }

    public String getUserContFld3Label() {
        return userContFld3Label;
    }

    public void setUserContFld3Label(String userContFld3Label) {
        this.userContFld3Label = userContFld3Label;
    }

    public String getUserContFld4Label() {
        return userContFld4Label;
    }

    public void setUserContFld4Label(String userContFld4Label) {
        this.userContFld4Label = userContFld4Label;
    }

    public String getUserContFld5Label() {
        return userContFld5Label;
    }

    public void setUserContFld5Label(String userContFld5Label) {
        this.userContFld5Label = userContFld5Label;
    }

    public String getUserContFld6Label() {
        return userContFld6Label;
    }

    public void setUserContFld6Label(String userContFld6Label) {
        this.userContFld6Label = userContFld6Label;
    }

    public String getUserAcctFld1Label() {
        return userAcctFld1Label;
    }

    public void setUserAcctFld1Label(String userAcctFld1Label) {
        this.userAcctFld1Label = userAcctFld1Label;
    }

    public String getUserAcctFld2Label() {
        return userAcctFld2Label;
    }

    public void setUserAcctFld2Label(String userAcctFld2Label) {
        this.userAcctFld2Label = userAcctFld2Label;
    }

    public String getUserAcctFld3Label() {
        return userAcctFld3Label;
    }

    public void setUserAcctFld3Label(String userAcctFld3Label) {
        this.userAcctFld3Label = userAcctFld3Label;
    }

    public String getUserAcctFld4Label() {
        return userAcctFld4Label;
    }

    public void setUserAcctFld4Label(String userAcctFld4Label) {
        this.userAcctFld4Label = userAcctFld4Label;
    }

    public String getUserAcctFld5Label() {
        return userAcctFld5Label;
    }

    public void setUserAcctFld5Label(String userAcctFld5Label) {
        this.userAcctFld5Label = userAcctFld5Label;
    }

    public String getUserAcctFld6Label() {
        return userAcctFld6Label;
    }

    public void setUserAcctFld6Label(String userAcctFld6Label) {
        this.userAcctFld6Label = userAcctFld6Label;
    }

    public Short getDeptNum() {
        return deptNum;
    }

    public void setDeptNum(Short deptNum) {
        this.deptNum = deptNum;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<Desk> getDeskCollection() {
        return deskCollection;
    }

    public void setDeskCollection(Collection<Desk> deskCollection) {
        this.deskCollection = deskCollection;
    }

    public Account getTradingEntityNum() {
        return tradingEntityNum;
    }

    public void setTradingEntityNum(Account tradingEntityNum) {
        this.tradingEntityNum = tradingEntityNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptCode != null ? deptCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.deptCode == null && other.deptCode != null) || (this.deptCode != null && !this.deptCode.equals(other.deptCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Department[ deptCode=" + deptCode + " ]";
    }
    
}
