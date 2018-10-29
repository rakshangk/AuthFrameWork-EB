package com.techmust.utils;

import java.util.List;

import javax.persistence.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
			Query qQuery = oSession.createNativeQuery("select tenant_id from user_tenants where username = :username");
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

	public static String getCookie (HttpServletRequest oRequest, String strCookieName)
	{
		String strCookie = null;
		Cookie[] cookies = oRequest.getCookies();
		for (Cookie nthCookie : cookies) 
		{
			if (strCookieName.equals(nthCookie.getName()))
			{
				strCookie = nthCookie.getValue();
				break;
			}
		}
		return strCookie;
	}
}