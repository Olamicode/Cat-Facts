package com.ola.consumewithinjection.data.repository

import com.ola.consumewithinjection.data.remote.dto.CatFact
import kotlinx.coroutines.flow.Flow

interface CatFactRepository {
   suspend fun getCatFact(amount: Int): Result<List<CatFact>>
}