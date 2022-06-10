package com.example.bankapp.presentation.bank_list.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.R
import com.example.bankapp.databinding.OneRowBankItemBinding
import com.example.bankapp.domain.model.Bank
import com.example.bankapp.presentation.bank_list.adapter.BankAdapter.*

class BankAdapter() : ListAdapter<Bank, CustomViewHolder>(customCallBack) {

    private var onItemCoinClickListener: ((bank: Bank) -> Unit)? = null

    fun setOnItemCoinClickListener(onItemCoinClickListener: ((bank: Bank) -> Unit)?) {
        this.onItemCoinClickListener = onItemCoinClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.one_row_bank_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.bind(getItem(position))
    }
    
    inner class CustomViewHolder(
        private val binding :OneRowBankItemBinding
        ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemCoinClickListener?.invoke(
                    getItem(adapterPosition)
                )
            }
        }
        fun bind(bankModel: Bank) {
            with(binding) {
                bank = bankModel
            }
        }
    }

    companion object {
        val customCallBack = object : DiffUtil.ItemCallback<Bank>() {
            override fun areItemsTheSame(oldItem: Bank, newItem: Bank): Boolean {
                return oldItem.ID == newItem.ID
            }

            override fun areContentsTheSame(oldItem: Bank, newItem: Bank): Boolean {
                return oldItem == newItem
            }
        }
    }

    
}