package dev.lucasnlm.arch.core.system

import java.io.File
import java.io.InputStream

open class InternalDataReader: DataReader() {

    override fun read(source: String): InputStream = File(source).inputStream()
}
