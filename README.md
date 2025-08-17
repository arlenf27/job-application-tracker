# Job Application Tracker

__Development Status: IN PROGRESS__

## Overview
This application aims to encompass the functionalities of a convenient job application tracker. It will provide all controllers, models, utilities, data access repositories, and other back-end components. 

## Technology
Languages: Java 21 (Microsoft OpenJDK 21), C++17 (g++ 14.2)

Main Framework: Java Spring/Spring Boot
* Data Access/ORM: JPA with Hibernate
* Database: PostgreSQL
	
Auxiliary Algorithms/Utilities (Scope TBD): JNI with C++

Package Manager: Maven
* External System Command Execution: exec-maven-plugin
	
## Development Status
### Implemented
* User and job application functionality
	* Create, delete job applications and users    
* Password Hashing with SHA-256   
* Admin User Access   

### Potential Future Scopes
* User/job application functionality upgrades/extensions
	* Resume/Cover letter/Documents features   
	* More fields for job applications and users     
	* Advanced searching/filtering capabilities     
* Parser using regex and/or NER (Named Entity Recognition) for convenient parsing of job descriptions
* Statistical metrics/analytics for users and job applications
* Job interview tracker
	* Automatic email alerts (Microsoft Grapb API, etc.)    
	* Calendar file generation or potential cloud calendar integration    
* Export capabilities (Excel, CSV, etc.)

### Potential Future Scopes Not Part of this Repository
* Web/mobile/desktop frontend (React, Android, Java Swing, etc.)m
		