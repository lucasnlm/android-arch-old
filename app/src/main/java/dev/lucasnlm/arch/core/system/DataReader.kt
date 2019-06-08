package dev.lucasnlm.arch.core.system

import java.io.InputStream

abstract class DataReader {
    abstract fun read(source: String): InputStream
}
