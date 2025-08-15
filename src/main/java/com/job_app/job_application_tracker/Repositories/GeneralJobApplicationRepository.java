package com.job_app.job_application_tracker.Repositories;

import com.job_app.job_application_tracker.Models.GeneralJobApplication;
import com.job_app.job_application_tracker.Models.GeneralUser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralJobApplicationRepository extends JpaRepository<GeneralJobApplication, Long> {
	List<GeneralJobApplication> findByUser(GeneralUser user);

}
