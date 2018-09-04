package com.techmust.tenant.response;

import java.util.List;

public class LoginResponse 
{
	private boolean m_bIsSuccess = false;
	private String  strResponseMessage = "";
	private List<String> arrTenantList = null;
	
	public boolean isM_bIsSuccess()
	{
		return m_bIsSuccess;
	}
	
	public List<String> getArrTenantList() {
		return arrTenantList;
	}

	public void setArrTenantList(List<String> arrTenantList) {
		this.arrTenantList = arrTenantList;
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