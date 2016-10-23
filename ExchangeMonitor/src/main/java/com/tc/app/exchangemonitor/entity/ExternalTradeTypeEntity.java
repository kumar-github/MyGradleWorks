/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlRootElement;

import com.tc.app.exchangemonitor.entitybase.IExternalTradeTypeEntity;
import com.tc.app.exchangemonitor.model.ExternalTradeSource;
import com.tc.app.exchangemonitor.model.ExternalTradeType;

/**
 *
 * @author Saravana Kumar M
 */
//@Entity
//@Table(name = "external_trade_type", catalog = "QA_30_trade_sep12", schema = "dbo")
@MappedSuperclass
@XmlRootElement

public class ExternalTradeTypeEntity implements IExternalTradeTypeEntity
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "oid")
    private Integer oid;
    @Basic(optional = false)
    @Column(name = "trade_type_code")
    private String tradeTypeCode;
    @Basic(optional = false)
    @Column(name = "trade_type_name")
    private String tradeTypeName;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumn(name = "external_trade_source_oid", referencedColumnName = "oid")
    @ManyToOne(optional = false)
    private ExternalTradeSource externalTradeSourceOid;

    public ExternalTradeTypeEntity() {
    }

    public ExternalTradeTypeEntity(Integer oid) {
        this.oid = oid;
    }

    public ExternalTradeTypeEntity(Integer oid, String tradeTypeCode, String tradeTypeName, int transId) {
        this.oid = oid;
        this.tradeTypeCode = tradeTypeCode;
        this.tradeTypeName = tradeTypeName;
        this.transId = transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeTypeEntity#getOid()
	 */
    @Override
	public Integer getOid() {
        return oid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeTypeEntity#setOid(java.lang.Integer)
	 */
    @Override
	public void setOid(Integer oid) {
        this.oid = oid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeTypeEntity#getTradeTypeCode()
	 */
    @Override
	public String getTradeTypeCode() {
        return tradeTypeCode;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeTypeEntity#setTradeTypeCode(java.lang.String)
	 */
    @Override
	public void setTradeTypeCode(String tradeTypeCode) {
        this.tradeTypeCode = tradeTypeCode;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeTypeEntity#getTradeTypeName()
	 */
    @Override
	public String getTradeTypeName() {
        return tradeTypeName;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeTypeEntity#setTradeTypeName(java.lang.String)
	 */
    @Override
	public void setTradeTypeName(String tradeTypeName) {
        this.tradeTypeName = tradeTypeName;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeTypeEntity#getTransId()
	 */
    @Override
	public int getTransId() {
        return transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeTypeEntity#setTransId(int)
	 */
    @Override
	public void setTransId(int transId) {
        this.transId = transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeTypeEntity#getExternalTradeSourceOid()
	 */
    @Override
	public ExternalTradeSource getExternalTradeSourceOid() {
        return externalTradeSourceOid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeTypeEntity#setExternalTradeSourceOid(com.tc.app.exchangemonitor.entitybase.IExternalTradeSourceEntity)
	 */
    @Override
	public void setExternalTradeSourceOid(ExternalTradeSource externalTradeSourceOid) {
        this.externalTradeSourceOid = externalTradeSourceOid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oid != null ? oid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExternalTradeType)) {
            return false;
        }
        ExternalTradeType other = (ExternalTradeType) object;
        if ((this.getOid() == null && other.getOid() != null) || (this.getOid() != null && !this.getOid().equals(other.getOid()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ExternalTradeType[ oid=" + oid + " ]";
    }
}