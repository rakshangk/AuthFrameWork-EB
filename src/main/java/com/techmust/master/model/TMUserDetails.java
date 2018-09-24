package com.techmust.master.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TMUserDetails
{
	private String m_strFirstName;	
	private String m_strLastName;	
	private int m_strPhoneNumber;	
	private String m_strUserEmailAddress;
	private String m_strPassword;
	
	
	public String getM_strFirstName()
	{
		return m_strFirstName;
	}

	public void setM_strFirstName(String m_strFirstName) 
	{
		this.m_strFirstName = m_strFirstName;
	}

	public String getM_strLastName()
	{
		return m_strLastName;
	}

	public void setM_strLastName(String m_strLastName) 
	{
		this.m_strLastName = m_strLastName;
	}

	public int getM_strPhoneNumber() 
	{
		return m_strPhoneNumber;
	}

	public void setM_strPhoneNumber(int m_strPhoneNumber) 
	{
		this.m_strPhoneNumber = m_strPhoneNumber;
	}

	public String getM_strUserEmailAddress() 
	{
		return m_strUserEmailAddress;
	}

	public void setM_strUserEmailAddress(String m_strUserEmailAddress) 
	{
		this.m_strUserEmailAddress = m_strUserEmailAddress;
	}

	public String getM_strPassword() 
	{
		return m_strPassword;
	}

	public void setM_strPassword(String m_strPassword)
	{
		this.m_strPassword = m_strPassword;
	}
	
	@JsonCreator
	public TMUserDetails(@JsonProperty("m_strFirstName") String m_strFirstName, @JsonProperty("m_strLastName") String m_strLastName, @JsonProperty("m_strPhoneNumber") int m_strPhoneNumber, @JsonProperty("m_strUserEmailAddress") String m_strUserEmailAddress, @JsonProperty("m_strPassword") String m_strPassword) 
	{
		super();
		this.m_strFirstName = m_strFirstName;
		this.m_strLastName = m_strLastName;
		this.m_strPhoneNumber = m_strPhoneNumber;
		this.m_strUserEmailAddress = m_strUserEmailAddress;
		this.m_strPassword = m_strPassword;
	}
	
	
}