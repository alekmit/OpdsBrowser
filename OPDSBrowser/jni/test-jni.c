/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
#include <string.h>
#include <jni.h>
#include <sys/socket.h>
#include <errno.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <android/log.h>
#include <netdb.h>
/*#include "include/hello-jni.h"*/

jstring Java_home_example_opdsbrowser_MainActivity_stringFromJNI(JNIEnv* env, jobject obj)
{
    return (*env)->NewStringUTF(env, "JNI loading test!");
}

int Java_home_example_opdsbrowser_MainActivity_testConnect()
{
	 int sock;
	 struct sockaddr_in addr;
	 struct hostent* hs;
	 const char* TEST_TAG = "connection test";
	 const char* hostname = "flibusta.net";
	 if ((hs = gethostbyname(hostname)) == NULL ) {
	       return 1;
	 }
	 memcpy(&addr.sin_addr, hs->h_addr_list[0], hs->h_length);
	 __android_log_write(ANDROID_LOG_WARN, TEST_TAG, "host is resolved");
	 sock = socket(AF_INET, SOCK_STREAM, 0);
	 if(sock < 0) {
		 return 2;
	 }
	 __android_log_write(ANDROID_LOG_WARN, TEST_TAG, "A socket is created");
	 addr.sin_family = AF_INET;
	 addr.sin_port = htons(80);
	 //addr.sin_addr.s_addr = inet_addr("37.221.170.51");
	 if(connect(sock, (struct sockaddr*) &addr, sizeof(addr)) < 0){
	        return 3;
	 }
	 __android_log_write(ANDROID_LOG_WARN, TEST_TAG, "A socket is connected");
	 close(sock);
	 __android_log_write(ANDROID_LOG_WARN, TEST_TAG, "A socket is closed");
	 return 0;
}
