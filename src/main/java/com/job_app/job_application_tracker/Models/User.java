package com.job_app.job_application_tracker.Models;

public interface User {
	public long getId();
	public String getUsername();
	public void setUsername(String username);
	public String getPassword();
	public void setPassword(String password);
	public String getEmail();
	public void setEmail(String email);
}
