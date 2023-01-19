package com.kplptik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.adapters.AdapterListDetailKhsMatkul
import com.kplptik.adapters.AdapterListDetailKhsMatkulDosen
import com.kplptik.databinding.ActivityListDetailKhsDosenBinding
import com.kplptik.databinding.ActivityListDetailKhsMatkulBinding
import com.kplptik.models.ListDetailKhsMatkul
import com.kplptik.models.ListDetailKhsMatkulDosen

class ListDetailKhsDosenActivity : AppCompatActivity() {

    lateinit var binding: ActivityListDetailKhsDosenBinding
    lateinit var adapter: AdapterListDetailKhsMatkulDosen
    lateinit var rvlistdetailkhsmatkuldosen: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDetailKhsDosenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = ArrayList<ListDetailKhsMatkulDosen>()
        data.add(ListDetailKhsMatkulDosen(1,"Manajemen Proyek Sistem Informasi",3,"A"))
        data.add(ListDetailKhsMatkulDosen(2,"Pemrograman Teknologi Bergerak",3,"B+"))
        data.add(ListDetailKhsMatkulDosen(3,"E Bisnis",3,"B-"))
        data.add(ListDetailKhsMatkulDosen(4,"Analisa dan erancangan Sistem, Informasi",3,"A"))
        data.add(ListDetailKhsMatkulDosen(5,"Tata Kelola",3,"B+"))
        data.add(ListDetailKhsMatkulDosen(6,"Proyek pengembangan Sistem Infoermasi",3,"B-"))

        rvlistdetailkhsmatkuldosen = binding.rvListDetailKhsDosen
        adapter = AdapterListDetailKhsMatkulDosen(data)

        rvlistdetailkhsmatkuldosen.layoutManager = LinearLayoutManager(this)
        rvlistdetailkhsmatkuldosen.adapter = adapter

        adapter.setOnClickListener(object : AdapterListDetailKhsMatkulDosen.clickListener{
            override fun onItemClick(position: Int) {
//                val intent = Intent(this@ListDetailKHSMatkulActivity, DetailMatkulDosenActivity::class.java)
//                startActivity(intent)
            }
        })

    }
}