/*
 * Copyright (C) 2025 Kaushik Saurabh
 * SPDX-License-Identifier: Apache-2.0
 */
package com.commitlist.core.domain.repository

import com.commitlist.core.domain.model.SubTask
import com.commitlist.libs.kotlin.bits.id.Id
import com.commitlist.libs.kotlin.bits.result.Resultant

interface SubTaskRepository {
    suspend fun create(task: SubTask): Resultant<Unit>

    suspend fun read(id: Id): Resultant<SubTask>

    suspend fun update(task: SubTask): Resultant<Unit>

    suspend fun delete(id: Id): Resultant<Unit>
}
