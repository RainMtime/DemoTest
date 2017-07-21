
#include <com_example_chunyu_demotest_ndk_NdkTest.h>

JNIEXPORT jstring JNICALL Java_com_example_chunyu_demotest_ndk_NdkTest_getStringFromNative
  (JNIEnv *env, jobject obj){
  return env ->NewStringUTF("hello jni!");
  }

