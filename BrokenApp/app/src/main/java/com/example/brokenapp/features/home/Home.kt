package com.example.brokenapp.features.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brokenapp.*
import com.example.brokenapp.data.AdapterItem
import com.example.brokenapp.data.ItemModel
import com.example.brokenapp.databinding.ActivityHomeBinding
import com.example.brokenapp.features.notification.Notification
import com.example.brokenapp.features.UserCardInterface
import com.example.brokenapp.features.history.HIstory

class Home : AppCompatActivity(), HomeContract {
    private lateinit var binding: ActivityHomeBinding
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
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userProfile.setUpperText("Abdullah Ibn Hassan")
        binding.userProfile.setLowerText("666 Poin")
        binding.userProfile.setTextColor(R.color.black, R.color.blue)
        binding.userProfile.setTextSize(16f, 12f)
        binding.userProfile.setIcon(R.drawable.ic_notif)
        binding.userProfile.setListener(object : UserCardInterface {
            override fun onClickIcon() {
                startActivity(Intent(this@Home, Notification::class.java))
            }
        })

        adapterNotif = AdapterItem(listTransactionTitle.toMutableList())

        val layoutManager = LinearLayoutManager(this)
        binding.rvTransaction.adapter = adapterNotif
        binding.rvTransaction.layoutManager = layoutManager

        adapterNotif.setOnClickItemListener(rvClickListener)

    }

    private val rvClickListener: (title: String, accountDetail: String, transactionAmount: String) -> Unit =
        {title, accountDetail, transactionAmount ->
            startActivity(Intent(this@Home, HIstory::class.java).apply {
                putExtra("accTitle", title)
                putExtra("accDetail", accountDetail)
                putExtra("accAmount", transactionAmount)
            })
    }

}