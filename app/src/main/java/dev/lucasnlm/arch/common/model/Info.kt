package dev.lucasnlm.arch.common.model

sealed class Info(
    val id: Long
) {
    class Named(
        id: Long = 0L,
        val name: String,
        val value: String? = null
    ) : Info(id) {
        fun hasValue(): Boolean = value != null && value.isNullOrEmpty().not()
    }

    class Tag(
        id: Long = 0L,
        val name: String
    ) : Info(id)
}
