package com.job_app.job_application_tracker.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.job_app.job_application_tracker.JNI.JNICPP;

/**
 * Controller for testing purposes.
 * Provides endpoints to test custom request parameters and native JNI functionality.
 */
@RestController
public class TestController {
	
	@GetMapping("/test")
	public String test(@RequestParam(value = "custom_test_value", defaultValue = "default_value") String testValue) {
		return String.format("Hello, this is a test endpoint with the following value: %s", testValue);
	}
	
	@GetMapping("/test_native")
	public String testNative(@RequestParam(value = "custom_test_input", defaultValue = "default_input") String input) {
		return String.format("Native test output: %s", JNICPP.getStringFromNative(input));
	}

}
