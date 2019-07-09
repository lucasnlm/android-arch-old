package dev.lucasnlm.arch.core.system

import org.junit.Assert.*
import org.junit.Test
import java.io.File
import java.io.FileNotFoundException

class InternalDataReaderTest {

    @Test
    fun testRead() {
        val content = "The quick brown fox jumps over the lazy dog."
        val file = File(TEST_FILE).apply {
            writeText(content)
        }

        assertTrue(file.exists())

        val internalDataReader = InternalDataReader()
        val readText: String = internalDataReader.read(TEST_FILE)
        assertEquals(content, readText)

        file.delete()
        assertFalse(file.exists())
    }

    @Test(expected = FileNotFoundException::class)
    fun testReadInvalidFile() {
        val internalDataReader = InternalDataReader()
        assertNull(internalDataReader.read(TEST_FILE))
    }

    companion object {
        const val TEST_FILE = "test.txt"
    }
}
