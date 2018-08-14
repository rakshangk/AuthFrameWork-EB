package com.techmust.response;

public class LoginResponse 
{
	private boolean m_bIsSuccess = false;
	private String  strResponseMessage = "";
	public boolean isM_bIsSuccess()
	{
		return m_bIsSuccess;
	}
	
	public void setM_bIsSuccess(boolean m_bIsSuccess)
	{
		this.m_bIsSuccess = m_bIsSuccess;
	}
	
	public String getStrResponseMessage() 
	{
		return strResponseMessage;
	}
	
	public void setStrResponseMessage(String strResponseMessage)
	{
		this.strResponseMessage = strResponseMessage;
	}
}
