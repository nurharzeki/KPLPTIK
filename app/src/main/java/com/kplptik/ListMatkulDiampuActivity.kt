package com.kplptik

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.adapters.adapterMatkulDiampu
import com.kplptik.databinding.ActivityListMatkulDiampuBinding
import com.kplptik.models.ListMatkulDiampu

class ListMatkulDiampuActivity : AppCompatActivity() {

    lateinit var rvlistmatkul:RecyclerView
    lateinit var adapter:adapterMatkulDiampu
    lateinit var binding: ActivityListMatkulDiampuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMatkulDiampuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data =ArrayList<ListMatkulDiampu>()
        data.add(ListMatkulDiampu(1,"Pemograman Web", "Senin (08.00 - 11.00)", "H2.4"))
        data.add(ListMatkulDiampu(1,"Pemograman Berorientasi Object", "Selasa (08.00 - 11.00)", "H2.3"))
        data.add(ListMatkulDiampu(1,"Pemograman Android", "Rabu (08.00 - 11.00)", "H2.4"))

        rvlistmatkul = binding.rvListMatKulDiampu
        adapter = adapterMatkulDiampu(data)

        rvlistmatkul.layoutManager =LinearLayoutManager(this)
        rvlistmatkul.adapter = adapter
    }
}