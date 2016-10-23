package com.tc.app.exchangemonitor.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.tc.app.exchangemonitor.entitybase.IExternalTradeEntity;
import com.tc.app.exchangemonitor.model.ExchToolsTrade;
import com.tc.app.exchangemonitor.model.ExternalComment;
import com.tc.app.exchangemonitor.model.ExternalTradeSource;
import com.tc.app.exchangemonitor.model.ExternalTradeState;
import com.tc.app.exchangemonitor.model.ExternalTradeStatus;
import com.tc.app.exchangemonitor.model.ExternalTradeSystem;

/**
 *
 * @author Saravana Kumar M
 */
//@Entity
//@Table(name = "external_trade", catalog = "QA_30_trade_sep12", schema = "dbo")
@MappedSuperclass
@XmlRootElement
public class ExternalTradeEntity implements IExternalTradeEntity
{
	private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "oid")
    private Integer oid;
    
    @Basic(optional = false)
    @Column(name = "entry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;
    
    @Column(name = "port_num")
    private Integer portNum;
    @Column(name = "trade_num")
    private Integer tradeNum;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    
    @Basic(optional = false)
    @Column(name = "sequence", columnDefinition="big_decimal")
    private BigDecimal sequence;
    
    /*
    @Column(name = "external_comment_oid")
    private Integer externalCommentOid;
    */
    /* modified the above code as below. Implemented a soft relationship to ExternalComment entity though we don't have a real relationship in DB level */
    @JoinColumn(name = "external_comment_oid", referencedColumnName = "oid")
    @OneToOne
    //private ExternalCommentEntity externalCommentOid;
    private ExternalComment externalCommentOid;
    
    @Column(name = "inhouse_port_num")
    private Integer inhousePortNum;
    @Column(name = "order_num")
    private Short orderNum;
    @Column(name = "item_num")
    private Short itemNum;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "externalTrade")
    private ExchToolsTrade exchToolsTrade;
    
    /*
    @JoinColumn(name = "ext_pos_num", referencedColumnName = "ext_pos_num")
    @ManyToOne
    private ExternalPosition extPosNum;
    */
    //modified the above code as below. Broker the ExternalPosition entity relationship to avoid dependency.
    @Column(name = "ext_pos_num")
    private Integer extPosNum;
    
    @JoinColumn(name = "external_trade_source_oid", referencedColumnName = "oid")
    @ManyToOne(optional = false)
    private ExternalTradeSource externalTradeSourceOid;
    
    @JoinColumn(name = "external_trade_state_oid", referencedColumnName = "oid")
    @ManyToOne
    private ExternalTradeState externalTradeStateOid;
    @JoinColumn(name = "external_trade_status_oid", referencedColumnName = "oid")
    @ManyToOne(optional = false)
    private ExternalTradeStatus externalTradeStatusOid;
    
    @JoinColumn(name = "external_trade_system_oid", referencedColumnName = "oid")
    @ManyToOne(optional = false)
    private ExternalTradeSystem externalTradeSystemOid;

    public ExternalTradeEntity()
    {
    }

    public ExternalTradeEntity(Integer oid) {
        this.oid = oid;
    }

    public ExternalTradeEntity(Integer oid, Date entryDate, int transId, BigDecimal sequence) {
        this.oid = oid;
        this.entryDate = entryDate;
        this.transId = transId;
        this.sequence = sequence;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#getOid()
	 */
    @Override
	public Integer getOid() {
        return oid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#setOid(java.lang.Integer)
	 */
    @Override
	public void setOid(Integer oid) {
        this.oid = oid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#getEntryDate()
	 */
    @Override
	public Date getEntryDate() {
        return entryDate;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#setEntryDate(java.util.Date)
	 */
    @Override
	public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#getPortNum()
	 */
    @Override
	public Integer getPortNum() {
        return portNum;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#setPortNum(java.lang.Integer)
	 */
    @Override
	public void setPortNum(Integer portNum) {
        this.portNum = portNum;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#getTradeNum()
	 */
    @Override
	public Integer getTradeNum() {
        return tradeNum;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#setTradeNum(java.lang.Integer)
	 */
    @Override
	public void setTradeNum(Integer tradeNum) {
        this.tradeNum = tradeNum;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#getTransId()
	 */
    @Override
	public int getTransId() {
        return transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#setTransId(int)
	 */
    @Override
	public void setTransId(int transId) {
        this.transId = transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#getSequence()
	 */
    @Override
	public BigDecimal getSequence() {
        return sequence;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#setSequence(java.math.BigDecimal)
	 */
    @Override
	public void setSequence(BigDecimal sequence) {
        this.sequence = sequence;
    }
    
    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#getExternalCommentOid()
	 */
    @Override
	public ExternalComment getExternalCommentOid()
    {
    	return externalCommentOid;
    }
    
    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#setExternalCommentOid(com.tc.app.exchangemonitor.entity.IExternalCommentEntity)
	 */
	@Override
    public void setExternalCommentOid(ExternalComment externalCommentOid)
	{
    	this.externalCommentOid = externalCommentOid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#getInhousePortNum()
	 */
    @Override
	public Integer getInhousePortNum() {
        return inhousePortNum;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#setInhousePortNum(java.lang.Integer)
	 */
    @Override
	public void setInhousePortNum(Integer inhousePortNum) {
        this.inhousePortNum = inhousePortNum;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#getOrderNum()
	 */
    @Override
	public Short getOrderNum() {
        return orderNum;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#setOrderNum(java.lang.Short)
	 */
    @Override
	public void setOrderNum(Short orderNum) {
        this.orderNum = orderNum;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#getItemNum()
	 */
    @Override
	public Short getItemNum() {
        return itemNum;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#setItemNum(java.lang.Short)
	 */
    @Override
	public void setItemNum(Short itemNum) {
        this.itemNum = itemNum;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#getExchToolsTrade()
	 */
    @Override
	public ExchToolsTrade getExchToolsTrade() {
        return exchToolsTrade;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#setExchToolsTrade(com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity)
	 */
    @Override
    public void setExchToolsTrade(ExchToolsTrade exchToolsTrade)
    {
        this.exchToolsTrade = exchToolsTrade;
    }

    /*
    public ExternalPosition getExtPosNum() {
        return extPosNum;
    }

    public void setExtPosNum(ExternalPosition extPosNum) {
        this.extPosNum = extPosNum;
    }
    */
    
    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#getExtPosNum()
	 */
    @Override
	public Integer getExtPosNum()
    {
        return extPosNum;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#setExtPosNum(java.lang.Integer)
	 */
    @Override
	public void setExtPosNum(Integer extPosNum)
    {
        this.extPosNum = extPosNum;
    }
    
    @Override
	public ExternalTradeSource getExternalTradeSourceOid()
    {
        return externalTradeSourceOid;
    }

    @Override
    public void setExternalTradeSourceOid(ExternalTradeSource externalTradeSourceOid)
    {
        this.externalTradeSourceOid = externalTradeSourceOid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#getExternalTradeStateOid()
	 */
    @Override
	public ExternalTradeState getExternalTradeStateOid()
    {
        return externalTradeStateOid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#setExternalTradeStateOid(com.tc.app.exchangemonitor.entity.ExternalTradeStateEntity)
	 */
    @Override
    public void setExternalTradeStateOid(ExternalTradeState externalTradeStateOid)
	{
        this.externalTradeStateOid = externalTradeStateOid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#getExternalTradeStatusOid()
	 */
    @Override
	public ExternalTradeStatus getExternalTradeStatusOid() {
        return externalTradeStatusOid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#setExternalTradeStatusOid(com.tc.app.exchangemonitor.entity.ExternalTradeStatusEntity)
	 */
    @Override
    public void setExternalTradeStatusOid(ExternalTradeStatus externalTradeStatusOid) {
        this.externalTradeStatusOid = externalTradeStatusOid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#getExternalTradeSystemOid()
	 */
    @Override
	public ExternalTradeSystem getExternalTradeSystemOid() {
        return externalTradeSystemOid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExternalTradeEntity#setExternalTradeSystemOid(com.tc.app.exchangemonitor.entity.ExternalTradeSystem)
	 */
    @Override
    public void setExternalTradeSystemOid(ExternalTradeSystem externalTradeSystemOid) {
        this.externalTradeSystemOid = externalTradeSystemOid;
    }

    /*
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (oid != null ? oid.hashCode() : 0);
        return hash;
    }
    */
    
    @Override
    public int hashCode()
    {
    	int hash = 5;
    	hash = 97 * hash + Objects.hashCode(this.oid);
    	return hash;
    }

    /*
    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExternalTrade))
        {
            return false;
        }
        ExternalTrade other = (ExternalTrade) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid)))
        {
            return false;
        }
        return true;
    }
    */
    
    @Override
    public boolean equals(Object obj)
    {
    	if(obj == null)
    		return false;
    	if(this.getClass() != obj.getClass())
    		return false;
    	
    	final ExternalTradeEntity other = (ExternalTradeEntity)obj;
    	if(!Objects.equals(this.oid, other.oid))
    		return false;
    	return true;
    }

    @Override
    public String toString()
    {
        return "ExternalTradeEntity[ oid=" + oid + " ]";
    }
}