/*
 * Copyright (C) 2025 Kaushik Saurabh
 * SPDX-License-Identifier: Apache-2.0
 */
package com.commitlist.core.domain.model

import kotlinx.datetime.LocalDateTime

sealed interface ScheduleEnds {
    object Never : ScheduleEnds

    data class On(val dateTime: LocalDateTime) : ScheduleEnds

    data class Occurrence(val count: Int) : ScheduleEnds
}
