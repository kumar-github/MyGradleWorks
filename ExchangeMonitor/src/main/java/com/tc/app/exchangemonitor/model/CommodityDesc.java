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
@Table(name = "commodity_desc", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommodityDesc.findAll", query = "SELECT c FROM CommodityDesc c"),
    @NamedQuery(name = "CommodityDesc.findByCmdtyCode", query = "SELECT c FROM CommodityDesc c WHERE c.commodityDescPK.cmdtyCode = :cmdtyCode"),
    @NamedQuery(name = "CommodityDesc.findByCmdtyDescLang", query = "SELECT c FROM CommodityDesc c WHERE c.commodityDescPK.cmdtyDescLang = :cmdtyDescLang"),
    @NamedQuery(name = "CommodityDesc.findByCmdtyDescFor", query = "SELECT c FROM CommodityDesc c WHERE c.commodityDescPK.cmdtyDescFor = :cmdtyDescFor"),
    @NamedQuery(name = "CommodityDesc.findByCmdtyDesc", query = "SELECT c FROM CommodityDesc c WHERE c.cmdtyDesc = :cmdtyDesc"),
    @NamedQuery(name = "CommodityDesc.findByTransId", query = "SELECT c FROM CommodityDesc c WHERE c.transId = :transId")})
public class CommodityDesc implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CommodityDescPK commodityDescPK;
    @Basic(optional = false)
    @Column(name = "cmdty_desc")
    private String cmdtyDesc;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumn(name = "cmdty_code", referencedColumnName = "cmdty_code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Commodity commodity;

    public CommodityDesc() {
    }

    public CommodityDesc(CommodityDescPK commodityDescPK) {
        this.commodityDescPK = commodityDescPK;
    }

    public CommodityDesc(CommodityDescPK commodityDescPK, String cmdtyDesc, int transId) {
        this.commodityDescPK = commodityDescPK;
        this.cmdtyDesc = cmdtyDesc;
        this.transId = transId;
    }

    public CommodityDesc(String cmdtyCode, String cmdtyDescLang, String cmdtyDescFor) {
        this.commodityDescPK = new CommodityDescPK(cmdtyCode, cmdtyDescLang, cmdtyDescFor);
    }

    public CommodityDescPK getCommodityDescPK() {
        return commodityDescPK;
    }

    public void setCommodityDescPK(CommodityDescPK commodityDescPK) {
        this.commodityDescPK = commodityDescPK;
    }

    public String getCmdtyDesc() {
        return cmdtyDesc;
    }

    public void setCmdtyDesc(String cmdtyDesc) {
        this.cmdtyDesc = cmdtyDesc;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
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
        hash += (commodityDescPK != null ? commodityDescPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommodityDesc)) {
            return false;
        }
        CommodityDesc other = (CommodityDesc) object;
        if ((this.commodityDescPK == null && other.commodityDescPK != null) || (this.commodityDescPK != null && !this.commodityDescPK.equals(other.commodityDescPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CommodityDesc[ commodityDescPK=" + commodityDescPK + " ]";
    }
    
}
