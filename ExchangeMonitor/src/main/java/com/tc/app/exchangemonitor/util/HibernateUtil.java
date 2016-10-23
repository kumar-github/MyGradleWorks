package com.tc.app.exchangemonitor.util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil
{
	public static String HIBERNATE_CONNECTION_URL_KEY = "hibernate.connection.url";
	//public static String HIBERNATE_CONNECTION_URL_VALUE="jdbc:jtds:sqlserver://HYDDB07:1460;databaseName=QA_30_trade";
	//public static String HIBERNATE_CONNECTION_URL_VALUE = LoginController.CONNECTION_URL;
	public static String HIBERNATE_CONNECTION_URL_VALUE = "jdbc:jtds:sqlserver://HYDDB07:1460;databaseName=QA_30_trade_sep12";
	private static final SessionFactory sessionFactory;

	static
	{
		try
		{
			//Create the SessionFactory from standard (hibernate.cfg.xml) config file.
			//1st method
			/*Configuration configuration = new Configuration();
			configuration.configure("/hibernate/hibernate.cfg.xml");
			configuration.setProperty(HIBERNATE_CONNECTION_URL, "jdbc:jtds:sqlserver://HYDDB07:1460;databaseName=QA_30_trade");
			configuration.configure();
			configuration.addAnnotatedClass(com.tc.app.exchangemonitor.controller.ExternalTradeStatus.class);
			sessionFactory = configuration.buildSessionFactory();*/

			//2nd method
			/*sessionFactory = new Configuration().configure("/hibernate/hibernate.cfg.xml").buildSessionFactory();*/

			//3rd method
			//final StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure("/hibernate/hibernate.cfg.xml").build();
			//final StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();						//this requires the .cfg/.properties file to be directly inside the resource folder
			//sessionFactory = new MetadataSources( standardServiceRegistry ).buildMetadata().buildSessionFactory();

			//4th method
			/*Configuration configuration = new Configuration();
			configuration.configure();
			final StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			sessionFactory = new MetadataSources( standardServiceRegistry ).buildMetadata().buildSessionFactory();*/

			/*
			java.util.Properties properties = new Properties();
			properties.load(new FileInputStream("myFile.properties"));
			Configuration cfg = new AnnotationConfiguration().configure();//this line reads hibernate.cfg.xml (for mapping classes)
			cfg.addProperties(properties);//this one reads properties file (for connections settings)
			sessionFactory = cfg.buildSessionFactory();*/

			/*
			Configuration cfg = new Configuration();
			cfg.addClass(ExternalComment.class);
			cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
			cfg.setProperty("hibernate.connection.datasource", "java:comp/env/jdbc/test");
			cfg.setProperty("hibernate.order_updates", "true");
			 */

			/*Configuration configuration = new Configuration();
			configuration.configure();
			StandardServiceRegistryBuilder reg_builder = new StandardServiceRegistryBuilder();
			reg_builder.applySettings(configuration.getProperties());
			StandardServiceRegistry reg = reg_builder.build();
			sessionFactory = configuration.buildSessionFactory(reg);
			System.out.println(sessionFactory);*/

			final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("/hibernate/hibernate.cfg.xml").loadProperties("/hibernate/hibernate.properties").applySetting(HIBERNATE_CONNECTION_URL_KEY, HIBERNATE_CONNECTION_URL_VALUE).build();
			//final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("/hibernate/hibernate.cfg.xml").loadProperties("/hibernate/hibernate.properties").applySetting(HIBERNATE_CONNECTION_URL_KEY, "jdbc:jtds:sqlserver://HYDDB07:1460;databaseName=QA_30_trade_sep12").build();
			sessionFactory = new MetadataSources(serviceRegistry ).buildMetadata().buildSessionFactory();
		}
		catch (Throwable ex)
		{
			// Log the exception. 
			System.err.println("Initial SessionFactory creation failed." + ex);
			//StandardServiceRegistryBuilder.destroy(serviceRegistry);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}


	public static Session beginTransaction()
	{
		Session hibernateSession = HibernateUtil.getCurrentSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}

	public static void commitTransaction()
	{
		HibernateUtil.getCurrentSession().getTransaction().commit();
	}

	public static void rollbackTransaction()
	{
		HibernateUtil.getCurrentSession().getTransaction().rollback();
	}

	/**
	 * Returns a session from the session context. If there is no session in the context it opens a session, stores it in the context and returns it.
	 * This factory is intended to be used with a hibernate.cfg.xml including the following property.
	 * <property name="hibernate.current_session_context_class">thread</property>
	 * This would return the current open session or if this does not exist, will create a new session
	 * @return session
	 */
	public static Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	/* Opens a session and will not bind it to a session context */
	public static Session openSession()
	{
		return sessionFactory.openSession();
	}

	public static void closeSession()
	{
		Session hibernateSession = HibernateUtil.getCurrentSession();
		if(hibernateSession != null)
			hibernateSession.close();
	}

	public static void closeSessionFactory()
	{
		if(sessionFactory != null)
			sessionFactory.close();
	}
}