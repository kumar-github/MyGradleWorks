package com.tc.app.exchangemonitor.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class HibernateReferenceDataFetchUtil
{
	public static List fetchDataFromDBForSQLNamedQuery(final String sqlNamedQueryName)
	{
		List data = null;
		Session session = null;

		try
		{
			session = HibernateUtil.beginTransaction();
			//session = IctsEOSessionFactory.getFactory().editingContext().getSession();
			final Query query = session.getNamedQuery(sqlNamedQueryName);
			//long sTime = System.currentTimeMillis();
			data = query.list();
			//long eTime = System.currentTimeMillis();

			//System.out.println(eTime - sTime);

			//sTime = System.currentTimeMillis();
			//final List x = session.createCriteria(ExternalTradeSource.class).list();
			//eTime = System.currentTimeMillis();
			//System.out.println(eTime - sTime);
			//x.stream().forEach(System.out::println);
		}
		catch(final Exception localException)
		{
			throw localException;
		}
		finally
		{
			if((session != null) && session.isOpen())
			{
				if((session.getTransaction() != null) && (session.getTransaction().getStatus() == TransactionStatus.ACTIVE))
				{
					session.getTransaction().commit();//This is mandatory - to avoid DB locking
				}
			}
		}
		//HibernateUtil.commitTransaction();
		//HibernateUtil.closeSession();

		return data;
	}

	public static List fetchDataFromDBForHibernateNamedQuery(final String hibernateNamedQueryName)
	{
		List data = null;
		Session session = null;

		try
		{
			session = HibernateUtil.beginTransaction();
			//session = IctsEOSessionFactory.getFactory().editingContext().getSession();
			final Query query = session.getNamedQuery(hibernateNamedQueryName);
			data = query.list();
		}
		catch(final Exception localException)
		{
			throw localException;
		}
		finally
		{
			if((session != null) && session.isOpen())
			{
				if((session.getTransaction() != null) && (session.getTransaction().getStatus() == TransactionStatus.ACTIVE))
				{
					session.getTransaction().commit();//This is mandatory - to avoid DB locking
				}
			}
		}
		//HibernateUtil.commitTransaction();
		//HibernateUtil.closeSession();

		return data;
	}

	public static Integer generateNewTransaction()
	{
		Integer transId = null;
		Session session = null;

		try
		{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			transId = (Integer) session.getNamedQuery("GenNewTransactionSP").uniqueResult();
		}
		catch(final Exception localException)
		{
			throw localException;
		}
		finally
		{
			if((session != null) && session.isOpen())
			{
				if((session.getTransaction() != null) && (session.getTransaction().getStatus() == TransactionStatus.ACTIVE))
				{
					session.getTransaction().commit();//This is mandatory - to avoid DB locking
					session.close();
				}
			}
		}
		//HibernateUtil.commitTransaction();
		//HibernateUtil.closeSession();

		return transId;
	}

	public static Integer generateNewNum()
	{
		Integer newNum = null;
		Session session = null;

		try
		{
			session = HibernateUtil.openSession();
			session.beginTransaction();
			newNum = (Integer) session.getNamedQuery("GetNewNumSP").uniqueResult();
		}
		catch(final Exception localException)
		{
			throw localException;
		}
		finally
		{
			if((session != null) && session.isOpen())
			{
				if((session.getTransaction() != null) && (session.getTransaction().getStatus() == TransactionStatus.ACTIVE))
				{
					session.getTransaction().commit();//This is mandatory - to avoid DB locking
					session.close();
				}
			}
		}
		//HibernateUtil.commitTransaction();
		//HibernateUtil.closeSession();

		return newNum;
	}
}