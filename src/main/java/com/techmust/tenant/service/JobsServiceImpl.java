package com.techmust.tenant.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmust.tenant.Repository.JobsRepository;
import com.techmust.tenant.model.Job;

/**
 * Implementation of the {@link UserService} which accesses the {@link User}
 * entity. This is the recommended way to access the entities through an
 * interface rather than using the corresponding repository. This allows for
 * separation into repository code and the service layer.
 */
@Service
public class JobsServiceImpl implements JobsService 
{

    private static final Logger LOG = LoggerFactory.getLogger(JobsServiceImpl.class);

    @Autowired
    private JobsRepository jobsRepository;
    
	@Override
	public List <Job> findAllJobs()
	{				
		 LOG.info("-------------- findAllJobs method log---------------");
		 return jobsRepository.findAll();
	}
}