package com.job_app.job_application_tracker.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class GeneralJobApplication implements JobApplication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private GeneralUser user;
	
	private String companyName;
	private String positionTitle;
	
	public GeneralJobApplication() {
		/* Default constructor for JPA */
	}
	
	public GeneralJobApplication(String companyName, String positionTitle, GeneralUser user) {
		this.companyName = companyName;
		this.positionTitle = positionTitle;
		this.user = user;
	}

	@Override
	public String getCompanyName() {
		return this.companyName;
	}

	@Override
	public void setCompanyName(String name) {
		this.companyName = name;
	}

	@Override
	public String getPositionTitle() {
		return this.positionTitle;
	}

	@Override
	public void setPositionTitle(String title) {
		this.positionTitle = title;
	}

	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public GeneralUser getUser() {
		return this.user;
	}

	@Override
	public void setUser(GeneralUser user) {
		this.user = user;
	}

}
