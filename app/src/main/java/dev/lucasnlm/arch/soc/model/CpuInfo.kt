package dev.lucasnlm.arch.soc.model

data class CpuClockInfo(
    val clocks: List<Int>,
    val maxClock: Int?,
    val minClock: Int?
)

data class CpuInfo(
    val model: String?,
    val modelName: String?,
    val vendor: String?,
    val revision: String?,
    var architecture: String?,
    val cpuCores: Int,
    val clockInfo: CpuClockInfo,
    val stepping: String?,
    val bogoMips: String?,
    val bigLittle: Int,
    val abi: String,
    val serial: String?,
    val device: String?,
    val governor: String?,
    val flags: List<String>
)
