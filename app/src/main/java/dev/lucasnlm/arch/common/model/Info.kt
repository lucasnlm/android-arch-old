package dev.lucasnlm.arch.common.model

open class Info(
    val id: Long
)

class NamedInfo(
    id: Long = 0,
    val name: String,
    val value: String? = null
) : Info(id)

class TagInfo(
    id: Long = 0,
    val name: String
) : Info(id)
