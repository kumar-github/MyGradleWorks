/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
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
@Table(name = "specification_group", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SpecificationGroup.findAll", query = "SELECT s FROM SpecificationGroup s"),
    @NamedQuery(name = "SpecificationGroup.findBySpecGroupCode", query = "SELECT s FROM SpecificationGroup s WHERE s.specGroupCode = :specGroupCode"),
    @NamedQuery(name = "SpecificationGroup.findBySpecGroupDesc", query = "SELECT s FROM SpecificationGroup s WHERE s.specGroupDesc = :specGroupDesc"),
    @NamedQuery(name = "SpecificationGroup.findByTransId", query = "SELECT s FROM SpecificationGroup s WHERE s.transId = :transId")})
public class SpecificationGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "spec_group_code", columnDefinition="CHAR")
    private String specGroupCode;
    
    @Basic(optional = false)
    @Column(name = "spec_group_desc")
    private String specGroupDesc;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(mappedBy = "specGroupCode")
    private Collection<Specification> specificationCollection;

    public SpecificationGroup() {
    }

    public SpecificationGroup(String specGroupCode) {
        this.specGroupCode = specGroupCode;
    }

    public SpecificationGroup(String specGroupCode, String specGroupDesc, int transId) {
        this.specGroupCode = specGroupCode;
        this.specGroupDesc = specGroupDesc;
        this.transId = transId;
    }

    public String getSpecGroupCode() {
        return specGroupCode;
    }

    public void setSpecGroupCode(String specGroupCode) {
        this.specGroupCode = specGroupCode;
    }

    public String getSpecGroupDesc() {
        return specGroupDesc;
    }

    public void setSpecGroupDesc(String specGroupDesc) {
        this.specGroupDesc = specGroupDesc;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<Specification> getSpecificationCollection() {
        return specificationCollection;
    }

    public void setSpecificationCollection(Collection<Specification> specificationCollection) {
        this.specificationCollection = specificationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (specGroupCode != null ? specGroupCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpecificationGroup)) {
            return false;
        }
        SpecificationGroup other = (SpecificationGroup) object;
        if ((this.specGroupCode == null && other.specGroupCode != null) || (this.specGroupCode != null && !this.specGroupCode.equals(other.specGroupCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SpecificationGroup[ specGroupCode=" + specGroupCode + " ]";
    }
    
}
