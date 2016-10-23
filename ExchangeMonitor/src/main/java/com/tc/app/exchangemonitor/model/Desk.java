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
@Table(name = "desk", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Desk.findAll", query = "SELECT d FROM Desk d"),
    @NamedQuery(name = "Desk.findByDeskCode", query = "SELECT d FROM Desk d WHERE d.deskCode = :deskCode"),
    @NamedQuery(name = "Desk.findByDeskName", query = "SELECT d FROM Desk d WHERE d.deskName = :deskName"),
    @NamedQuery(name = "Desk.findByManagerInit", query = "SELECT d FROM Desk d WHERE d.managerInit = :managerInit"),
    @NamedQuery(name = "Desk.findByTransId", query = "SELECT d FROM Desk d WHERE d.transId = :transId")})
public class Desk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "desk_code", columnDefinition="CHAR")
    private String deskCode;
    @Basic(optional = false)
    @Column(name = "desk_name")
    private String deskName;
    @Column(name = "manager_init", columnDefinition="CHAR")
    private String managerInit;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deskCode")
    private Collection<IctsUser> ictsUserCollection;
    @JoinColumn(name = "dept_code", referencedColumnName = "dept_code")
    @ManyToOne(optional = false)
    private Department deptCode;

    public Desk() {
    }

    public Desk(String deskCode) {
        this.deskCode = deskCode;
    }

    public Desk(String deskCode, String deskName, int transId) {
        this.deskCode = deskCode;
        this.deskName = deskName;
        this.transId = transId;
    }

    public String getDeskCode() {
        return deskCode;
    }

    public void setDeskCode(String deskCode) {
        this.deskCode = deskCode;
    }

    public String getDeskName() {
        return deskName;
    }

    public void setDeskName(String deskName) {
        this.deskName = deskName;
    }

    public String getManagerInit() {
        return managerInit;
    }

    public void setManagerInit(String managerInit) {
        this.managerInit = managerInit;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<IctsUser> getIctsUserCollection() {
        return ictsUserCollection;
    }

    public void setIctsUserCollection(Collection<IctsUser> ictsUserCollection) {
        this.ictsUserCollection = ictsUserCollection;
    }

    public Department getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(Department deptCode) {
        this.deptCode = deptCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deskCode != null ? deskCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Desk)) {
            return false;
        }
        Desk other = (Desk) object;
        if ((this.deskCode == null && other.deskCode != null) || (this.deskCode != null && !this.deskCode.equals(other.deskCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Desk[ deskCode=" + deskCode + " ]";
    }
    
}
