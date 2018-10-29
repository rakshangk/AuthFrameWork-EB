package com.techmust.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.techmust.utils.TenantContextHolder;
import com.techmust.utils.Utils;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler 
												implements AuthenticationSuccessHandler 
{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest oRequest, HttpServletResponse oResponse,
			Authentication oAuthentication) throws IOException, ServletException {
		
		HttpSession oSession = oRequest.getSession();
		User oAuthUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		oSession.setAttribute("username", oAuthUser.getUsername());
		List<String> arrTenants = Utils.getUserConnectedTenents(oAuthUser.getUsername());
		if(arrTenants.size() > 0)
		{
			TenantContextHolder.setTenantId(arrTenants.get(0));
			saveCookie("tenant", arrTenants.get(0), oResponse);
		}
		
		//set our response to OK status
		oResponse.setStatus(HttpServletResponse.SC_OK);
		
		super.onAuthenticationSuccess(oRequest, oResponse, oAuthentication);
	}
	
	private static void saveCookie(String strCookieName, String strValue, HttpServletResponse oResponse) 
	{
	    Cookie cookie = new Cookie(strCookieName, strValue);
//	    //maxAge is one month: 30*24*60*60 
//	    cookie.setMaxAge(2592000);
//	    cookie.setDomain("projectName");
//	    cookie.setPath("/");
	    oResponse.addCookie(cookie);
	}
}
