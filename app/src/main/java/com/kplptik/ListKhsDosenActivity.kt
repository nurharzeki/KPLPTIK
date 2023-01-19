package com.kplptik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.adapters.AdapterListKhsMatkul
import com.kplptik.adapters.AdapterListKhsMatkulDosen
import com.kplptik.databinding.ActivityListKhsDosenBinding
import com.kplptik.models.ListKhsDosen
import com.kplptik.models.ListKhsMatkul

class ListKhsDosenActivity : AppCompatActivity() {

    lateinit var binding: ActivityListKhsDosenBinding
    lateinit var adapter: AdapterListKhsMatkulDosen
    lateinit var rvlistkhssemesterdosen: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListKhsDosenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = ArrayList<ListKhsDosen>()
        data.add(ListKhsDosen(1,"Ganjil 2020",22, 3.55F))
        data.add(ListKhsDosen(2,"Genap 2021",22,3.55F))
        data.add(ListKhsDosen(3,"Ganjil 2021",22,3.55F))
        data.add(ListKhsDosen(4,"Genap 2022",22,3.55F))
        data.add(ListKhsDosen(5,"Ganjil 2022",22,3.55F))
        data.add(ListKhsDosen(1,"Ganjil 2020",22, 3.55F))
        data.add(ListKhsDosen(2,"Genap 2021",22,3.55F))
        data.add(ListKhsDosen(3,"Ganjil 2021",22,3.55F))
        data.add(ListKhsDosen(4,"Genap 2022",22,3.55F))
        data.add(ListKhsDosen(5,"Ganjil 2022",22,3.55F))

        rvlistkhssemesterdosen = binding.rvListKhsSemesterDosen
        adapter = AdapterListKhsMatkulDosen(data)

        rvlistkhssemesterdosen.layoutManager = LinearLayoutManager(this)
        rvlistkhssemesterdosen.adapter = adapter

        adapter.setOnClickListener(object : AdapterListKhsMatkulDosen.clickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@ListKhsDosenActivity, ListDetailKhsDosenActivity::class.java)
                startActivity(intent)
            }
        })

    }
}