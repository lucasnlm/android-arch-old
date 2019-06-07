package dev.lucasnlm.arch.core.system

import android.os.Build

open class NativeDeviceInfo: DeviceInfo {

    override fun getCpuCoresNumber(): Int = readCpuCoresNumber()

    override fun getPlatformAbi(): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Build.SUPPORTED_ABIS.first()
        } else {
            @Suppress("DEPRECATION")
            Build.CPU_ABI
        }

    private external fun readCpuCoresNumber(): Int
}
