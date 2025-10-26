/*
 * Copyright (C) 2025 Kaushik Saurabh
 * SPDX-License-Identifier: Apache-2.0
 */
package com.commitlist.libs.kotlin.bits.ranges

import com.commitlist.libs.kotlin.bits.datetime.plus
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DateRangeTest {

    private val start = LocalDateTime(2024, 1, 1, 12, 0)
    private val end = LocalDateTime(2024, 1, 5, 12, 0)
    private val range = DateRange(start, end)

    @Test
    fun `contains - value within range returns true`() {
        val valueInside = LocalDateTime(2024, 1, 3, 12, 0)
        assertTrue(range.contains(valueInside))
    }

    @Test
    fun `contains - value on start boundary returns true`() {
        assertTrue(range.contains(start))
    }

    @Test
    fun `contains - value on end boundary returns true`() {
        assertTrue(range.contains(end))
    }

    @Test
    fun `contains - value outside range returns false`() {
        val valueOutside = LocalDateTime(2024, 1, 6, 12, 0)
        assertFalse(range.contains(valueOutside))
    }

    @Test
    fun `isEmpty - non-empty range returns false`() {
        assertFalse(range.isEmpty())
    }

    @Test
    fun `isEmpty - empty range returns true`() {
        val emptyRange = DateRange(end, start)
        assertTrue(emptyRange.isEmpty())
    }

    @Test
    fun `equals - same range returns true`() {
        val sameRange = DateRange(start, end)
        assertEquals(range, sameRange)
    }

    @Test
    fun `equals - different range returns false`() {
        val differentRange = DateRange(start, end.plus(1, DateTimeUnit.HOUR))
        assertNotEquals(range, differentRange)
    }

    @Test
    fun `equals - two empty ranges are equal`() {
        val empty1 = DateRange(end, start)
        val empty2 = DateRange(end.plus(1, DateTimeUnit.HOUR), start.plus(1, DateTimeUnit.HOUR))
        assertEquals(empty1, empty2)
    }

    @Test
    fun `hashCode - equal ranges have equal hashCodes`() {
        val sameRange = DateRange(start, end)
        assertEquals(range.hashCode(), sameRange.hashCode())
    }

    @Test
    fun `hashCode - empty ranges have same hashCode`() {
        val empty1 = DateRange(end, start)
        val empty2 = DateRange(end.plus(1, DateTimeUnit.HOUR), start.plus(1, DateTimeUnit.HOUR))
        assertEquals(empty1.hashCode(), empty2.hashCode())
    }

    @Test
    fun `toString - returns correct format`() {
        val expected = "2024-01-01T12:00..2024-01-05T12:00"
        assertEquals(expected, range.toString())
    }
}
