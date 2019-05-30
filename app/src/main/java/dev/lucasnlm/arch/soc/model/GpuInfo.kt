package dev.lucasnlm.arch.soc.model

data class GpuInfo(
    val renderer: String,
    val vendor: String,
    val version: String,
    val extensions: List<String>
)
