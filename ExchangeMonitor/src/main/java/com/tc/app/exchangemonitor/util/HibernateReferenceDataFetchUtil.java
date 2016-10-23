package com.tc.app.exchangemonitor.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class HibernateReferenceDataFetchUtil
{
	public static List fetchDataFromDBForSQLNamedQuery(String sqlNamedQueryName)
	{
		List data = null;
		Session session = null;

		try
		{
			session = HibernateUtil.beginTransaction();
			//session = IctsEOSessionFactory.getFactory().editingContext().getSession();
			Query query = session.getNamedQuery(sqlNamedQueryName);
			data = query.list();
		}
		catch(Exception localException)
		{
			throw localException;
		}
		finally
		{
			if(session!=null && session.isOpen())
			{
				if(session.getTransaction() != null && session.getTransaction().getStatus() == TransactionStatus.ACTIVE)
				{
					session.getTransaction().commit();//This is mandatory - to avoid DB locking
				}
			}
		}
		//HibernateUtil.commitTransaction();
		//HibernateUtil.closeSession();
		
		return data;
	}
	
	public static List fetchDataFromDBForHibernateNamedQuery(String hibernateNamedQueryName)
	{
		List data = null;
		Session session = null;

		try
		{
			session = HibernateUtil.beginTransaction();
			//session = IctsEOSessionFactory.getFactory().editingContext().getSession();
			Query query = session.getNamedQuery(hibernateNamedQueryName);
			data = query.list();
		}
		catch(Exception localException)
		{
			throw localException;
		}
		finally
		{
			if(session!=null && session.isOpen())
			{
				if(session.getTransaction() != null && session.getTransaction().getStatus() == TransactionStatus.ACTIVE)
				{
					session.getTransaction().commit();//This is mandatory - to avoid DB locking
				}
			}
		}
		//HibernateUtil.commitTransaction();
		//HibernateUtil.closeSession();

		return data;
	}
}