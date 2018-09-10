package com.techmust.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@PropertySource("classpath:application.properties")
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter 
{  
	@Autowired
    private DataSource datasource;   
	
	@Override
	 protected void configure(HttpSecurity oHttpSecurity) throws Exception
    {	
		oHttpSecurity.authorizeRequests()
        .antMatchers("/").permitAll()
        .anyRequest().authenticated()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).sessionFixation().newSession()
        .and()
        .csrf().disable().headers().frameOptions().disable().and().httpBasic();		
    }

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder oAuthenticationManagerBuilder) throws Exception 
	{
		oAuthenticationManagerBuilder.jdbcAuthentication().dataSource(datasource) ;
	}
}