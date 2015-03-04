package com.natickweb.spring.web.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.natickweb.spring.web.validation.ValidEmail;

public class Job {
	private int id;
	
	@Size(min=5, max=100, message="Name must be between 5 and 100 characters: ")
	private String name;
	
	@NotNull
	//@Pattern(regexp=".*\\@.*\\..*", message="This does not appear to be a valid email address")
	@ValidEmail(min=6, message="This email address is not valid.")
	private String email;
	
	@Size(min=5, max=100, message="Name must be between 20 and 255 characters: ")
	private String text;
	
	public Job() {
		
	}

	public Job(String name, String email, String text) {
		this.name = name;
		this.email = email;
		this.text = text;
	}
	
	

	public Job(int id, String name, String email, String text) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", name=" + name + ", email=" + email
				+ ", text=" + text + "]";
	}

}
