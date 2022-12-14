package com.example.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterNotif: AdapterItem

    private val listTransactionTitle = listOf(
        ItemModel("Keluar", "Top Up E-Wallet", "Gopay - 08123123123", "Rp. 150.000"),
        ItemModel("Masuk", "Transaksi Masuk", "BRI - 3453 3434 3435", "Rp. 20.000"),
        ItemModel("Keluar", "Pembelian", "Telkomsel - 08123123123", "Rp. 1.500.000"),
        ItemModel("Keluar", "Pembelian", "BNI - 0896734212", "Rp. 150.000"),
        ItemModel("Masuk", "Gaji Infosys", "Mandiri - 78755112893", "Rp. 4.500.000"),
        ItemModel("Keluar", "Pembelian", "ShopeePay - 0897534877", "Rp. 780.000"),
        ItemModel("Masuk", "Top Up Steam", "OVO - 087590212903", "Rp. 659.000"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterNotif = AdapterItem(listTransactionTitle.toMutableList())

        val layoutManager = LinearLayoutManager(this)
        binding.rvTransaction.adapter = adapterNotif
        binding.rvTransaction.layoutManager = layoutManager

    }
}