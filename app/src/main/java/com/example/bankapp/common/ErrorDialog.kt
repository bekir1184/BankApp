package com.example.bankapp.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bankapp.databinding.FragmentErrorDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ErrorDialog(
    private var title:String ,
    private var content:String,
    private var img:Int) : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentErrorDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentErrorDialogBinding.inflate(layoutInflater)
        val view = binding.root
        binding.errorImg.setImageResource(img)
        binding.errorTitle.text = title
        binding.errorContent.text = content

        binding.okBtn.setOnClickListener {
            dismiss()
        }
        return view
    }

}