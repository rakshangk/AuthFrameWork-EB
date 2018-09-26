package com.techmust.response;

import java.util.List;

public class LoginResponse extends ServerResponse
{	
	private List<String> m_arrTenantList = null;

	public List<String> getM_arrTenantList()
	{
		return m_arrTenantList;
	}

	public void setM_arrTenantList(List<String> arrTenantList) 
	{
		this.m_arrTenantList = arrTenantList;
	}
}