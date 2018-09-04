package com.techmust.master.config;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import com.techmust.utils.TenantContextHolder;

public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver 
{
	 private static final String DEFAULT_TENANT_ID = "dbtenant1";

	    @Override
	    public String resolveCurrentTenantIdentifier() 
	    {
	        String strTenantId = TenantContextHolder.getTenant();
	        return StringUtils.isNotBlank(strTenantId) ? strTenantId : DEFAULT_TENANT_ID;
	    }

	    @Override
	    public boolean validateExistingCurrentSessions()
	    {
	        return true;
	    }
}