/*
 * Copyright (C) 2025 Kaushik Saurabh
 * SPDX-License-Identifier: Apache-2.0
 */
package com.commitlist.libs.kotlin.bits.result

/** Inspired by: https://oneeyedmen.com/failure-is-not-an-option-part-1.html */
sealed interface Either<out L : Any, out R : Any> {
    @JvmInline value class Left<out L : Any>(val value: L) : Either<L, Nothing>

    @JvmInline value class Right<out R : Any>(val value: R) : Either<Nothing, R>
}
