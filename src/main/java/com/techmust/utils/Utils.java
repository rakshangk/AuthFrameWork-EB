package com.techmust.utils;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

public class Utils 
{
	@SuppressWarnings("unchecked")
	public static List<String> getUserConnectedTenents(String strUsername)
	{
		Session oSession  = null;
		List<String> arrTenantList = null;
		oSession = HibernateUtil.getSession();
		try
		{
			oSession.beginTransaction();
			Query qQuery = oSession.createNativeQuery("select tenant from user_tenants where username = :username");
			qQuery.setParameter("username", strUsername);
			arrTenantList = qQuery.getResultList();
			oSession.getTransaction().commit();
		}
		catch (Exception eException)
		{
			eException.printStackTrace();
		}
		finally
		{
			oSession.close();
		}
		return arrTenantList;
	}
}