package com.kplptik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.adapters.AdapterListDetailKhsMatkul
import com.kplptik.databinding.ActivityListDetailKhsMatkulBinding
import com.kplptik.models.ListDetailKhsMatkul

class ListDetailKHSMatkulActivity : AppCompatActivity() {

    lateinit var binding: ActivityListDetailKhsMatkulBinding
    lateinit var adapter: AdapterListDetailKhsMatkul
    lateinit var rvlistdetailkhsmatkul: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDetailKhsMatkulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = ArrayList<ListDetailKhsMatkul>()
        data.add(ListDetailKhsMatkul(1,"Manajemen Proyek Sistem Informasi",3,"A"))
        data.add(ListDetailKhsMatkul(2,"Pemrograman Teknologi Bergerak",3,"B+"))
        data.add(ListDetailKhsMatkul(3,"E Bisnis",3,"B-"))
        data.add(ListDetailKhsMatkul(4,"Analisa dan erancangan Sistem, Informasi",3,"A"))
        data.add(ListDetailKhsMatkul(5,"Tata Kelola",3,"B+"))
        data.add(ListDetailKhsMatkul(6,"Proyek pengembangan Sistem Infoermasi",3,"B-"))

        rvlistdetailkhsmatkul = binding.rvListDetailKhs
        adapter = AdapterListDetailKhsMatkul(data)

        rvlistdetailkhsmatkul.layoutManager = LinearLayoutManager(this)
        rvlistdetailkhsmatkul.adapter = adapter

        adapter.setOnClickListener(object : AdapterListDetailKhsMatkul.clickListener{
            override fun onItemClick(position: Int) {
//                val intent = Intent(this@ListDetailKHSMatkulActivity, DetailMatkulDosenActivity::class.java)
//                startActivity(intent)
            }
        })
    }
}