package com.techmust.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.techmust.utils.TenantContextHolder;
import com.techmust.utils.Utils;

public class RequestInterceptor extends HandlerInterceptorAdapter 
{
	@Override
	public boolean preHandle(final HttpServletRequest oHttpServletRequest, final HttpServletResponse oHttpServletResponse, final Object handler) throws Exception 
	{	
		String strTenant = Utils.getCookie(oHttpServletRequest, "tenant");
		if (strTenant != null)
			TenantContextHolder.setTenantId(strTenant);
		return true;
	}
}