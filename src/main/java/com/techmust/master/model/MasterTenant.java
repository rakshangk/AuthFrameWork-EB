package com.techmust.master.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * This JPA entity represents the <tt>master_tenant</tt> table in the
 * <tt>masterdb</tt> database. This table holds the details of the tenant
 * databases.
 * 
 */
@Entity
@Table(name = "master_tenant")
public class MasterTenant
{
	@Id
	@Size(max = 30)
	@Column(name = "tenant_id")
	private String m_strTenantId;

	@Size(max = 256)
	@Column(name = "db_url")
	private String m_strUrl;

	@Size(max = 30)
	@Column(name = "db_username")
	private String m_strConnectionUsername;

	/**
	 * For simplicity, we are not storing an encrypted password. In production
	 * this should be a encrypted password.
	 */
	@Size(max = 30)
	@Column(name = "db_password")
	private String m_strConnectionPassword;

	/**
	 * @return the tenantId
	 */
	public String getM_strTenantId()
	{
		return m_strTenantId;
	}

	/**
	 * @param tenantId
	 *            the tenantId to set
	 */
	public void setM_strTenantId(String m_strTenantId)
	{
		this.m_strTenantId = m_strTenantId;
	}

	/**
	 * @return the url
	 */
	public String getM_strUrl()
	{
		return m_strUrl;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setM_strUrl(String m_strUrl)
	{
		this.m_strUrl = m_strUrl;
	}

	 /**
	 * @return the ConnectionUsername
	 */
	public String getM_strConnectionUsername()
	{
		return m_strConnectionUsername;
	}

	/**
	 * @param m_strConnectionUsername
	 * the ConnectionUsername to set
	 */
	public void setM_strConnectionUsername(String m_strConnectionUsername)
	{
		this.m_strConnectionUsername = m_strConnectionUsername;
	}

	/**
	 * @return the password
	 */
	public String getM_strConnectionPassword()
	{
		return m_strConnectionPassword;
	}

	 /**
	 * @param m_strConnectionPassword
	 * the ConnectionPassword to set
	 */
	public void setM_strConnectionPassword(String m_strConnectionPassword)
	{
		this.m_strConnectionPassword = m_strConnectionPassword;
	}
}