package com.techmust.master.controller;

import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techmust.master.model.TMUserDetails;
import com.techmust.response.ServerResponse;

import org.springframework.dao.DuplicateKeyException;

@RestController("UserController")
public class UserController extends TMController
{	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);	

	@RequestMapping(value = "/signUp",  method = RequestMethod.POST , produces = "application/json; charset = utf-8")
	public @ResponseBody ServerResponse SignUp(@RequestBody TMUserDetails oTMUserDetails) throws SQLException
	{
		Connection  oConnection = m_datasource.getConnection();
		ServerResponse oServerResponse = new ServerResponse();
		try
		{
			oServerResponse.setM_bIsSuccess(oTMUserDetails.createUser(oConnection, m_oJdbcUserDetailsManager));
		}
		catch (DuplicateKeyException eDuplicateKeyException)
		{
			LOG.info(eDuplicateKeyException.getMessage());
			oServerResponse.setM_strResponseMessage("userId already exixts, please use differnt userid or go through signIn page");
		}
		finally
		{
			oConnection.close();
		}
		return oServerResponse;
	}
}