package com.job_app.job_application_tracker;

/* Import JUnit 5's @Test annotation for marking test methods */
import org.junit.jupiter.api.Test;

/* Import Spring Boot test annotations and MockMvc for simulating HTTP requests */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/* Import static methods for building requests and checking results */
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/* Tells Spring Boot to start the application context for testing */
@SpringBootTest
/* Automatically configures MockMvc for HTTP request simulation */
@AutoConfigureMockMvc
class JobApplicationTrackerApplicationTests {
	
    /* Injects the MockMvc object for simulating HTTP requests */
    @Autowired
    private MockMvc mockMvc;
    /* Test that the application context loads successfully */
    
    @Test
    void contextLoads() {
    	/* This test will pass if the application context loads without errors */
    }
    
    /* Test the /test endpoint with no parameters (should use default) */
    @Test
    void testTestEndpointWithDefaultValue() throws Exception {
        mockMvc.perform(get("/test"))
            .andExpect(status().isOk())
            .andExpect(content().string("Hello, this is a test endpoint with the follwing value: default_value"));
    }
    
    /* Test the /test endpoint with a custom parameter */
    @Test
    void testTestEndpointWithCustomValue() throws Exception {
        mockMvc.perform(get("/test").param("custom_test_value", "my_value"))
            .andExpect(status().isOk())
            .andExpect(content().string("Hello, this is a test endpoint with the follwing value: my_value"));
    }
}
