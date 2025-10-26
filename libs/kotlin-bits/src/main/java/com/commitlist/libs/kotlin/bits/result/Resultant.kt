/*
 * Copyright (C) 2025 Kaushik Saurabh
 * SPDX-License-Identifier: Apache-2.0
 */
package com.commitlist.libs.kotlin.bits.result

sealed interface Resultant<T : Any> {
    data class Success<T : Any>(val value: T) : Resultant<T>

    data class Failure(val error: Throwable) : Resultant<Nothing>
}
