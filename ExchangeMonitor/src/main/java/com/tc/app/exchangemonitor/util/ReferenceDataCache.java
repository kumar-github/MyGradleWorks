package com.tc.app.exchangemonitor.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.tc.app.exchangemonitor.entitybase.IExternalMappingEntity;
import com.tc.app.exchangemonitor.entitybase.IExternalTradeStateEntity;
import com.tc.app.exchangemonitor.entitybase.IExternalTradeStatusEntity;
import com.tc.app.exchangemonitor.model.ExternalTradeSource;

public class ReferenceDataCache
{
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
	}
	
	//public static ConcurrentMap<Integer, IExternalTradeSourceEntity> fetchExternalTradeSources()
	public static ConcurrentMap<Integer, ExternalTradeSource> fetchExternalTradeSources()
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
	
	/* Do we really need a map here? Think please...*/
	//private static ConcurrentMap<Integer, IExternalTradeSourceEntity> externalTradeSourceReferenceDataHashMap = null;
	private static ConcurrentMap<Integer, ExternalTradeSource> externalTradeSourceReferenceDataHashMap = null;
	@SuppressWarnings("unchecked")
	public static void loadExternalTradeSourceReferenceData()
	{
		if(externalTradeSourceReferenceDataHashMap == null)
		{
			//externalTradeSourceReferenceDataHashMap = new ConcurrentHashMap<Integer, IExternalTradeSourceEntity>();
			externalTradeSourceReferenceDataHashMap = new ConcurrentHashMap<Integer, ExternalTradeSource>();

			long startTime = System.currentTimeMillis();
			//List<IExternalTradeSourceEntity> externalTradeSourceEntityList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalTradeSource.findAllExternalTradeSources");
			List<ExternalTradeSource> externalTradeSourceEntityList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalTradeSource.findAllExternalTradeSources");
			long endTime = System.currentTimeMillis();
			System.out.println("It took " + (endTime - startTime) + " milli seconds to fetch " + externalTradeSourceEntityList.size() + " external trade sources.");

			if(externalTradeSourceEntityList != null)
			{
				for(ExternalTradeSource anExternalTradeSourceEntity : externalTradeSourceEntityList)
				{
					externalTradeSourceReferenceDataHashMap.put(anExternalTradeSourceEntity.getOid(), anExternalTradeSourceEntity);
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
			externalTradeStateReferenceDataHashMap = new ConcurrentHashMap<Integer, IExternalTradeStateEntity>();

			long startTime = System.currentTimeMillis();
			List<IExternalTradeStateEntity> externalTradeStateEntityList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalTradeState.findAllExternalTradeStates");
			long endTime = System.currentTimeMillis();
			System.out.println("It took " + (endTime - startTime) + " milli seconds to fetch " + externalTradeStateEntityList.size() + " external trade states.");

			if(externalTradeStateEntityList != null)
			{
				for(IExternalTradeStateEntity anExternalTradeStateEntity : externalTradeStateEntityList)
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
			externalTradeStatusReferenceDataHashMap = new ConcurrentHashMap<Integer, IExternalTradeStatusEntity>();

			long startTime = System.currentTimeMillis();
			List<IExternalTradeStatusEntity> externalTradeStatusEntityList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalTradeStatus.findAllExternalTradeStatuses");
			long endTime = System.currentTimeMillis();
			System.out.println("It took " + (endTime - startTime) + " milli seconds to fetch " + externalTradeStatusEntityList.size() + " external trade statuses.");

			if(externalTradeStatusEntityList != null)
			{
				for(IExternalTradeStatusEntity anExternalTradeStatusEntity : externalTradeStatusEntityList)
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
			externalTradeAccountReferenceDataHashMap = new ConcurrentHashMap<String, IExternalMappingEntity>();

			long startTime = System.currentTimeMillis();
			List<IExternalMappingEntity> externalTradeAccountEntityList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalMapping.findAllExternalTradeAccounts");
			long endTime = System.currentTimeMillis();
			System.out.println("It took " + (endTime - startTime) + " milli seconds to fetch " + externalTradeAccountEntityList.size() + " external trade accounts.");

			if(externalTradeAccountEntityList != null)
			{
				for(IExternalMappingEntity anExternalTradeAccountEntity : externalTradeAccountEntityList)
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
			externalMappingReferenceDataList = new ArrayList<IExternalMappingEntity>();
			
			long startTime = System.currentTimeMillis();
			//List<ExternalMapping> externalMappingList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalMapping.findAllExternalMappings");
			externalMappingReferenceDataList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalMapping.findAllExternalMappings");
			long endTime = System.currentTimeMillis();
			System.out.println("It took " + (endTime - startTime) + " milli seconds to fetch " + externalMappingReferenceDataList.size() + " external mappings.");
		}
	}
}