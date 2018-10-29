package com.techmust.tenant.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techmust.tenant.response.JobResponse;
import com.techmust.tenant.service.JobsService;
import com.techmust.utils.TenantContextHolder;
import com.techmust.utils.Utils;

@RestController("TenantJobListController")
public class TenantJobListController
{
	@Autowired
	private JobsService jobsService;

	@RequestMapping(value="/listJobs",method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody JobResponse  getJobList(HttpServletRequest oRequest) throws SQLException 
	{
		/*String strTenant = Utils.getCookie(oRequest, "tenant");
		if (strTenant != null)
			TenantContextHolder.setTenantId(strTenant);*/
		JobResponse oJobResponse = new JobResponse();
		oJobResponse.setArrJobList(jobsService.findAllJobs());
		return oJobResponse;
	}
}