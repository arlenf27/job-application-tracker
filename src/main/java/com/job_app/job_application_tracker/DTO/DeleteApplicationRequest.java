package com.job_app.job_application_tracker.DTO;

public class DeleteApplicationRequest {
	private long jobAppId;
	private String username;
	private String password;

	public long getJobAppId() {
		return jobAppId;
	}
	
	public void setJobAppId(long jobAppId) {
		this.jobAppId = jobAppId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
