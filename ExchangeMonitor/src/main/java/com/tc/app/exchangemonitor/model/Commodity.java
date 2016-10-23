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
@Table(name = "commodity", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commodity.findAll", query = "SELECT c FROM Commodity c"),
    @NamedQuery(name = "Commodity.findByCmdtyCode", query = "SELECT c FROM Commodity c WHERE c.cmdtyCode = :cmdtyCode"),
    @NamedQuery(name = "Commodity.findByCmdtyTradeableInd", query = "SELECT c FROM Commodity c WHERE c.cmdtyTradeableInd = :cmdtyTradeableInd"),
    @NamedQuery(name = "Commodity.findByCmdtyStatus", query = "SELECT c FROM Commodity c WHERE c.cmdtyStatus = :cmdtyStatus"),
    @NamedQuery(name = "Commodity.findByCmdtyShortName", query = "SELECT c FROM Commodity c WHERE c.cmdtyShortName = :cmdtyShortName"),
    @NamedQuery(name = "Commodity.findByCmdtyFullName", query = "SELECT c FROM Commodity c WHERE c.cmdtyFullName = :cmdtyFullName"),
    @NamedQuery(name = "Commodity.findByCmdtyLocDesc", query = "SELECT c FROM Commodity c WHERE c.cmdtyLocDesc = :cmdtyLocDesc"),
    @NamedQuery(name = "Commodity.findByPrimCurrConvRate", query = "SELECT c FROM Commodity c WHERE c.primCurrConvRate = :primCurrConvRate"),
    @NamedQuery(name = "Commodity.findByTransId", query = "SELECT c FROM Commodity c WHERE c.transId = :transId"),
    @NamedQuery(name = "Commodity.findByGrade", query = "SELECT c FROM Commodity c WHERE c.grade = :grade")})
public class Commodity implements Serializable
{
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "desiredPlCurrCode", fetch = FetchType.LAZY)
    private Collection<Portfolio> portfolioCollection;
    @OneToMany(mappedBy = "cmdtyCode", fetch = FetchType.LAZY)
    private Collection<UomConversion> uomConversionCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cmdty_code", columnDefinition="CHAR")
    private String cmdtyCode;
    
    @Basic(optional = false)
    @Column(name = "cmdty_tradeable_ind")
    private Character cmdtyTradeableInd;
    @Basic(optional = false)
    @Column(name = "cmdty_status")
    private Character cmdtyStatus;
    @Basic(optional = false)
    @Column(name = "cmdty_short_name")
    private String cmdtyShortName;
    @Basic(optional = false)
    @Column(name = "cmdty_full_name")
    private String cmdtyFullName;
    @Column(name = "cmdty_loc_desc")
    private String cmdtyLocDesc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prim_curr_conv_rate")
    private Double primCurrConvRate;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    
    @Column(name = "grade", columnDefinition="CHAR")
    private String grade;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commktCurrCode")
    private Collection<CommktOptionAttr> commktOptionAttrCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmdtyCode")
    private Collection<CommodityMarket> commodityMarketCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commodity")
    private Collection<CommodityUom> commodityUomCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commktCurrCode")
    private Collection<CommktClrdSwapAttr> commktClrdSwapAttrCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compCmdtyCode")
    private Collection<CommktClrdSwapAttr> commktClrdSwapAttrCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commodity")
    private Collection<CommoditySpecification> commoditySpecificationCollection;
    @OneToMany(mappedBy = "primCurrCode")
    private Collection<Commodity> commodityCollection;
    @JoinColumn(name = "prim_curr_code", referencedColumnName = "cmdty_code")
    @ManyToOne
    private Commodity primCurrCode;
    
    @JoinColumn(name = "cmdty_category_code", referencedColumnName = "cmdty_category_code", columnDefinition="CHAR")
    @ManyToOne
    private CommodityCategory cmdtyCategoryCode;
    
    @JoinColumn(name = "cmdty_type", referencedColumnName = "cmdty_type_code", columnDefinition="CHAR")
    @ManyToOne(optional = false)
    private CommodityType cmdtyType;
    
    @JoinColumn(name = "country_code", referencedColumnName = "country_code", columnDefinition="CHAR")
    @ManyToOne
    private Country countryCode;
    
    @JoinColumn(name = "prim_uom_code", referencedColumnName = "uom_code", columnDefinition="CHAR")
    @ManyToOne
    private Uom primUomCode;
    
    @JoinColumn(name = "sec_uom_code", referencedColumnName = "uom_code", columnDefinition="CHAR")
    @ManyToOne
    private Uom secUomCode;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commktCurrCode")
    private Collection<CommktPhysicalAttr> commktPhysicalAttrCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commodity")
    private Collection<CommodityGroup> commodityGroupCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commodity1")
    private Collection<CommodityGroup> commodityGroupCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commodity")
    private Collection<CommodityLocation> commodityLocationCollection;
    @OneToMany(mappedBy = "cmdtyDeliveredScheduled")
    private Collection<CommodityLocation> commodityLocationCollection1;
    @OneToMany(mappedBy = "cmdtyReceivedScheduled")
    private Collection<CommodityLocation> commodityLocationCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commodity")
    private Collection<CommodityDesc> commodityDescCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commktCurrCode")
    private Collection<CommktFutureAttr> commktFutureAttrCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commodity")
    private Collection<CommodityAlias> commodityAliasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commodity")
    private Collection<CommodityRollupHierarchy> commodityRollupHierarchyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commodity1")
    private Collection<CommodityRollupHierarchy> commodityRollupHierarchyCollection1;

    public Commodity() {
    }

    public Commodity(String cmdtyCode) {
        this.cmdtyCode = cmdtyCode;
    }

    public Commodity(String cmdtyCode, Character cmdtyTradeableInd, Character cmdtyStatus, String cmdtyShortName, String cmdtyFullName, int transId) {
        this.cmdtyCode = cmdtyCode;
        this.cmdtyTradeableInd = cmdtyTradeableInd;
        this.cmdtyStatus = cmdtyStatus;
        this.cmdtyShortName = cmdtyShortName;
        this.cmdtyFullName = cmdtyFullName;
        this.transId = transId;
    }

    public String getCmdtyCode()
    {
        return cmdtyCode;
    }
    
    public String cmdtyCode()
    {
    	return cmdtyCode;
    }

    public void setCmdtyCode(String cmdtyCode) {
        this.cmdtyCode = cmdtyCode;
    }

    public Character getCmdtyTradeableInd() {
        return cmdtyTradeableInd;
    }

    public void setCmdtyTradeableInd(Character cmdtyTradeableInd) {
        this.cmdtyTradeableInd = cmdtyTradeableInd;
    }

    public Character getCmdtyStatus() {
        return cmdtyStatus;
    }

    public void setCmdtyStatus(Character cmdtyStatus) {
        this.cmdtyStatus = cmdtyStatus;
    }

    public String getCmdtyShortName() {
        return cmdtyShortName;
    }
    
    public String cmdtyShortName()
    {
    	return cmdtyShortName;
    }

    public void setCmdtyShortName(String cmdtyShortName) {
        this.cmdtyShortName = cmdtyShortName;
    }

    public String getCmdtyFullName() {
        return cmdtyFullName;
    }
    
    public String cmdtyFullName()
    {
    	return cmdtyFullName;
    }

    public void setCmdtyFullName(String cmdtyFullName) {
        this.cmdtyFullName = cmdtyFullName;
    }

    public String getCmdtyLocDesc() {
        return cmdtyLocDesc;
    }

    public void setCmdtyLocDesc(String cmdtyLocDesc) {
        this.cmdtyLocDesc = cmdtyLocDesc;
    }

    public Double getPrimCurrConvRate() {
        return primCurrConvRate;
    }

    public void setPrimCurrConvRate(Double primCurrConvRate) {
        this.primCurrConvRate = primCurrConvRate;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @XmlTransient
    public Collection<CommktOptionAttr> getCommktOptionAttrCollection() {
        return commktOptionAttrCollection;
    }

    public void setCommktOptionAttrCollection(Collection<CommktOptionAttr> commktOptionAttrCollection) {
        this.commktOptionAttrCollection = commktOptionAttrCollection;
    }

    @XmlTransient
    public Collection<CommodityMarket> getCommodityMarketCollection() {
        return commodityMarketCollection;
    }

    public void setCommodityMarketCollection(Collection<CommodityMarket> commodityMarketCollection) {
        this.commodityMarketCollection = commodityMarketCollection;
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
    public Collection<CommoditySpecification> getCommoditySpecificationCollection() {
        return commoditySpecificationCollection;
    }

    public void setCommoditySpecificationCollection(Collection<CommoditySpecification> commoditySpecificationCollection) {
        this.commoditySpecificationCollection = commoditySpecificationCollection;
    }

    @XmlTransient
    public Collection<Commodity> getCommodityCollection() {
        return commodityCollection;
    }

    public void setCommodityCollection(Collection<Commodity> commodityCollection) {
        this.commodityCollection = commodityCollection;
    }

    public Commodity getPrimCurrCode() {
        return primCurrCode;
    }

    public void setPrimCurrCode(Commodity primCurrCode) {
        this.primCurrCode = primCurrCode;
    }

    public CommodityCategory getCmdtyCategoryCode() {
        return cmdtyCategoryCode;
    }

    public void setCmdtyCategoryCode(CommodityCategory cmdtyCategoryCode) {
        this.cmdtyCategoryCode = cmdtyCategoryCode;
    }

    public CommodityType getCmdtyType() {
        return cmdtyType;
    }

    public void setCmdtyType(CommodityType cmdtyType) {
        this.cmdtyType = cmdtyType;
    }

    public Country getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Country countryCode) {
        this.countryCode = countryCode;
    }

    public Uom getPrimUomCode() {
        return primUomCode;
    }

    public void setPrimUomCode(Uom primUomCode) {
        this.primUomCode = primUomCode;
    }

    public Uom getSecUomCode() {
        return secUomCode;
    }

    public void setSecUomCode(Uom secUomCode) {
        this.secUomCode = secUomCode;
    }

    @XmlTransient
    public Collection<CommktPhysicalAttr> getCommktPhysicalAttrCollection() {
        return commktPhysicalAttrCollection;
    }

    public void setCommktPhysicalAttrCollection(Collection<CommktPhysicalAttr> commktPhysicalAttrCollection) {
        this.commktPhysicalAttrCollection = commktPhysicalAttrCollection;
    }

    @XmlTransient
    public Collection<CommodityGroup> getCommodityGroupCollection() {
        return commodityGroupCollection;
    }

    public void setCommodityGroupCollection(Collection<CommodityGroup> commodityGroupCollection) {
        this.commodityGroupCollection = commodityGroupCollection;
    }

    @XmlTransient
    public Collection<CommodityGroup> getCommodityGroupCollection1() {
        return commodityGroupCollection1;
    }

    public void setCommodityGroupCollection1(Collection<CommodityGroup> commodityGroupCollection1) {
        this.commodityGroupCollection1 = commodityGroupCollection1;
    }

    @XmlTransient
    public Collection<CommodityLocation> getCommodityLocationCollection() {
        return commodityLocationCollection;
    }

    public void setCommodityLocationCollection(Collection<CommodityLocation> commodityLocationCollection) {
        this.commodityLocationCollection = commodityLocationCollection;
    }

    @XmlTransient
    public Collection<CommodityLocation> getCommodityLocationCollection1() {
        return commodityLocationCollection1;
    }

    public void setCommodityLocationCollection1(Collection<CommodityLocation> commodityLocationCollection1) {
        this.commodityLocationCollection1 = commodityLocationCollection1;
    }

    @XmlTransient
    public Collection<CommodityLocation> getCommodityLocationCollection2() {
        return commodityLocationCollection2;
    }

    public void setCommodityLocationCollection2(Collection<CommodityLocation> commodityLocationCollection2) {
        this.commodityLocationCollection2 = commodityLocationCollection2;
    }

    @XmlTransient
    public Collection<CommodityDesc> getCommodityDescCollection() {
        return commodityDescCollection;
    }

    public void setCommodityDescCollection(Collection<CommodityDesc> commodityDescCollection) {
        this.commodityDescCollection = commodityDescCollection;
    }

    @XmlTransient
    public Collection<CommktFutureAttr> getCommktFutureAttrCollection() {
        return commktFutureAttrCollection;
    }

    public void setCommktFutureAttrCollection(Collection<CommktFutureAttr> commktFutureAttrCollection) {
        this.commktFutureAttrCollection = commktFutureAttrCollection;
    }

    @XmlTransient
    public Collection<CommodityAlias> getCommodityAliasCollection() {
        return commodityAliasCollection;
    }

    public void setCommodityAliasCollection(Collection<CommodityAlias> commodityAliasCollection) {
        this.commodityAliasCollection = commodityAliasCollection;
    }

    @XmlTransient
    public Collection<CommodityRollupHierarchy> getCommodityRollupHierarchyCollection() {
        return commodityRollupHierarchyCollection;
    }

    public void setCommodityRollupHierarchyCollection(Collection<CommodityRollupHierarchy> commodityRollupHierarchyCollection) {
        this.commodityRollupHierarchyCollection = commodityRollupHierarchyCollection;
    }

    @XmlTransient
    public Collection<CommodityRollupHierarchy> getCommodityRollupHierarchyCollection1() {
        return commodityRollupHierarchyCollection1;
    }

    public void setCommodityRollupHierarchyCollection1(Collection<CommodityRollupHierarchy> commodityRollupHierarchyCollection1) {
        this.commodityRollupHierarchyCollection1 = commodityRollupHierarchyCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cmdtyCode != null ? cmdtyCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commodity)) {
            return false;
        }
        Commodity other = (Commodity) object;
        if ((this.cmdtyCode == null && other.cmdtyCode != null) || (this.cmdtyCode != null && !this.cmdtyCode.equals(other.cmdtyCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commodity[ cmdtyCode=" + cmdtyCode + " ]";
    }

    @XmlTransient
    public Collection<Portfolio> getPortfolioCollection() {
        return portfolioCollection;
    }

    public void setPortfolioCollection(Collection<Portfolio> portfolioCollection) {
        this.portfolioCollection = portfolioCollection;
    }

    @XmlTransient
    public Collection<UomConversion> getUomConversionCollection() {
        return uomConversionCollection;
    }

    public void setUomConversionCollection(Collection<UomConversion> uomConversionCollection) {
        this.uomConversionCollection = uomConversionCollection;
    }
}