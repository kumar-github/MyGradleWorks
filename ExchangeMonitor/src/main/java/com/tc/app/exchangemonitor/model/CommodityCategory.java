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
@Table(name = "commodity_category", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommodityCategory.findAll", query = "SELECT c FROM CommodityCategory c"),
    @NamedQuery(name = "CommodityCategory.findByCmdtyCategoryCode", query = "SELECT c FROM CommodityCategory c WHERE c.cmdtyCategoryCode = :cmdtyCategoryCode"),
    @NamedQuery(name = "CommodityCategory.findByCmdtyCategoryDesc", query = "SELECT c FROM CommodityCategory c WHERE c.cmdtyCategoryDesc = :cmdtyCategoryDesc"),
    @NamedQuery(name = "CommodityCategory.findByTransId", query = "SELECT c FROM CommodityCategory c WHERE c.transId = :transId")})
public class CommodityCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cmdty_category_code", columnDefinition="CHAR")
    private String cmdtyCategoryCode;
    
    @Column(name = "cmdty_category_desc")
    private String cmdtyCategoryDesc;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(mappedBy = "cmdtyCategoryCode")
    private Collection<Commodity> commodityCollection;

    public CommodityCategory() {
    }

    public CommodityCategory(String cmdtyCategoryCode) {
        this.cmdtyCategoryCode = cmdtyCategoryCode;
    }

    public CommodityCategory(String cmdtyCategoryCode, int transId) {
        this.cmdtyCategoryCode = cmdtyCategoryCode;
        this.transId = transId;
    }

    public String getCmdtyCategoryCode() {
        return cmdtyCategoryCode;
    }

    public void setCmdtyCategoryCode(String cmdtyCategoryCode) {
        this.cmdtyCategoryCode = cmdtyCategoryCode;
    }

    public String getCmdtyCategoryDesc() {
        return cmdtyCategoryDesc;
    }

    public void setCmdtyCategoryDesc(String cmdtyCategoryDesc) {
        this.cmdtyCategoryDesc = cmdtyCategoryDesc;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<Commodity> getCommodityCollection() {
        return commodityCollection;
    }

    public void setCommodityCollection(Collection<Commodity> commodityCollection) {
        this.commodityCollection = commodityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cmdtyCategoryCode != null ? cmdtyCategoryCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityCategory)) {
            return false;
        }
        CommodityCategory other = (CommodityCategory) object;
        if ((this.cmdtyCategoryCode == null && other.cmdtyCategoryCode != null) || (this.cmdtyCategoryCode != null && !this.cmdtyCategoryCode.equals(other.cmdtyCategoryCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityCategory[ cmdtyCategoryCode=" + cmdtyCategoryCode + " ]";
    }
    
}
