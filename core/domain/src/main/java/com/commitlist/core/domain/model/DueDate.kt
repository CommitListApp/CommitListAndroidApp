/*
 * Copyright (C) 2025 Kaushik Saurabh
 * SPDX-License-Identifier: Apache-2.0
 */
package com.commitlist.core.domain.model

import kotlinx.datetime.LocalDateTime

sealed interface DueDate {
    data class Set(val date: LocalDateTime) : DueDate

    object Unset : DueDate
}
