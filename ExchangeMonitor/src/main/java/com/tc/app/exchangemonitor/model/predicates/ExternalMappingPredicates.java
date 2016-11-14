
package com.tc.app.exchangemonitor.model.predicates;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.tc.app.exchangemonitor.entitybase.IExternalMappingEntity;

public class ExternalMappingPredicates
{
	/* An utility Predicate to check whether a given object is NULL. */
	public static final Predicate<IExternalMappingEntity> isNull = Objects::isNull;

	/* An utility Predicate to check whether a given object is NOT NULL. */
	public static final Predicate<IExternalMappingEntity> isNotNull = Objects::nonNull;

	public static final Predicate<IExternalMappingEntity> applyShowAllPredicate = (anExternalMapping) -> {
		return true;
	};

	public static final Predicate<IExternalMappingEntity> applyShowNonePredicate = (anExternalMapping) -> {
		return false;
	};

	/* A Predicate to check whether a given mapping's source is NYMEX. */
	public static final Predicate<IExternalMappingEntity> isNYMEXPredicate = (anExternalMapping) -> {
		//return anExternalMapping.getExternalTradeSourceOid().getOid() == 1;
		return anExternalMapping.getExternalTradeSourceOid().getExternalTradeSrcName().trim().equals("NYMEX");
	};

	/* A Predicate to check whether a given mapping's source is IPE. */
	public static final Predicate<IExternalMappingEntity> isIPEPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getExternalTradeSrcName().trim().equals("IPE");
	};

	/* A Predicate to check whether a given mapping's source is ICE. */
	public static final Predicate<IExternalMappingEntity> isICEPredicate = (anExternalMapping) -> {
		//return anExternalMapping.getExternalTradeSourceOid().getOid() == 3;
		return anExternalMapping.getExternalTradeSourceOid().getExternalTradeSrcName().trim().equals("ICE");
	};

	/* A Predicate to check whether a given mapping's source is EXCHANGE TOOLS. */
	public static final Predicate<IExternalMappingEntity> isExchangeToolsPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getExternalTradeSrcName().trim().equals("ExchangeTools");
	};

	/* A Predicate to check whether a given mapping's source is DASHBOARD. */
	public static final Predicate<IExternalMappingEntity> isDashboardPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getExternalTradeSrcName().trim().equals("Dashboard");
	};

	/* A Predicate to check whether a given mapping's source is EXCEL. */
	public static final Predicate<IExternalMappingEntity> isExcelPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getExternalTradeSrcName().trim().equals("Excel");
	};

	/* A Predicate to check whether a given mapping's source is DME. */
	public static final Predicate<IExternalMappingEntity> isDMEPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getExternalTradeSrcName().trim().equals("DME");
	};

	/* A Predicate to check whether a given mapping's source is ECONFIRM. */
	public static final Predicate<IExternalMappingEntity> isECONFIRMPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getExternalTradeSrcName().trim().equals("ECONFIRM");
	};

	/* A Predicate to check whether a given mapping's source is CEE. */
	public static final Predicate<IExternalMappingEntity> isCEEPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getExternalTradeSrcName().trim().equals("CEE");
	};

	/* A Predicate to check whether a given mapping's source is OLYMPUS. */
	public static final Predicate<IExternalMappingEntity> isOlympusPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getExternalTradeSrcName().trim().equals("Olympus");
	};

	/* A Predicate to check whether a given mapping's source is CBT. */
	public static final Predicate<IExternalMappingEntity> isCBTPredicate = (anExternalMapping) -> {
		return anExternalMapping.getExternalTradeSourceOid().getExternalTradeSrcName().trim().equals("CBT");
	};

	/* A Predicate to tell whether the given mapping is a TRADER. */
	public static final Predicate<IExternalMappingEntity> isTraderPredicate = (anExternalMapping) -> {
		return anExternalMapping.getMappingType().equals('T');
	};

	/* A Predicate to tell whether the given mapping is a BROKER. */
	public static final Predicate<IExternalMappingEntity> isBrokerPredicate = (anExternalMapping) -> {
		return anExternalMapping.getMappingType().equals('B');
	};

	/* A Predicate to tell whether the given mapping is a COMPANY. */
	public static final Predicate<IExternalMappingEntity> isCompanyPredicate = (anExternalMapping) -> {
		return anExternalMapping.getMappingType().equals('C');
	};

	/* A Predicate to tell whether the given mapping is a CURRENCY. */
	public static final Predicate<IExternalMappingEntity> isCurrencyPredicate = (anExternalMapping) -> {
		return anExternalMapping.getMappingType().equals('U');
	};

	/* A Predicate to tell whether the given mapping is a PORTFOLIO. */
	public static final Predicate<IExternalMappingEntity> isPortfolioPredicate = (anExternalMapping) -> {
		return anExternalMapping.getMappingType().equals('P');
	};

	/* A Predicate to tell whether the given mapping is a TEMPLATE TRADE. */
	public static final Predicate<IExternalMappingEntity> isTemplateTradePredicate = (anExternalMapping) -> {
		return anExternalMapping.getMappingType().equals('S');
	};

	/* A Predicate to tell whether the given mapping is an ACCOUNT. */
	public static final Predicate<IExternalMappingEntity> isAccountPredicate = (anExternalMapping) -> {
		return anExternalMapping.getMappingType().equals('K');
	};

	/* A Predicate to tell whether the given mapping is an UOM CONVERSION. */
	public static final Predicate<IExternalMappingEntity> isUomConversionPredicate = (anExternalMapping) -> {
		return anExternalMapping.getMappingType().equals('Q');
	};

	/* A Predicate to tell whether the given mapping is a TRADING PERIOD. */
	public static final Predicate<IExternalMappingEntity> isTradingPeriodPredicate = (anExternalMapping) -> {
		return anExternalMapping.getMappingType().equals('O');
	};

	/* A Predicate to tell whether the given mapping is a BOOKING COMPANY. */
	public static final Predicate<IExternalMappingEntity> isBookingCompanyPredicate = (anExternalMapping) -> {
		return anExternalMapping.getMappingType().equals('C') && (anExternalMapping.getExternalValue2() != null) && anExternalMapping.getExternalValue2().equals("BOOKING COMPANY");
	};

	//NYMEX
	/* Few utility predicates to test NYMEX related mappings. */
	public static final Predicate<IExternalMappingEntity> isNymexTraderPredicate = isNYMEXPredicate.and(isTraderPredicate);
	public static final Predicate<IExternalMappingEntity> isNymexBrokerPredicate = isNYMEXPredicate.and(isBrokerPredicate);
	public static final Predicate<IExternalMappingEntity> isNymexCompanyPredicate = isNYMEXPredicate.and(isCompanyPredicate);
	public static final Predicate<IExternalMappingEntity> isNymexCurrencyPredicate = isNYMEXPredicate.and(isCurrencyPredicate);
	public static final Predicate<IExternalMappingEntity> isNymexPortfolioPredicate = isNYMEXPredicate.and(isPortfolioPredicate);
	public static final Predicate<IExternalMappingEntity> isNymexTemplateTradePredicate = isNYMEXPredicate.and(isTemplateTradePredicate);
	public static final Predicate<IExternalMappingEntity> isNymexAccountPredicate = isNYMEXPredicate.and(isAccountPredicate);
	public static final Predicate<IExternalMappingEntity> isNymexUomConversionPredicate = isNYMEXPredicate.and(isUomConversionPredicate);
	public static final Predicate<IExternalMappingEntity> isNymexTradingPeriodPredicate = isNYMEXPredicate.and(isTradingPeriodPredicate);
	public static final Predicate<IExternalMappingEntity> isNymexBookingCompanyPredicate = isNYMEXPredicate.and(isBookingCompanyPredicate);

	/* A Predicate to tell whether the given mapping is an IPE BOOKING COMPANY. */
	public static final Predicate<IExternalMappingEntity> isIpeBookingCompanyPredicate = isIPEPredicate.and(isBookingCompanyPredicate);

	//ICE
	/* Few utility predicates to test ICE related mappings. */
	public static final Predicate<IExternalMappingEntity> isIceTraderPredicate = isICEPredicate.and(isTraderPredicate);
	public static final Predicate<IExternalMappingEntity> isIceBrokerPredicate = isICEPredicate.and(isBrokerPredicate);
	public static final Predicate<IExternalMappingEntity> isIceCompanyPredicate = isICEPredicate.and(isCompanyPredicate);
	public static final Predicate<IExternalMappingEntity> isIceCurrencyPredicate = isICEPredicate.and(isCurrencyPredicate);
	public static final Predicate<IExternalMappingEntity> isIcePortfolioPredicate = isICEPredicate.and(isPortfolioPredicate);
	public static final Predicate<IExternalMappingEntity> isIceTemplateTradePredicate = isICEPredicate.and(isTemplateTradePredicate);
	public static final Predicate<IExternalMappingEntity> isIceAccountPredicate = isICEPredicate.and(isAccountPredicate);
	public static final Predicate<IExternalMappingEntity> isIceUomConversionPredicate = isICEPredicate.and(isUomConversionPredicate);
	public static final Predicate<IExternalMappingEntity> isIceTradingPeriodPredicate = isICEPredicate.and(isTradingPeriodPredicate);
	public static final Predicate<IExternalMappingEntity> isIceBookingCompanyPredicate = isICEPredicate.and(isBookingCompanyPredicate);

	/* A Predicate to tell whether the given mapping is an EXCHANGE TOOLS BOOKING COMPANY. */
	public static final Predicate<IExternalMappingEntity> isExchangeToolsBookingCompanyPredicate = isExchangeToolsPredicate.and(isBookingCompanyPredicate);

	/* A Predicate to tell whether the given mapping is a DASHBOARD BOOKING COMPANY. */
	public static final Predicate<IExternalMappingEntity> isDashboardBookingCompanyPredicate = isDashboardPredicate.and(isBookingCompanyPredicate);

	/* A Predicate to tell whether the given mapping is a EXCEL BOOKING COMPANY. */
	public static final Predicate<IExternalMappingEntity> isExcelBookingCompanyPredicate = isExcelPredicate.and(isBookingCompanyPredicate);

	/* A Predicate to tell whether the given mapping is a DME BOOKING COMPANY. */
	public static final Predicate<IExternalMappingEntity> isDMEBookingCompanyPredicate = isDMEPredicate.and(isBookingCompanyPredicate);

	/* A Predicate to tell whether the given mapping is a ECONFIRM BOOKING COMPANY. */
	public static final Predicate<IExternalMappingEntity> isECONFIRMBookingCompanyPredicate = isECONFIRMPredicate.and(isBookingCompanyPredicate);

	/* A Predicate to tell whether the given mapping is a CEE BOOKING COMPANY. */
	public static final Predicate<IExternalMappingEntity> isCEEBookingCompanyPredicate = isCEEPredicate.and(isBookingCompanyPredicate);

	/* A Predicate to tell whether the given mapping is a OLYMPUS BOOKING COMPANY. */
	public static final Predicate<IExternalMappingEntity> isOlympusBookingCompanyPredicate = isOlympusPredicate.and(isBookingCompanyPredicate);

	/* A Predicate to tell whether the given mapping is a CBT BOOKING COMPANY. */
	public static final Predicate<IExternalMappingEntity> isCBTBookingCompanyPredicate = isCBTPredicate.and(isBookingCompanyPredicate);

	/* A utility method to filter a given list of mappings with the given predicate. */
	public static final List<IExternalMappingEntity> filterExternalMappings(final List<IExternalMappingEntity> externalMappings, final Predicate<IExternalMappingEntity> predicate)
	{
		return externalMappings.stream().filter(predicate).collect(Collectors.<IExternalMappingEntity>toList());
	}

	/* A utility method to filter a given list of mappings with the given predicate. */
	public static final List<IExternalMappingEntity> filter(final List<IExternalMappingEntity> externalMappings, final Predicate<IExternalMappingEntity> predicate)
	{
		return externalMappings.stream().filter(predicate).collect(Collectors.<IExternalMappingEntity>toList());
	}
	
	/*
	 Predicate<BBTeam> nonNullPredicate = Objects::nonNull;
	 Predicate<BBTeam> nameNotNull = p -> p.teamName != null;
	 Predicate<BBTeam> teamWIPredicate = p -> p.teamName.equals("Wisconsin");
	 Predicate<BBTeam> fullPredicate = nonNullPredicate.and(nameNotNull).and(teamWIPredicate);
	 List<BBTeam> teams2 = teams.stream().filter(fullPredicate).collect(Collectors.toList());
	 */
}