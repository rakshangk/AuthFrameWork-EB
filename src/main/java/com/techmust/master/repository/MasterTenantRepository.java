package com.techmust.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.techmust.master.model.MasterTenant;

@Repository
public interface MasterTenantRepository extends JpaRepository<MasterTenant, Long> 
{
	@Query("select TenantId from MasterTenant TenantId where TenantId.m_strTenantId = :tenantId")
	MasterTenant findByTenantId(@Param("tenantId") String tenantId);
}