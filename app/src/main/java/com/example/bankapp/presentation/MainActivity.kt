package com.example.bankapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.bankapp.R
import com.example.bankapp.check_network_connection.CheckNetworkConnection
import com.example.bankapp.common.ErrorDialog
import com.example.bankapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var checkNetworkConnection: CheckNetworkConnection
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        callNetworkConnection()
    }

    private fun callNetworkConnection() {
        checkNetworkConnection = CheckNetworkConnection(application)
        checkNetworkConnection.observe(this) { isConnected ->
            if (!isConnected) {
                val error =  ErrorDialog(
                    getString(R.string.wifi_error_title),
                    getString(R.string.wifi_error_text),
                    R.drawable.ic_baseline_wifi_off_24)
                error.show(supportFragmentManager,"WIFI-ERROR-TAG")
                binding.wifiError.visibility = View.VISIBLE
            }else{
                binding.wifiError.visibility = View.GONE
            }
        }
    }

}