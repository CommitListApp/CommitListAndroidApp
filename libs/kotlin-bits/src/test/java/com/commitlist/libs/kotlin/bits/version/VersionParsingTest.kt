package com.commitlist.libs.kotlin.bits.version

import org.junit.Assert.assertEquals
import org.junit.Test

class VersionParsingTest {
    @Test
    fun `fromString with valid string returns correct Version`() {
        val versionString = "1.2.3"
        val expected = Version(1, 2, 3)
        assertEquals(expected, Version.fromString(versionString))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `fromString with invalid string throws exception`() {
        val versionString = "1.2"
        Version.fromString(versionString)
    }

    @Test
    fun `toString returns correct format`() {
        val version = Version(1, 2, 3)
        val expected = "1.2.3"
        assertEquals(expected, Version.toString(version))
    }
}