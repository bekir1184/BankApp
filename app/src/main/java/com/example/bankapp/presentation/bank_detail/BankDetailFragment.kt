package com.example.bankapp.presentation.bank_detail

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.bankapp.R
import com.example.bankapp.databinding.FragmentBankDetailBinding
import com.example.bankapp.presentation.bank_detail.maps.MapsFragment
import com.google.android.gms.maps.model.LatLng
import java.io.IOException

class BankDetailFragment : Fragment() {
    private lateinit var binding : FragmentBankDetailBinding
    private val navArgs : BankDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBankDetailBinding.inflate(inflater,container,false)

        setBankData()
        setMap()

        return binding.root
    }

    private fun setBankData() {
        binding.bankName.text = navArgs.bank.dc_BANKA_SUBE
        binding.cityName.text = navArgs.bank.dc_SEHIR
        binding.townName.text = navArgs.bank.dc_ILCE
    }

    private fun setMap() {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mapFrame, MapsFragment(navArgs.bank.dc_ADRES))
        fragmentTransaction.commit()
    }
}