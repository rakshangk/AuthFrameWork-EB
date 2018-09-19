package com.techmust.master.model;

import org.springframework.security.core.userdetails.User;

public class UserDetails
{
	private User m_oUser;
	private String m_strFirstName;	
	private String m_strLastName;
	private int m_nPhoneNumber;	
	
	public User getM_oUser() {
		return m_oUser;
	}
	
	public void setM_oUser(User m_oUser) 
	{
		this.m_oUser = m_oUser;
	}
	
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
	
	public int getM_nPhoneNumber() 
	{
		return m_nPhoneNumber;
	}
	
	public void setM_nPhoneNumber(int m_nPhoneNumber) 
	{
		this.m_nPhoneNumber = m_nPhoneNumber;
	}	
}
