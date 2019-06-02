package dev.lucasnlm.arch.core.system

import android.os.Build

interface DeviceInfo {
    fun getCpuCoresNumber(): Int

    fun getPlatformAbi(): String
}

open class NativeDeviceInfo: DeviceInfo {

    override fun getCpuCoresNumber(): Int = readCpuCoresNumber()

    override fun getPlatformAbi(): String = Build.CPU_ABI // TODO fix this deprecated line

    private external fun readCpuCoresNumber(): Int
}
