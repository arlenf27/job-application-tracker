package com.job_app.job_application_tracker.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.job_app.job_application_tracker.DTO.DeleteApplicationRequest;
import com.job_app.job_application_tracker.DTO.JobApplicationRequest;
import com.job_app.job_application_tracker.Models.GeneralJobApplication;
import com.job_app.job_application_tracker.Models.GeneralUser;
import com.job_app.job_application_tracker.Repositories.GeneralJobApplicationRepository;
import com.job_app.job_application_tracker.Repositories.GeneralUserRepository;
import com.job_app.job_application_tracker.Services.JobApplicationService;
import com.job_app.job_application_tracker.Utilities.PasswordUtil;
import com.job_app.job_application_tracker.DTO.LoginRequest;

/**
 * Controller for user-specific job application operations.
 * Handles user creation and job application submission.
 */
@RestController
public class UserController {
	
	@Autowired
	private GeneralJobApplicationRepository generalJobApplicationRepository;
	
	@Autowired
	private JobApplicationService jobApplicationService;
	
	@Autowired
	private GeneralUserRepository generalUserRepository;
	
	private Optional<GeneralUser> authenticateUser(LoginRequest loginRequest) {
		GeneralUser user = this.generalUserRepository.findByUsernameAndPassword(
			loginRequest.getUsername(), 
			PasswordUtil.hashPassword(loginRequest.getPassword())
		);
		return Optional.ofNullable(user);
	}
	
	@PostMapping("/user_apps")
	public ResponseEntity<Iterable<GeneralJobApplication>> getUserApplications(@RequestBody LoginRequest loginRequest) {
		Optional<GeneralUser> userOpt = this.authenticateUser(loginRequest);
		if (userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		GeneralUser user = userOpt.get();
		return ResponseEntity.ok(this.jobApplicationService.getApplicationsByUser(user));
	}
	
	@PostMapping("/users")
	public ResponseEntity<String> createUser(@RequestBody GeneralUser user) {
		Iterable<GeneralJobApplication> allApps = this.generalJobApplicationRepository.findAll();
		for(GeneralJobApplication app : allApps) {
			if (app.getUser().getUsername().equals(user.getUsername())) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(String.format("User %s already exists.", user.getUsername()));
			}
		}
		user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
		this.generalUserRepository.save(user);
		return ResponseEntity.ok(String.format("User %s created successfully.", user.getUsername()));
	}
	
	@DeleteMapping("/users")
	public ResponseEntity<String> deleteUser(@RequestBody LoginRequest loginRequest) {
		Optional<GeneralUser> userOpt = this.authenticateUser(loginRequest);
		if (userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
		}
		GeneralUser user = userOpt.get();
		this.generalUserRepository.delete(user);
		return ResponseEntity.ok(String.format("User %s deleted successfully.", user.getUsername()));
	}
	
	@DeleteMapping("/applications")
	public ResponseEntity<String> deleteApplication(@RequestBody DeleteApplicationRequest req) {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername(req.getUsername());
		loginRequest.setPassword(req.getPassword());
		Optional<GeneralUser> userOpt = this.authenticateUser(loginRequest);
		if (userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
		}
		GeneralUser user = userOpt.get();
		Optional<GeneralJobApplication> appOpt = this.generalJobApplicationRepository.findById(req.getJobAppId());
		if (appOpt.isEmpty() || !appOpt.get().getUser().equals(user)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Application not found. ");
		}
		this.generalJobApplicationRepository.delete(appOpt.get());
		return ResponseEntity.ok(String.format("Application with ID %d deleted successfully.", req.getJobAppId()));
	}
	
	@PostMapping("/applications")
	public ResponseEntity<String> submitApplication(@RequestBody JobApplicationRequest req){
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername(req.getUsername());
		loginRequest.setPassword(req.getPassword());
		Optional<GeneralUser> userOpt = this.authenticateUser(loginRequest);
		if (userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
		}
		GeneralUser user = userOpt.get();
		GeneralJobApplication app = new GeneralJobApplication(req.getCompanyName(), req.getPositionTitle(), user);
		this.generalJobApplicationRepository.save(app);
		return ResponseEntity.ok(String.format("Application submitted for %s at %s", app.getCompanyName(), app.getPositionTitle()));
	}
}
