package com.example.bankapp.presentation.bank_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankapp.R
import com.example.bankapp.databinding.FragmentBankListBinding
import com.example.bankapp.domain.model.Bank
import com.example.bankapp.presentation.bank_list.adapter.BankAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BankListFragment() : Fragment() {
    private lateinit var binding: FragmentBankListBinding
    private val bankListViewModel : BankListViewModel by viewModels()
    private var wholeBanklist  = mutableListOf<Bank>()
    private val bankAdapter = BankAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBankListBinding.inflate(layoutInflater,container,false)
        setObserver()
        setOnClicks()
        return binding.root
    }

    private fun setOnClicks() {
        bankAdapter.setOnItemCoinClickListener {
            val action = BankListFragmentDirections.actionBankListFragmentToBankDetailFragment(it)
            findNavController().navigate(action)
        }
    }

    private fun setObserver() {
        bankListViewModel.state.observe(viewLifecycleOwner){
            if(it != null){
                if(it.banks.isNotEmpty()){
                    wholeBanklist.addAll(it.banks)
                    bankAdapter.submitList(it.banks)
                    binding.searchBar.setText("")
                    setRecyclerView()
                    binding.shimmerLayout.visibility = View.GONE
                }
                if (it.error.isNotBlank()){
                    println(it.error)
                }
                if (it.isLoading){
                    binding.shimmerLayout.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setRecyclerView() {
        binding.bankRecyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayout.VERTICAL,
            false
        )
        binding.bankRecyclerView.apply {
            adapter = bankAdapter
        }
        binding.searchBar.doOnTextChanged { text, _, _, _ ->
            bankAdapter.submitList(wholeBanklist.filter {
                it.dc_SEHIR!!.contains(
                    (text ?: ""),
                    true
                )
            })
        }

    }
}