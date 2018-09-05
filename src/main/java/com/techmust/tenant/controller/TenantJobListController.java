package com.techmust.tenant.controller;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techmust.tenant.response.JobResponse;
import com.techmust.tenant.service.JobsService;

@RestController("TenantJobListController")
public class TenantJobListController 
{
	@Autowired
	 private JobsService jobsService; 
	
    @RequestMapping(value="/listJobs",method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody JobResponse  getJobList() throws SQLException 
	{
    	JobResponse oJobResponse = new JobResponse();
    	oJobResponse.setArrJobList(jobsService.findAllJobs());
    	return oJobResponse;
	}
}