package dev.lucasnlm.arch.core.system

class NativeDataReader: DataReader() {
    override fun read(source: String): String = nativeRead(source)

    private external fun nativeRead(source: String): String
}
