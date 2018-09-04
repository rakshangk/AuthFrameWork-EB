package com.techmust.master.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techmust.tenant.constants.LoginConstants;
import com.techmust.tenant.response.LoginResponse;
import com.techmust.tenant.service.JobsService;
import com.techmust.utils.TenantContextHolder;
import com.techmust.utils.Utils;

@RestController("loginController")
public class LoginController 
{		
	 @Autowired
	 private JobsService jobsService;

    @RequestMapping(value="/signIn",method = RequestMethod.POST,
			produces = {"application/json"})
	public @ResponseBody LoginResponse authenticate() throws SQLException 
	{
    	LoginResponse oLoginResponse = new LoginResponse();
    	oLoginResponse.setM_bIsSuccess(true);    	
    	Authentication oAuthentication = SecurityContextHolder.getContext().getAuthentication();
    	String strUserName = oAuthentication.getName();
    	oLoginResponse.setStrResponseMessage(LoginConstants.m_strLoginSuccessMessage + "For : " + strUserName);  
    	List<String> arrTenants = Utils.getUserConnectedTenents(strUserName);
    	oLoginResponse.setArrTenantList(arrTenants);
    	if(arrTenants.size() > 0)
    		TenantContextHolder.setTenantId(arrTenants.get(0));
    	System.out.println(jobsService.findAllJobs());
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
}