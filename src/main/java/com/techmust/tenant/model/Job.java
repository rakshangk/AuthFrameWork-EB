package com.techmust.tenant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "job")
public class Job
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_id")
	private int m_nId;
	
	@Column(name = "job_name")
	private String m_strJobName;

	public int getM_nId() {
		return m_nId;
	}

	public void setM_nId(int m_nId) {
		this.m_nId = m_nId;
	}

	public String getM_strJobName() {
		return m_strJobName;
	}

	public void setM_strJobName(String m_strJobName) {
		this.m_strJobName = m_strJobName;
	}

	@Override
	public String toString() {
		return "Job [m_nId=" + m_nId + ", m_strJobName=" + m_strJobName + "]";
	}	
}