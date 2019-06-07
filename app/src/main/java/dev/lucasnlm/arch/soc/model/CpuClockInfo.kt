package dev.lucasnlm.arch.soc.model

data class CpuClockInfo(
    val clocks: List<Int>,
    val maxClock: Int?,
    val minClock: Int?
)
