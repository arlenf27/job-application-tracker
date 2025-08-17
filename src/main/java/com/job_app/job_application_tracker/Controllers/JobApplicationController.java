package com.job_app.job_application_tracker.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.job_app.job_application_tracker.Models.GeneralJobApplication;
import com.job_app.job_application_tracker.Models.GeneralUser;
import com.job_app.job_application_tracker.Repositories.GeneralJobApplicationRepository;
import com.job_app.job_application_tracker.Repositories.GeneralUserRepository;
import com.job_app.job_application_tracker.Utilities.PasswordUtil;
import com.job_app.job_application_tracker.DTO.LoginRequest;

/**
 * Controller for general job application operations that are not for specific a user.
 * Requires a special "admin" user to access. 
 */
@RestController
public class JobApplicationController {
	
	@Autowired
	private GeneralJobApplicationRepository generalJobApplicationRepository;
	
	@Autowired
	private GeneralUserRepository generalUserRepository;
	
	private Optional<GeneralUser> authenticateAdmin(LoginRequest loginRequest) {
		GeneralUser user = this.generalUserRepository.findByUsernameAndPassword(
			loginRequest.getUsername(), 
			PasswordUtil.hashPassword(loginRequest.getPassword())
		);
		return Optional.ofNullable(user).filter(GeneralUser::isAdmin);
	}
	
	@PostMapping("/num_apps_all_users")
	public ResponseEntity<String> numApplications(@RequestBody LoginRequest loginRequest) {
		Optional<GeneralUser> userOpt = this.authenticateAdmin(loginRequest);
		if (userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access denied. Only admin users can access this endpoint.");
		}
		long count = this.generalJobApplicationRepository.count();
		return ResponseEntity.ok(String.format("There are currently %d job applications in the database.", count));
	}
	
	@PostMapping("/all_apps")
	public ResponseEntity<Iterable<GeneralJobApplication>> getAllApplications(@RequestBody LoginRequest loginRequest) {
		Optional<GeneralUser> userOpt = this.authenticateAdmin(loginRequest);
		if (userOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return ResponseEntity.ok(this.generalJobApplicationRepository.findAll());
	}
}
