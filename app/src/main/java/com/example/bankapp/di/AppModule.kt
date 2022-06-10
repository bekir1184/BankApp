package com.example.bankapp.di

import com.example.bankapp.common.Constants
import com.example.bankapp.data.remote.Api
import com.example.bankapp.data.repository.BankRepositoryImpl
import com.example.bankapp.domain.repository.BankRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi() : Api{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideBankRepository(api : Api) : BankRepository{
        return BankRepositoryImpl(api)
    }
}