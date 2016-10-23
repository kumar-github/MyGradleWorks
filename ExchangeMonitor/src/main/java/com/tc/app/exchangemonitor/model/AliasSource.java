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
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name = "alias_source", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AliasSource.findAll", query = "SELECT a FROM AliasSource a"),
    @NamedQuery(name = "AliasSource.findByAliasSourceCode", query = "SELECT a FROM AliasSource a WHERE a.aliasSourceCode = :aliasSourceCode"),
    @NamedQuery(name = "AliasSource.findByAliasSourceType", query = "SELECT a FROM AliasSource a WHERE a.aliasSourceType = :aliasSourceType"),
    @NamedQuery(name = "AliasSource.findByAliasSourceDesc", query = "SELECT a FROM AliasSource a WHERE a.aliasSourceDesc = :aliasSourceDesc"),
    @NamedQuery(name = "AliasSource.findByTransId", query = "SELECT a FROM AliasSource a WHERE a.transId = :transId")})
public class AliasSource implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "alias_source_code", nullable = false, length = 8, columnDefinition="CHAR")
    private String aliasSourceCode;
    
    @Basic(optional = false)
    @Column(name = "alias_source_type", nullable = false)
    private Character aliasSourceType;
    @Basic(optional = false)
    @Column(name = "alias_source_desc", nullable = false, length = 40)
    private String aliasSourceDesc;
    @Basic(optional = false)
    @Column(name = "trans_id", nullable = false)
    private int transId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aliasSource", fetch = FetchType.LAZY)
    private Collection<AccountAlias> accountAliasCollection;

    public AliasSource() {
    }

    public AliasSource(String aliasSourceCode) {
        this.aliasSourceCode = aliasSourceCode;
    }

    public AliasSource(String aliasSourceCode, Character aliasSourceType, String aliasSourceDesc, int transId) {
        this.aliasSourceCode = aliasSourceCode;
        this.aliasSourceType = aliasSourceType;
        this.aliasSourceDesc = aliasSourceDesc;
        this.transId = transId;
    }

    public String getAliasSourceCode() {
        return aliasSourceCode;
    }

    public void setAliasSourceCode(String aliasSourceCode) {
        this.aliasSourceCode = aliasSourceCode;
    }

    public Character getAliasSourceType() {
        return aliasSourceType;
    }

    public void setAliasSourceType(Character aliasSourceType) {
        this.aliasSourceType = aliasSourceType;
    }

    public String getAliasSourceDesc() {
        return aliasSourceDesc;
    }

    public void setAliasSourceDesc(String aliasSourceDesc) {
        this.aliasSourceDesc = aliasSourceDesc;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<AccountAlias> getAccountAliasCollection() {
        return accountAliasCollection;
    }

    public void setAccountAliasCollection(Collection<AccountAlias> accountAliasCollection) {
        this.accountAliasCollection = accountAliasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aliasSourceCode != null ? aliasSourceCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AliasSource)) {
            return false;
        }
        AliasSource other = (AliasSource) object;
        if ((this.aliasSourceCode == null && other.aliasSourceCode != null) || (this.aliasSourceCode != null && !this.aliasSourceCode.equals(other.aliasSourceCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generated11.AliasSource[ aliasSourceCode=" + aliasSourceCode + " ]";
    }
    
}
