package com.example.bankapp.presentation.bank_detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bankapp.R
import com.example.bankapp.common.Constants.getLocation
import com.example.bankapp.databinding.FragmentBankDetailBinding
import com.example.bankapp.presentation.bank_detail.maps.MapsFragment


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
        setOnClicks()
        return binding.root
    }

    private fun setOnClicks() {
        binding.goToNearestATM.setOnClickListener {
            val location = navArgs.bank.dc_EN_YAKIM_ATM.getLocation(requireActivity())
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=${location.latitude},${location.longitude}")
            )
            startActivity(intent)
        }
        binding.goToBank.setOnClickListener {
            val location = navArgs.bank.dc_ADRES.getLocation(requireActivity())
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=${location.latitude},${location.longitude}")
            )
            startActivity(intent)
        }
        binding.backPress.setOnClickListener {
            val action = BankDetailFragmentDirections.actionBankDetailFragmentToBankListFragment()
            findNavController().navigate(action)
        }
    }

    private fun setBankData() {
        binding.bankName.text = navArgs.bank.dc_BANKA_SUBE
        binding.cityName.text = navArgs.bank.dc_SEHIR
        binding.townName.text = navArgs.bank.dc_ILCE
        binding.bankType.text = navArgs.bank.dc_BANKA_TIPI
        binding.bankCode.text = navArgs.bank.dc_BANK_KODU
        binding.nearestATM.text = navArgs.bank.dc_EN_YAKIM_ATM
        binding.regionalCoordinatory.text = navArgs.bank.dc_BOLGE_KOORDINATORLUGU
        binding.zipCode.text = navArgs.bank.dc_POSTA_KODU
        binding.onOfLine.text = navArgs.bank.dc_ON_OFF_LINE
        binding.onOfSite.text = navArgs.bank.dc_ON_OFF_SITE
    }

    private fun setMap() {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mapFrame, MapsFragment(navArgs.bank.dc_ADRES))
        fragmentTransaction.commit()
    }
}