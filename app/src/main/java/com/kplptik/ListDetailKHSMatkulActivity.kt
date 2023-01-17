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
        data.add(ListDetailKhsMatkul(1,"MPSI","3 SKS","A++"))
        data.add(ListDetailKhsMatkul(1,"MPSI","3 SKS","A++"))
        data.add(ListDetailKhsMatkul(1,"MPSI","3 SKS","A++"))

        rvlistdetailkhsmatkul = binding.rvListDetailKhs
        adapter = AdapterListDetailKhsMatkul(data)

        rvlistdetailkhsmatkul.layoutManager = LinearLayoutManager(this)
        rvlistdetailkhsmatkul.adapter = adapter

        /*adapter.setOnClickListener(object : AdapterListDetailKhsMatkul.clickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@ListMatkulDiampuActivity, DetailMatkulDosenActivity::class.java)
                startActivity(intent)
            }
        })*/
    }
}