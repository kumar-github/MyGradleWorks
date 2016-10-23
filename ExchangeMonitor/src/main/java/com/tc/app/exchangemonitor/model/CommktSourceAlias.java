/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "commkt_source_alias", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommktSourceAlias.findAll", query = "SELECT c FROM CommktSourceAlias c"),
    @NamedQuery(name = "CommktSourceAlias.findByCommktKey", query = "SELECT c FROM CommktSourceAlias c WHERE c.commktSourceAliasPK.commktKey = :commktKey"),
    @NamedQuery(name = "CommktSourceAlias.findByPriceSourceCode", query = "SELECT c FROM CommktSourceAlias c WHERE c.commktSourceAliasPK.priceSourceCode = :priceSourceCode"),
    @NamedQuery(name = "CommktSourceAlias.findByAliasSourceCode", query = "SELECT c FROM CommktSourceAlias c WHERE c.commktSourceAliasPK.aliasSourceCode = :aliasSourceCode"),
    @NamedQuery(name = "CommktSourceAlias.findByCommktSourceAliasName", query = "SELECT c FROM CommktSourceAlias c WHERE c.commktSourceAliasName = :commktSourceAliasName"),
    @NamedQuery(name = "CommktSourceAlias.findByAliasFormatCode", query = "SELECT c FROM CommktSourceAlias c WHERE c.aliasFormatCode = :aliasFormatCode"),
    @NamedQuery(name = "CommktSourceAlias.findByTransId", query = "SELECT c FROM CommktSourceAlias c WHERE c.transId = :transId")})
public class CommktSourceAlias implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommktSourceAliasPK commktSourceAliasPK;
    @Basic(optional = false)
    @Column(name = "commkt_source_alias_name")
    private String commktSourceAliasName;
    
    @Column(name = "alias_format_code", columnDefinition="CHAR")
    private String aliasFormatCode;
    
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "commktSourceAlias")
    private CommktSrcAliasInfo commktSrcAliasInfo;
    
    @JoinColumn(name = "alias_source_code", referencedColumnName = "alias_source_code",  insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AliasSource aliasSource;
    
    @JoinColumns({
        @JoinColumn(name = "commkt_key", referencedColumnName = "commkt_key", insertable = false, updatable = false),
        @JoinColumn(name = "price_source_code", referencedColumnName = "price_source_code", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CommodityMarketSource commodityMarketSource;

    public CommktSourceAlias() {
    }

    public CommktSourceAlias(CommktSourceAliasPK commktSourceAliasPK) {
        this.commktSourceAliasPK = commktSourceAliasPK;
    }

    public CommktSourceAlias(CommktSourceAliasPK commktSourceAliasPK, String commktSourceAliasName, int transId) {
        this.commktSourceAliasPK = commktSourceAliasPK;
        this.commktSourceAliasName = commktSourceAliasName;
        this.transId = transId;
    }

    public CommktSourceAlias(int commktKey, String priceSourceCode, String aliasSourceCode) {
        this.commktSourceAliasPK = new CommktSourceAliasPK(commktKey, priceSourceCode, aliasSourceCode);
    }

    public CommktSourceAliasPK getCommktSourceAliasPK() {
        return commktSourceAliasPK;
    }

    public void setCommktSourceAliasPK(CommktSourceAliasPK commktSourceAliasPK) {
        this.commktSourceAliasPK = commktSourceAliasPK;
    }

    public String getCommktSourceAliasName() {
        return commktSourceAliasName;
    }

    public void setCommktSourceAliasName(String commktSourceAliasName) {
        this.commktSourceAliasName = commktSourceAliasName;
    }

    public String getAliasFormatCode() {
        return aliasFormatCode;
    }

    public void setAliasFormatCode(String aliasFormatCode) {
        this.aliasFormatCode = aliasFormatCode;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public CommktSrcAliasInfo getCommktSrcAliasInfo() {
        return commktSrcAliasInfo;
    }

    public void setCommktSrcAliasInfo(CommktSrcAliasInfo commktSrcAliasInfo) {
        this.commktSrcAliasInfo = commktSrcAliasInfo;
    }

    public AliasSource getAliasSource() {
        return aliasSource;
    }

    public void setAliasSource(AliasSource aliasSource) {
        this.aliasSource = aliasSource;
    }

    public CommodityMarketSource getCommodityMarketSource() {
        return commodityMarketSource;
    }

    public void setCommodityMarketSource(CommodityMarketSource commodityMarketSource) {
        this.commodityMarketSource = commodityMarketSource;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commktSourceAliasPK != null ? commktSourceAliasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommktSourceAlias)) {
            return false;
        }
        CommktSourceAlias other = (CommktSourceAlias) object;
        if ((this.commktSourceAliasPK == null && other.commktSourceAliasPK != null) || (this.commktSourceAliasPK != null && !this.commktSourceAliasPK.equals(other.commktSourceAliasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommktSourceAlias[ commktSourceAliasPK=" + commktSourceAliasPK + " ]";
    }
    
}
