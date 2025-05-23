package com.persistance.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtilNA {
	
	private static SessionFactory sessionFactory;
	 private static StandardServiceRegistry  serviceRegistry;
	 
	 static
	 {
		 try
	     {
			 Configuration configuration = new Configuration().configure();

			 serviceRegistry = new StandardServiceRegistryBuilder()
					 .applySettings(configuration.configure("hibernate.cfg.xml").getProperties()).build();
			 sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	     }
		 catch (HibernateException Ex)
		 {
			 throw new ExceptionInInitializerError(Ex);
		 }
	 }

	 public static SessionFactory getSessionFactory()
	 {
		 return sessionFactory;
	 }
	    
}
