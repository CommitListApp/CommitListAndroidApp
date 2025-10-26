/*
 * Copyright (C) 2025 Kaushik Saurabh
 * SPDX-License-Identifier: Apache-2.0
 */
package com.commitlist.core.domain.model

import com.commitlist.libs.kotlin.bits.version.Version
import kotlin.time.Duration

data class Commitment(
    val id: String,
    val title: String,
    val description: String,
    val approximateTimeCommitment: Duration = Duration.INFINITE,
    val schedules: List<ScheduleType>,
    val subtasks: List<SubTask>,
    val version: Version,
    val dueDate: DueDate = DueDate.Unset,
    val state: CommitmentState,
)
