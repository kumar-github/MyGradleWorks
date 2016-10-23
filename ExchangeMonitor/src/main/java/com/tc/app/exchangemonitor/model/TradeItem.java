/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "trade_group", catalog = "QA_30_trade_sep12", schema = "dbo")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "TradeItem.findAll", query = "SELECT t FROM TradeItem t"),
	@NamedQuery(name = "TradeItem.findByTradeNum", query = "SELECT t FROM TradeItem t WHERE t.tradeItemPK.tradeNum = :tradeNum"),
	@NamedQuery(name = "TradeItem.findByOrderNum", query = "SELECT t FROM TradeItem t WHERE t.tradeItemPK.orderNum = :orderNum"),
	@NamedQuery(name = "TradeItem.findByItemNum", query = "SELECT t FROM TradeItem t WHERE t.tradeItemPK.itemNum = :itemNum"),
	@NamedQuery(name = "TradeItem.findByItemStatusCode", query = "SELECT t FROM TradeItem t WHERE t.itemStatusCode = :itemStatusCode"),
	@NamedQuery(name = "TradeItem.findByPSInd", query = "SELECT t FROM TradeItem t WHERE t.pSInd = :pSInd"),
	@NamedQuery(name = "TradeItem.findByTradingPrd", query = "SELECT t FROM TradeItem t WHERE t.tradingPrd = :tradingPrd"),
	@NamedQuery(name = "TradeItem.findByContrQty", query = "SELECT t FROM TradeItem t WHERE t.contrQty = :contrQty"),
	@NamedQuery(name = "TradeItem.findByContrQtyPeriodicity", query = "SELECT t FROM TradeItem t WHERE t.contrQtyPeriodicity = :contrQtyPeriodicity"),
	@NamedQuery(name = "TradeItem.findByAccumPeriodicity", query = "SELECT t FROM TradeItem t WHERE t.accumPeriodicity = :accumPeriodicity"),
	@NamedQuery(name = "TradeItem.findByUomConvRate", query = "SELECT t FROM TradeItem t WHERE t.uomConvRate = :uomConvRate"),
	@NamedQuery(name = "TradeItem.findByItemType", query = "SELECT t FROM TradeItem t WHERE t.itemType = :itemType"),
	@NamedQuery(name = "TradeItem.findByFormulaInd", query = "SELECT t FROM TradeItem t WHERE t.formulaInd = :formulaInd"),
	@NamedQuery(name = "TradeItem.findByTotalPricedQty", query = "SELECT t FROM TradeItem t WHERE t.totalPricedQty = :totalPricedQty"),
	@NamedQuery(name = "TradeItem.findByAvgPrice", query = "SELECT t FROM TradeItem t WHERE t.avgPrice = :avgPrice"),
	@NamedQuery(name = "TradeItem.findByIdmsBbRefNum", query = "SELECT t FROM TradeItem t WHERE t.idmsBbRefNum = :idmsBbRefNum"),
	@NamedQuery(name = "TradeItem.findByIdmsContrNum", query = "SELECT t FROM TradeItem t WHERE t.idmsContrNum = :idmsContrNum"),
	@NamedQuery(name = "TradeItem.findByIdmsProfitCenter", query = "SELECT t FROM TradeItem t WHERE t.idmsProfitCenter = :idmsProfitCenter"),
	@NamedQuery(name = "TradeItem.findByIdmsAcctAlloc", query = "SELECT t FROM TradeItem t WHERE t.idmsAcctAlloc = :idmsAcctAlloc"),
	@NamedQuery(name = "TradeItem.findByCmntNum", query = "SELECT t FROM TradeItem t WHERE t.cmntNum = :cmntNum"),
	@NamedQuery(name = "TradeItem.findByBrkrCommAmt", query = "SELECT t FROM TradeItem t WHERE t.brkrCommAmt = :brkrCommAmt"),
	@NamedQuery(name = "TradeItem.findByBrkrRefNum", query = "SELECT t FROM TradeItem t WHERE t.brkrRefNum = :brkrRefNum"),
	@NamedQuery(name = "TradeItem.findByParentItemNum", query = "SELECT t FROM TradeItem t WHERE t.parentItemNum = :parentItemNum"),
	@NamedQuery(name = "TradeItem.findByRealPortNum", query = "SELECT t FROM TradeItem t WHERE t.realPortNum = :realPortNum"),
	@NamedQuery(name = "TradeItem.findByAmendNum", query = "SELECT t FROM TradeItem t WHERE t.amendNum = :amendNum"),
	@NamedQuery(name = "TradeItem.findByAmendCreationDate", query = "SELECT t FROM TradeItem t WHERE t.amendCreationDate = :amendCreationDate"),
	@NamedQuery(name = "TradeItem.findByAmendEffectStartDate", query = "SELECT t FROM TradeItem t WHERE t.amendEffectStartDate = :amendEffectStartDate"),
	@NamedQuery(name = "TradeItem.findByAmendEffectEndDate", query = "SELECT t FROM TradeItem t WHERE t.amendEffectEndDate = :amendEffectEndDate"),
	@NamedQuery(name = "TradeItem.findBySummaryItemNum", query = "SELECT t FROM TradeItem t WHERE t.summaryItemNum = :summaryItemNum"),
	@NamedQuery(name = "TradeItem.findByPoolingType", query = "SELECT t FROM TradeItem t WHERE t.poolingType = :poolingType"),
	@NamedQuery(name = "TradeItem.findByPoolingPortNum", query = "SELECT t FROM TradeItem t WHERE t.poolingPortNum = :poolingPortNum"),
	@NamedQuery(name = "TradeItem.findByPoolingPortInd", query = "SELECT t FROM TradeItem t WHERE t.poolingPortInd = :poolingPortInd"),
	@NamedQuery(name = "TradeItem.findByTotalSchQty", query = "SELECT t FROM TradeItem t WHERE t.totalSchQty = :totalSchQty"),
	@NamedQuery(name = "TradeItem.findByOpenQty", query = "SELECT t FROM TradeItem t WHERE t.openQty = :openQty"),
	@NamedQuery(name = "TradeItem.findByOpenQtyUomCode", query = "SELECT t FROM TradeItem t WHERE t.openQtyUomCode = :openQtyUomCode"),
	@NamedQuery(name = "TradeItem.findByMtmPl", query = "SELECT t FROM TradeItem t WHERE t.mtmPl = :mtmPl"),
	@NamedQuery(name = "TradeItem.findByMtmPlAsOfDate", query = "SELECT t FROM TradeItem t WHERE t.mtmPlAsOfDate = :mtmPlAsOfDate"),
	@NamedQuery(name = "TradeItem.findByStripItemStatus", query = "SELECT t FROM TradeItem t WHERE t.stripItemStatus = :stripItemStatus"),
	@NamedQuery(name = "TradeItem.findByEstimateInd", query = "SELECT t FROM TradeItem t WHERE t.estimateInd = :estimateInd"),
	@NamedQuery(name = "TradeItem.findByBillingType", query = "SELECT t FROM TradeItem t WHERE t.billingType = :billingType"),
	@NamedQuery(name = "TradeItem.findBySchedStatus", query = "SELECT t FROM TradeItem t WHERE t.schedStatus = :schedStatus"),
	@NamedQuery(name = "TradeItem.findByHedgeRate", query = "SELECT t FROM TradeItem t WHERE t.hedgeRate = :hedgeRate"),
	@NamedQuery(name = "TradeItem.findByHedgeMultiDivInd", query = "SELECT t FROM TradeItem t WHERE t.hedgeMultiDivInd = :hedgeMultiDivInd"),
	@NamedQuery(name = "TradeItem.findByRecapItemNum", query = "SELECT t FROM TradeItem t WHERE t.recapItemNum = :recapItemNum"),
	@NamedQuery(name = "TradeItem.findByHedgePosInd", query = "SELECT t FROM TradeItem t WHERE t.hedgePosInd = :hedgePosInd"),
	@NamedQuery(name = "TradeItem.findByAddlCostSum", query = "SELECT t FROM TradeItem t WHERE t.addlCostSum = :addlCostSum"),
	@NamedQuery(name = "TradeItem.findByContrMtmPl", query = "SELECT t FROM TradeItem t WHERE t.contrMtmPl = :contrMtmPl"),
	@NamedQuery(name = "TradeItem.findByMaxAccumNum", query = "SELECT t FROM TradeItem t WHERE t.maxAccumNum = :maxAccumNum"),
	@NamedQuery(name = "TradeItem.findByFormulaDeclarDate", query = "SELECT t FROM TradeItem t WHERE t.formulaDeclarDate = :formulaDeclarDate"),
	@NamedQuery(name = "TradeItem.findByPurchasingGroup", query = "SELECT t FROM TradeItem t WHERE t.purchasingGroup = :purchasingGroup"),
	@NamedQuery(name = "TradeItem.findByTransId", query = "SELECT t FROM TradeItem t WHERE t.transId = :transId"),
	@NamedQuery(name = "TradeItem.findByInternalParentTradeNum", query = "SELECT t FROM TradeItem t WHERE t.internalParentTradeNum = :internalParentTradeNum"),
	@NamedQuery(name = "TradeItem.findByInternalParentOrderNum", query = "SELECT t FROM TradeItem t WHERE t.internalParentOrderNum = :internalParentOrderNum"),
	@NamedQuery(name = "TradeItem.findByInternalParentItemNum", query = "SELECT t FROM TradeItem t WHERE t.internalParentItemNum = :internalParentItemNum"),
	@NamedQuery(name = "TradeItem.findByTradeModifiedInd", query = "SELECT t FROM TradeItem t WHERE t.tradeModifiedInd = :tradeModifiedInd"),
	@NamedQuery(name = "TradeItem.findByItemConfirmInd", query = "SELECT t FROM TradeItem t WHERE t.itemConfirmInd = :itemConfirmInd"),
	@NamedQuery(name = "TradeItem.findByActiveStatusInd", query = "SELECT t FROM TradeItem t WHERE t.activeStatusInd = :activeStatusInd"),
	@NamedQuery(name = "TradeItem.findByMarketValue", query = "SELECT t FROM TradeItem t WHERE t.marketValue = :marketValue"),
	@NamedQuery(name = "TradeItem.findByIncludesExciseTaxInd", query = "SELECT t FROM TradeItem t WHERE t.includesExciseTaxInd = :includesExciseTaxInd"),
	@NamedQuery(name = "TradeItem.findByIncludesFuelTaxInd", query = "SELECT t FROM TradeItem t WHERE t.includesFuelTaxInd = :includesFuelTaxInd"),
	@NamedQuery(name = "TradeItem.findByIsClearedInd", query = "SELECT t FROM TradeItem t WHERE t.isClearedInd = :isClearedInd"),
	@NamedQuery(name = "TradeItem.findByTotalCommittedQty", query = "SELECT t FROM TradeItem t WHERE t.totalCommittedQty = :totalCommittedQty"),
	@NamedQuery(name = "TradeItem.findByIsLcAssigned", query = "SELECT t FROM TradeItem t WHERE t.isLcAssigned = :isLcAssigned"),
	@NamedQuery(name = "TradeItem.findByIsRcAssigned", query = "SELECT t FROM TradeItem t WHERE t.isRcAssigned = :isRcAssigned"),
	@NamedQuery(name = "TradeItem.findByRinInd", query = "SELECT t FROM TradeItem t WHERE t.rinInd = :rinInd"),
	@NamedQuery(name = "TradeItem.findByB2bTradeItem", query = "SELECT t FROM TradeItem t WHERE t.b2bTradeItem = :b2bTradeItem"),
	@NamedQuery(name = "TradeItem.findByUseMktFormulaForPl", query = "SELECT t FROM TradeItem t WHERE t.useMktFormulaForPl = :useMktFormulaForPl"),
	@NamedQuery(name = "TradeItem.findBySapOrderNum", query = "SELECT t FROM TradeItem t WHERE t.sapOrderNum = :sapOrderNum")})
public class TradeItem implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected TradeItemPK tradeItemPK;
	@Column(name = "item_status_code")
	private String itemStatusCode;
	@Column(name = "p_s_ind")
	private Character pSInd;
	@Column(name = "trading_prd")
	private String tradingPrd;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "contr_qty")
	private Double contrQty;
	@Column(name = "contr_qty_periodicity")
	private Character contrQtyPeriodicity;
	@Column(name = "accum_periodicity")
	private Character accumPeriodicity;
	@Column(name = "uom_conv_rate")
	private Double uomConvRate;
	@Column(name = "item_type")
	private Character itemType;
	@Column(name = "formula_ind")
	private Character formulaInd;
	@Column(name = "total_priced_qty")
	private Double totalPricedQty;
	@Column(name = "avg_price")
	private Double avgPrice;
	@Column(name = "idms_bb_ref_num")
	private String idmsBbRefNum;
	@Column(name = "idms_contr_num")
	private String idmsContrNum;
	@Column(name = "idms_profit_center")
	private String idmsProfitCenter;
	@Column(name = "idms_acct_alloc")
	private String idmsAcctAlloc;
	@Column(name = "cmnt_num")
	private Integer cmntNum;
	@Column(name = "brkr_comm_amt")
	private Double brkrCommAmt;
	@Column(name = "brkr_ref_num")
	private String brkrRefNum;
	@Column(name = "parent_item_num")
	private Short parentItemNum;
	@Column(name = "real_port_num")
	private Integer realPortNum;
	@Column(name = "amend_num")
	private Short amendNum;
	@Column(name = "amend_creation_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date amendCreationDate;
	@Column(name = "amend_effect_start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date amendEffectStartDate;
	@Column(name = "amend_effect_end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date amendEffectEndDate;
	@Column(name = "summary_item_num")
	private Short summaryItemNum;
	@Column(name = "pooling_type")
	private Character poolingType;
	@Column(name = "pooling_port_num")
	private Integer poolingPortNum;
	@Column(name = "pooling_port_ind")
	private Character poolingPortInd;
	@Column(name = "total_sch_qty")
	private Double totalSchQty;
	@Column(name = "open_qty")
	private Double openQty;
	@Column(name = "open_qty_uom_code")
	private String openQtyUomCode;
	@Column(name = "mtm_pl")
	private Double mtmPl;
	@Column(name = "mtm_pl_as_of_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date mtmPlAsOfDate;
	@Column(name = "strip_item_status")
	private Character stripItemStatus;
	@Column(name = "estimate_ind")
	private Character estimateInd;
	@Column(name = "billing_type")
	private Character billingType;
	@Column(name = "sched_status")
	private Short schedStatus;
	@Column(name = "hedge_rate")
	private Double hedgeRate;
	@Column(name = "hedge_multi_div_ind")
	private Character hedgeMultiDivInd;
	@Column(name = "recap_item_num")
	private Integer recapItemNum;
	@Column(name = "hedge_pos_ind")
	private Character hedgePosInd;
	@Column(name = "addl_cost_sum")
	private Double addlCostSum;
	@Column(name = "contr_mtm_pl")
	private Double contrMtmPl;
	@Column(name = "max_accum_num")
	private Short maxAccumNum;
	@Column(name = "formula_declar_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date formulaDeclarDate;
	@Column(name = "purchasing_group")
	private String purchasingGroup;
	@Basic(optional = false)
	@Column(name = "trans_id")
	private int transId;
	@Column(name = "internal_parent_trade_num")
	private Integer internalParentTradeNum;
	@Column(name = "internal_parent_order_num")
	private Short internalParentOrderNum;
	@Column(name = "internal_parent_item_num")
	private Short internalParentItemNum;
	@Column(name = "trade_modified_ind")
	private Character tradeModifiedInd;
	@Column(name = "item_confirm_ind")
	private Character itemConfirmInd;
	@Column(name = "active_status_ind")
	private Character activeStatusInd;
	@Column(name = "market_value")
	private BigDecimal marketValue;
	@Basic(optional = false)
	@Column(name = "includes_excise_tax_ind")
	private boolean includesExciseTaxInd;
	@Basic(optional = false)
	@Column(name = "includes_fuel_tax_ind")
	private boolean includesFuelTaxInd;
	@Column(name = "is_cleared_ind")
	private Character isClearedInd;
	@Column(name = "total_committed_qty")
	private BigDecimal totalCommittedQty;
	@Basic(optional = false)
	@Column(name = "is_lc_assigned")
	private Character isLcAssigned;
	@Basic(optional = false)
	@Column(name = "is_rc_assigned")
	private Character isRcAssigned;
	@Column(name = "rin_ind")
	private Character rinInd;
	@Column(name = "b2b_trade_item")
	private String b2bTradeItem;
	@Basic(optional = false)
	@Column(name = "use_mkt_formula_for_pl")
	private Character useMktFormulaForPl;
	@Column(name = "sap_order_num")
	private String sapOrderNum;
	@JoinColumn(name = "exch_brkr_num", referencedColumnName = "acct_num")
	@ManyToOne(fetch = FetchType.LAZY)
	private Account exchBrkrNum;
	@JoinColumn(name = "booking_comp_num", referencedColumnName = "acct_num")
	@ManyToOne(fetch = FetchType.LAZY)
	private Account bookingCompNum;
	@JoinColumn(name = "brkr_num", referencedColumnName = "acct_num")
	@ManyToOne(fetch = FetchType.LAZY)
	private Account brkrNum;
	@JoinColumn(name = "risk_mkt_code", referencedColumnName = "mkt_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private Market riskMktCode;
	@JoinColumn(name = "finance_bank_num", referencedColumnName = "acct_num")
	@ManyToOne(fetch = FetchType.LAZY)
	private Account financeBankNum;
	@JoinColumn(name = "title_mkt_code", referencedColumnName = "mkt_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private Market titleMktCode;
	@JoinColumn(name = "price_uom_code", referencedColumnName = "uom_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private Uom priceUomCode;
	@JoinColumn(name = "clr_service_num", referencedColumnName = "acct_num")
	@ManyToOne(fetch = FetchType.LAZY)
	private Account clrServiceNum;
	@JoinColumn(name = "agreement_num", referencedColumnName = "agreement_num")
	@ManyToOne(fetch = FetchType.LAZY)
	private AccountAgreement agreementNum;
	@JoinColumns({
		@JoinColumn(name = "brkr_num", referencedColumnName = "acct_num"),
		@JoinColumn(name = "brkr_cont_num", referencedColumnName = "acct_cont_num")})
	@ManyToOne(fetch = FetchType.LAZY)
	private AccountContact accountContact;
	@JoinColumn(name = "brkr_comm_uom_code", referencedColumnName = "uom_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private Uom brkrCommUomCode;
	@JoinColumn(name = "cmdty_code", referencedColumnName = "cmdty_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private Commodity cmdtyCode;
	@JoinColumn(name = "fut_trader_init", referencedColumnName = "user_init")
	@ManyToOne(fetch = FetchType.LAZY)
	private IctsUser futTraderInit;
	@JoinColumn(name = "priced_qty_uom_code", referencedColumnName = "uom_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private Uom pricedQtyUomCode;
	@JoinColumn(name = "sch_qty_uom_code", referencedColumnName = "uom_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private Uom schQtyUomCode;
	@JoinColumn(name = "committed_qty_uom_code", referencedColumnName = "uom_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private Uom committedQtyUomCode;
	@JoinColumn(name = "price_curr_code", referencedColumnName = "cmdty_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private Commodity priceCurrCode;
	@JoinColumn(name = "excp_addns_code", referencedColumnName = "excp_addns_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private ExceptionsAdditions excpAddnsCode;
	@JoinColumn(name = "disch_port_loc_code", referencedColumnName = "loc_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private Location dischPortLocCode;
	@JoinColumn(name = "contr_qty_uom_code", referencedColumnName = "uom_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private Uom contrQtyUomCode;
	@JoinColumn(name = "brkr_comm_curr_code", referencedColumnName = "cmdty_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private Commodity brkrCommCurrCode;
	@JoinColumn(name = "origin_country_code", referencedColumnName = "country_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private Country originCountryCode;
	@JoinColumn(name = "gtc_code", referencedColumnName = "gtc_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private Gtc gtcCode;
	@JoinColumn(name = "load_port_loc_code", referencedColumnName = "loc_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private Location loadPortLocCode;
	@JoinColumn(name = "hedge_curr_code", referencedColumnName = "cmdty_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private Commodity hedgeCurrCode;
	@JoinColumn(name = "mtm_pl_curr_code", referencedColumnName = "cmdty_code")
	@ManyToOne(fetch = FetchType.LAZY)
	private Commodity mtmPlCurrCode;

	public TradeItem() {
	}

	public TradeItem(TradeItemPK tradeItemPK) {
		this.tradeItemPK = tradeItemPK;
	}

	public TradeItem(TradeItemPK tradeItemPK, int transId, boolean includesExciseTaxInd, boolean includesFuelTaxInd, Character isLcAssigned, Character isRcAssigned, Character useMktFormulaForPl) {
		this.tradeItemPK = tradeItemPK;
		this.transId = transId;
		this.includesExciseTaxInd = includesExciseTaxInd;
		this.includesFuelTaxInd = includesFuelTaxInd;
		this.isLcAssigned = isLcAssigned;
		this.isRcAssigned = isRcAssigned;
		this.useMktFormulaForPl = useMktFormulaForPl;
	}

	public TradeItem(int tradeNum, short orderNum, short itemNum) {
		this.tradeItemPK = new TradeItemPK(tradeNum, orderNum, itemNum);
	}

	public TradeItemPK getTradeItemPK() {
		return tradeItemPK;
	}

	public void setTradeItemPK(TradeItemPK tradeItemPK) {
		this.tradeItemPK = tradeItemPK;
	}

	public String getItemStatusCode() {
		return itemStatusCode;
	}

	public void setItemStatusCode(String itemStatusCode) {
		this.itemStatusCode = itemStatusCode;
	}

	public Character getPSInd() {
		return pSInd;
	}

	public void setPSInd(Character pSInd) {
		this.pSInd = pSInd;
	}

	public String getTradingPrd() {
		return tradingPrd;
	}

	public void setTradingPrd(String tradingPrd) {
		this.tradingPrd = tradingPrd;
	}

	public Double getContrQty() {
		return contrQty;
	}

	public void setContrQty(Double contrQty) {
		this.contrQty = contrQty;
	}

	public Character getContrQtyPeriodicity() {
		return contrQtyPeriodicity;
	}

	public void setContrQtyPeriodicity(Character contrQtyPeriodicity) {
		this.contrQtyPeriodicity = contrQtyPeriodicity;
	}

	public Character getAccumPeriodicity() {
		return accumPeriodicity;
	}

	public void setAccumPeriodicity(Character accumPeriodicity) {
		this.accumPeriodicity = accumPeriodicity;
	}

	public Double getUomConvRate() {
		return uomConvRate;
	}

	public void setUomConvRate(Double uomConvRate) {
		this.uomConvRate = uomConvRate;
	}

	public Character getItemType() {
		return itemType;
	}

	public void setItemType(Character itemType) {
		this.itemType = itemType;
	}

	public Character getFormulaInd() {
		return formulaInd;
	}

	public void setFormulaInd(Character formulaInd) {
		this.formulaInd = formulaInd;
	}

	public Double getTotalPricedQty() {
		return totalPricedQty;
	}

	public void setTotalPricedQty(Double totalPricedQty) {
		this.totalPricedQty = totalPricedQty;
	}

	public Double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getIdmsBbRefNum() {
		return idmsBbRefNum;
	}

	public void setIdmsBbRefNum(String idmsBbRefNum) {
		this.idmsBbRefNum = idmsBbRefNum;
	}

	public String getIdmsContrNum() {
		return idmsContrNum;
	}

	public void setIdmsContrNum(String idmsContrNum) {
		this.idmsContrNum = idmsContrNum;
	}

	public String getIdmsProfitCenter() {
		return idmsProfitCenter;
	}

	public void setIdmsProfitCenter(String idmsProfitCenter) {
		this.idmsProfitCenter = idmsProfitCenter;
	}

	public String getIdmsAcctAlloc() {
		return idmsAcctAlloc;
	}

	public void setIdmsAcctAlloc(String idmsAcctAlloc) {
		this.idmsAcctAlloc = idmsAcctAlloc;
	}

	public Integer getCmntNum() {
		return cmntNum;
	}

	public void setCmntNum(Integer cmntNum) {
		this.cmntNum = cmntNum;
	}

	public Double getBrkrCommAmt() {
		return brkrCommAmt;
	}

	public void setBrkrCommAmt(Double brkrCommAmt) {
		this.brkrCommAmt = brkrCommAmt;
	}

	public String getBrkrRefNum() {
		return brkrRefNum;
	}

	public void setBrkrRefNum(String brkrRefNum) {
		this.brkrRefNum = brkrRefNum;
	}

	public Short getParentItemNum() {
		return parentItemNum;
	}

	public void setParentItemNum(Short parentItemNum) {
		this.parentItemNum = parentItemNum;
	}

	public Integer getRealPortNum() {
		return realPortNum;
	}

	public void setRealPortNum(Integer realPortNum) {
		this.realPortNum = realPortNum;
	}

	public Short getAmendNum() {
		return amendNum;
	}

	public void setAmendNum(Short amendNum) {
		this.amendNum = amendNum;
	}

	public Date getAmendCreationDate() {
		return amendCreationDate;
	}

	public void setAmendCreationDate(Date amendCreationDate) {
		this.amendCreationDate = amendCreationDate;
	}

	public Date getAmendEffectStartDate() {
		return amendEffectStartDate;
	}

	public void setAmendEffectStartDate(Date amendEffectStartDate) {
		this.amendEffectStartDate = amendEffectStartDate;
	}

	public Date getAmendEffectEndDate() {
		return amendEffectEndDate;
	}

	public void setAmendEffectEndDate(Date amendEffectEndDate) {
		this.amendEffectEndDate = amendEffectEndDate;
	}

	public Short getSummaryItemNum() {
		return summaryItemNum;
	}

	public void setSummaryItemNum(Short summaryItemNum) {
		this.summaryItemNum = summaryItemNum;
	}

	public Character getPoolingType() {
		return poolingType;
	}

	public void setPoolingType(Character poolingType) {
		this.poolingType = poolingType;
	}

	public Integer getPoolingPortNum() {
		return poolingPortNum;
	}

	public void setPoolingPortNum(Integer poolingPortNum) {
		this.poolingPortNum = poolingPortNum;
	}

	public Character getPoolingPortInd() {
		return poolingPortInd;
	}

	public void setPoolingPortInd(Character poolingPortInd) {
		this.poolingPortInd = poolingPortInd;
	}

	public Double getTotalSchQty() {
		return totalSchQty;
	}

	public void setTotalSchQty(Double totalSchQty) {
		this.totalSchQty = totalSchQty;
	}

	public Double getOpenQty() {
		return openQty;
	}

	public void setOpenQty(Double openQty) {
		this.openQty = openQty;
	}

	public String getOpenQtyUomCode() {
		return openQtyUomCode;
	}

	public void setOpenQtyUomCode(String openQtyUomCode) {
		this.openQtyUomCode = openQtyUomCode;
	}

	public Double getMtmPl() {
		return mtmPl;
	}

	public void setMtmPl(Double mtmPl) {
		this.mtmPl = mtmPl;
	}

	public Date getMtmPlAsOfDate() {
		return mtmPlAsOfDate;
	}

	public void setMtmPlAsOfDate(Date mtmPlAsOfDate) {
		this.mtmPlAsOfDate = mtmPlAsOfDate;
	}

	public Character getStripItemStatus() {
		return stripItemStatus;
	}

	public void setStripItemStatus(Character stripItemStatus) {
		this.stripItemStatus = stripItemStatus;
	}

	public Character getEstimateInd() {
		return estimateInd;
	}

	public void setEstimateInd(Character estimateInd) {
		this.estimateInd = estimateInd;
	}

	public Character getBillingType() {
		return billingType;
	}

	public void setBillingType(Character billingType) {
		this.billingType = billingType;
	}

	public Short getSchedStatus() {
		return schedStatus;
	}

	public void setSchedStatus(Short schedStatus) {
		this.schedStatus = schedStatus;
	}

	public Double getHedgeRate() {
		return hedgeRate;
	}

	public void setHedgeRate(Double hedgeRate) {
		this.hedgeRate = hedgeRate;
	}

	public Character getHedgeMultiDivInd() {
		return hedgeMultiDivInd;
	}

	public void setHedgeMultiDivInd(Character hedgeMultiDivInd) {
		this.hedgeMultiDivInd = hedgeMultiDivInd;
	}

	public Integer getRecapItemNum() {
		return recapItemNum;
	}

	public void setRecapItemNum(Integer recapItemNum) {
		this.recapItemNum = recapItemNum;
	}

	public Character getHedgePosInd() {
		return hedgePosInd;
	}

	public void setHedgePosInd(Character hedgePosInd) {
		this.hedgePosInd = hedgePosInd;
	}

	public Double getAddlCostSum() {
		return addlCostSum;
	}

	public void setAddlCostSum(Double addlCostSum) {
		this.addlCostSum = addlCostSum;
	}

	public Double getContrMtmPl() {
		return contrMtmPl;
	}

	public void setContrMtmPl(Double contrMtmPl) {
		this.contrMtmPl = contrMtmPl;
	}

	public Short getMaxAccumNum() {
		return maxAccumNum;
	}

	public void setMaxAccumNum(Short maxAccumNum) {
		this.maxAccumNum = maxAccumNum;
	}

	public Date getFormulaDeclarDate() {
		return formulaDeclarDate;
	}

	public void setFormulaDeclarDate(Date formulaDeclarDate) {
		this.formulaDeclarDate = formulaDeclarDate;
	}

	public String getPurchasingGroup() {
		return purchasingGroup;
	}

	public void setPurchasingGroup(String purchasingGroup) {
		this.purchasingGroup = purchasingGroup;
	}

	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public Integer getInternalParentTradeNum() {
		return internalParentTradeNum;
	}

	public void setInternalParentTradeNum(Integer internalParentTradeNum) {
		this.internalParentTradeNum = internalParentTradeNum;
	}

	public Short getInternalParentOrderNum() {
		return internalParentOrderNum;
	}

	public void setInternalParentOrderNum(Short internalParentOrderNum) {
		this.internalParentOrderNum = internalParentOrderNum;
	}

	public Short getInternalParentItemNum() {
		return internalParentItemNum;
	}

	public void setInternalParentItemNum(Short internalParentItemNum) {
		this.internalParentItemNum = internalParentItemNum;
	}

	public Character getTradeModifiedInd() {
		return tradeModifiedInd;
	}

	public void setTradeModifiedInd(Character tradeModifiedInd) {
		this.tradeModifiedInd = tradeModifiedInd;
	}

	public Character getItemConfirmInd() {
		return itemConfirmInd;
	}

	public void setItemConfirmInd(Character itemConfirmInd) {
		this.itemConfirmInd = itemConfirmInd;
	}

	public Character getActiveStatusInd() {
		return activeStatusInd;
	}

	public void setActiveStatusInd(Character activeStatusInd) {
		this.activeStatusInd = activeStatusInd;
	}

	public BigDecimal getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(BigDecimal marketValue) {
		this.marketValue = marketValue;
	}

	public boolean getIncludesExciseTaxInd() {
		return includesExciseTaxInd;
	}

	public void setIncludesExciseTaxInd(boolean includesExciseTaxInd) {
		this.includesExciseTaxInd = includesExciseTaxInd;
	}

	public boolean getIncludesFuelTaxInd() {
		return includesFuelTaxInd;
	}

	public void setIncludesFuelTaxInd(boolean includesFuelTaxInd) {
		this.includesFuelTaxInd = includesFuelTaxInd;
	}

	public Character getIsClearedInd() {
		return isClearedInd;
	}

	public void setIsClearedInd(Character isClearedInd) {
		this.isClearedInd = isClearedInd;
	}

	public BigDecimal getTotalCommittedQty() {
		return totalCommittedQty;
	}

	public void setTotalCommittedQty(BigDecimal totalCommittedQty) {
		this.totalCommittedQty = totalCommittedQty;
	}

	public Character getIsLcAssigned() {
		return isLcAssigned;
	}

	public void setIsLcAssigned(Character isLcAssigned) {
		this.isLcAssigned = isLcAssigned;
	}

	public Character getIsRcAssigned() {
		return isRcAssigned;
	}

	public void setIsRcAssigned(Character isRcAssigned) {
		this.isRcAssigned = isRcAssigned;
	}

	public Character getRinInd() {
		return rinInd;
	}

	public void setRinInd(Character rinInd) {
		this.rinInd = rinInd;
	}

	public String getB2bTradeItem() {
		return b2bTradeItem;
	}

	public void setB2bTradeItem(String b2bTradeItem) {
		this.b2bTradeItem = b2bTradeItem;
	}

	public Character getUseMktFormulaForPl() {
		return useMktFormulaForPl;
	}

	public void setUseMktFormulaForPl(Character useMktFormulaForPl) {
		this.useMktFormulaForPl = useMktFormulaForPl;
	}

	public String getSapOrderNum() {
		return sapOrderNum;
	}

	public void setSapOrderNum(String sapOrderNum) {
		this.sapOrderNum = sapOrderNum;
	}

	public Account getExchBrkrNum() {
		return exchBrkrNum;
	}

	public void setExchBrkrNum(Account exchBrkrNum) {
		this.exchBrkrNum = exchBrkrNum;
	}

	public Account getBookingCompNum() {
		return bookingCompNum;
	}

	public void setBookingCompNum(Account bookingCompNum) {
		this.bookingCompNum = bookingCompNum;
	}

	public Account getBrkrNum() {
		return brkrNum;
	}

	public void setBrkrNum(Account brkrNum) {
		this.brkrNum = brkrNum;
	}

	public Market getRiskMktCode() {
		return riskMktCode;
	}

	public void setRiskMktCode(Market riskMktCode) {
		this.riskMktCode = riskMktCode;
	}

	public Account getFinanceBankNum() {
		return financeBankNum;
	}

	public void setFinanceBankNum(Account financeBankNum) {
		this.financeBankNum = financeBankNum;
	}

	public Market getTitleMktCode() {
		return titleMktCode;
	}

	public void setTitleMktCode(Market titleMktCode) {
		this.titleMktCode = titleMktCode;
	}

	public Uom getPriceUomCode() {
		return priceUomCode;
	}

	public void setPriceUomCode(Uom priceUomCode) {
		this.priceUomCode = priceUomCode;
	}

	public Account getClrServiceNum() {
		return clrServiceNum;
	}

	public void setClrServiceNum(Account clrServiceNum) {
		this.clrServiceNum = clrServiceNum;
	}

	public AccountAgreement getAgreementNum() {
		return agreementNum;
	}

	public void setAgreementNum(AccountAgreement agreementNum) {
		this.agreementNum = agreementNum;
	}

	public AccountContact getAccountContact() {
		return accountContact;
	}

	public void setAccountContact(AccountContact accountContact) {
		this.accountContact = accountContact;
	}

	public Uom getBrkrCommUomCode() {
		return brkrCommUomCode;
	}

	public void setBrkrCommUomCode(Uom brkrCommUomCode) {
		this.brkrCommUomCode = brkrCommUomCode;
	}

	public Commodity getCmdtyCode() {
		return cmdtyCode;
	}

	public void setCmdtyCode(Commodity cmdtyCode) {
		this.cmdtyCode = cmdtyCode;
	}

	public IctsUser getFutTraderInit() {
		return futTraderInit;
	}

	public void setFutTraderInit(IctsUser futTraderInit) {
		this.futTraderInit = futTraderInit;
	}

	public Uom getPricedQtyUomCode() {
		return pricedQtyUomCode;
	}

	public void setPricedQtyUomCode(Uom pricedQtyUomCode) {
		this.pricedQtyUomCode = pricedQtyUomCode;
	}

	public Uom getSchQtyUomCode() {
		return schQtyUomCode;
	}

	public void setSchQtyUomCode(Uom schQtyUomCode) {
		this.schQtyUomCode = schQtyUomCode;
	}

	public Uom getCommittedQtyUomCode() {
		return committedQtyUomCode;
	}

	public void setCommittedQtyUomCode(Uom committedQtyUomCode) {
		this.committedQtyUomCode = committedQtyUomCode;
	}

	public Commodity getPriceCurrCode() {
		return priceCurrCode;
	}

	public void setPriceCurrCode(Commodity priceCurrCode) {
		this.priceCurrCode = priceCurrCode;
	}

	public ExceptionsAdditions getExcpAddnsCode() {
		return excpAddnsCode;
	}

	public void setExcpAddnsCode(ExceptionsAdditions excpAddnsCode) {
		this.excpAddnsCode = excpAddnsCode;
	}

	public Location getDischPortLocCode() {
		return dischPortLocCode;
	}

	public void setDischPortLocCode(Location dischPortLocCode) {
		this.dischPortLocCode = dischPortLocCode;
	}

	public Uom getContrQtyUomCode() {
		return contrQtyUomCode;
	}

	public void setContrQtyUomCode(Uom contrQtyUomCode) {
		this.contrQtyUomCode = contrQtyUomCode;
	}

	public Commodity getBrkrCommCurrCode() {
		return brkrCommCurrCode;
	}

	public void setBrkrCommCurrCode(Commodity brkrCommCurrCode) {
		this.brkrCommCurrCode = brkrCommCurrCode;
	}

	public Country getOriginCountryCode() {
		return originCountryCode;
	}

	public void setOriginCountryCode(Country originCountryCode) {
		this.originCountryCode = originCountryCode;
	}

	public Gtc getGtcCode() {
		return gtcCode;
	}

	public void setGtcCode(Gtc gtcCode) {
		this.gtcCode = gtcCode;
	}

	public Location getLoadPortLocCode() {
		return loadPortLocCode;
	}

	public void setLoadPortLocCode(Location loadPortLocCode) {
		this.loadPortLocCode = loadPortLocCode;
	}

	public Commodity getHedgeCurrCode() {
		return hedgeCurrCode;
	}

	public void setHedgeCurrCode(Commodity hedgeCurrCode) {
		this.hedgeCurrCode = hedgeCurrCode;
	}

	public Commodity getMtmPlCurrCode() {
		return mtmPlCurrCode;
	}

	public void setMtmPlCurrCode(Commodity mtmPlCurrCode) {
		this.mtmPlCurrCode = mtmPlCurrCode;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tradeItemPK != null ? tradeItemPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof TradeItem)) {
			return false;
		}
		TradeItem other = (TradeItem) object;
		if ((this.tradeItemPK == null && other.tradeItemPK != null) || (this.tradeItemPK != null && !this.tradeItemPK.equals(other.tradeItemPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TradeItem[ tradeItemPK=" + tradeItemPK + " ]";
	}

}
