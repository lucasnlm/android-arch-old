package dev.lucasnlm.arch.system.model

data class ProductInfo(
    val model: String,
    val product: String,
    val deviceName: String,
    val bootloader: String,
    val boardName: String
)
