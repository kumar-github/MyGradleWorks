/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.entity;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.tc.app.exchangemonitor.entitybase.IExternalTradeStatusEntity;
import com.tc.app.exchangemonitor.model.ExternalTrade;
import com.tc.app.exchangemonitor.model.ExternalTradeStatus;

/**
 *
 * @author Saravana Kumar M
 */
//@Entity
//@Table(name = "external_trade_status", catalog = "QA_30_trade_sep12", schema = "dbo")
@MappedSuperclass
@XmlRootElement

public class ExternalTradeStatusEntity implements IExternalTradeStatusEntity
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "oid")
    private Integer oid;
    @Basic(optional = false)
    @Column(name = "external_trade_status_name")
    private String externalTradeStatusName;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "externalTradeStatusOid")
    private Collection<ExternalTrade> externalTradeCollection;

    public ExternalTradeStatusEntity()
    {
    }

    public ExternalTradeStatusEntity(Integer oid)
    {
        this.oid = oid;
    }

    public ExternalTradeStatusEntity(Integer oid, String externalTradeStatusName, int transId)
    {
        this.oid = oid;
        this.externalTradeStatusName = externalTradeStatusName;
        this.transId = transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeStatusEntity#getOid()
	 */
    @Override
	public Integer getOid() {
        return oid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeStatusEntity#setOid(java.lang.Integer)
	 */
    @Override
	public void setOid(Integer oid) {
        this.oid = oid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeStatusEntity#getExternalTradeStatusName()
	 */
    @Override
	public String getExternalTradeStatusName() {
        return externalTradeStatusName;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeStatusEntity#setExternalTradeStatusName(java.lang.String)
	 */
    @Override
	public void setExternalTradeStatusName(String externalTradeStatusName) {
        this.externalTradeStatusName = externalTradeStatusName;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeStatusEntity#getTransId()
	 */
    @Override
	public int getTransId() {
        return transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeStatusEntity#setTransId(int)
	 */
    @Override
	public void setTransId(int transId) {
        this.transId = transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeStatusEntity#getExternalTradeCollection()
	 */
    @Override
	@XmlTransient
    public Collection<ExternalTrade> getExternalTradeCollection() {
        return externalTradeCollection;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeStatusEntity#setExternalTradeCollection(java.util.Collection)
	 */
    @Override
	public void setExternalTradeCollection(Collection<ExternalTrade> externalTradeCollection) {
        this.externalTradeCollection = externalTradeCollection;
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
        if (!(object instanceof ExternalTradeStatus))
        {
            return false;
        }
        ExternalTradeStatus other = (ExternalTradeStatus) object;
        if ((this.getOid() == null && other.getOid() != null) || (this.getOid() != null && !this.getOid().equals(other.getOid()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return getExternalTradeStatusName();
    }
}