package com.example.bankapp.presentation.bank_list


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankapp.common.Resource
import com.example.bankapp.domain.use_case.get_banks.GetBanksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BankListViewModel @Inject constructor(
    private val getBanksUseCase: GetBanksUseCase
) : ViewModel() {

    private val _state = MutableLiveData<BankListState>()
    val state : LiveData<BankListState>
        get() = _state

    init {
        getBanks()
    }

    private fun getBanks(){
        getBanksUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = BankListState(banks = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = BankListState(error = "Beklenmedik bir hata oluştu.")
                }
                is Resource.Loading ->{
                    _state.value = BankListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}