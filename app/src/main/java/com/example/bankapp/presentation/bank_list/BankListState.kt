package com.example.bankapp.presentation.bank_list

import com.example.bankapp.domain.model.Bank

data class BankListState(
    val isLoading : Boolean = false ,
    val banks : List<Bank> = emptyList() ,
    val error: String = ""
)
