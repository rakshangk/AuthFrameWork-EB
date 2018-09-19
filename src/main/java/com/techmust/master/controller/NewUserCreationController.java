package com.techmust.master.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("NewUserCreationController")
public class NewUserCreationController 
{
	private static final Logger LOG = LoggerFactory.getLogger(NewUserCreationController.class);	
	@RequestMapping(value = "/signUp",  method = RequestMethod.POST , headers = "Accept=application/json")
	public Boolean NewUserCreation(HttpServletRequest oServletRequest,HttpServletResponse oServletResponse)
	{
		LOG.info("inside NewUserCreationController"); 
		JSONObject myResponse = new JSONObject ( oServletRequest.getParameter("user").toString());
		LOG.info("signUp JSON : " + myResponse);		
		Boolean bNewUserCreationStatus = false;	
		return bNewUserCreationStatus;
	}
}
