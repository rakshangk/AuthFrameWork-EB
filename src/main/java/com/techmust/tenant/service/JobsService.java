package com.techmust.tenant.service;

import java.util.List;

import com.techmust.tenant.model.Job;

/**
 * Service definition which accesses the {@link com.example.model.User} entity.
 * This is the recommended way to access the entities through an interface
 * rather than using the corresponding repository directly. This allows for
 * separation into repository code and the service layer.
 */
public interface JobsService
{
	List<Job> findAllJobs();
}