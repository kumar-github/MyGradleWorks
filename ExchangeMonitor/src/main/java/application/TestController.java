package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.tc.app.exchangemonitor.controller.DummySettlePrice;
import com.tc.app.exchangemonitor.model.Commodity;
import com.tc.app.exchangemonitor.model.IctsUser;
import com.tc.app.exchangemonitor.model.Uom;
import com.tc.app.exchangemonitor.util.HibernateReferenceDataFetchUtil;
import com.tc.app.exchangemonitor.util.HibernateUtil;

import control.SearchableTextField;
import control.SearchableTextField.AutoCompletionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class TestController implements Initializable
{
	@FXML private TextField txt;
	@FXML private SearchableTextField<IctsUser> traderSearchableTextField;
	@FXML private SearchableTextField<Uom> uomSearchableTextField;
	@FXML private SearchableTextField<Commodity> commoditySearchableTextField;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		//String s = "SELECT til.trade_num, til.order_num, til.item_num, til.item_fill_num, til.fill_date, ti.cmdty_code, ti.risk_mkt_code, commkt.commkt_key, ti.trading_prd, ti.p_s_ind, til.fill_qty, til.fill_price, usr.user_first_name, usr.user_last_name, ti.real_port_num, port.port_full_name, tif.price_source_code, toe.order_instr_code, toe.order_price, ti.cmnt_num, til.external_trade_num FROM dbo.trade AS trd INNER JOIN dbo.trade_item_fill AS til ON trd.trade_num = til.trade_num INNER JOIN dbo.trade_order AS trdord ON til.trade_num = trdord.trade_num AND til.order_num = trdord.order_num INNER JOIN dbo.trade_item AS ti ON til.trade_num = ti.trade_num AND til.order_num = ti.order_num AND til.item_num = ti.item_num INNER JOIN dbo.trade_item_fut AS tif ON til.trade_num = tif.trade_num AND til.order_num = tif.order_num AND til.item_num = tif.item_num INNER JOIN dbo.trade_order_on_exch AS toe ON til.trade_num = toe.trade_num AND til.order_num = toe.order_num INNER JOIN dbo.comment AS cmnt ON ti.cmnt_num = cmnt.cmnt_num INNER JOIN dbo.commodity_market AS commkt ON ti.cmdty_code = commkt.cmdty_code AND toe.order_instr_code = commkt.mkt_code LEFT OUTER JOIN dbo.icts_user AS usr ON trd.trader_init = usr.user_init LEFT OUTER JOIN dbo.portfolio AS port ON ti.real_port_num = port.port_num WHERE (trdord.strip_summary_ind <> 'Y') AND (trd.conclusion_type = 'C') AND  (RTRIM(LTRIM(UPPER(cmnt.tiny_cmnt))) = 'NOT PRICED' OR RTRIM(LTRIM(UPPER(cmnt.tiny_cmnt))) = 'NOTPRICED') AND til.fill_date >= '2011-09-05 00:00:00.000' AND til.fill_date <= '2015-01-09 00:00:00.000' ORDER BY til.fill_date DESC";
		String s = "SELECT tif.trade_num as tradeNum, tif.order_num as orderNum, tif.item_num as itemNum, tif.item_fill_num as itemFillNum, tif.fill_date as fillDate, ti.cmdty_code as cmdtyCode, ti.risk_mkt_code as riskMktCode, ti.trading_prd as tradingPrd, ti.p_s_ind as psInd, tif.fill_qty as fillQty, tif.fill_price as fillPrice, iu.user_first_name as firstName, iu.user_last_name as lastName, ti.real_port_num as realPortNum, p.port_full_name as portFullName, tifut.price_source_code as priceSourceCode, toexch.order_instr_code as orderInstrCode, toexch.order_price as orderPrice, ti.cmnt_num as cmntNum, tif.external_trade_num as externalTradeNum, cm.commkt_key as commktKey FROM trade_item_fill tif INNER JOIN trade_item ti ON tif.trade_num = ti.trade_num AND tif.order_num = ti.order_num AND tif.item_num = ti.item_num INNER JOIN trade t ON tif.trade_num = t.trade_num INNER JOIN trade_order tor ON tif.trade_num = tor.trade_num AND tif.order_num = tor.order_num INNER JOIN trade_item_fut tifut ON tif.trade_num = tifut.trade_num AND tif.order_num = tifut.order_num AND tif.item_num = tifut.item_num INNER JOIN trade_order_on_exch toexch ON tif.trade_num = toexch.trade_num AND tif.order_num = toexch.order_num INNER JOIN comment c ON ti.cmnt_num = c.cmnt_num INNER JOIN commodity_market cm ON ti.cmdty_code = cm.cmdty_code AND toexch.order_instr_code = cm.mkt_code left outer join icts_user iu ON iu.user_init = t.trader_init left outer join portfolio p ON p.port_num = ti.real_port_num where (tor.strip_summary_ind != 'Y' AND t.conclusion_type = 'C') AND (tif.fill_date >= '2011-09-05 00:00:00.000' AND tif.fill_date <= '2015-01-09 00:00:00.000') AND (UPPER(RTRIM(LTRIM(c.tiny_cmnt))) = 'NOT PRICED' OR UPPER(RTRIM(LTRIM(c.tiny_cmnt))) = 'NOTPRICED') ORDER BY tif.fill_date DESC";
		
		SQLQuery sqlQuery = null;
		Session session = HibernateUtil.beginTransaction();
		sqlQuery = session.createSQLQuery(s);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(com.tc.app.exchangemonitor.controller.DummySettlePrice.class));
		List<DummySettlePrice> x = null;
		try
		{
			x = sqlQuery.list();
		}
		catch(Exception exception)
		{
			System.out.println(exception);
			System.exit(0);
		}
		
		x.stream().forEach(aSettlePrice -> System.out.println(aSettlePrice.getTradeNum()));
		
		//List<DummySettlePrice> x = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("FetchSettlePrices");
		List<DummySettlePrice> y = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("FetchSettlePrices");
		System.out.println("traderSearchableTextField : " + traderSearchableTextField);
		System.out.println("uomSearchableTextField : " + uomSearchableTextField);
		System.out.println("commoditySearchableTextField : " + commoditySearchableTextField);
		
		traderSearchableTextField.setOnAutoCompleted(new EventHandler<SearchableTextField.AutoCompletionEvent<IctsUser>>() {
			@Override
			public void handle(AutoCompletionEvent<IctsUser> event)
			{
				System.out.println("just to test....");
			}
		});
	}
}