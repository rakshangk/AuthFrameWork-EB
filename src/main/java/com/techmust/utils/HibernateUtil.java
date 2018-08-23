package com.techmust.utils;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

public class HibernateUtil
{

	private static SessionFactory m_oSessionFactory = null;
	 private static StandardServiceRegistry m_StandardServiceRegistry;
	
	private static SessionFactory getSessionFactory()
	{
		if(m_oSessionFactory == null)
		{
			try {

	            // Create registry builder
	            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
	            Map<String, String> settings = new HashMap<>();
	            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
	            settings.put(Environment.URL, "jdbc:mysql://authframework.cbuatu53m418.ap-southeast-1.rds.amazonaws.com:3306/authFramework?useSSL=false");
	            settings.put(Environment.USER, "TMUser");
	            settings.put(Environment.PASS, "Tech49Must42");
	            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
	            // Apply settings
	            registryBuilder.applySettings(settings);
	            // Create registry
	            m_StandardServiceRegistry = registryBuilder.build();
	            // Create MetadataSources
	            MetadataSources sources = new MetadataSources(m_StandardServiceRegistry);
	            // Create Metadata
	            Metadata metadata = sources.getMetadataBuilder().build();
	            // Create SessionFactory
	            m_oSessionFactory = metadata.getSessionFactoryBuilder().build();

	         }
			catch (Exception eException)
			{
	        	eException.printStackTrace();
	            if (m_StandardServiceRegistry != null) 
	               StandardServiceRegistryBuilder.destroy(m_StandardServiceRegistry);
	         }
	      }			
		return m_oSessionFactory;
	}
	
	public static Session getSession()
	{
		Session oSession = null;
		try 
		{
			oSession = getSessionFactory().openSession();
		}
		catch (Exception eException)
		{
			eException.printStackTrace();
		}
		return oSession;
	}
}