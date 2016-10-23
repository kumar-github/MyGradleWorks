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
@Table(name = "commodity_market_alias", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommodityMarketAlias.findAll", query = "SELECT c FROM CommodityMarketAlias c"),
    @NamedQuery(name = "CommodityMarketAlias.findByCommktKey", query = "SELECT c FROM CommodityMarketAlias c WHERE c.commodityMarketAliasPK.commktKey = :commktKey"),
    @NamedQuery(name = "CommodityMarketAlias.findByAliasSourceCode", query = "SELECT c FROM CommodityMarketAlias c WHERE c.commodityMarketAliasPK.aliasSourceCode = :aliasSourceCode"),
    @NamedQuery(name = "CommodityMarketAlias.findByType", query = "SELECT c FROM CommodityMarketAlias c WHERE c.commodityMarketAliasPK.type = :type"),
    @NamedQuery(name = "CommodityMarketAlias.findByCommktAliasName", query = "SELECT c FROM CommodityMarketAlias c WHERE c.commktAliasName = :commktAliasName"),
    @NamedQuery(name = "CommodityMarketAlias.findByAliasFormatCode", query = "SELECT c FROM CommodityMarketAlias c WHERE c.aliasFormatCode = :aliasFormatCode"),
    @NamedQuery(name = "CommodityMarketAlias.findByTransId", query = "SELECT c FROM CommodityMarketAlias c WHERE c.transId = :transId")})
public class CommodityMarketAlias implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommodityMarketAliasPK commodityMarketAliasPK;
    @Basic(optional = false)
    @Column(name = "commkt_alias_name")
    private String commktAliasName;
    
    @Column(name = "alias_format_code", columnDefinition="CHAR")
    private String aliasFormatCode;
    
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumn(name = "alias_source_code", referencedColumnName = "alias_source_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AliasSource aliasSource;
    @JoinColumn(name = "commkt_key", referencedColumnName = "commkt_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CommodityMarket commodityMarket;

    public CommodityMarketAlias() {
    }

    public CommodityMarketAlias(CommodityMarketAliasPK commodityMarketAliasPK) {
        this.commodityMarketAliasPK = commodityMarketAliasPK;
    }

    public CommodityMarketAlias(CommodityMarketAliasPK commodityMarketAliasPK, String commktAliasName, int transId) {
        this.commodityMarketAliasPK = commodityMarketAliasPK;
        this.commktAliasName = commktAliasName;
        this.transId = transId;
    }

    public CommodityMarketAlias(int commktKey, String aliasSourceCode, Character type) {
        this.commodityMarketAliasPK = new CommodityMarketAliasPK(commktKey, aliasSourceCode, type);
    }

    public CommodityMarketAliasPK getCommodityMarketAliasPK() {
        return commodityMarketAliasPK;
    }

    public void setCommodityMarketAliasPK(CommodityMarketAliasPK commodityMarketAliasPK) {
        this.commodityMarketAliasPK = commodityMarketAliasPK;
    }

    public String getCommktAliasName() {
        return commktAliasName;
    }

    public void setCommktAliasName(String commktAliasName) {
        this.commktAliasName = commktAliasName;
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

    public AliasSource getAliasSource() {
        return aliasSource;
    }

    public void setAliasSource(AliasSource aliasSource) {
        this.aliasSource = aliasSource;
    }

    public CommodityMarket getCommodityMarket() {
        return commodityMarket;
    }

    public void setCommodityMarket(CommodityMarket commodityMarket) {
        this.commodityMarket = commodityMarket;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commodityMarketAliasPK != null ? commodityMarketAliasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityMarketAlias)) {
            return false;
        }
        CommodityMarketAlias other = (CommodityMarketAlias) object;
        if ((this.commodityMarketAliasPK == null && other.commodityMarketAliasPK != null) || (this.commodityMarketAliasPK != null && !this.commodityMarketAliasPK.equals(other.commodityMarketAliasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityMarketAlias[ commodityMarketAliasPK=" + commodityMarketAliasPK + " ]";
    }
    
}
