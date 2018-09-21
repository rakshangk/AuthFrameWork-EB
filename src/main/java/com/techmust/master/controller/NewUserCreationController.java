package com.techmust.master.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techmust.constants.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;


@RestController("NewUserCreationController")
public class NewUserCreationController 
{
	@Autowired
	private JdbcUserDetailsManager oJdbcUserDetailsManager;
	
	@Autowired
	private DataSource datasource;
	
	private static final Logger LOG = LoggerFactory.getLogger(NewUserCreationController.class);	
	@RequestMapping(value = "/signUp",  method = RequestMethod.POST , headers = "Accept=application/json")
	public Boolean NewUserCreation(HttpServletRequest oServletRequest,HttpServletResponse oServletResponse)throws SQLException
	{
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(Constants.m_DefaultRole )); //When signUp setting the user with default role.
		try
		{
			JSONObject oSignUpJSONObject = new JSONObject (oServletRequest.getParameter("SignUpData").toString());
			UserDetails oNewUserDetails = new User(oSignUpJSONObject.getString("strEmailId").toLowerCase(), "{noop}"+oSignUpJSONObject.getString("strPassword"), authorities);
			oJdbcUserDetailsManager.createUser(oNewUserDetails);
			Connection  oConnection = datasource.getConnection();			
			PreparedStatement oPreparedStatement = oConnection.prepareStatement("insert into user_details (username, user_firstname, user_lastname, user_phonenumber) values (?, ?, ?, ?)");
			oPreparedStatement.setString(1, oSignUpJSONObject.getString("strEmailId").toLowerCase());
			oPreparedStatement.setString(2, oSignUpJSONObject.getString("strFirstName"));
			oPreparedStatement.setString(3, oSignUpJSONObject.getString("strLastName"));
			oPreparedStatement.setString(4, oSignUpJSONObject. getString("strMobile"));
			oPreparedStatement.execute();
			oConnection.close();
			//Authentication authentication = new UsernamePasswordAuthenticationToken(oNewUserDetails, null, authorities);
		}
		catch (DuplicateKeyException eDuplicateKeyException)
		{
			LOG.info(eDuplicateKeyException.getMessage());
		}
		finally
		{
			
		}	
		//JSONObject myResponse = new JSONObject ( oServletRequest.getParameter("user").toString());
		//LOG.info("signUp JSON : " + myResponse);		
		Boolean bNewUserCreationStatus = false;	
		return bNewUserCreationStatus;
	}	
}
