package com.job_app.job_application_tracker.JNI;

public class JNICPP {
	
	static {
		System.loadLibrary("job_app_regex");
	}
	
	private static native String getTestNativeString(String input);
	
	public static String getStringFromNative(String input) {
		return JNICPP.getTestNativeString(input);
	}
}
