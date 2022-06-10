package com.example.bankapp.domain.use_case.get_banks

import com.example.bankapp.common.Resource
import com.example.bankapp.data.remote.dto.toBank
import com.example.bankapp.domain.model.Bank
import com.example.bankapp.domain.repository.BankRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetBanksUseCase @Inject constructor(
    private val repository: BankRepository
) {

    operator fun invoke() : Flow<Resource<List<Bank>>> = flow {
        try {
            emit(Resource.Loading())
            val banks = repository.getBanks().map { it.toBank() }
            emit(Resource.Success(banks))
        }catch (e : HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Beklenmedik bir hata oluştu."))
        }catch (e : IOException){
            emit(Resource.Error("Sunucuya ulaşılamıyor. İnternet bağlnatınızı kontrol ediniz."))
        }
    }
}