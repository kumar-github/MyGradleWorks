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

import com.tc.app.exchangemonitor.entitybase.IExternalTradeSystemEntity;
import com.tc.app.exchangemonitor.model.ExternalTrade;
import com.tc.app.exchangemonitor.model.ExternalTradeSource;
import com.tc.app.exchangemonitor.model.ExternalTradeSystem;

/**
 *
 * @author smurugabushanam
 */
//@Entity
//@Table(name = "external_trade_system", catalog = "QA_30_trade_sep12", schema = "dbo")
@MappedSuperclass
@XmlRootElement

public class ExternalTradeSystemEntity implements IExternalTradeSystemEntity
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "oid")
    private Integer oid;
    @Basic(optional = false)
    @Column(name = "external_trade_system_name")
    private String externalTradeSystemName;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "externalTradeSystemOid")
    private Collection<ExternalTradeSource> externalTradeSourceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "externalTradeSystemOid")
    private Collection<ExternalTrade> externalTradeCollection;

    public ExternalTradeSystemEntity()
    {
    }

    public ExternalTradeSystemEntity(Integer oid)
    {
        this.oid = oid;
    }

    public ExternalTradeSystemEntity(Integer oid, String externalTradeSystemName, int transId)
    {
        this.oid = oid;
        this.externalTradeSystemName = externalTradeSystemName;
        this.transId = transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSystemEntity#getOid()
	 */
    @Override
	public Integer getOid() {
        return oid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSystemEntity#setOid(java.lang.Integer)
	 */
    @Override
	public void setOid(Integer oid) {
        this.oid = oid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSystemEntity#getExternalTradeSystemName()
	 */
    @Override
	public String getExternalTradeSystemName() {
        return externalTradeSystemName;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSystemEntity#setExternalTradeSystemName(java.lang.String)
	 */
    @Override
	public void setExternalTradeSystemName(String externalTradeSystemName) {
        this.externalTradeSystemName = externalTradeSystemName;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSystemEntity#getTransId()
	 */
    @Override
	public int getTransId() {
        return transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSystemEntity#setTransId(int)
	 */
    @Override
	public void setTransId(int transId) {
        this.transId = transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSystemEntity#getExternalTradeSourceCollection()
	 */
    @Override
	@XmlTransient
    public Collection<ExternalTradeSource> getExternalTradeSourceCollection() {
        return externalTradeSourceCollection;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSystemEntity#setExternalTradeSourceCollection(java.util.Collection)
	 */
    @Override
	public void setExternalTradeSourceCollection(Collection<ExternalTradeSource> externalTradeSourceCollection) {
        this.externalTradeSourceCollection = externalTradeSourceCollection;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSystemEntity#getExternalTradeCollection()
	 */
    @Override
	@XmlTransient
    public Collection<ExternalTrade> getExternalTradeCollection() {
        return externalTradeCollection;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSystemEntity#setExternalTradeCollection(java.util.Collection)
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
        if (!(object instanceof ExternalTradeSystem)) {
            return false;
        }
        ExternalTradeSystem other = (ExternalTradeSystem) object;
        if ((this.getOid() == null && other.getOid() != null) || (this.getOid() != null && !this.getOid().equals(other.getOid()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generated3.ExternalTradeSystem[ oid=" + oid + " ]";
    }
    
}