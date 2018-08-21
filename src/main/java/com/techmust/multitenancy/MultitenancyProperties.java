package com.techmust.multitenancy;

import java.util.List;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/*
 * This class reads the <code>multitenancy.mtapp</code> node from
 * <code>application.yml</code> file and populates a list of
 * {@link org.springframework.boot.autoconfigure.jdbc.DataSourceProperties}
 * objects, with each instance containing the data source details about the
 * database like url, username, password etc
 */

@Configuration
@ConfigurationProperties("multitenancy.mtapp")
public class MultitenancyProperties 
{

    private List<DataSourceProperties> m_arrDataSourceProperties;
    
    public List<DataSourceProperties> getDataSources()
    {
        return this.m_arrDataSourceProperties;
    }

    public void setDataSources(List<DataSourceProperties> arrDataSourceProperties) 
    {
        this.m_arrDataSourceProperties = arrDataSourceProperties;
    }    
}