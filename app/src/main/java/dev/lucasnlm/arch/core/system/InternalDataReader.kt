package dev.lucasnlm.arch.core.system

import java.io.File

open class InternalDataReader: DataReader() {
    override fun read(source: String): String = File(source).reader().use { it.readText() }
}
