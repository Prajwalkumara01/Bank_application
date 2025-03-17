package com.bank;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
	private static final SessionFactory sessionFactory=buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration config=new Configuration();
			return config.configure("hibernate.cfg.xml").addAnnotatedClass(UserAccount.class).buildSessionFactory();
		}
		catch(Exception e)
		{
			throw new ExceptionInInitializerError(e);
		}
		
	}  
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
