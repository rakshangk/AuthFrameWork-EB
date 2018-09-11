package com.techmust.utils;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.techmust.master.model.MasterTenant;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Utility class for DataSource
 * 
 */
public final class DataSourceUtil
{
	private static final Logger LOG = LoggerFactory.getLogger(DataSourceUtil.class);	
	/**
	* Utility method to create and configure a data source
	* 
	* @param masterTenant
	* @return
	*/
	public static DataSource createAndConfigureDataSource(MasterTenant oMasterTenant)
	{
		HikariDataSource oHikariDataSource = new HikariDataSource();
		oHikariDataSource.setUsername(oMasterTenant.getM_strConnectionUsername());
		oHikariDataSource.setPassword(oMasterTenant.getM_strConnectionPassword());
		oHikariDataSource.setJdbcUrl(oMasterTenant.getM_strUrl());
		oHikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		// HikariCP settings - could come from the master_tenant table but        
		// hardcoded here for brevity
		// Maximum waiting time for a connection from the pool
		oHikariDataSource.setConnectionTimeout(20000);
		// Minimum number of idle connections in the pool
		oHikariDataSource.setMinimumIdle(10);
		// Maximum number of actual connection in the pool
		oHikariDataSource.setMaximumPoolSize(20);
		// Maximum time that a connection is allowed to sit idle in the pool
		oHikariDataSource.setIdleTimeout(300000);
		oHikariDataSource.setConnectionTimeout(20000);
		// Setting up a pool name for each tenant datasource
		String tenantId = oMasterTenant.getM_strTenantId();
		String tenantConnectionPoolName = tenantId + "-connection-pool";
		oHikariDataSource.setPoolName(tenantConnectionPoolName);
		LOG.info("Configured datasource:" + oMasterTenant.getM_strTenantId()+ ". Connection poolname:" + tenantConnectionPoolName);
		return oHikariDataSource;
	}
}