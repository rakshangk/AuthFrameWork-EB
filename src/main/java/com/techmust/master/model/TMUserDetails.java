package com.techmust.master.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import com.techmust.constants.Constants;

public class TMUserDetails
{	
	private static final Logger LOG = LoggerFactory.getLogger(TMUserDetails.class);
	
	private String m_strFirstName;
	private String m_strLastName;
	private String m_strPhoneNumber;
	
	// temp variables - not to be relied on
	private String m_strUserID;
	private String m_strPassword;
	
	public String getM_strFirstName()
	{
		return m_strFirstName;
	}

	public void setM_strFirstName(String strFirstName)
	{
		this.m_strFirstName = strFirstName;
	}

	public String getM_strLastName()
	{
		return m_strLastName;
	}

	public void setM_strLastName(String strLastName)
	{
		this.m_strLastName = strLastName;
	}	

	public String getM_strPhoneNumber()
	{
		return m_strPhoneNumber;
	}

	public void setM_strPhoneNumber(String strPhoneNumber)
	{
		this.m_strPhoneNumber = strPhoneNumber;
	}

	public String getM_strUserID()
	{
		return m_strUserID;
	}

	public void setM_strUserID(String strUserID)
	{
		this.m_strUserID = strUserID;
	}

	public String getM_strPassword()
	{
		return m_strPassword;
	}

	public void setM_strPassword(String strPassword)
	{
		this.m_strPassword = strPassword;
	}	
	
	public boolean createUser (Connection oConnection, JdbcUserDetailsManager oJdbcUserDetailsManager)
	{
		boolean bUserCreated = false;
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();	
		authorities.add(new SimpleGrantedAuthority(Constants.m_DefaultRole )); //When signUp setting the user with default role.
		User oNewUser = new User (getM_strUserID(), "{noop}" + getM_strPassword(), authorities);		
		oJdbcUserDetailsManager.createUser(oNewUser);
		
		try
		{
			// if user creation is successful, prepare statement for execution
			PreparedStatement oPreparedStatement = oConnection.prepareStatement("insert into user_details "
					+ "(username, "
					+ "user_firstname, "
					+ "user_lastname, "
					+ "user_phonenumber) "
					+ "values (?, ?, ?, ?)");
			oPreparedStatement.setString(1, getM_strUserID());
			oPreparedStatement.setString(2, getM_strFirstName());
			oPreparedStatement.setString(3, getM_strLastName());
			oPreparedStatement.setString(4, getM_strPhoneNumber());
			oPreparedStatement.execute();
			bUserCreated = true;
		}
		catch (SQLException oSQLException)
		{
			// log the error and return false
			LOG.info(oSQLException.getMessage());
			bUserCreated = false;
			// delete user;
			oJdbcUserDetailsManager.deleteUser(oNewUser.getUsername());
		}

		return bUserCreated;
	}
}