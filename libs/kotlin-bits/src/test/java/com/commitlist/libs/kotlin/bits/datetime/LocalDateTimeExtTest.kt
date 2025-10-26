/*
 * Copyright (C) 2025 Kaushik Saurabh
 * SPDX-License-Identifier: Apache-2.0
 */
package com.commitlist.libs.kotlin.bits.datetime

import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalDateTimeExtTest {

    // A fixed time zone is used to make tests deterministic, especially around DST changes.
    private val timeZone = TimeZone.of("America/New_York")

    @Test
    fun `plus value and unit adds correctly`() {
        val start = LocalDateTime(2024, 1, 10, 10, 0)
        val expected = LocalDateTime(2024, 1, 12, 10, 0)

        val result = start.plus(2, DateTimeUnit.DAY, timeZone)

        assertEquals(expected, result)
    }

    @Test
    fun `minus value and unit subtracts correctly`() {
        val start = LocalDateTime(2024, 1, 10, 10, 0)
        val expected = LocalDateTime(2024, 1, 8, 10, 0)

        val result = start.minus(2, DateTimeUnit.DAY, timeZone)

        assertEquals(expected, result)
    }

    @Test
    fun `plus period adds correctly`() {
        val start = LocalDateTime(2024, 1, 10, 10, 0)
        val period = DateTimePeriod(days = 1, hours = 5, minutes = 30)
        val expected = LocalDateTime(2024, 1, 11, 15, 30)

        val result = start.plus(period, timeZone)

        assertEquals(expected, result)
    }

    @Test
    fun `plus value handles DST spring forward`() {
        // In America/New_York, 2024-03-10 at 2:00 AM, clocks jump to 3:00 AM.
        val start = LocalDateTime(2024, 3, 10, 1, 30)
        // Adding 2 hours should result in 4:30 AM, skipping the 2:xx hour.
        val expected = LocalDateTime(2024, 3, 10, 4, 30)

        val result = start.plus(2, DateTimeUnit.HOUR, timeZone)

        assertEquals(expected, result)
    }
}
