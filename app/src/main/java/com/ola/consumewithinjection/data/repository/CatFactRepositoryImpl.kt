package com.ola.consumewithinjection.data.repository

import com.ola.consumewithinjection.data.remote.CatFactApi
import com.ola.consumewithinjection.data.remote.dto.CatFact
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

class CatFactRepositoryImpl(
    private val api: CatFactApi
): CatFactRepository {
    override suspend fun getCatFact(amount: Int): Result<List<CatFact>> {
        return try {
            Result.success(api.getCatFact(amount))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}