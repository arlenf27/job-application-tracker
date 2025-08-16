package com.job_app.job_application_tracker.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.job_app.job_application_tracker.DTO.JobApplicationRequest;
import com.job_app.job_application_tracker.Models.GeneralJobApplication;
import com.job_app.job_application_tracker.Models.GeneralUser;
import com.job_app.job_application_tracker.Repositories.GeneralJobApplicationRepository;
import com.job_app.job_application_tracker.Repositories.GeneralUserRepository;
import com.job_app.job_application_tracker.Services.JobApplicationService;

import com.job_app.job_application_tracker.JNI.JNICPP;

@RestController
public class JobApplicationController {
	
	@Autowired
	private GeneralJobApplicationRepository generalJobApplicationRepository;
	
	@Autowired
	private JobApplicationService jobApplicationService;
	
	@Autowired
	private GeneralUserRepository generalUserRepository;
	
	/*
	 * Temporary place for user-related endpoints. Will be moved to a separate controller later.
	 */	
	
	@GetMapping("/user_apps")
	public Iterable<GeneralJobApplication> getUserApplications(@RequestParam Long userId) {
		GeneralUser user = this.generalUserRepository.findById(userId).orElseThrow();
		return jobApplicationService.getApplicationsByUser(user);
	}
	
	@PostMapping("/users")
	public String createUser(@RequestBody GeneralUser user) {
		this.generalUserRepository.save(user);
		return String.format("User %s created successfully.", user.getUsername());
	}
	
	@GetMapping("/test")
	public String test(@RequestParam(value = "custom_test_value", defaultValue = "default_value") String testValue) {
		return String.format("Hello, this is a test endpoint with the follwing value: %s", testValue);
	}
	
	@GetMapping("/test_native")
	public String testNative(@RequestParam(value = "custom_test_input", defaultValue = "default_input") String input) {
		return String.format("Native test output: %s", JNICPP.getStringFromNative(input));
	}
	
	@GetMapping("/num_apps_all_users")
	public String numApplications(){
		long count = this.generalJobApplicationRepository.count();
		return String.format("There are currently %d job applications in the database.", count);
	}
	
	@GetMapping("/all_apps")
	public Iterable<GeneralJobApplication> getAllApplications() {
		return this.generalJobApplicationRepository.findAll();
	}
	
	@PostMapping("/applications")
	public String submitApplication(@RequestBody JobApplicationRequest req) {
		Optional<GeneralUser> userOpt = this.generalUserRepository.findById(req.userId);
		if (userOpt.isEmpty()) {
			return "User not found.";
		}
		GeneralJobApplication app = new GeneralJobApplication(req.companyName, req.positionTitle, userOpt.get());
		this.generalJobApplicationRepository.save(app);
		return String.format("Application submitted for %s at %s", app.getCompanyName(), app.getPositionTitle());
	}
}
