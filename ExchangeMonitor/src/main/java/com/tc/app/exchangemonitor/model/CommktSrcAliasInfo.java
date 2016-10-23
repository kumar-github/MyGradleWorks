/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "commkt_src_alias_info", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommktSrcAliasInfo.findAll", query = "SELECT c FROM CommktSrcAliasInfo c"),
    @NamedQuery(name = "CommktSrcAliasInfo.findByCommktKey", query = "SELECT c FROM CommktSrcAliasInfo c WHERE c.commktSrcAliasInfoPK.commktKey = :commktKey"),
    @NamedQuery(name = "CommktSrcAliasInfo.findByPriceSourceCode", query = "SELECT c FROM CommktSrcAliasInfo c WHERE c.commktSrcAliasInfoPK.priceSourceCode = :priceSourceCode"),
    @NamedQuery(name = "CommktSrcAliasInfo.findByAliasSourceCode", query = "SELECT c FROM CommktSrcAliasInfo c WHERE c.commktSrcAliasInfoPK.aliasSourceCode = :aliasSourceCode"),
    @NamedQuery(name = "CommktSrcAliasInfo.findByCalcAvgPriceInd", query = "SELECT c FROM CommktSrcAliasInfo c WHERE c.calcAvgPriceInd = :calcAvgPriceInd"),
    @NamedQuery(name = "CommktSrcAliasInfo.findByPriceLoadStart", query = "SELECT c FROM CommktSrcAliasInfo c WHERE c.priceLoadStart = :priceLoadStart"),
    @NamedQuery(name = "CommktSrcAliasInfo.findByPriceLoadFreq", query = "SELECT c FROM CommktSrcAliasInfo c WHERE c.priceLoadFreq = :priceLoadFreq"),
    @NamedQuery(name = "CommktSrcAliasInfo.findByPriceLoadDuration", query = "SELECT c FROM CommktSrcAliasInfo c WHERE c.priceLoadDuration = :priceLoadDuration"),
    @NamedQuery(name = "CommktSrcAliasInfo.findByCommktGenerateSpotInd", query = "SELECT c FROM CommktSrcAliasInfo c WHERE c.commktGenerateSpotInd = :commktGenerateSpotInd"),
    @NamedQuery(name = "CommktSrcAliasInfo.findByCommktCodedAsSpotInd", query = "SELECT c FROM CommktSrcAliasInfo c WHERE c.commktCodedAsSpotInd = :commktCodedAsSpotInd"),
    @NamedQuery(name = "CommktSrcAliasInfo.findByTransId", query = "SELECT c FROM CommktSrcAliasInfo c WHERE c.transId = :transId")})
public class CommktSrcAliasInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommktSrcAliasInfoPK commktSrcAliasInfoPK;
    @Column(name = "calc_avg_price_ind")
    private Character calcAvgPriceInd;
    @Column(name = "price_load_start")
    private Integer priceLoadStart;
    @Column(name = "price_load_freq")
    private Integer priceLoadFreq;
    @Column(name = "price_load_duration")
    private Integer priceLoadDuration;
    @Column(name = "commkt_generate_spot_ind")
    private Character commktGenerateSpotInd;
    @Column(name = "commkt_coded_as_spot_ind")
    private Character commktCodedAsSpotInd;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumns({
        @JoinColumn(name = "commkt_key", referencedColumnName = "commkt_key", insertable = false, updatable = false),
        @JoinColumn(name = "price_source_code", referencedColumnName = "price_source_code", insertable = false, updatable = false),
        @JoinColumn(name = "alias_source_code", referencedColumnName = "alias_source_code", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private CommktSourceAlias commktSourceAlias;
    
    @JoinColumn(name = "price_uom_code", columnDefinition="CHAR", referencedColumnName = "uom_code")
    @ManyToOne
    private Uom priceUomCode;

    public CommktSrcAliasInfo() {
    }

    public CommktSrcAliasInfo(CommktSrcAliasInfoPK commktSrcAliasInfoPK) {
        this.commktSrcAliasInfoPK = commktSrcAliasInfoPK;
    }

    public CommktSrcAliasInfo(CommktSrcAliasInfoPK commktSrcAliasInfoPK, int transId) {
        this.commktSrcAliasInfoPK = commktSrcAliasInfoPK;
        this.transId = transId;
    }

    public CommktSrcAliasInfo(int commktKey, String priceSourceCode, String aliasSourceCode) {
        this.commktSrcAliasInfoPK = new CommktSrcAliasInfoPK(commktKey, priceSourceCode, aliasSourceCode);
    }

    public CommktSrcAliasInfoPK getCommktSrcAliasInfoPK() {
        return commktSrcAliasInfoPK;
    }

    public void setCommktSrcAliasInfoPK(CommktSrcAliasInfoPK commktSrcAliasInfoPK) {
        this.commktSrcAliasInfoPK = commktSrcAliasInfoPK;
    }

    public Character getCalcAvgPriceInd() {
        return calcAvgPriceInd;
    }

    public void setCalcAvgPriceInd(Character calcAvgPriceInd) {
        this.calcAvgPriceInd = calcAvgPriceInd;
    }

    public Integer getPriceLoadStart() {
        return priceLoadStart;
    }

    public void setPriceLoadStart(Integer priceLoadStart) {
        this.priceLoadStart = priceLoadStart;
    }

    public Integer getPriceLoadFreq() {
        return priceLoadFreq;
    }

    public void setPriceLoadFreq(Integer priceLoadFreq) {
        this.priceLoadFreq = priceLoadFreq;
    }

    public Integer getPriceLoadDuration() {
        return priceLoadDuration;
    }

    public void setPriceLoadDuration(Integer priceLoadDuration) {
        this.priceLoadDuration = priceLoadDuration;
    }

    public Character getCommktGenerateSpotInd() {
        return commktGenerateSpotInd;
    }

    public void setCommktGenerateSpotInd(Character commktGenerateSpotInd) {
        this.commktGenerateSpotInd = commktGenerateSpotInd;
    }

    public Character getCommktCodedAsSpotInd() {
        return commktCodedAsSpotInd;
    }

    public void setCommktCodedAsSpotInd(Character commktCodedAsSpotInd) {
        this.commktCodedAsSpotInd = commktCodedAsSpotInd;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public CommktSourceAlias getCommktSourceAlias() {
        return commktSourceAlias;
    }

    public void setCommktSourceAlias(CommktSourceAlias commktSourceAlias) {
        this.commktSourceAlias = commktSourceAlias;
    }

    public Uom getPriceUomCode() {
        return priceUomCode;
    }

    public void setPriceUomCode(Uom priceUomCode) {
        this.priceUomCode = priceUomCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commktSrcAliasInfoPK != null ? commktSrcAliasInfoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommktSrcAliasInfo)) {
            return false;
        }
        CommktSrcAliasInfo other = (CommktSrcAliasInfo) object;
        if ((this.commktSrcAliasInfoPK == null && other.commktSrcAliasInfoPK != null) || (this.commktSrcAliasInfoPK != null && !this.commktSrcAliasInfoPK.equals(other.commktSrcAliasInfoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommktSrcAliasInfo[ commktSrcAliasInfoPK=" + commktSrcAliasInfoPK + " ]";
    }
    
}
