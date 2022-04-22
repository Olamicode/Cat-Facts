package com.ola.consumewithinjection.data.di

import com.ola.consumewithinjection.data.remote.CatFactApi
import com.ola.consumewithinjection.data.repository.CatFactRepository
import com.ola.consumewithinjection.data.repository.CatFactRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideCatFactApi(okHttpClient: OkHttpClient) : CatFactApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(CatFactApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatFactApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCatFactRepository(api: CatFactApi): CatFactRepository {
        return CatFactRepositoryImpl(api)
    }
}