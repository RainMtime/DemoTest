//
// Created by chunyu on 2017/8/4.
//

#include <com_example_chunyu_demotest_ndk_TestNdk.h>


JNIEXPORT jstring JNICALL Java_com_example_chunyu_demotest_ndk_TestNdk_getfriend
        (JNIEnv *env, jobject thiz) {
    return (*env).NewStringUTF("hahaha");
}