package com.techmust.master.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techmust.constants.Constants;
import com.techmust.master.model.TMUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;


@RestController("UserController")
public class UserController 
{
	@Autowired
	private JdbcUserDetailsManager oJdbcUserDetailsManager;
	
	@Autowired
	private DataSource datasource;
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);	

	@RequestMapping(value = "/signUp",  method = RequestMethod.POST , produces = "application/json")
	public @ResponseBody HttpStatus SignUp(@RequestBody TMUserDetails oTMUserDetails)throws SQLException
	{
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(Constants.m_DefaultRole )); //When signUp setting the user with default role.
		try
		{			
			User oNewUserDetails = new User(oTMUserDetails.getM_strUserEmailAddress(), "{noop}" + oTMUserDetails.getM_strPassword(), authorities);
			oJdbcUserDetailsManager.createUser(oNewUserDetails);
			Connection  oConnection = datasource.getConnection();			
			PreparedStatement oPreparedStatement = oConnection.prepareStatement("insert into user_details (username, user_firstname, user_lastname, user_phonenumber) values (?, ?, ?, ?)");
			oPreparedStatement.setString(1, oTMUserDetails.getM_strUserEmailAddress());
			oPreparedStatement.setString(2, oTMUserDetails.getM_strFirstName());
			oPreparedStatement.setString(3, oTMUserDetails.getM_strLastName());
			oPreparedStatement.setString(4, oTMUserDetails.getM_strPhoneNumber());
			oPreparedStatement.execute();
			oConnection.close();
		}
		catch (DuplicateKeyException eDuplicateKeyException)
		{
			LOG.info(eDuplicateKeyException.getMessage());
		}
		finally
		{
			
		}
		return HttpStatus.OK;
	}
}
