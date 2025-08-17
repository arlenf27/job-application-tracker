package com.job_app.job_application_tracker.Models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GeneralUser implements User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	private String password;
	private String email;
	
	private boolean isAdmin;
	
	public GeneralUser() {
		this.isAdmin = false;
		/* Default constructor for JPA */
	}
	
	public GeneralUser(String username, String password, boolean isAdmin) {
		this.username = username;
		this.password = password;
		this.email = null; 
		this.isAdmin = isAdmin;
	}
	
	public GeneralUser(String username, String password, String email, boolean isAdmin) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.isAdmin = isAdmin;
	}

	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public boolean isAdmin() {
		return this.isAdmin;
	}

	@Override
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
