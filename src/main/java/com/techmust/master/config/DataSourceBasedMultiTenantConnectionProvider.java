package com.techmust.master.config;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.DataSourceBasedMultiTenantConnectionProviderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.techmust.master.model.MasterTenant;
import com.techmust.master.repository.MasterTenantRepository;
import com.techmust.utils.DataSourceUtil;
import com.techmust.utils.TenantContextHolder;
import com.techmust.utils.Utils;

@Configuration
public class DataSourceBasedMultiTenantConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl
{
	private static final Logger LOG = LoggerFactory.getLogger(DataSourceBasedMultiTenantConnectionProviderImpl.class);
	private static final long serialVersionUID = 1L;
	 @Autowired
	private MasterTenantRepository m_oMasterTenantRepo;

    private Map<String, DataSource> m_arrMTdataSourcesMap = new TreeMap<>();
	
    @Override
    protected DataSource selectAnyDataSource()
    {
        // This method is called more than once. So check if the data source map
        // is empty. If it is then rescan master_tenant table for all tenant
        // entries.
        if (m_arrMTdataSourcesMap.isEmpty())
        {
            List<MasterTenant> arrMasterTenantList = m_oMasterTenantRepo.findAll();
            LOG.info(">>>> selectAnyDataSource() -- Total tenants:" + arrMasterTenantList.size());
            for (MasterTenant oNthMasterTenant : arrMasterTenantList) 
            	m_arrMTdataSourcesMap.put(oNthMasterTenant.getM_strTenantId(), DataSourceUtil.createAndConfigureDataSource(oNthMasterTenant));
        }
        return this.m_arrMTdataSourcesMap.values().iterator().next();
    }
	 
    @Override
    protected DataSource selectDataSource(String strTenantIdentifier) 
    {
    	strTenantIdentifier = initializeTenantIfLost(strTenantIdentifier);
        if (!this.m_arrMTdataSourcesMap.containsKey(strTenantIdentifier)) 
        {
            List<MasterTenant> arrMasterTenantList = m_oMasterTenantRepo.findAll();
            LOG.info(">>>> selectDataSource() -- tenant:" + strTenantIdentifier + " Total tenants:" + arrMasterTenantList.size());
            for (MasterTenant oNthMasterTenant : arrMasterTenantList)
            	m_arrMTdataSourcesMap.put(oNthMasterTenant.getM_strTenantId(), DataSourceUtil.createAndConfigureDataSource(oNthMasterTenant));
        }
        return this.m_arrMTdataSourcesMap.get(strTenantIdentifier);
    }

    /**
     * Initialize tenantId based on the logged in user if the tenant Id got lost in after form submission in a user
     * session.
     * 
     * @param tenantIdentifier
     * @return tenantIdentifier
     */
    private String initializeTenantIfLost(String strTenantIdentifier) 
    {
    	 LOG.info("Inside initializeTenantIfLost method");
        if (TenantContextHolder.getTenant() == null) 
        {
            Authentication oAuthentication = SecurityContextHolder.getContext().getAuthentication();
        	List<String> arrTenants = Utils.getUserConnectedTenents(oAuthentication.getName());        	
            if(arrTenants.size() > 0)
            	TenantContextHolder.setTenantId(arrTenants.get(0));
        }
        if (strTenantIdentifier != TenantContextHolder.getTenant())
        	strTenantIdentifier = TenantContextHolder.getTenant();
        LOG.info("-----------------   initializeTenantIfLost method Exit------------------------ ");
        return strTenantIdentifier;
    }
}