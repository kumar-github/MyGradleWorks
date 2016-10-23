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
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "uom", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uom.findAll", query = "SELECT u FROM Uom u"),
    @NamedQuery(name = "Uom.findAllActiveUoms", query = "SELECT u FROM Uom u where u.uomStatus = 'A'"),
    @NamedQuery(name = "Uom.findByUomCode", query = "SELECT u FROM Uom u WHERE u.uomCode = :uomCode"),
    @NamedQuery(name = "Uom.findByUomType", query = "SELECT u FROM Uom u WHERE u.uomType = :uomType"),
    @NamedQuery(name = "Uom.findByUomStatus", query = "SELECT u FROM Uom u WHERE u.uomStatus = :uomStatus"),
    @NamedQuery(name = "Uom.findByUomShortName", query = "SELECT u FROM Uom u WHERE u.uomShortName = :uomShortName"),
    @NamedQuery(name = "Uom.findByUomFullName", query = "SELECT u FROM Uom u WHERE u.uomFullName = :uomFullName"),
    @NamedQuery(name = "Uom.findByUomNum", query = "SELECT u FROM Uom u WHERE u.uomNum = :uomNum"),
    @NamedQuery(name = "Uom.findByTransId", query = "SELECT u FROM Uom u WHERE u.transId = :transId")})
public class Uom implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uomCodeConvFrom", fetch = FetchType.LAZY)
    private Collection<UomConversion> uomConversionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uomCodeConvTo", fetch = FetchType.LAZY)
    private Collection<UomConversion> uomConversionCollection1;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uom_code", columnDefinition="CHAR")
    private String uomCode;
    
    @Basic(optional = false)
    @Column(name = "uom_type")
    private Character uomType;
    @Basic(optional = false)
    @Column(name = "uom_status")
    private Character uomStatus;
    @Basic(optional = false)
    @Column(name = "uom_short_name")
    private String uomShortName;
    @Basic(optional = false)
    @Column(name = "uom_full_name")
    private String uomFullName;
    @Column(name = "uom_num")
    private Short uomNum;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(mappedBy = "priceUomCode")
    private Collection<CommktSrcAliasInfo> commktSrcAliasInfoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commktLotUomCode")
    private Collection<CommktOptionAttr> commktOptionAttrCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commktPriceUomCode")
    private Collection<CommktOptionAttr> commktOptionAttrCollection1;
    @OneToMany(mappedBy = "qtyUomCode")
    private Collection<ExternalPosition> externalPositionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uom")
    private Collection<CommodityUom> commodityUomCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commktLotUomCode")
    private Collection<CommktClrdSwapAttr> commktClrdSwapAttrCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commktPriceUomCode")
    private Collection<CommktClrdSwapAttr> commktClrdSwapAttrCollection1;
    @OneToMany(mappedBy = "primUomCode")
    private Collection<Commodity> commodityCollection;
    @OneToMany(mappedBy = "secUomCode")
    private Collection<Commodity> commodityCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commktPriceUomCode")
    private Collection<CommktPhysicalAttr> commktPhysicalAttrCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commktLotUomCode")
    private Collection<CommktFutureAttr> commktFutureAttrCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commktPriceUomCode")
    private Collection<CommktFutureAttr> commktFutureAttrCollection1;

    public Uom() {
    }

    public Uom(String uomCode) {
        this.uomCode = uomCode;
    }

    public Uom(String uomCode, Character uomType, Character uomStatus, String uomShortName, String uomFullName, int transId) {
        this.uomCode = uomCode;
        this.uomType = uomType;
        this.uomStatus = uomStatus;
        this.uomShortName = uomShortName;
        this.uomFullName = uomFullName;
        this.transId = transId;
    }

    public String getUomCode() {
        return uomCode;
    }

    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    public Character getUomType() {
        return uomType;
    }

    public void setUomType(Character uomType) {
        this.uomType = uomType;
    }

    public Character getUomStatus() {
        return uomStatus;
    }

    public void setUomStatus(Character uomStatus) {
        this.uomStatus = uomStatus;
    }

    public String getUomShortName() {
        return uomShortName;
    }
    
    public String uomShortName()
    {
    	return uomShortName;
    }

    public void setUomShortName(String uomShortName) {
        this.uomShortName = uomShortName;
    }

    public String getUomFullName() {
        return uomFullName;
    }
    
    public String uomFullName()
    {
    	return uomFullName;
    }

    public void setUomFullName(String uomFullName) {
        this.uomFullName = uomFullName;
    }

    public Short getUomNum() {
        return uomNum;
    }

    public void setUomNum(Short uomNum) {
        this.uomNum = uomNum;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<CommktSrcAliasInfo> getCommktSrcAliasInfoCollection() {
        return commktSrcAliasInfoCollection;
    }

    public void setCommktSrcAliasInfoCollection(Collection<CommktSrcAliasInfo> commktSrcAliasInfoCollection) {
        this.commktSrcAliasInfoCollection = commktSrcAliasInfoCollection;
    }

    @XmlTransient
    public Collection<CommktOptionAttr> getCommktOptionAttrCollection() {
        return commktOptionAttrCollection;
    }

    public void setCommktOptionAttrCollection(Collection<CommktOptionAttr> commktOptionAttrCollection) {
        this.commktOptionAttrCollection = commktOptionAttrCollection;
    }

    @XmlTransient
    public Collection<CommktOptionAttr> getCommktOptionAttrCollection1() {
        return commktOptionAttrCollection1;
    }

    public void setCommktOptionAttrCollection1(Collection<CommktOptionAttr> commktOptionAttrCollection1) {
        this.commktOptionAttrCollection1 = commktOptionAttrCollection1;
    }

    @XmlTransient
    public Collection<ExternalPosition> getExternalPositionCollection() {
        return externalPositionCollection;
    }

    public void setExternalPositionCollection(Collection<ExternalPosition> externalPositionCollection) {
        this.externalPositionCollection = externalPositionCollection;
    }

    @XmlTransient
    public Collection<CommodityUom> getCommodityUomCollection() {
        return commodityUomCollection;
    }

    public void setCommodityUomCollection(Collection<CommodityUom> commodityUomCollection) {
        this.commodityUomCollection = commodityUomCollection;
    }

    @XmlTransient
    public Collection<CommktClrdSwapAttr> getCommktClrdSwapAttrCollection() {
        return commktClrdSwapAttrCollection;
    }

    public void setCommktClrdSwapAttrCollection(Collection<CommktClrdSwapAttr> commktClrdSwapAttrCollection) {
        this.commktClrdSwapAttrCollection = commktClrdSwapAttrCollection;
    }

    @XmlTransient
    public Collection<CommktClrdSwapAttr> getCommktClrdSwapAttrCollection1() {
        return commktClrdSwapAttrCollection1;
    }

    public void setCommktClrdSwapAttrCollection1(Collection<CommktClrdSwapAttr> commktClrdSwapAttrCollection1) {
        this.commktClrdSwapAttrCollection1 = commktClrdSwapAttrCollection1;
    }

    @XmlTransient
    public Collection<Commodity> getCommodityCollection() {
        return commodityCollection;
    }

    public void setCommodityCollection(Collection<Commodity> commodityCollection) {
        this.commodityCollection = commodityCollection;
    }

    @XmlTransient
    public Collection<Commodity> getCommodityCollection1() {
        return commodityCollection1;
    }

    public void setCommodityCollection1(Collection<Commodity> commodityCollection1) {
        this.commodityCollection1 = commodityCollection1;
    }

    @XmlTransient
    public Collection<CommktPhysicalAttr> getCommktPhysicalAttrCollection() {
        return commktPhysicalAttrCollection;
    }

    public void setCommktPhysicalAttrCollection(Collection<CommktPhysicalAttr> commktPhysicalAttrCollection) {
        this.commktPhysicalAttrCollection = commktPhysicalAttrCollection;
    }

    @XmlTransient
    public Collection<CommktFutureAttr> getCommktFutureAttrCollection() {
        return commktFutureAttrCollection;
    }

    public void setCommktFutureAttrCollection(Collection<CommktFutureAttr> commktFutureAttrCollection) {
        this.commktFutureAttrCollection = commktFutureAttrCollection;
    }

    @XmlTransient
    public Collection<CommktFutureAttr> getCommktFutureAttrCollection1() {
        return commktFutureAttrCollection1;
    }

    public void setCommktFutureAttrCollection1(Collection<CommktFutureAttr> commktFutureAttrCollection1) {
        this.commktFutureAttrCollection1 = commktFutureAttrCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uomCode != null ? uomCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uom)) {
            return false;
        }
        Uom other = (Uom) object;
        if ((this.uomCode == null && other.uomCode != null) || (this.uomCode != null && !this.uomCode.equals(other.uomCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Uom[ uomCode=" + uomCode + " ]";
    }

    @XmlTransient
    public Collection<UomConversion> getUomConversionCollection() {
        return uomConversionCollection;
    }

    public void setUomConversionCollection(Collection<UomConversion> uomConversionCollection) {
        this.uomConversionCollection = uomConversionCollection;
    }

    @XmlTransient
    public Collection<UomConversion> getUomConversionCollection1() {
        return uomConversionCollection1;
    }

    public void setUomConversionCollection1(Collection<UomConversion> uomConversionCollection1) {
        this.uomConversionCollection1 = uomConversionCollection1;
    }
    
}
