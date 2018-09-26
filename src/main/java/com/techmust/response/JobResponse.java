package com.techmust.response;

import java.util.List;

import com.techmust.tenant.model.Job;

public class JobResponse 
{
	private List<Job> arrJobList = null;
	public List<Job> getArrJobList() 
	{
		return arrJobList;
	}
	
	public void setArrJobList(List<Job> arrJobList)
	{
		this.arrJobList = arrJobList;
	}
}