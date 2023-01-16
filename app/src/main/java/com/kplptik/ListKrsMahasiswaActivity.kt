package com.kplptik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.adapters.KrsMahasiswaAdapter
import com.kplptik.databinding.ActivityListKrsMahasiswaBinding
import com.kplptik.models.KrsMahasiswa

class ListKrsMahasiswaActivity : AppCompatActivity() {
    lateinit var rvListKrsMahasiswaActivity: RecyclerView
    lateinit var adapter: KrsMahasiswaAdapter
    lateinit var binding: ActivityListKrsMahasiswaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListKrsMahasiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = ArrayList<KrsMahasiswa>()
        data.add(KrsMahasiswa("PBO C","Rabu (15.00 - 17.00)","H 2.5"))
        data.add(KrsMahasiswa("APSI A","Senin (15.00 - 17.00)","H 2.4"))
        data.add(KrsMahasiswa("MPSI","Kamis (15.00 - 17.00)","H 2.3"))
        data.add(KrsMahasiswa("PPSI B","Senin (15.00 - 17.00)","H 2.6"))
        data.add(KrsMahasiswa("AKDAT A","Jumat (15.00 - 17.00)","H 2.9"))
        data.add(KrsMahasiswa("E-Bisnis C","Selasa (15.00 - 17.00)","H 2.10"))
        data.add(KrsMahasiswa("TAKEL","Selasa (15.00 - 17.00)","H 2.2"))
        data.add(KrsMahasiswa("E COMMERCE A","Kamis (15.00 - 17.00)","H 2.5"))
        data.add(KrsMahasiswa("ISI B","Selasa (15.00 - 17.00)","H 2.5"))

        rvListKrsMahasiswaActivity = binding.rvKrsKelas
        adapter = KrsMahasiswaAdapter(data)

        rvListKrsMahasiswaActivity.layoutManager = LinearLayoutManager(this)
        rvListKrsMahasiswaActivity.adapter = adapter

        adapter.setOnClickListener(object: KrsMahasiswaAdapter.clickListener{
            override fun onItemClick(position: Int) {
                val detailKrsMhsIntent = Intent(this@ListKrsMahasiswaActivity, DetailMatkulMahasiswaActivity::class.java)
            }

        })
    }
}