package dev.lucasnlm.arch.system.model

data class SystemInfo(
    val androidApi: Int,
    val androidName: String?,
    val productInfo: ProductInfo,
    val brand: BrandInfo,
    val version: VersionInfo
)
