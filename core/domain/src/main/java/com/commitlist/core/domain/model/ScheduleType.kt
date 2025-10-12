/*
 * Copyright (C) 2025 Kaushik Saurabh
 * SPDX-License-Identifier: Apache-2.0
 */
package com.commitlist.core.domain.model

import com.commitlist.libs.kotlin.bits.ranges.DateRange
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Month

sealed interface ScheduleType {
    object NONE : ScheduleType

    data class Once(val dateRange: DateRange) : ScheduleType

    sealed interface Recurring : ScheduleType {
        val dateRange: DateRange
        val ends: ScheduleEnds

        data class Daily(
            override val dateRange: DateRange,
            override val ends: ScheduleEnds = ScheduleEnds.Never,
        ) : Recurring

        data class Weekly(
            override val dateRange: DateRange,
            override val ends: ScheduleEnds = ScheduleEnds.Never,
            val days: Set<DayOfWeek>,
        ) : Recurring

        data class Monthly(
            override val dateRange: DateRange,
            override val ends: ScheduleEnds = ScheduleEnds.Never,
            val dayOfMonth: Int,
        ) : Recurring

        data class Yearly(
            override val dateRange: DateRange,
            override val ends: ScheduleEnds = ScheduleEnds.Never,
            val dayOfMonth: Int,
            val month: Month,
        ) : Recurring
    }
}
