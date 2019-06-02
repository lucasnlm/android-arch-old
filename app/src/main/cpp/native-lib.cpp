#include <jni.h>
#include <string>
#include <thread>
#include <unistd.h>

extern "C" JNIEXPORT jint JNICALL
Java_dev_lucasnlm_arch_core_system_NativeDeviceInfo_readCpuCoresNumber(
        JNIEnv*,
        jobject) {

    // Return the CPU cores count from sysconf, otherwise on Kotlin side
    // the return may be wrong (e.g, using emulator).

    return (int) sysconf(_SC_NPROCESSORS_ONLN);
}
