#include <jni.h>
#include <thread>
#include <unistd.h>

#include "native_data_reader.h"

extern "C" JNIEXPORT jint JNICALL
Java_dev_lucasnlm_arch_core_system_NativeDeviceInfo_readCpuCoresNumber(JNIEnv*, jobject) {

    // Return the CPU cores count from sysconf, otherwise on Kotlin side
    // the return may be wrong (e.g, using emulator).

    return (int) sysconf(_SC_NPROCESSORS_ONLN);
}

extern "C" JNIEXPORT jstring JNICALL
Java_dev_lucasnlm_arch_core_system_NativeDataReader_nativeRead(JNIEnv* env, jobject, jstring source) {
    const char *input_source = env->GetStringUTFChars(source, nullptr);
    std::string result = arch::NativeDataReader(input_source).read();
    return env->NewStringUTF(result.c_str());
}
