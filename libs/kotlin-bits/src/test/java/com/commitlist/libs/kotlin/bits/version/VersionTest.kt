/*
 * Copyright (C) 2025 Kaushik Saurabh
 * SPDX-License-Identifier: Apache-2.0
 */
package com.commitlist.libs.kotlin.bits.version

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.math.sign

@RunWith(Parameterized::class)
class VersionComparisonTest(
    private val v1: Version,
    private val v2: Version,
    private val expected: Int,
    private val message: String
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{3}")
        fun data(): Collection<Array<Any>> {
            return listOf(
                // Greater than
                arrayOf(Version(2, 0, 0), Version(1, 9, 9), 1, "v1 > v2 by major version"),
                arrayOf(Version(1, 1, 0), Version(1, 0, 9), 1, "v1 > v2 by minor version"),
                arrayOf(Version(1, 0, 1), Version(1, 0, 0), 1, "v1 > v2 by patch version"),
                arrayOf(Version(1, 2, 1), Version(1, 2), 1, "v1 > v2 when other has default patch"),

                // Less than
                arrayOf(Version(1, 9, 9), Version(2, 0, 0), -1, "v1 < v2 by major version"),
                arrayOf(Version(1, 0, 9), Version(1, 1, 0), -1, "v1 < v2 by minor version"),
                arrayOf(Version(1, 0, 0), Version(1, 0, 1), -1, "v1 < v2 by patch version"),

                // Equal
                arrayOf(Version(1, 2, 3), Version(1, 2, 3), 0, "v1 == v2 with all parts specified"),
                arrayOf(Version(1, 2), Version(1, 2, 0), 0, "v1 == v2 when patch is default")
            )
        }
    }

    @Test
    fun `version comparison`() {
        // Use sign to normalize the result of compareTo to -1, 0, or 1
        val actual = v1.compareTo(v2).sign
        assertEquals(message, expected, actual)
    }
}

