package com.job_app.job_application_tracker.Models;

public interface JobApplication {
	public String getCompanyName();
	public void setCompanyName(String name);
	public String getPositionTitle();
	public void setPositionTitle(String title);
	public long getId();
	public GeneralUser getUser();
	public void setUser(GeneralUser user);
}
