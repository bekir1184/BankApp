package com.example.bankapp.data.remote

import com.example.bankapp.data.remote.dto.BankDto
import retrofit2.http.GET

interface Api {

    @GET("bankdata")
    suspend fun getBanks() : List<BankDto>
}