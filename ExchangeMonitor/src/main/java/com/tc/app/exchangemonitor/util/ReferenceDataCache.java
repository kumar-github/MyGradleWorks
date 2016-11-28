package com.tc.app.exchangemonitor.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tc.app.exchangemonitor.entitybase.IExternalMappingEntity;
import com.tc.app.exchangemonitor.entitybase.IExternalTradeStateEntity;
import com.tc.app.exchangemonitor.entitybase.IExternalTradeStatusEntity;
import com.tc.app.exchangemonitor.model.Account;
import com.tc.app.exchangemonitor.model.Commodity;
import com.tc.app.exchangemonitor.model.Country;
import com.tc.app.exchangemonitor.model.ExternalTradeSource;
import com.tc.app.exchangemonitor.model.IctsUser;
import com.tc.app.exchangemonitor.model.Portfolio;

public class ReferenceDataCache
{
	private static final Logger LOGGER = LogManager.getLogger(ReferenceDataCache.class);

	public ReferenceDataCache()
	{
	}

	public static void loadAllReferenceData()
	{
		loadExternalTradeSourceReferenceData();
		loadExternalTradeStateReferenceData();
		loadExternalTradeStatusReferenceData();
		loadExternalTradeAccountReferenceData();
		loadExternalMappingReferenceData();
		loadAccountsReferenceData();
		loadIctsUsersReferenceData();
		loadCountriesReferenceData();
		loadCommoditiesReferenceData();
		loadPortfoliosReferenceData();
	}

	/* Do we really need a map here? Think please...*/
	//private static ConcurrentMap<Integer, IExternalTradeSourceEntity> externalTradeSourceReferenceDataHashMap = null;
	private static ConcurrentMap<String, ExternalTradeSource> externalTradeSourceReferenceDataHashMap = null;
	@SuppressWarnings("unchecked")
	public static void loadExternalTradeSourceReferenceData()
	{
		if(externalTradeSourceReferenceDataHashMap == null)
		{
			//externalTradeSourceReferenceDataHashMap = new ConcurrentHashMap<Integer, IExternalTradeSourceEntity>();
			externalTradeSourceReferenceDataHashMap = new ConcurrentHashMap<>();

			final long startTime = System.currentTimeMillis();
			//List<IExternalTradeSourceEntity> externalTradeSourceEntityList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalTradeSource.findAllExternalTradeSources");
			final List<ExternalTradeSource> externalTradeSourceEntityList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalTradeSource.findAllExternalTradeSources");
			final long endTime = System.currentTimeMillis();
			LOGGER.info("It took " + (endTime - startTime) + " milli seconds to fetch " + externalTradeSourceEntityList.size() + " external trade sources.");

			if(externalTradeSourceEntityList != null)
			{
				for(final ExternalTradeSource anExternalTradeSourceEntity : externalTradeSourceEntityList)
				{
					//externalTradeSourceReferenceDataHashMap.put(anExternalTradeSourceEntity.getOid(), anExternalTradeSourceEntity);
					externalTradeSourceReferenceDataHashMap.put(anExternalTradeSourceEntity.getExternalTradeSrcName(), anExternalTradeSourceEntity);
				}
			}
		}
	}

	private static ConcurrentMap<Integer, IExternalTradeStateEntity> externalTradeStateReferenceDataHashMap = null;
	@SuppressWarnings("unchecked")
	public static void loadExternalTradeStateReferenceData()
	{
		if(externalTradeStateReferenceDataHashMap == null)
		{
			externalTradeStateReferenceDataHashMap = new ConcurrentHashMap<>();

			final long startTime = System.currentTimeMillis();
			final List<IExternalTradeStateEntity> externalTradeStateEntityList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalTradeState.findAllExternalTradeStates");
			final long endTime = System.currentTimeMillis();
			LOGGER.info("It took " + (endTime - startTime) + " milli seconds to fetch " + externalTradeStateEntityList.size() + " external trade states.");

			if(externalTradeStateEntityList != null)
			{
				for(final IExternalTradeStateEntity anExternalTradeStateEntity : externalTradeStateEntityList)
				{
					externalTradeStateReferenceDataHashMap.put(anExternalTradeStateEntity.getOid(), anExternalTradeStateEntity);
				}
			}
		}
	}

	private static ConcurrentMap<Integer, IExternalTradeStatusEntity> externalTradeStatusReferenceDataHashMap = null;
	@SuppressWarnings("unchecked")
	public static void loadExternalTradeStatusReferenceData()
	{
		if(externalTradeStatusReferenceDataHashMap == null)
		{
			externalTradeStatusReferenceDataHashMap = new ConcurrentHashMap<>();

			final long startTime = System.currentTimeMillis();
			final List<IExternalTradeStatusEntity> externalTradeStatusEntityList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalTradeStatus.findAllExternalTradeStatuses");
			final long endTime = System.currentTimeMillis();
			LOGGER.info("It took " + (endTime - startTime) + " milli seconds to fetch " + externalTradeStatusEntityList.size() + " external trade statuses.");

			if(externalTradeStatusEntityList != null)
			{
				for(final IExternalTradeStatusEntity anExternalTradeStatusEntity : externalTradeStatusEntityList)
				{
					externalTradeStatusReferenceDataHashMap.put(anExternalTradeStatusEntity.getOid(), anExternalTradeStatusEntity);
				}
			}
		}
	}

	private static ConcurrentMap<String, IExternalMappingEntity> externalTradeAccountReferenceDataHashMap = null;
	@SuppressWarnings("unchecked")
	public static void loadExternalTradeAccountReferenceData()
	{
		if(externalTradeAccountReferenceDataHashMap == null)
		{
			externalTradeAccountReferenceDataHashMap = new ConcurrentHashMap<>();

			final long startTime = System.currentTimeMillis();
			final List<IExternalMappingEntity> externalTradeAccountEntityList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalMapping.findAllExternalTradeAccounts");
			final long endTime = System.currentTimeMillis();
			LOGGER.info("It took " + (endTime - startTime) + " milli seconds to fetch " + externalTradeAccountEntityList.size() + " external trade accounts.");

			if(externalTradeAccountEntityList != null)
			{
				for(final IExternalMappingEntity anExternalTradeAccountEntity : externalTradeAccountEntityList)
				{
					//externalTradeAccountReferenceDataHashMap.put(anExternalTradeStatus.getOid(), anExternalTradeStatus);
					externalTradeAccountReferenceDataHashMap.put(anExternalTradeAccountEntity.getExternalValue1(), anExternalTradeAccountEntity);
				}
			}
		}
	}

	//private static ConcurrentMap<String, ExternalMapping> externalMappingReferenceDataHashMap = null;
	private static List<IExternalMappingEntity> externalMappingReferenceDataList = null;
	@SuppressWarnings("unchecked")
	public static void loadExternalMappingReferenceData()
	{
		if(externalMappingReferenceDataList == null)
		{
			externalMappingReferenceDataList = new ArrayList<>();

			final long startTime = System.currentTimeMillis();
			//List<ExternalMapping> externalMappingList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalMapping.findAllExternalMappings");
			//externalMappingReferenceDataList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalMapping.findAllExternalMappings");
			externalMappingReferenceDataList = HibernateReferenceDataFetchUtil.fetchDataFromDBForHibernateNamedQuery("ExternalMapping.findAllExternalMappings");
			final long endTime = System.currentTimeMillis();
			LOGGER.info("It took " + (endTime - startTime) + " milli seconds to fetch " + externalMappingReferenceDataList.size() + " external mappings.");
		}
	}

	private static ConcurrentMap<Integer, Account> accountsReferenceDataHashMap = null;
	@SuppressWarnings("unchecked")
	public static void loadAccountsReferenceData()
	{
		if(accountsReferenceDataHashMap == null)
		{
			accountsReferenceDataHashMap = new ConcurrentHashMap<>();

			final long startTime = System.currentTimeMillis();
			//final List<Account> accountList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("FetchAllAccounts");
			final List<Account> accountList = HibernateReferenceDataFetchUtil.fetchDataFromDBForHibernateNamedQuery("FetchAllActiveAccountsUsingFetchJoin");
			final long endTime = System.currentTimeMillis();
			LOGGER.info("It took " + (endTime - startTime) + " milli seconds to fetch " + accountList.size() + " accounts.");

			if(accountList != null)
			{
				for(final Account anAccount : accountList)
				{
					accountsReferenceDataHashMap.put(anAccount.getAcctNum(), anAccount);
				}
			}
		}
	}

	private static ConcurrentMap<String, IctsUser> ictsUsersReferenceDataHashMap = null;
	@SuppressWarnings("unchecked")
	public static void loadIctsUsersReferenceData()
	{
		if(ictsUsersReferenceDataHashMap == null)
		{
			ictsUsersReferenceDataHashMap = new ConcurrentHashMap<>();

			final long startTime = System.currentTimeMillis();
			final List<IctsUser> ictsUsersList = HibernateReferenceDataFetchUtil.fetchDataFromDBForHibernateNamedQuery("FetchAllActiveIctsUsers");
			final long endTime = System.currentTimeMillis();
			LOGGER.info("It took " + (endTime - startTime) + " milli seconds to fetch " + ictsUsersList.size() + " icts users.");

			if(ictsUsersList != null)
			{
				for(final IctsUser anIctsUser : ictsUsersList)
				{
					ictsUsersReferenceDataHashMap.put(anIctsUser.getUserInit(), anIctsUser);
				}
			}
		}
	}

	private static ConcurrentMap<String, Country> countriesReferenceDataHashMap = null;
	@SuppressWarnings("unchecked")
	public static void loadCountriesReferenceData()
	{
		if(countriesReferenceDataHashMap == null)
		{
			countriesReferenceDataHashMap = new ConcurrentHashMap<>();

			final long startTime = System.currentTimeMillis();
			final List<Country> countriesList = HibernateReferenceDataFetchUtil.fetchDataFromDBForHibernateNamedQuery("FetchAllActiveCountries");
			final long endTime = System.currentTimeMillis();
			LOGGER.info("It took " + (endTime - startTime) + " milli seconds to fetch " + countriesList.size() + " countries.");

			if(countriesList != null)
			{
				for(final Country aCountry : countriesList)
				{
					countriesReferenceDataHashMap.put(aCountry.getCountryCode(), aCountry);
				}
			}
		}
	}

	private static ConcurrentMap<String, Commodity> commoditiesReferenceDataHashMap = null;
	@SuppressWarnings("unchecked")
	public static void loadCommoditiesReferenceData()
	{
		if(commoditiesReferenceDataHashMap == null)
		{
			commoditiesReferenceDataHashMap = new ConcurrentHashMap<>();

			final long startTime = System.currentTimeMillis();
			final List<Commodity> commoditiesList = HibernateReferenceDataFetchUtil.fetchDataFromDBForHibernateNamedQuery("FetchAllActiveCommodities");
			final long endTime = System.currentTimeMillis();
			LOGGER.info("It took " + (endTime - startTime) + " milli seconds to fetch " + commoditiesList.size() + " commodities.");

			if(commoditiesList != null)
			{
				for(final Commodity aCommodity : commoditiesList)
				{
					commoditiesReferenceDataHashMap.put(aCommodity.getCmdtyCode(), aCommodity);
				}
			}
		}
	}

	private static ConcurrentMap<Integer, Portfolio> portfoliosReferenceDataHashMap = null;

	@SuppressWarnings("unchecked")
	public static void loadPortfoliosReferenceData()
	{
		if(portfoliosReferenceDataHashMap == null)
		{
			portfoliosReferenceDataHashMap = new ConcurrentHashMap<>();

			final long startTime = System.currentTimeMillis();
			final List<Portfolio> portfoliosList = HibernateReferenceDataFetchUtil.fetchDataFromDBForHibernateNamedQuery("FetchAllPortfolios");
			final long endTime = System.currentTimeMillis();
			LOGGER.info("It took " + (endTime - startTime) + " milli seconds to fetch " + portfoliosList.size() + " portfolios.");

			if(portfoliosList != null)
			{
				for(final Portfolio aPortfolio : portfoliosList)
				{
					portfoliosReferenceDataHashMap.put(aPortfolio.getPortNum(), aPortfolio);
				}
			}
		}
	}

	//public static ConcurrentMap<Integer, IExternalTradeSourceEntity> fetchExternalTradeSources()
	public static ConcurrentMap<String, ExternalTradeSource> fetchExternalTradeSources()
	{
		if(externalTradeSourceReferenceDataHashMap == null)
		{
			loadExternalTradeSourceReferenceData();
		}
		return externalTradeSourceReferenceDataHashMap;
	}

	public static ConcurrentMap<Integer, IExternalTradeStateEntity> fetchExternalTradeStates()
	{
		if(externalTradeStateReferenceDataHashMap == null)
		{
			loadExternalTradeStateReferenceData();
		}
		return externalTradeStateReferenceDataHashMap;
	}

	public static ConcurrentMap<Integer, IExternalTradeStatusEntity> fetchExternalTradeStatuses()
	{
		if(externalTradeStatusReferenceDataHashMap == null)
		{
			loadExternalTradeStatusReferenceData();
		}
		return externalTradeStatusReferenceDataHashMap;
	}

	public static ConcurrentMap<String, IExternalMappingEntity> fetchExternalTradeAccounts()
	{
		if(externalTradeAccountReferenceDataHashMap == null)
		{
			loadExternalTradeAccountReferenceData();
		}
		return externalTradeAccountReferenceDataHashMap;
	}

	public static List<IExternalMappingEntity> fetchExternalMappings()
	{
		if(externalMappingReferenceDataList == null)
		{
			loadExternalMappingReferenceData();
		}
		return externalMappingReferenceDataList;
	}

	public static ConcurrentMap<Integer, Account> fetchAllActiveAccounts()
	{
		if(accountsReferenceDataHashMap == null)
		{
			loadAccountsReferenceData();
		}
		return accountsReferenceDataHashMap;
	}

	public static ConcurrentMap<String, IctsUser> fetchAllActiveIctsUsers()
	{
		if(ictsUsersReferenceDataHashMap == null)
		{
			loadIctsUsersReferenceData();
		}
		return ictsUsersReferenceDataHashMap;
	}

	public static ConcurrentMap<String, Country> fetchAllActiveCountries()
	{
		if(countriesReferenceDataHashMap == null)
		{
			loadCountriesReferenceData();
		}
		return countriesReferenceDataHashMap;
	}

	public static ConcurrentMap<String, Commodity> fetchAllActiveCommodities()
	{
		if(commoditiesReferenceDataHashMap == null)
		{
			loadCommoditiesReferenceData();
		}
		return commoditiesReferenceDataHashMap;
	}

	public static ConcurrentMap<Integer, Portfolio> fetchAllPortfolios()
	{
		if(portfoliosReferenceDataHashMap == null)
		{
			loadPortfoliosReferenceData();
		}
		return portfoliosReferenceDataHashMap;
	}
}