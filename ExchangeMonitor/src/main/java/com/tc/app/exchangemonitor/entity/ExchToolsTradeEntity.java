package com.tc.app.exchangemonitor.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.tc.app.exchangemonitor.entitybase.IExchToolsTradeEntity;
import com.tc.app.exchangemonitor.model.ExchToolsTrade;
import com.tc.app.exchangemonitor.model.ExternalComment;
import com.tc.app.exchangemonitor.model.ExternalTrade;

/**
 *
 * @author Saravana Kumar M
 */
//@Entity
//@Table(name = "exch_tools_trade", catalog = "QA_30_trade_sep12", schema = "dbo")
@MappedSuperclass
@XmlRootElement

public class ExchToolsTradeEntity implements IExchToolsTradeEntity
{
	private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "external_trade_oid")
    private Integer externalTradeOid;
    
    @Basic(optional = false)
    @Column(name = "accepted_action", columnDefinition="CHAR")
    private String acceptedAction;
    
    @Column(name = "accepted_broker")
    private String acceptedBroker;
    @Basic(optional = false)
    @Column(name = "accepted_company")
    private String acceptedCompany;
    @Basic(optional = false)
    @Column(name = "accepted_trader")
    private String acceptedTrader;
    @Column(name = "buyer_account")
    private String buyerAccount;
    @Basic(optional = false)
    @Column(name = "commodity")
    private String commodity;
    @Basic(optional = false)
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    @Column(name = "exch_tools_trade_num")
    private String exchToolsTradeNum;
    
    @Basic(optional = false)
    @Column(name = "input_action", columnDefinition="CHAR")
    private String inputAction;
    
    @Column(name = "input_broker")
    private String inputBroker;
    @Basic(optional = false)
    @Column(name = "input_company")
    private String inputCompany;
    @Basic(optional = false)
    @Column(name = "input_trader")
    private String inputTrader;
    @Basic(optional = false)
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @Column(name = "quantity")
    private double quantity;
    @Column(name = "seller_account")
    private String sellerAccount;
    @Basic(optional = false)
    @Column(name = "trading_period")
    private String tradingPeriod;
    @Column(name = "begin_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date beginDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    
    @Column(name = "call_put", columnDefinition="CHAR")
    private String callPut;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "strike_price")
    private Double strikePrice;
    @Column(name = "buyer_comm_cost")
    private Double buyerCommCost;
    
    @Column(name = "buyer_comm_curr", columnDefinition="CHAR")
    private String buyerCommCurr;
    
    @Column(name = "seller_comm_cost")
    private Double sellerCommCost;
    
    @Column(name = "seller_comm_curr", columnDefinition="CHAR")
    private String sellerCommCurr;
    
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @Column(name = "buyer_clrng_broker")
    private String buyerClrngBroker;
    @Column(name = "seller_clrng_broker")
    private String sellerClrngBroker;
    @Column(name = "acct_contact")
    private String acctContact;
    @Column(name = "gtc")
    private String gtc;
    
    @Column(name = "trade_type")
    private String tradeType;
    
    @Column(name = "risk_market", columnDefinition="CHAR")
    private String riskMarket;
    
    @Column(name = "title_market", columnDefinition="CHAR")
    private String titleMarket;
    
    @Column(name = "qty_uom", columnDefinition="CHAR")
    private String qtyUom;
    
    @Column(name = "del_date_from")
    @Temporal(TemporalType.TIMESTAMP)
    private Date delDateFrom;
    @Column(name = "del_date_to")
    @Temporal(TemporalType.TIMESTAMP)
    private Date delDateTo;
    
    @Column(name = "mot", columnDefinition="CHAR")
    private String mot;
    
    @Column(name = "title_transfer")
    private String titleTransfer;
    @Column(name = "price_type")
    private Character priceType;
    @Column(name = "formula_name")
    private String formulaName;
    @Column(name = "event_deemed_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDeemedDate;
    
    @Column(name = "price_uom", columnDefinition="CHAR")
    private String priceUom;
    
    @Column(name = "price_currency", columnDefinition="CHAR")
    private String priceCurrency;
    
    @Column(name = "template_trade_num")
    private Integer templateTradeNum;
    @Column(name = "float_market_quote1")
    private String floatMarketQuote1;
    @Column(name = "float_market_quote2")
    private String floatMarketQuote2;
    @Column(name = "float_qty1")
    private BigDecimal floatQty1;
    @Column(name = "float_qty2")
    private BigDecimal floatQty2;
    @Column(name = "premium_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date premiumDate;
    @Column(name = "auto_exerc_ind")
    private Character autoExercInd;
    @Column(name = "product_id")
    private Integer productId;
    
    @JoinColumn(name = "external_comment_oid", referencedColumnName = "oid")
    @ManyToOne
    private ExternalComment externalCommentOid;
    
    @JoinColumn(name = "external_trade_oid", referencedColumnName = "oid", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private ExternalTrade externalTrade;

    public ExchToolsTradeEntity()
    {
    }

    public ExchToolsTradeEntity(Integer externalTradeOid)
    {
        this.externalTradeOid = externalTradeOid;
    }

    public ExchToolsTradeEntity(Integer externalTradeOid, String acceptedAction, String acceptedCompany, String acceptedTrader, String commodity, Date creationDate, String exchToolsTradeNum, String inputAction, String inputCompany, String inputTrader, double price, double quantity, String tradingPeriod, int transId)
    {
        this.externalTradeOid = externalTradeOid;
        this.acceptedAction = acceptedAction;
        this.acceptedCompany = acceptedCompany;
        this.acceptedTrader = acceptedTrader;
        this.commodity = commodity;
        this.creationDate = creationDate;
        this.exchToolsTradeNum = exchToolsTradeNum;
        this.inputAction = inputAction;
        this.inputCompany = inputCompany;
        this.inputTrader = inputTrader;
        this.price = price;
        this.quantity = quantity;
        this.tradingPeriod = tradingPeriod;
        this.transId = transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getExternalTradeOid()
	 */
    @Override
	public Integer getExternalTradeOid() {
        return externalTradeOid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setExternalTradeOid(java.lang.Integer)
	 */
    @Override
	public void setExternalTradeOid(Integer externalTradeOid) {
        this.externalTradeOid = externalTradeOid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getAcceptedAction()
	 */
    @Override
	public String getAcceptedAction() {
        return acceptedAction;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setAcceptedAction(java.lang.String)
	 */
    @Override
	public void setAcceptedAction(String acceptedAction) {
        this.acceptedAction = acceptedAction;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getAcceptedBroker()
	 */
    @Override
	public String getAcceptedBroker() {
        return acceptedBroker;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setAcceptedBroker(java.lang.String)
	 */
    @Override
	public void setAcceptedBroker(String acceptedBroker) {
        this.acceptedBroker = acceptedBroker;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getAcceptedCompany()
	 */
    @Override
	public String getAcceptedCompany() {
        return acceptedCompany;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setAcceptedCompany(java.lang.String)
	 */
    @Override
	public void setAcceptedCompany(String acceptedCompany) {
        this.acceptedCompany = acceptedCompany;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getAcceptedTrader()
	 */
    @Override
	public String getAcceptedTrader() {
        return acceptedTrader;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setAcceptedTrader(java.lang.String)
	 */
    @Override
	public void setAcceptedTrader(String acceptedTrader) {
        this.acceptedTrader = acceptedTrader;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getBuyerAccount()
	 */
    @Override
	public String getBuyerAccount() {
        return buyerAccount;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setBuyerAccount(java.lang.String)
	 */
    @Override
	public void setBuyerAccount(String buyerAccount) {
        this.buyerAccount = buyerAccount;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getCommodity()
	 */
    @Override
	public String getCommodity() {
        return commodity;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setCommodity(java.lang.String)
	 */
    @Override
	public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getCreationDate()
	 */
    @Override
	public Date getCreationDate() {
        return creationDate;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setCreationDate(java.util.Date)
	 */
    @Override
	public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getExchToolsTradeNum()
	 */
    @Override
	public String getExchToolsTradeNum() {
        return exchToolsTradeNum;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setExchToolsTradeNum(java.lang.String)
	 */
    @Override
	public void setExchToolsTradeNum(String exchToolsTradeNum) {
        this.exchToolsTradeNum = exchToolsTradeNum;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getInputAction()
	 */
    @Override
	public String getInputAction() {
        return inputAction;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setInputAction(java.lang.String)
	 */
    @Override
	public void setInputAction(String inputAction) {
        this.inputAction = inputAction;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getInputBroker()
	 */
    @Override
	public String getInputBroker() {
        return inputBroker;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setInputBroker(java.lang.String)
	 */
    @Override
	public void setInputBroker(String inputBroker) {
        this.inputBroker = inputBroker;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getInputCompany()
	 */
    @Override
	public String getInputCompany() {
        return inputCompany;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setInputCompany(java.lang.String)
	 */
    @Override
	public void setInputCompany(String inputCompany) {
        this.inputCompany = inputCompany;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getInputTrader()
	 */
    @Override
	public String getInputTrader() {
        return inputTrader;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setInputTrader(java.lang.String)
	 */
    @Override
	public void setInputTrader(String inputTrader) {
        this.inputTrader = inputTrader;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getPrice()
	 */
    @Override
	public double getPrice() {
        return price;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setPrice(double)
	 */
    @Override
	public void setPrice(double price) {
        this.price = price;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getQuantity()
	 */
    @Override
	public double getQuantity() {
        return quantity;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setQuantity(double)
	 */
    @Override
	public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getSellerAccount()
	 */
    @Override
	public String getSellerAccount() {
        return sellerAccount;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setSellerAccount(java.lang.String)
	 */
    @Override
	public void setSellerAccount(String sellerAccount) {
        this.sellerAccount = sellerAccount;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getTradingPeriod()
	 */
    @Override
	public String getTradingPeriod() {
        return tradingPeriod;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setTradingPeriod(java.lang.String)
	 */
    @Override
	public void setTradingPeriod(String tradingPeriod) {
        this.tradingPeriod = tradingPeriod;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getBeginDate()
	 */
    @Override
	public Date getBeginDate() {
        return beginDate;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setBeginDate(java.util.Date)
	 */
    @Override
	public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getEndDate()
	 */
    @Override
	public Date getEndDate() {
        return endDate;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setEndDate(java.util.Date)
	 */
    @Override
	public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getCallPut()
	 */
    @Override
	public String getCallPut() {
        return callPut;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setCallPut(java.lang.String)
	 */
    @Override
	public void setCallPut(String callPut) {
        this.callPut = callPut;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getStrikePrice()
	 */
    @Override
	public Double getStrikePrice() {
        return strikePrice;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setStrikePrice(java.lang.Double)
	 */
    @Override
	public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getBuyerCommCost()
	 */
    @Override
	public Double getBuyerCommCost() {
        return buyerCommCost;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setBuyerCommCost(java.lang.Double)
	 */
    @Override
	public void setBuyerCommCost(Double buyerCommCost) {
        this.buyerCommCost = buyerCommCost;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getBuyerCommCurr()
	 */
    @Override
	public String getBuyerCommCurr() {
        return buyerCommCurr;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setBuyerCommCurr(java.lang.String)
	 */
    @Override
	public void setBuyerCommCurr(String buyerCommCurr) {
        this.buyerCommCurr = buyerCommCurr;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getSellerCommCost()
	 */
    @Override
	public Double getSellerCommCost() {
        return sellerCommCost;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setSellerCommCost(java.lang.Double)
	 */
    @Override
	public void setSellerCommCost(Double sellerCommCost) {
        this.sellerCommCost = sellerCommCost;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getSellerCommCurr()
	 */
    @Override
	public String getSellerCommCurr() {
        return sellerCommCurr;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setSellerCommCurr(java.lang.String)
	 */
    @Override
	public void setSellerCommCurr(String sellerCommCurr) {
        this.sellerCommCurr = sellerCommCurr;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getTransId()
	 */
    @Override
	public int getTransId() {
        return transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setTransId(int)
	 */
    @Override
	public void setTransId(int transId) {
        this.transId = transId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getBuyerClrngBroker()
	 */
    @Override
	public String getBuyerClrngBroker() {
        return buyerClrngBroker;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setBuyerClrngBroker(java.lang.String)
	 */
    @Override
	public void setBuyerClrngBroker(String buyerClrngBroker) {
        this.buyerClrngBroker = buyerClrngBroker;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getSellerClrngBroker()
	 */
    @Override
	public String getSellerClrngBroker() {
        return sellerClrngBroker;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setSellerClrngBroker(java.lang.String)
	 */
    @Override
	public void setSellerClrngBroker(String sellerClrngBroker) {
        this.sellerClrngBroker = sellerClrngBroker;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getAcctContact()
	 */
    @Override
	public String getAcctContact() {
        return acctContact;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setAcctContact(java.lang.String)
	 */
    @Override
	public void setAcctContact(String acctContact) {
        this.acctContact = acctContact;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getGtc()
	 */
    @Override
	public String getGtc() {
        return gtc;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setGtc(java.lang.String)
	 */
    @Override
	public void setGtc(String gtc) {
        this.gtc = gtc;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getTradeType()
	 */
    @Override
	public String getTradeType() {
        return tradeType;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setTradeType(java.lang.String)
	 */
    @Override
	public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getRiskMarket()
	 */
    @Override
	public String getRiskMarket() {
        return riskMarket;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setRiskMarket(java.lang.String)
	 */
    @Override
	public void setRiskMarket(String riskMarket) {
        this.riskMarket = riskMarket;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getTitleMarket()
	 */
    @Override
	public String getTitleMarket() {
        return titleMarket;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setTitleMarket(java.lang.String)
	 */
    @Override
	public void setTitleMarket(String titleMarket) {
        this.titleMarket = titleMarket;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getQtyUom()
	 */
    @Override
	public String getQtyUom() {
        return qtyUom;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setQtyUom(java.lang.String)
	 */
    @Override
	public void setQtyUom(String qtyUom) {
        this.qtyUom = qtyUom;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getDelDateFrom()
	 */
    @Override
	public Date getDelDateFrom() {
        return delDateFrom;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setDelDateFrom(java.util.Date)
	 */
    @Override
	public void setDelDateFrom(Date delDateFrom) {
        this.delDateFrom = delDateFrom;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getDelDateTo()
	 */
    @Override
	public Date getDelDateTo() {
        return delDateTo;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setDelDateTo(java.util.Date)
	 */
    @Override
	public void setDelDateTo(Date delDateTo) {
        this.delDateTo = delDateTo;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getMot()
	 */
    @Override
	public String getMot() {
        return mot;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setMot(java.lang.String)
	 */
    @Override
	public void setMot(String mot) {
        this.mot = mot;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getTitleTransfer()
	 */
    @Override
	public String getTitleTransfer() {
        return titleTransfer;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setTitleTransfer(java.lang.String)
	 */
    @Override
	public void setTitleTransfer(String titleTransfer) {
        this.titleTransfer = titleTransfer;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getPriceType()
	 */
    @Override
	public Character getPriceType() {
        return priceType;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setPriceType(java.lang.Character)
	 */
    @Override
	public void setPriceType(Character priceType) {
        this.priceType = priceType;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getFormulaName()
	 */
    @Override
	public String getFormulaName() {
        return formulaName;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setFormulaName(java.lang.String)
	 */
    @Override
	public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getEventDeemedDate()
	 */
    @Override
	public Date getEventDeemedDate() {
        return eventDeemedDate;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setEventDeemedDate(java.util.Date)
	 */
    @Override
	public void setEventDeemedDate(Date eventDeemedDate) {
        this.eventDeemedDate = eventDeemedDate;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getPriceUom()
	 */
    @Override
	public String getPriceUom() {
        return priceUom;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setPriceUom(java.lang.String)
	 */
    @Override
	public void setPriceUom(String priceUom) {
        this.priceUom = priceUom;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getPriceCurrency()
	 */
    @Override
	public String getPriceCurrency() {
        return priceCurrency;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setPriceCurrency(java.lang.String)
	 */
    @Override
	public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getTemplateTradeNum()
	 */
    @Override
	public Integer getTemplateTradeNum() {
        return templateTradeNum;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setTemplateTradeNum(java.lang.Integer)
	 */
    @Override
	public void setTemplateTradeNum(Integer templateTradeNum) {
        this.templateTradeNum = templateTradeNum;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getFloatMarketQuote1()
	 */
    @Override
	public String getFloatMarketQuote1() {
        return floatMarketQuote1;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setFloatMarketQuote1(java.lang.String)
	 */
    @Override
	public void setFloatMarketQuote1(String floatMarketQuote1) {
        this.floatMarketQuote1 = floatMarketQuote1;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getFloatMarketQuote2()
	 */
    @Override
	public String getFloatMarketQuote2() {
        return floatMarketQuote2;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setFloatMarketQuote2(java.lang.String)
	 */
    @Override
	public void setFloatMarketQuote2(String floatMarketQuote2) {
        this.floatMarketQuote2 = floatMarketQuote2;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getFloatQty1()
	 */
    @Override
	public BigDecimal getFloatQty1() {
        return floatQty1;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setFloatQty1(java.math.BigDecimal)
	 */
    @Override
	public void setFloatQty1(BigDecimal floatQty1) {
        this.floatQty1 = floatQty1;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getFloatQty2()
	 */
    @Override
	public BigDecimal getFloatQty2() {
        return floatQty2;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setFloatQty2(java.math.BigDecimal)
	 */
    @Override
	public void setFloatQty2(BigDecimal floatQty2) {
        this.floatQty2 = floatQty2;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getPremiumDate()
	 */
    @Override
	public Date getPremiumDate() {
        return premiumDate;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setPremiumDate(java.util.Date)
	 */
    @Override
	public void setPremiumDate(Date premiumDate) {
        this.premiumDate = premiumDate;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getAutoExercInd()
	 */
    @Override
	public Character getAutoExercInd() {
        return autoExercInd;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setAutoExercInd(java.lang.Character)
	 */
    @Override
	public void setAutoExercInd(Character autoExercInd) {
        this.autoExercInd = autoExercInd;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getProductId()
	 */
    @Override
	public Integer getProductId() {
        return productId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setProductId(java.lang.Integer)
	 */
    @Override
	public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getExternalCommentOid()
	 */
    @Override
	public ExternalComment getExternalCommentOid() {
        return externalCommentOid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setExternalCommentOid(com.tc.app.exchangemonitor.entity.ExternalCommentEntity)
	 */
    @Override
	public void setExternalCommentOid(ExternalComment externalCommentOid) {
        this.externalCommentOid = externalCommentOid;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#getExternalTrade()
	 */
    @Override
	public ExternalTrade getExternalTrade() {
        return externalTrade;
    }

    /* (non-Javadoc)
	 * @see com.tc.app.exchangemonitor.entity.IExchToolsTradeEntity#setExternalTrade(com.tc.app.exchangemonitor.entity.ExternalTradeEntity)
	 */
    @Override
	public void setExternalTrade(ExternalTrade externalTrade) {
        this.externalTrade = externalTrade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (externalTradeOid != null ? externalTradeOid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExchToolsTrade)) {
            return false;
        }
        ExchToolsTrade other = (ExchToolsTrade) object;
        if ((this.getExternalTradeOid() == null && other.getExternalTradeOid() != null) || (this.getExternalTradeOid() != null && !this.getExternalTradeOid().equals(other.getExternalTradeOid()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ExchToolsTradeEntity[ externalTradeOid=" + externalTradeOid + " ]";
    }
}