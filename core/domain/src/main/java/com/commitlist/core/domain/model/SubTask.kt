/*
 * Copyright (C) 2025 Kaushik Saurabh
 * SPDX-License-Identifier: Apache-2.0
 */
package com.commitlist.core.domain.model

import com.commitlist.libs.kotlin.bits.version.Version

data class SubTask(
    val id: String,
    val description: String,
    val scheduleType: ScheduleType,
    val version: Version,
)
