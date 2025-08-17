package com.job_app.job_application_tracker.Repositories;

import com.job_app.job_application_tracker.Models.GeneralUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralUserRepository extends JpaRepository<GeneralUser, Long> {
		GeneralUser findByUsernameAndPassword(String username, String password);
}
