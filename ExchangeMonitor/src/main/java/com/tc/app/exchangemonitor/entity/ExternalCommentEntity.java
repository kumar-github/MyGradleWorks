package com.tc.app.exchangemonitor.entity;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

import com.tc.app.exchangemonitor.entitybase.IExternalCommentEntity;
import com.tc.app.exchangemonitor.model.ExchToolsTrade;

/**
 *
 * @author Saravana Kumar M
 */
//@Entity
//@Table(name = "external_comment", catalog = "QA_30_trade_sep12", schema = "dbo")
@MappedSuperclass

public class ExternalCommentEntity implements IExternalCommentEntity
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "oid")
    private Integer oid;
    @Column(name = "comment_text")
    private String commentText;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(mappedBy = "externalCommentOid")
    private Collection<ExchToolsTrade> exchToolsTradeCollection;

    public ExternalCommentEntity()
    {
    }

    public ExternalCommentEntity(Integer oid)
    {
        this.oid = oid;
    }

    public ExternalCommentEntity(Integer oid, int transId)
    {
        this.oid = oid;
        this.transId = transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalCommentEntity#getOid()
	 */
    @Override
	public Integer getOid() {
        return oid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalCommentEntity#setOid(java.lang.Integer)
	 */
    @Override
	public void setOid(Integer oid) {
        this.oid = oid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalCommentEntity#getCommentText()
	 */
    @Override
	public String getCommentText() {
        return commentText;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalCommentEntity#setCommentText(java.lang.String)
	 */
    @Override
	public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalCommentEntity#getTransId()
	 */
    @Override
	public int getTransId() {
        return transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalCommentEntity#setTransId(int)
	 */
    @Override
	public void setTransId(int transId) {
        this.transId = transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalCommentEntity#getExchToolsTradeCollection()
	 */
    @Override
	@XmlTransient
    public Collection<ExchToolsTrade> getExchToolsTradeCollection() {
        return exchToolsTradeCollection;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalCommentEntity#setExchToolsTradeCollection(java.util.Collection)
	 */
    @Override
	public void setExchToolsTradeCollection(Collection<ExchToolsTrade> exchToolsTradeCollection) {
        this.exchToolsTradeCollection = exchToolsTradeCollection;
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
        if (!(object instanceof ExternalCommentEntity)) {
            return false;
        }
        ExternalCommentEntity other = (ExternalCommentEntity) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExternalCommentEntity[ oid=" + oid + " ]";
    }
    
}
