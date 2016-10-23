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
@Table(name = "commodity_alias", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommodityAlias.findAll", query = "SELECT c FROM CommodityAlias c"),
    @NamedQuery(name = "CommodityAlias.findByCmdtyCode", query = "SELECT c FROM CommodityAlias c WHERE c.commodityAliasPK.cmdtyCode = :cmdtyCode"),
    @NamedQuery(name = "CommodityAlias.findByAliasSourceCode", query = "SELECT c FROM CommodityAlias c WHERE c.commodityAliasPK.aliasSourceCode = :aliasSourceCode"),
    @NamedQuery(name = "CommodityAlias.findByCmdtyAliasName", query = "SELECT c FROM CommodityAlias c WHERE c.cmdtyAliasName = :cmdtyAliasName"),
    @NamedQuery(name = "CommodityAlias.findByTransId", query = "SELECT c FROM CommodityAlias c WHERE c.transId = :transId")})
public class CommodityAlias implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommodityAliasPK commodityAliasPK;
    
    @Basic(optional = false)
    @Column(name = "cmdty_alias_name", columnDefinition="NVARCHAR")
    private String cmdtyAliasName;
    
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    
    @JoinColumn(name = "alias_source_code", referencedColumnName = "alias_source_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AliasSource aliasSource;
    
    @JoinColumn(name = "cmdty_code", referencedColumnName = "cmdty_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Commodity commodity;

    public CommodityAlias() {
    }

    public CommodityAlias(CommodityAliasPK commodityAliasPK) {
        this.commodityAliasPK = commodityAliasPK;
    }

    public CommodityAlias(CommodityAliasPK commodityAliasPK, String cmdtyAliasName, int transId) {
        this.commodityAliasPK = commodityAliasPK;
        this.cmdtyAliasName = cmdtyAliasName;
        this.transId = transId;
    }

    public CommodityAlias(String cmdtyCode, String aliasSourceCode) {
        this.commodityAliasPK = new CommodityAliasPK(cmdtyCode, aliasSourceCode);
    }

    public CommodityAliasPK getCommodityAliasPK() {
        return commodityAliasPK;
    }

    public void setCommodityAliasPK(CommodityAliasPK commodityAliasPK) {
        this.commodityAliasPK = commodityAliasPK;
    }

    public String getCmdtyAliasName() {
        return cmdtyAliasName;
    }

    public void setCmdtyAliasName(String cmdtyAliasName) {
        this.cmdtyAliasName = cmdtyAliasName;
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

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commodityAliasPK != null ? commodityAliasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityAlias)) {
            return false;
        }
        CommodityAlias other = (CommodityAlias) object;
        if ((this.commodityAliasPK == null && other.commodityAliasPK != null) || (this.commodityAliasPK != null && !this.commodityAliasPK.equals(other.commodityAliasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityAlias[ commodityAliasPK=" + commodityAliasPK + " ]";
    }
    
}
