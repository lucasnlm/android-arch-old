package dev.lucasnlm.arch.core.system

interface DeviceInfo {
    fun getCpuCoresNumber(): Int
    fun getPlatformAbi(): String
}
