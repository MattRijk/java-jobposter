package com.natickweb.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natickweb.spring.web.dao.Job;
import com.natickweb.spring.web.dao.JobDAO;

@Service("JobsService")
public class JobsService {
	
	private JobDAO JobsDao;
	
	@Autowired
	public void setJobsDao(JobDAO JobsDao) {
		this.JobsDao = JobsDao;
	}

	public List<Job> getCurrent() {
		return JobsDao.getJobs();
	}

	public void create(Job job) {
		JobsDao.create(job);
	}

	public void throwTestException() {
		JobsDao.getJob(999999);
	}
}
