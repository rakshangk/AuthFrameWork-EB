package com.techmust.controller;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techmust.constants.LoginConstants;
import com.techmust.response.LoginResponse;
import com.techmust.utils.HibernateUtil;

@RestController("loginController")
public class LoginController 
{		 
    @RequestMapping(value="/signIn",method = RequestMethod.POST,
			produces = {"application/json"})
	public @ResponseBody LoginResponse authenticate() 
	{
    	LoginResponse oLoginResponse = new LoginResponse();
    	oLoginResponse.setM_bIsSuccess(true);    	
    	Authentication oAuthentication = SecurityContextHolder.getContext().getAuthentication();
    	String strUserName = oAuthentication.getName();
    	oLoginResponse.setStrResponseMessage(LoginConstants.m_strLoginSuccessMessage + "For : " + strUserName);  
    	List<String> arrTenants = getUserConnectedTenents(strUserName);
    	oLoginResponse.setArrTenantList(arrTenants);
	    return oLoginResponse;
	}
    
    @RequestMapping(value="/users",method = RequestMethod.POST,
			produces = {"application/json"})
	public @ResponseBody LoginResponse users() 
	{
    	LoginResponse oLoginResponse = new LoginResponse();
    	oLoginResponse.setM_bIsSuccess(true);
    	oLoginResponse.setStrResponseMessage("user method");
	    return oLoginResponse;
	}
    
  
	public List<String> getUserConnectedTenents(String strUsername)
    {
		Session oSession  = null;
		List<String> arrTenantList = null;
		try 
		{
			oSession = HibernateUtil.getSession();
			oSession.beginTransaction();
	    	Query qQuery = oSession.createNativeQuery("select tenant from user_tenants where username = :username");
	    	qQuery.setParameter("username", strUsername);
	    	arrTenantList = qQuery.getResultList();
	    	oSession.getTransaction().commit();
	    	oSession.close();
		}
		catch (Exception eException) 
		{
			eException.printStackTrace();
		}    	
		return arrTenantList;    	
    }
}
