/*
 * Copyright (C) 2025 Kaushik Saurabh
 * SPDX-License-Identifier: Apache-2.0
 */
package com.commitlist.libs.kotlin.bits.datetime

import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun LocalDateTime.plus(
    value: Int,
    unit: DateTimeUnit,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): LocalDateTime =
    this.toInstant(timeZone = timeZone).plus(value, unit, timeZone).toLocalDateTime(timeZone)

fun LocalDateTime.minus(
    value: Int,
    unit: DateTimeUnit,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): LocalDateTime =
    this.toInstant(timeZone = timeZone).minus(value, unit, timeZone).toLocalDateTime(timeZone)

fun LocalDateTime.plus(
    period: DateTimePeriod,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): LocalDateTime = this.toInstant(timeZone).plus(period, timeZone).toLocalDateTime(timeZone)
