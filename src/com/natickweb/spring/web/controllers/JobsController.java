package com.natickweb.spring.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.natickweb.spring.web.dao.Job;
import com.natickweb.spring.web.service.JobsService;

@Controller
public class JobsController {

	private JobsService JobsService;

	@Autowired
	public void setJobsService(JobsService JobsService) {
		this.JobsService = JobsService;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String showTest(Model model, @RequestParam("id") String id) {

		System.out.println("Id is: " + id);

		return "home";
	}
/*
	@ExceptionHandler(DataAccessException.class)
	public String handleDatabaseException(DataAccessException ex){
		return "error";
		
	}
*/
	@RequestMapping("/jobs")
	public String showJobs(Model model) {
		
		//JobsService.throwTestException();

		List<Job> jobs = JobsService.getCurrent();

		model.addAttribute("jobs", jobs);

		return "jobs";
	}

	@RequestMapping("/createjob")
	public String createJob(Model model) {

		model.addAttribute("job", new Job());
		return "createjob";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreate(Model model, @Valid Job job, BindingResult result) {

		if (result.hasErrors()) {
			return "createjob";
		
		} 
		JobsService.create(job);

		return "jobcreated";
	}
}
