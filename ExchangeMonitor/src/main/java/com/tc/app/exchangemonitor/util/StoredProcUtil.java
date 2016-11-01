package com.tc.app.exchangemonitor.util;

import org.hibernate.Session;

public class StoredProcUtil
{
	public static Integer getNewNum(String keyName, Integer location)
	{
		Session newSession = HibernateUtil.beginTransaction();
		Integer key = null;
		try
		{
			key = (Integer) newSession.getNamedQuery("getNewNum").setParameter("keyName", keyName).setParameter("location", location).uniqueResult();
		}
		finally
		{
			newSession.getTransaction().commit();
		}
		return key;
	}
}