/*
 * Copyright (C) 2025 Kaushik Saurabh
 * SPDX-License-Identifier: Apache-2.0
 */
package com.commitlist.core.domain.repository

import com.commitlist.core.domain.model.Commitment
import com.commitlist.libs.kotlin.bits.id.Id
import com.commitlist.libs.kotlin.bits.result.Resultant
import kotlinx.datetime.LocalDate

interface CommitmentRepository {
    suspend fun create(commitment: Commitment): Resultant<Unit>

    suspend fun read(id: Id): Resultant<Commitment>

    suspend fun update(commitment: Commitment): Resultant<Unit>

    suspend fun delete(id: Id): Resultant<Unit>

    suspend fun observe(date: LocalDate): List<Commitment>

    /** Both dates inclusive */
    suspend fun observe(from: LocalDate, to: LocalDate): List<Commitment>
}
