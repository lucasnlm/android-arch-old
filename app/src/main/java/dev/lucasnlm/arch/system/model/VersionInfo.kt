package dev.lucasnlm.arch.system.model

data class VersionInfo(
    val base: String?,
    val release: String,
    val securityPatch: String?,
    val codename: String
)
