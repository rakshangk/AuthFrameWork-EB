package com.techmust.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@PropertySource("classpath:application.properties")
@EnableWebSecurity
@EnableWebMvc
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private DataSource datasource;

	@Bean
	public JdbcUserDetailsManager GetJdbcUserDetailsManagerInstance() throws Exception
	{
		JdbcUserDetailsManager oJdbcUserDetailsManager = new JdbcUserDetailsManager();
		oJdbcUserDetailsManager.setDataSource(datasource);
		return oJdbcUserDetailsManager;
	}	

	@Bean
	public AuthenticationSuccessHandler customAuthenticationSuccessHandler ()
	{
		return new CustomAuthenticationSuccessHandler();
	}

	@Override
	protected void configure(HttpSecurity oHttpSecurity) throws Exception
	{
		oHttpSecurity.authorizeRequests()
		.antMatchers("/").permitAll()
		.anyRequest().authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).sessionFixation().newSession()
		.and()
		.formLogin()
		.loginProcessingUrl("/signIn")
		.successHandler(customAuthenticationSuccessHandler())
		.and()
		.csrf().disable().headers().frameOptions().disable().and().httpBasic();
	}

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder oAuthenticationManagerBuilder) throws Exception 
	{
		oAuthenticationManagerBuilder.jdbcAuthentication().dataSource(datasource) ;
	}

	@Override
	public void configure(WebSecurity oWebSecurity) throws Exception 
	{
		oWebSecurity.ignoring().antMatchers("/signUp");
	}
}