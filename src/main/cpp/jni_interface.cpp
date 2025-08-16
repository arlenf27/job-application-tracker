#include <string>
#include <cstring>
#include "com_job_app_job_application_tracker_JNI_JNICPP.h"
#include "job_app_regex_fields.h"

using namespace std;

extern "C"{
	JNIEXPORT jstring JNICALL Java_com_job_1app_job_1application_1tracker_JNI_JNICPP_getTestNativeString(JNIEnv* env, jclass cls, jstring str){
		const char* native_string = env->GetStringUTFChars(str, 0);
		const char* message_prefix = "Test from C++! You passed: ";
		char* result = new char[strlen(native_string) + strlen(message_prefix) + 1];
		*result = '\0';
		strcat(result, message_prefix);
		strcat(result, native_string);
		jstring result_jstring = env->NewStringUTF(result);
		delete[] result; 
		env->ReleaseStringUTFChars(str, native_string);
		return result_jstring;
	}
}
