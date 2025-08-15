package com.job_app.job_application_tracker.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job_app.job_application_tracker.Models.GeneralJobApplication;
import com.job_app.job_application_tracker.Models.GeneralUser;
import com.job_app.job_application_tracker.Repositories.GeneralJobApplicationRepository;

@Service
public class JobApplicationService {
	@Autowired
	private GeneralJobApplicationRepository generalJobApplicationRepository;
	
	public List<GeneralJobApplication> getApplicationsByUser(GeneralUser user) {
		return this.generalJobApplicationRepository.findByUser(user);
	}
}
