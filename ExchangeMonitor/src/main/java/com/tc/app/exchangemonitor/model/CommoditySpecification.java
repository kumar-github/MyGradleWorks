/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "commodity_specification", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommoditySpecification.findAll", query = "SELECT c FROM CommoditySpecification c"),
    @NamedQuery(name = "CommoditySpecification.findByCmdtyCode", query = "SELECT c FROM CommoditySpecification c WHERE c.commoditySpecificationPK.cmdtyCode = :cmdtyCode"),
    @NamedQuery(name = "CommoditySpecification.findBySpecCode", query = "SELECT c FROM CommoditySpecification c WHERE c.commoditySpecificationPK.specCode = :specCode"),
    @NamedQuery(name = "CommoditySpecification.findByCmdtySpecMinVal", query = "SELECT c FROM CommoditySpecification c WHERE c.cmdtySpecMinVal = :cmdtySpecMinVal"),
    @NamedQuery(name = "CommoditySpecification.findByCmdtySpecMaxVal", query = "SELECT c FROM CommoditySpecification c WHERE c.cmdtySpecMaxVal = :cmdtySpecMaxVal"),
    @NamedQuery(name = "CommoditySpecification.findByCmdtySpecTypicalVal", query = "SELECT c FROM CommoditySpecification c WHERE c.cmdtySpecTypicalVal = :cmdtySpecTypicalVal"),
    @NamedQuery(name = "CommoditySpecification.findBySpecType", query = "SELECT c FROM CommoditySpecification c WHERE c.specType = :specType"),
    @NamedQuery(name = "CommoditySpecification.findByTransId", query = "SELECT c FROM CommoditySpecification c WHERE c.transId = :transId"),
    @NamedQuery(name = "CommoditySpecification.findByTypicalStringValue", query = "SELECT c FROM CommoditySpecification c WHERE c.typicalStringValue = :typicalStringValue"),
    @NamedQuery(name = "CommoditySpecification.findByDfltSpecTestCode", query = "SELECT c FROM CommoditySpecification c WHERE c.dfltSpecTestCode = :dfltSpecTestCode"),
    @NamedQuery(name = "CommoditySpecification.findByStandardInd", query = "SELECT c FROM CommoditySpecification c WHERE c.standardInd = :standardInd")})
public class CommoditySpecification implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommoditySpecificationPK commoditySpecificationPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cmdty_spec_min_val")
    private BigDecimal cmdtySpecMinVal;
    @Column(name = "cmdty_spec_max_val")
    private BigDecimal cmdtySpecMaxVal;
    @Column(name = "cmdty_spec_typical_val")
    private BigDecimal cmdtySpecTypicalVal;
    @Basic(optional = false)
    @Column(name = "spec_type")
    private Character specType;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @Column(name = "typical_string_value")
    private String typicalStringValue;
    
    @Column(name = "dflt_spec_test_code", columnDefinition="CHAR")
    private String dfltSpecTestCode;
    
    @Basic(optional = false)
    @Column(name = "standard_ind")
    private Character standardInd;
    @JoinColumn(name = "cmdty_code", referencedColumnName = "cmdty_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Commodity commodity;
    @JoinColumn(name = "spec_code", referencedColumnName = "spec_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Specification specification;

    public CommoditySpecification() {
    }

    public CommoditySpecification(CommoditySpecificationPK commoditySpecificationPK) {
        this.commoditySpecificationPK = commoditySpecificationPK;
    }

    public CommoditySpecification(CommoditySpecificationPK commoditySpecificationPK, Character specType, int transId, Character standardInd) {
        this.commoditySpecificationPK = commoditySpecificationPK;
        this.specType = specType;
        this.transId = transId;
        this.standardInd = standardInd;
    }

    public CommoditySpecification(String cmdtyCode, String specCode) {
        this.commoditySpecificationPK = new CommoditySpecificationPK(cmdtyCode, specCode);
    }

    public CommoditySpecificationPK getCommoditySpecificationPK() {
        return commoditySpecificationPK;
    }

    public void setCommoditySpecificationPK(CommoditySpecificationPK commoditySpecificationPK) {
        this.commoditySpecificationPK = commoditySpecificationPK;
    }

    public BigDecimal getCmdtySpecMinVal() {
        return cmdtySpecMinVal;
    }

    public void setCmdtySpecMinVal(BigDecimal cmdtySpecMinVal) {
        this.cmdtySpecMinVal = cmdtySpecMinVal;
    }

    public BigDecimal getCmdtySpecMaxVal() {
        return cmdtySpecMaxVal;
    }

    public void setCmdtySpecMaxVal(BigDecimal cmdtySpecMaxVal) {
        this.cmdtySpecMaxVal = cmdtySpecMaxVal;
    }

    public BigDecimal getCmdtySpecTypicalVal() {
        return cmdtySpecTypicalVal;
    }

    public void setCmdtySpecTypicalVal(BigDecimal cmdtySpecTypicalVal) {
        this.cmdtySpecTypicalVal = cmdtySpecTypicalVal;
    }

    public Character getSpecType() {
        return specType;
    }

    public void setSpecType(Character specType) {
        this.specType = specType;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public String getTypicalStringValue() {
        return typicalStringValue;
    }

    public void setTypicalStringValue(String typicalStringValue) {
        this.typicalStringValue = typicalStringValue;
    }

    public String getDfltSpecTestCode() {
        return dfltSpecTestCode;
    }

    public void setDfltSpecTestCode(String dfltSpecTestCode) {
        this.dfltSpecTestCode = dfltSpecTestCode;
    }

    public Character getStandardInd() {
        return standardInd;
    }

    public void setStandardInd(Character standardInd) {
        this.standardInd = standardInd;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commoditySpecificationPK != null ? commoditySpecificationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommoditySpecification)) {
            return false;
        }
        CommoditySpecification other = (CommoditySpecification) object;
        if ((this.commoditySpecificationPK == null && other.commoditySpecificationPK != null) || (this.commoditySpecificationPK != null && !this.commoditySpecificationPK.equals(other.commoditySpecificationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommoditySpecification[ commoditySpecificationPK=" + commoditySpecificationPK + " ]";
    }
    
}
