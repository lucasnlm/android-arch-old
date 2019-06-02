package dev.lucasnlm.arch.core.system

import java.io.InputStream

abstract class DataReader {
    abstract fun read(source: String): InputStream
}

open class InternalDataReader: DataReader() {

    override fun read(source: String): InputStream =
        ProcessBuilder(CAT_COMMAND, source).start().inputStream

    private companion object {
        const val CAT_COMMAND = "/system/bin/cat"
    }
}
