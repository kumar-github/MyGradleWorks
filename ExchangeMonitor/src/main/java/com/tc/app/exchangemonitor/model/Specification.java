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
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "specification", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Specification.findAll", query = "SELECT s FROM Specification s"),
    @NamedQuery(name = "Specification.findBySpecCode", query = "SELECT s FROM Specification s WHERE s.specCode = :specCode"),
    @NamedQuery(name = "Specification.findBySpecDesc", query = "SELECT s FROM Specification s WHERE s.specDesc = :specDesc"),
    @NamedQuery(name = "Specification.findBySortOrderingValue", query = "SELECT s FROM Specification s WHERE s.sortOrderingValue = :sortOrderingValue"),
    @NamedQuery(name = "Specification.findByTransId", query = "SELECT s FROM Specification s WHERE s.transId = :transId"),
    @NamedQuery(name = "Specification.findBySpecType", query = "SELECT s FROM Specification s WHERE s.specType = :specType"),
    @NamedQuery(name = "Specification.findBySpecValUomCode", query = "SELECT s FROM Specification s WHERE s.specValUomCode = :specValUomCode")})
public class Specification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "spec_code", columnDefinition="CHAR")
    private String specCode;
    
    @Basic(optional = false)
    @Column(name = "spec_desc")
    private String specDesc;
    @Column(name = "sort_ordering_value")
    private Short sortOrderingValue;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @Column(name = "spec_type")
    private Character specType;
    
    @Column(name = "spec_val_uom_code", columnDefinition="CHAR")
    private String specValUomCode;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "specification")
    private Collection<CommoditySpecification> commoditySpecificationCollection;
    
    @JoinColumn(name = "spec_group_code", referencedColumnName = "spec_group_code", columnDefinition="CHAR")
    @ManyToOne
    private SpecificationGroup specGroupCode;

    public Specification() {
    }

    public Specification(String specCode) {
        this.specCode = specCode;
    }

    public Specification(String specCode, String specDesc, int transId) {
        this.specCode = specCode;
        this.specDesc = specDesc;
        this.transId = transId;
    }

    public String getSpecCode() {
        return specCode;
    }

    public void setSpecCode(String specCode) {
        this.specCode = specCode;
    }

    public String getSpecDesc() {
        return specDesc;
    }

    public void setSpecDesc(String specDesc) {
        this.specDesc = specDesc;
    }

    public Short getSortOrderingValue() {
        return sortOrderingValue;
    }

    public void setSortOrderingValue(Short sortOrderingValue) {
        this.sortOrderingValue = sortOrderingValue;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public Character getSpecType() {
        return specType;
    }

    public void setSpecType(Character specType) {
        this.specType = specType;
    }

    public String getSpecValUomCode() {
        return specValUomCode;
    }

    public void setSpecValUomCode(String specValUomCode) {
        this.specValUomCode = specValUomCode;
    }

    @XmlTransient
    public Collection<CommoditySpecification> getCommoditySpecificationCollection() {
        return commoditySpecificationCollection;
    }

    public void setCommoditySpecificationCollection(Collection<CommoditySpecification> commoditySpecificationCollection) {
        this.commoditySpecificationCollection = commoditySpecificationCollection;
    }

    public SpecificationGroup getSpecGroupCode() {
        return specGroupCode;
    }

    public void setSpecGroupCode(SpecificationGroup specGroupCode) {
        this.specGroupCode = specGroupCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (specCode != null ? specCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Specification)) {
            return false;
        }
        Specification other = (Specification) object;
        if ((this.specCode == null && other.specCode != null) || (this.specCode != null && !this.specCode.equals(other.specCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Specification[ specCode=" + specCode + " ]";
    }
    
}
