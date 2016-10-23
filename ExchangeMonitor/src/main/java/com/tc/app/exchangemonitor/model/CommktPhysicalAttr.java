/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "commkt_physical_attr", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommktPhysicalAttr.findAll", query = "SELECT c FROM CommktPhysicalAttr c"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktKey", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktKey = :commktKey"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktPhyAttrStatus", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktPhyAttrStatus = :commktPhyAttrStatus"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktDfltQty", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktDfltQty = :commktDfltQty"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktQtyUomCode", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktQtyUomCode = :commktQtyUomCode"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktPriceFmt", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktPriceFmt = :commktPriceFmt"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktMinPriceVar", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktMinPriceVar = :commktMinPriceVar"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktMaxPriceVar", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktMaxPriceVar = :commktMaxPriceVar"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktSpotPrd", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktSpotPrd = :commktSpotPrd"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktPriceFreq", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktPriceFreq = :commktPriceFreq"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktPriceFreqAsOf", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktPriceFreqAsOf = :commktPriceFreqAsOf"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktPriceSeries", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktPriceSeries = :commktPriceSeries"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktFormulaType", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktFormulaType = :commktFormulaType"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktInterpolType", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktInterpolType = :commktInterpolType"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktNumMthOut", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktNumMthOut = :commktNumMthOut"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktSupportPriceType", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktSupportPriceType = :commktSupportPriceType"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktSameAsMktCode", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktSameAsMktCode = :commktSameAsMktCode"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktSameAsCmdtyCode", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktSameAsCmdtyCode = :commktSameAsCmdtyCode"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktForexMktCode", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktForexMktCode = :commktForexMktCode"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktForexCmdtyCode", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktForexCmdtyCode = :commktForexCmdtyCode"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktPriceDivMulInd", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktPriceDivMulInd = :commktPriceDivMulInd"),
    @NamedQuery(name = "CommktPhysicalAttr.findByCommktPointConvNum", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.commktPointConvNum = :commktPointConvNum"),
    @NamedQuery(name = "CommktPhysicalAttr.findByTransId", query = "SELECT c FROM CommktPhysicalAttr c WHERE c.transId = :transId")})
public class CommktPhysicalAttr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "commkt_key")
    private Integer commktKey;
    @Basic(optional = false)
    @Column(name = "commkt_phy_attr_status")
    private Character commktPhyAttrStatus;
    @Basic(optional = false)
    @Column(name = "commkt_dflt_qty")
    private double commktDfltQty;
    
    @Basic(optional = false)
    @Column(name = "commkt_qty_uom_code", columnDefinition="CHAR")
    private String commktQtyUomCode;
    
    @Basic(optional = false)
    @Column(name = "commkt_price_fmt", columnDefinition="CHAR")
    private String commktPriceFmt;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "commkt_min_price_var")
    private Double commktMinPriceVar;
    @Column(name = "commkt_max_price_var")
    private Double commktMaxPriceVar;
    
    @Basic(optional = false)
    @Column(name = "commkt_spot_prd", columnDefinition="CHAR")
    private String commktSpotPrd;
    
    @Column(name = "commkt_price_freq", columnDefinition="CHAR")
    private String commktPriceFreq;
    
    @Column(name = "commkt_price_freq_as_of", columnDefinition="CHAR")
    private String commktPriceFreqAsOf;
    
    @Basic(optional = false)
    @Column(name = "commkt_price_series")
    private Character commktPriceSeries;
    @Column(name = "commkt_formula_type")
    private Character commktFormulaType;
    @Column(name = "commkt_interpol_type")
    private Character commktInterpolType;
    @Column(name = "commkt_num_mth_out")
    private Short commktNumMthOut;
    @Column(name = "commkt_support_price_type")
    private String commktSupportPriceType;
    @Column(name = "commkt_same_as_mkt_code")
    private String commktSameAsMktCode;
    @Column(name = "commkt_same_as_cmdty_code")
    private String commktSameAsCmdtyCode;
    @Column(name = "commkt_forex_mkt_code")
    private String commktForexMktCode;
    @Column(name = "commkt_forex_cmdty_code")
    private String commktForexCmdtyCode;
    @Column(name = "commkt_price_div_mul_ind")
    private Character commktPriceDivMulInd;
    @Column(name = "commkt_point_conv_num")
    private Double commktPointConvNum;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumn(name = "sec_alias_source_code", referencedColumnName = "alias_source_code")
    @ManyToOne
    private AliasSource secAliasSourceCode;
    
    @JoinColumn(name = "commkt_curr_code", referencedColumnName = "cmdty_code", columnDefinition="CHAR")
    @ManyToOne(optional = false)
    private Commodity commktCurrCode;
    
    @JoinColumn(name = "commkt_key", referencedColumnName = "commkt_key", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private CommodityMarket commodityMarket;
    @JoinColumn(name = "user_init", referencedColumnName = "user_init")
    @ManyToOne
    private IctsUser userInit;
    @JoinColumn(name = "sec_price_source_code", referencedColumnName = "price_source_code", columnDefinition="CHAR")
    @ManyToOne
    private PriceSource secPriceSourceCode;
    
    @JoinColumn(name = "commkt_price_uom_code", referencedColumnName = "uom_code", columnDefinition="CHAR")
    @ManyToOne(optional = false)
    private Uom commktPriceUomCode;

    public CommktPhysicalAttr() {
    }

    public CommktPhysicalAttr(Integer commktKey) {
        this.commktKey = commktKey;
    }

    public CommktPhysicalAttr(Integer commktKey, Character commktPhyAttrStatus, double commktDfltQty, String commktQtyUomCode, String commktPriceFmt, String commktSpotPrd, Character commktPriceSeries, int transId) {
        this.commktKey = commktKey;
        this.commktPhyAttrStatus = commktPhyAttrStatus;
        this.commktDfltQty = commktDfltQty;
        this.commktQtyUomCode = commktQtyUomCode;
        this.commktPriceFmt = commktPriceFmt;
        this.commktSpotPrd = commktSpotPrd;
        this.commktPriceSeries = commktPriceSeries;
        this.transId = transId;
    }

    public Integer getCommktKey() {
        return commktKey;
    }

    public void setCommktKey(Integer commktKey) {
        this.commktKey = commktKey;
    }

    public Character getCommktPhyAttrStatus() {
        return commktPhyAttrStatus;
    }

    public void setCommktPhyAttrStatus(Character commktPhyAttrStatus) {
        this.commktPhyAttrStatus = commktPhyAttrStatus;
    }

    public double getCommktDfltQty() {
        return commktDfltQty;
    }

    public void setCommktDfltQty(double commktDfltQty) {
        this.commktDfltQty = commktDfltQty;
    }

    public String getCommktQtyUomCode() {
        return commktQtyUomCode;
    }

    public void setCommktQtyUomCode(String commktQtyUomCode) {
        this.commktQtyUomCode = commktQtyUomCode;
    }

    public String getCommktPriceFmt() {
        return commktPriceFmt;
    }

    public void setCommktPriceFmt(String commktPriceFmt) {
        this.commktPriceFmt = commktPriceFmt;
    }

    public Double getCommktMinPriceVar() {
        return commktMinPriceVar;
    }

    public void setCommktMinPriceVar(Double commktMinPriceVar) {
        this.commktMinPriceVar = commktMinPriceVar;
    }

    public Double getCommktMaxPriceVar() {
        return commktMaxPriceVar;
    }

    public void setCommktMaxPriceVar(Double commktMaxPriceVar) {
        this.commktMaxPriceVar = commktMaxPriceVar;
    }

    public String getCommktSpotPrd() {
        return commktSpotPrd;
    }

    public void setCommktSpotPrd(String commktSpotPrd) {
        this.commktSpotPrd = commktSpotPrd;
    }

    public String getCommktPriceFreq() {
        return commktPriceFreq;
    }

    public void setCommktPriceFreq(String commktPriceFreq) {
        this.commktPriceFreq = commktPriceFreq;
    }

    public String getCommktPriceFreqAsOf() {
        return commktPriceFreqAsOf;
    }

    public void setCommktPriceFreqAsOf(String commktPriceFreqAsOf) {
        this.commktPriceFreqAsOf = commktPriceFreqAsOf;
    }

    public Character getCommktPriceSeries() {
        return commktPriceSeries;
    }

    public void setCommktPriceSeries(Character commktPriceSeries) {
        this.commktPriceSeries = commktPriceSeries;
    }

    public Character getCommktFormulaType() {
        return commktFormulaType;
    }

    public void setCommktFormulaType(Character commktFormulaType) {
        this.commktFormulaType = commktFormulaType;
    }

    public Character getCommktInterpolType() {
        return commktInterpolType;
    }

    public void setCommktInterpolType(Character commktInterpolType) {
        this.commktInterpolType = commktInterpolType;
    }

    public Short getCommktNumMthOut() {
        return commktNumMthOut;
    }

    public void setCommktNumMthOut(Short commktNumMthOut) {
        this.commktNumMthOut = commktNumMthOut;
    }

    public String getCommktSupportPriceType() {
        return commktSupportPriceType;
    }

    public void setCommktSupportPriceType(String commktSupportPriceType) {
        this.commktSupportPriceType = commktSupportPriceType;
    }

    public String getCommktSameAsMktCode() {
        return commktSameAsMktCode;
    }

    public void setCommktSameAsMktCode(String commktSameAsMktCode) {
        this.commktSameAsMktCode = commktSameAsMktCode;
    }

    public String getCommktSameAsCmdtyCode() {
        return commktSameAsCmdtyCode;
    }

    public void setCommktSameAsCmdtyCode(String commktSameAsCmdtyCode) {
        this.commktSameAsCmdtyCode = commktSameAsCmdtyCode;
    }

    public String getCommktForexMktCode() {
        return commktForexMktCode;
    }

    public void setCommktForexMktCode(String commktForexMktCode) {
        this.commktForexMktCode = commktForexMktCode;
    }

    public String getCommktForexCmdtyCode() {
        return commktForexCmdtyCode;
    }

    public void setCommktForexCmdtyCode(String commktForexCmdtyCode) {
        this.commktForexCmdtyCode = commktForexCmdtyCode;
    }

    public Character getCommktPriceDivMulInd() {
        return commktPriceDivMulInd;
    }

    public void setCommktPriceDivMulInd(Character commktPriceDivMulInd) {
        this.commktPriceDivMulInd = commktPriceDivMulInd;
    }

    public Double getCommktPointConvNum() {
        return commktPointConvNum;
    }

    public void setCommktPointConvNum(Double commktPointConvNum) {
        this.commktPointConvNum = commktPointConvNum;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public AliasSource getSecAliasSourceCode() {
        return secAliasSourceCode;
    }

    public void setSecAliasSourceCode(AliasSource secAliasSourceCode) {
        this.secAliasSourceCode = secAliasSourceCode;
    }

    public Commodity getCommktCurrCode() {
        return commktCurrCode;
    }

    public void setCommktCurrCode(Commodity commktCurrCode) {
        this.commktCurrCode = commktCurrCode;
    }

    public CommodityMarket getCommodityMarket() {
        return commodityMarket;
    }

    public void setCommodityMarket(CommodityMarket commodityMarket) {
        this.commodityMarket = commodityMarket;
    }

    public IctsUser getUserInit() {
        return userInit;
    }

    public void setUserInit(IctsUser userInit) {
        this.userInit = userInit;
    }

    public PriceSource getSecPriceSourceCode() {
        return secPriceSourceCode;
    }

    public void setSecPriceSourceCode(PriceSource secPriceSourceCode) {
        this.secPriceSourceCode = secPriceSourceCode;
    }

    public Uom getCommktPriceUomCode() {
        return commktPriceUomCode;
    }

    public void setCommktPriceUomCode(Uom commktPriceUomCode) {
        this.commktPriceUomCode = commktPriceUomCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commktKey != null ? commktKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommktPhysicalAttr)) {
            return false;
        }
        CommktPhysicalAttr other = (CommktPhysicalAttr) object;
        if ((this.commktKey == null && other.commktKey != null) || (this.commktKey != null && !this.commktKey.equals(other.commktKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommktPhysicalAttr[ commktKey=" + commktKey + " ]";
    }
    
}
