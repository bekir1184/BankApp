package com.example.bankapp.presentation.bank_detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.bankapp.R
import com.example.bankapp.common.Constants.getLocation
import com.example.bankapp.databinding.FragmentBankDetailBinding
import com.example.bankapp.domain.model.Bank
import com.example.bankapp.presentation.bank_detail.maps.MapsFragment
import com.google.firebase.analytics.FirebaseAnalytics


class BankDetailFragment : Fragment() {
    private lateinit var binding : FragmentBankDetailBinding
    private lateinit var firebaseAnalytics : FirebaseAnalytics
    private val navArgs : BankDetailFragmentArgs by navArgs()
    private lateinit var bank: Bank
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBankDetailBinding.inflate(inflater,container,false)
        firebaseAnalytics = FirebaseAnalytics.getInstance(requireContext())
        bank = navArgs.bank
        logFirebaseAnalytics()
        setBankData()
        setMap()
        setOnClicks()
        return binding.root
    }

    private fun setOnClicks() {
        binding.goToNearestATM.setOnClickListener {
            val location = bank.dc_EN_YAKIM_ATM.getLocation(requireActivity())
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=${location.latitude},${location.longitude}")
            )
            startActivity(intent)
        }
        binding.goToBank.setOnClickListener {
            val location = bank.dc_ADRES.getLocation(requireActivity())
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=${location.latitude},${location.longitude}")
            )
            startActivity(intent)
        }
    }

    private fun setBankData() {
        binding.bankName.text = bank.dc_BANKA_SUBE
        binding.cityName.text = bank.dc_SEHIR
        binding.townName.text = bank.dc_ILCE
        binding.bankType.text = bank.dc_BANKA_TIPI
        binding.bankCode.text = bank.dc_BANK_KODU
        binding.nearestATM.text = bank.dc_EN_YAKIM_ATM
        binding.regionalCoordinatory.text = bank.dc_BOLGE_KOORDINATORLUGU
        binding.zipCode.text = bank.dc_POSTA_KODU
        binding.onOfLine.text = bank.dc_ON_OFF_LINE
        binding.onOfSite.text = bank.dc_ON_OFF_SITE
    }

    private fun setMap() {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mapFrame, MapsFragment(navArgs.bank.dc_ADRES))
        fragmentTransaction.commit()
    }

    private fun logFirebaseAnalytics() {
        val bundle = Bundle()
        bundle.putInt("ID",bank.ID)
        bundle.putString("BANKA_SUBE",bank.dc_BANKA_SUBE)
        bundle.putString("SEHIR",bank.dc_SEHIR)
        bundle.putString("ILCE",bank.dc_ILCE)
        bundle.putString("BANKA_TIPI",bank.dc_BANKA_TIPI)
        bundle.putString("BANKA_KODU",bank.dc_BANK_KODU)
        bundle.putString("EN_YAKIN_ATM",bank.dc_EN_YAKIM_ATM)
        bundle.putString("BOLGE_KOORDINATORLUGU",bank.dc_BOLGE_KOORDINATORLUGU)
        bundle.putString("POSTA_KODU",bank.dc_POSTA_KODU)
        bundle.putString("ON_OF_LINE",bank.dc_ON_OFF_LINE)
        bundle.putString("ON_OF_SITE",bank.dc_ON_OFF_SITE)

        firebaseAnalytics.logEvent("bank_data",bundle)
    }
}