package com.example.bankapp.data.remote.dto

import com.example.bankapp.domain.model.Bank

data class BankDto(
    val ID: Int,
    val dc_ADRES: String,
    val dc_ADRES_ADI: String,
    val dc_BANKA_SUBE: String,
    val dc_BANKA_TIPI: String,
    val dc_BANK_KODU: String,
    val dc_BOLGE_KOORDINATORLUGU: String,
    val dc_EN_YAKIM_ATM: String,
    val dc_ILCE: String,
    val dc_ON_OFF_LINE: String,
    val dc_ON_OFF_SITE: String,
    val dc_POSTA_KODU: String,
    val dc_SEHIR: String
)

fun BankDto.toBank() : Bank{
    return Bank(
        ID,
        dc_ADRES,
        dc_ADRES_ADI,
        dc_BANKA_SUBE,
        dc_BANKA_TIPI,
        dc_BANK_KODU,
        dc_BOLGE_KOORDINATORLUGU,
        dc_EN_YAKIM_ATM,
        dc_ILCE,
        dc_ON_OFF_LINE,
        dc_ON_OFF_SITE,
        dc_POSTA_KODU,
        dc_SEHIR
    )
}