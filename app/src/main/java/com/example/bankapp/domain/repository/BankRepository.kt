package com.example.bankapp.domain.repository

import com.example.bankapp.data.remote.dto.BankDto

interface BankRepository {
    suspend fun getBanks() : List<BankDto>
}