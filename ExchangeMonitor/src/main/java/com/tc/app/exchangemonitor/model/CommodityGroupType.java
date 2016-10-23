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
@Table(name = "commodity_group_type", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommodityGroupType.findAll", query = "SELECT c FROM CommodityGroupType c"),
    @NamedQuery(name = "CommodityGroupType.findByCmdtyGroupTypeCode", query = "SELECT c FROM CommodityGroupType c WHERE c.cmdtyGroupTypeCode = :cmdtyGroupTypeCode"),
    @NamedQuery(name = "CommodityGroupType.findByCmdtyGroupTypeDesc", query = "SELECT c FROM CommodityGroupType c WHERE c.cmdtyGroupTypeDesc = :cmdtyGroupTypeDesc"),
    @NamedQuery(name = "CommodityGroupType.findByTransId", query = "SELECT c FROM CommodityGroupType c WHERE c.transId = :transId")})
public class CommodityGroupType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cmdty_group_type_code", columnDefinition="CHAR")
    private String cmdtyGroupTypeCode;
    
    @Basic(optional = false)
    @Column(name = "cmdty_group_type_desc")
    private String cmdtyGroupTypeDesc;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commodityGroupType")
    private Collection<CommodityGroup> commodityGroupCollection;

    public CommodityGroupType() {
    }

    public CommodityGroupType(String cmdtyGroupTypeCode) {
        this.cmdtyGroupTypeCode = cmdtyGroupTypeCode;
    }

    public CommodityGroupType(String cmdtyGroupTypeCode, String cmdtyGroupTypeDesc, int transId) {
        this.cmdtyGroupTypeCode = cmdtyGroupTypeCode;
        this.cmdtyGroupTypeDesc = cmdtyGroupTypeDesc;
        this.transId = transId;
    }

    public String getCmdtyGroupTypeCode() {
        return cmdtyGroupTypeCode;
    }

    public void setCmdtyGroupTypeCode(String cmdtyGroupTypeCode) {
        this.cmdtyGroupTypeCode = cmdtyGroupTypeCode;
    }

    public String getCmdtyGroupTypeDesc() {
        return cmdtyGroupTypeDesc;
    }

    public void setCmdtyGroupTypeDesc(String cmdtyGroupTypeDesc) {
        this.cmdtyGroupTypeDesc = cmdtyGroupTypeDesc;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<CommodityGroup> getCommodityGroupCollection() {
        return commodityGroupCollection;
    }

    public void setCommodityGroupCollection(Collection<CommodityGroup> commodityGroupCollection) {
        this.commodityGroupCollection = commodityGroupCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cmdtyGroupTypeCode != null ? cmdtyGroupTypeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityGroupType)) {
            return false;
        }
        CommodityGroupType other = (CommodityGroupType) object;
        if ((this.cmdtyGroupTypeCode == null && other.cmdtyGroupTypeCode != null) || (this.cmdtyGroupTypeCode != null && !this.cmdtyGroupTypeCode.equals(other.cmdtyGroupTypeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityGroupType[ cmdtyGroupTypeCode=" + cmdtyGroupTypeCode + " ]";
    }
    
}
