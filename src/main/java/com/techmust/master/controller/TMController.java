package com.techmust.master.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

public class TMController
{
	@Autowired
	protected DataSource m_datasource;
	
	@Autowired
	protected JdbcUserDetailsManager m_oJdbcUserDetailsManager;
}