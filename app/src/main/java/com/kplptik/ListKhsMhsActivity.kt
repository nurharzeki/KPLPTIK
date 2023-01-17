package com.kplptik

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.adapters.AdapterListKhsMatkul
import com.kplptik.databinding.ActivityListKhsMhsBinding
import com.kplptik.models.ListKhsMatkul


class ListKhsMhsActivity : AppCompatActivity() {

    lateinit var binding: ActivityListKhsMhsBinding
    lateinit var adapter: AdapterListKhsMatkul
    lateinit var rvlistkhssemester: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListKhsMhsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = ArrayList<ListKhsMatkul>()
        data.add(ListKhsMatkul(1,"Ganjil 2020",22, 3.55F))
        data.add(ListKhsMatkul(2,"Genap 2021",22,3.55F))
        data.add(ListKhsMatkul(3,"Ganjil 2021",22,3.55F))
        data.add(ListKhsMatkul(4,"Genap 2022",22,3.55F))
        data.add(ListKhsMatkul(5,"Ganjil 2022",22,3.55F))

        rvlistkhssemester = binding.rvListKhsSemester
        adapter = AdapterListKhsMatkul(data)

        rvlistkhssemester.layoutManager = LinearLayoutManager(this)
        rvlistkhssemester.adapter = adapter

        adapter.setOnClickListener(object : AdapterListKhsMatkul.clickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@ListKhsMhsActivity, ListDetailKHSMatkulActivity::class.java)
                startActivity(intent)
            }
        })
    }
}