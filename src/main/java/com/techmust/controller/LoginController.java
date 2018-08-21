package com.techmust.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techmust.constants.LoginConstants;
import com.techmust.response.LoginResponse;

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
