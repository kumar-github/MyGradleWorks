/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.entity;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.tc.app.exchangemonitor.entitybase.IExternalTradeStateEntity;
import com.tc.app.exchangemonitor.model.ExternalTrade;
import com.tc.app.exchangemonitor.model.ExternalTradeState;

/**
 *
 * @author Saravana Kumar M
 */
//@Entity
//@Table(name = "external_trade_state", catalog = "QA_30_trade_sep12", schema = "dbo")
@MappedSuperclass
@XmlRootElement

public class ExternalTradeStateEntity implements IExternalTradeStateEntity
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "oid")
    private Integer oid;
    @Basic(optional = false)
    @Column(name = "external_trade_state_name")
    private String externalTradeStateName;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(mappedBy = "externalTradeStateOid")
    private Collection<ExternalTrade> externalTradeCollection;

    public ExternalTradeStateEntity()
    {
    }

    public ExternalTradeStateEntity(Integer oid)
    {
        this.oid = oid;
    }

    public ExternalTradeStateEntity(Integer oid, String externalTradeStateName, int transId)
    {
        this.oid = oid;
        this.externalTradeStateName = externalTradeStateName;
        this.transId = transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeStateEntity#getOid()
	 */
    @Override
	public Integer getOid() {
        return oid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeStateEntity#setOid(java.lang.Integer)
	 */
    @Override
	public void setOid(Integer oid) {
        this.oid = oid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeStateEntity#getExternalTradeStateName()
	 */
    @Override
	public String getExternalTradeStateName() {
        return externalTradeStateName;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeStateEntity#setExternalTradeStateName(java.lang.String)
	 */
    @Override
	public void setExternalTradeStateName(String externalTradeStateName) {
        this.externalTradeStateName = externalTradeStateName;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeStateEntity#getTransId()
	 */
    @Override
	public int getTransId() {
        return transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeStateEntity#setTransId(int)
	 */
    @Override
	public void setTransId(int transId) {
        this.transId = transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeStateEntity#getExternalTradeCollection()
	 */
    @Override
	@XmlTransient
    public Collection<ExternalTrade> getExternalTradeCollection() {
        return externalTradeCollection;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeStateEntity#setExternalTradeCollection(java.util.Collection)
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
        if (!(object instanceof ExternalTradeState))
        {
            return false;
        }
        ExternalTradeState other = (ExternalTradeState) object;
        if ((this.getOid() == null && other.getOid() != null) || (this.getOid() != null && !this.getOid().equals(other.getOid()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return getExternalTradeStateName();
    }
}