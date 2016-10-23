package com.tc.app.exchangemonitor.entity;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.tc.app.exchangemonitor.entitybase.IExternalTradeSourceEntity;
import com.tc.app.exchangemonitor.model.ExternalMapping;
import com.tc.app.exchangemonitor.model.ExternalTrade;
import com.tc.app.exchangemonitor.model.ExternalTradeSource;
import com.tc.app.exchangemonitor.model.ExternalTradeSystem;
import com.tc.app.exchangemonitor.model.ExternalTradeType;

/**
 *
 * @author Saravana Kumar M
 */
//@Entity
//@Table(name = "external_trade_source", catalog = "QA_30_trade_sep12", schema = "dbo")
@MappedSuperclass
@XmlRootElement

/*@NamedNativeQueries({
		@NamedNativeQuery(name = "ExternalTradeSource.findAllExternalTradeSource",
				query = "select etsource.* from external_trade_source etsource join external_trade_system etsystem on etsource.external_trade_system_oid = etsystem.oid and etsource.external_trade_src_name <> 'NonDefined' order by etsource.external_trade_src_name",
				resultClass = ExternalTradeSource.class)
})*/

public class ExternalTradeSourceEntity implements IExternalTradeSourceEntity
{

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "oid")
	private Integer oid;
	@Basic(optional = false)
	@Column(name = "external_trade_src_name")
	private String externalTradeSrcName;
	@Basic(optional = false)
	@Column(name = "trans_id")
	private int transId;

	/*
    @JoinColumn(name = "alias_source_code", referencedColumnName = "alias_source_code")
    @ManyToOne
    private AliasSource aliasSourceCode;
	 */

	//modified the above variable's type from AliasSource to String to avoid dependency
	@Column(name="alias_source_code", columnDefinition="CHAR")
	private String aliasSourceCode;

	@JoinColumn(name = "external_trade_system_oid", referencedColumnName = "oid")
	@ManyToOne(optional = false)
	private ExternalTradeSystem externalTradeSystemOid;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "externalTradeSourceOid")
	private Collection<ExternalTrade> externalTradeCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "externalTradeSourceOid")
	private Collection<ExternalTradeType> externalTradeTypeCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "externalTradeSourceOid")
	private Collection<ExternalMapping> externalMappingCollection;

	public ExternalTradeSourceEntity()
	{
	}

	public ExternalTradeSourceEntity(Integer oid)
	{
		this.oid = oid;
	}

	public ExternalTradeSourceEntity(Integer oid, String externalTradeSrcName, int transId)
	{
		this.oid = oid;
		this.externalTradeSrcName = externalTradeSrcName;
		this.transId = transId;
	}

	/* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSourceEntity#getOid()
	 */
	@Override
	public Integer getOid() {
		return oid;
	}

	/* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSourceEntity#setOid(java.lang.Integer)
	 */
	@Override
	public void setOid(Integer oid) {
		this.oid = oid;
	}

	/* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSourceEntity#getExternalTradeSrcName()
	 */
	@Override
	public String getExternalTradeSrcName()
	{
		return externalTradeSrcName;
	}

	/* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSourceEntity#setExternalTradeSrcName(java.lang.String)
	 */
	@Override
	public void setExternalTradeSrcName(String externalTradeSrcName) {
		this.externalTradeSrcName = externalTradeSrcName;
	}

	/* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSourceEntity#getTransId()
	 */
	@Override
	public int getTransId() {
		return transId;
	}

	/* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSourceEntity#setTransId(int)
	 */
	@Override
	public void setTransId(int transId) {
		this.transId = transId;
	}

	/*
    public AliasSource getAliasSourceCode() {
        return aliasSourceCode;
    }

    public void setAliasSourceCode(AliasSource aliasSourceCode) {
        this.aliasSourceCode = aliasSourceCode;
    }
	 */
	/* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSourceEntity#getAliasSourceCode()
	 */
	@Override
	public String getAliasSourceCode()
	{
		return aliasSourceCode;
	}

	/* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSourceEntity#setAliasSourceCode(java.lang.String)
	 */
	@Override
	public void setAliasSourceCode(String aliasSourceCode)
	{
		this.aliasSourceCode = aliasSourceCode;
	}


	/* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSourceEntity#getExternalTradeSystemOid()
	 */
	@Override
	public ExternalTradeSystem getExternalTradeSystemOid() {
		return externalTradeSystemOid;
	}

	/* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSourceEntity#setExternalTradeSystemOid(com.tc.app.exchangemonitor.entity.ExternalTradeSystemEntity)
	 */
	@Override
	public void setExternalTradeSystemOid(ExternalTradeSystem externalTradeSystemOid) {
		this.externalTradeSystemOid = externalTradeSystemOid;
	}

	/* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSourceEntity#getExternalTradeCollection()
	 */
	@Override
	@XmlTransient
	public Collection<ExternalTrade> getExternalTradeCollection() {
		return externalTradeCollection;
	}

	/* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSourceEntity#setExternalTradeCollection(java.util.Collection)
	 */
	@Override
	public void setExternalTradeCollection(Collection<ExternalTrade> externalTradeCollection) {
		this.externalTradeCollection = externalTradeCollection;
	}

	/* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSourceEntity#getExternalTradeTypeCollection()
	 */
	@Override
	@XmlTransient
	public Collection<ExternalTradeType> getExternalTradeTypeCollection() {
		return externalTradeTypeCollection;
	}

	/* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSourceEntity#setExternalTradeTypeCollection(java.util.Collection)
	 */
	@Override
	public void setExternalTradeTypeCollection(Collection<ExternalTradeType> externalTradeTypeCollection) {
		this.externalTradeTypeCollection = externalTradeTypeCollection;
	}

	/* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSourceEntity#getExternalMappingCollection()
	 */
	@Override
	@XmlTransient
	public Collection<ExternalMapping> getExternalMappingCollection()
	{
		return externalMappingCollection;
	}

	/* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSourceEntity#setExternalMappingCollection(java.util.Collection)
	 */
	@Override
	public void setExternalMappingCollection(Collection<ExternalMapping> externalMappingCollection)
	{
		this.externalMappingCollection = externalMappingCollection;
	}

	/* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeSourceEntity#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (oid != null ? oid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ExternalTradeSource)) {
			return false;
		}
		ExternalTradeSource other = (ExternalTradeSource) object;
		if ((this.getOid() == null && other.getOid() != null) || (this.getOid() != null && !this.getOid().equals(other.getOid()))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
        return getExternalTradeSrcName();
	}    
}