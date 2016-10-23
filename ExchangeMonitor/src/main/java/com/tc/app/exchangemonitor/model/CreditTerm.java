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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "credit_term", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CreditTerm.findAll", query = "SELECT c FROM CreditTerm c"),
    @NamedQuery(name = "CreditTerm.findByCreditTermCode", query = "SELECT c FROM CreditTerm c WHERE c.creditTermCode = :creditTermCode"),
    @NamedQuery(name = "CreditTerm.findByCreditTermDesc", query = "SELECT c FROM CreditTerm c WHERE c.creditTermDesc = :creditTermDesc"),
    @NamedQuery(name = "CreditTerm.findByCreditTermContrDesc", query = "SELECT c FROM CreditTerm c WHERE c.creditTermContrDesc = :creditTermContrDesc"),
    @NamedQuery(name = "CreditTerm.findByCreditSecureInd", query = "SELECT c FROM CreditTerm c WHERE c.creditSecureInd = :creditSecureInd"),
    @NamedQuery(name = "CreditTerm.findByDocTypeCode", query = "SELECT c FROM CreditTerm c WHERE c.docTypeCode = :docTypeCode"),
    @NamedQuery(name = "CreditTerm.findByTransId", query = "SELECT c FROM CreditTerm c WHERE c.transId = :transId")})
public class CreditTerm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "credit_term_code", columnDefinition="CHAR")
    private String creditTermCode;
    
    @Basic(optional = false)
    @Column(name = "credit_term_desc")
    private String creditTermDesc;
    @Column(name = "credit_term_contr_desc")
    private String creditTermContrDesc;
    @Basic(optional = false)
    @Column(name = "credit_secure_ind")
    private Character creditSecureInd;
    
    @Column(name = "doc_type_code", columnDefinition="CHAR")
    private String docTypeCode;
    
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creditTerm")
    private Collection<CreditTermGroup> creditTermGroupCollection;

    public CreditTerm() {
    }

    public CreditTerm(String creditTermCode) {
        this.creditTermCode = creditTermCode;
    }

    public CreditTerm(String creditTermCode, String creditTermDesc, Character creditSecureInd, int transId) {
        this.creditTermCode = creditTermCode;
        this.creditTermDesc = creditTermDesc;
        this.creditSecureInd = creditSecureInd;
        this.transId = transId;
    }

    public String getCreditTermCode() {
        return creditTermCode;
    }

    public void setCreditTermCode(String creditTermCode) {
        this.creditTermCode = creditTermCode;
    }

    public String getCreditTermDesc() {
        return creditTermDesc;
    }

    public void setCreditTermDesc(String creditTermDesc) {
        this.creditTermDesc = creditTermDesc;
    }

    public String getCreditTermContrDesc() {
        return creditTermContrDesc;
    }

    public void setCreditTermContrDesc(String creditTermContrDesc) {
        this.creditTermContrDesc = creditTermContrDesc;
    }

    public Character getCreditSecureInd() {
        return creditSecureInd;
    }

    public void setCreditSecureInd(Character creditSecureInd) {
        this.creditSecureInd = creditSecureInd;
    }

    public String getDocTypeCode() {
        return docTypeCode;
    }

    public void setDocTypeCode(String docTypeCode) {
        this.docTypeCode = docTypeCode;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<CreditTermGroup> getCreditTermGroupCollection() {
        return creditTermGroupCollection;
    }

    public void setCreditTermGroupCollection(Collection<CreditTermGroup> creditTermGroupCollection) {
        this.creditTermGroupCollection = creditTermGroupCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (creditTermCode != null ? creditTermCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditTerm)) {
            return false;
        }
        CreditTerm other = (CreditTerm) object;
        if ((this.creditTermCode == null && other.creditTermCode != null) || (this.creditTermCode != null && !this.creditTermCode.equals(other.creditTermCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CreditTerm[ creditTermCode=" + creditTermCode + " ]";
    }
    
}
