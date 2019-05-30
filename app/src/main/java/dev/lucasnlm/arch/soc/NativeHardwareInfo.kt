package dev.lucasnlm.arch.soc

import android.os.Build

interface HardwareInfo {
    fun getCpuCoresNumber(): Int

    fun getPlatformAbi(): String
}

open class NativeHardwareInfo: HardwareInfo {

    override fun getCpuCoresNumber(): Int = readCpuCoresNumber()

    override fun getPlatformAbi(): String = Build.CPU_ABI // TODO fix this deprecated line

    private external fun readCpuCoresNumber(): Int
}
