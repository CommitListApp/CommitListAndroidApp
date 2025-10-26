/*
 * Copyright (C) 2025 Kaushik Saurabh
 * SPDX-License-Identifier: Apache-2.0
 */
package com.commitlist.libs.kotlin.bits.ranges

import kotlinx.datetime.LocalDateTime

class DateRange(override val start: LocalDateTime, override val endInclusive: LocalDateTime) :
    ClosedRange<LocalDateTime> {

    override fun contains(value: LocalDateTime): Boolean {
        return value in start..endInclusive
    }

    override fun isEmpty(): Boolean {
        return endInclusive < start
    }

    override fun equals(other: Any?): Boolean {
        return other is DateRange &&
            ((isEmpty() && other.isEmpty()) ||
                start == other.start && endInclusive == other.endInclusive)
    }

    override fun hashCode(): Int {
        return if (isEmpty()) {
            -1
        } else {
            31 * start.hashCode() + endInclusive.hashCode()
        }
    }

    override fun toString(): String {
        return "$start..$endInclusive"
    }
}
