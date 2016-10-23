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

import com.tc.app.exchangemonitor.entitybase.IExternalMappingEntity;
import com.tc.app.exchangemonitor.model.ExternalTradeSource;

/**
 *
 * @author Saravana Kumar M
 */
//@Entity
//@Table(name = "external_mapping", catalog = "QA_30_trade_sep12", schema = "dbo")
@MappedSuperclass

public class ExternalMappingEntity implements IExternalMappingEntity
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "oid")
    private Integer oid;
    @Basic(optional = false)
    @Column(name = "mapping_type")
    private Character mappingType;
    
    @Column(name = "external_value1")
	//private String externalValue1;
    protected String externalValue1;
    
    @Column(name = "external_value2")
    private String externalValue2;
    @Column(name = "external_value3")
    private String externalValue3;
    @Column(name = "external_value4")
    private String externalValue4;
    @Basic(optional = false)
    @Column(name = "alias_value")
    private String aliasValue;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumn(name = "external_trade_source_oid", referencedColumnName = "oid")
    @ManyToOne(optional = false)
    private ExternalTradeSource externalTradeSourceOid;

    public ExternalMappingEntity()
    {
    }
    
    public ExternalMappingEntity(Integer oid)
    {
        this.oid = oid;
    }

    public ExternalMappingEntity(Integer oid, Character mappingType, String aliasValue, int transId)
    {
        this.oid = oid;
        this.mappingType = mappingType;
        this.aliasValue = aliasValue;
        this.transId = transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalMappingEntity#getOid()
	 */
    @Override
	public Integer getOid() {
        return oid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalMappingEntity#setOid(java.lang.Integer)
	 */
    @Override
	public void setOid(Integer oid) {
        this.oid = oid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalMappingEntity#getMappingType()
	 */
    @Override
	public Character getMappingType() {
        return mappingType;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalMappingEntity#setMappingType(java.lang.Character)
	 */
    @Override
	public void setMappingType(Character mappingType) {
        this.mappingType = mappingType;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalMappingEntity#getExternalValue1()
	 */
    @Override
	public String getExternalValue1() {
        return externalValue1;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalMappingEntity#setExternalValue1(java.lang.String)
	 */
    @Override
	public void setExternalValue1(String externalValue1) {
        this.externalValue1 = externalValue1;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalMappingEntity#getExternalValue2()
	 */
    @Override
	public String getExternalValue2() {
        return externalValue2;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalMappingEntity#setExternalValue2(java.lang.String)
	 */
    @Override
	public void setExternalValue2(String externalValue2) {
        this.externalValue2 = externalValue2;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalMappingEntity#getExternalValue3()
	 */
    @Override
	public String getExternalValue3() {
        return externalValue3;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalMappingEntity#setExternalValue3(java.lang.String)
	 */
    @Override
	public void setExternalValue3(String externalValue3) {
        this.externalValue3 = externalValue3;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalMappingEntity#getExternalValue4()
	 */
    @Override
	public String getExternalValue4() {
        return externalValue4;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalMappingEntity#setExternalValue4(java.lang.String)
	 */
    @Override
	public void setExternalValue4(String externalValue4) {
        this.externalValue4 = externalValue4;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalMappingEntity#getAliasValue()
	 */
    @Override
	public String getAliasValue() {
        return aliasValue;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalMappingEntity#setAliasValue(java.lang.String)
	 */
    @Override
	public void setAliasValue(String aliasValue) {
        this.aliasValue = aliasValue;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalMappingEntity#getTransId()
	 */
    @Override
	public int getTransId() {
        return transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalMappingEntity#setTransId(int)
	 */
    @Override
	public void setTransId(int transId) {
        this.transId = transId;
    }

    @Override
	public ExternalTradeSource getExternalTradeSourceOid() {
        return externalTradeSourceOid;
    }

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
        if (!(object instanceof ExternalMappingEntity)) {
            return false;
        }
        ExternalMappingEntity other = (ExternalMappingEntity) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
    	return getExternalValue1();
    }
}