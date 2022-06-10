package com.example.bankapp.data.repository

import com.example.bankapp.data.remote.Api
import com.example.bankapp.data.remote.dto.BankDto
import com.example.bankapp.domain.repository.BankRepository
import javax.inject.Inject

class BankRepositoryImpl @Inject constructor(
    private val api: Api
) : BankRepository {

    override suspend fun getBanks(): List<BankDto> {
        return api.getBanks()
    }
}