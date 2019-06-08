package dev.lucasnlm.arch.core.system

import org.junit.Assert.*
import org.junit.Test
import java.io.File
import java.io.FileNotFoundException

class InternalDataReaderTest {

    @Test
    fun testRead() {
        val content = "The quick brown fox jumps over the lazy dog."
        val fileName = "test.txt"
        val file = File(fileName).apply {
            writeText(content)
        }

        val internalDataReader = InternalDataReader()

        assertTrue(file.exists())

        val readText: String = internalDataReader.read(fileName).use { inputStream ->
            inputStream.bufferedReader().readLines().map {
                it.replace("\t", "")
            }.firstOrNull().toString()
        }

        assertEquals(content, readText)
        file.delete()
    }

    @Test(expected = FileNotFoundException::class)
    fun testReadInvalidFile() {
        val internalDataReader = InternalDataReader()
        assertNull(internalDataReader.read("test.txt"))
    }
}
