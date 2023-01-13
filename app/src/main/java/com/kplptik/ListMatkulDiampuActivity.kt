package com.kplptik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.adapters.adapterMatkulDiampu
import com.kplptik.databinding.ActivityListMatkulDiampuBinding
import com.kplptik.models.MatkulDiampu

class ListMatkulDiampuActivity : AppCompatActivity() {

    lateinit var rvlistmatkul:RecyclerView
    lateinit var adapter:adapterMatkulDiampu
    lateinit var binding: ActivityListMatkulDiampuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMatkulDiampuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data =ArrayList<MatkulDiampu>()
        data.add(MatkulDiampu(1,"DSIPW001","3 sks","Prof.Kemal.com","Pemograman Web", "Senin (08.00 - 12.00)", "H2.4"))
        data.add(MatkulDiampu(2,"DSIPBO001","4 sks","Prof.Kemal.com","Pemograman Berorientasi Object", "Selasa (09.00 - 12.50)", "H2.3"))
        data.add(MatkulDiampu(3,"DSIPA001","3 sks","Prof.Kemal.com","Pemograman Android", "Rabu (14.00 - 16.00)", "H2.4"))

        rvlistmatkul = binding.rvListMatKulDiampu
        adapter = adapterMatkulDiampu(data)

        rvlistmatkul.layoutManager =LinearLayoutManager(this)
        rvlistmatkul.adapter = adapter

//        val getNim = intent.getStringExtra("nim")
//        binding.tvNimMhsLogKp.text = getNim.toString()

        adapter.setOnClickListener(object: adapterMatkulDiampu.clickListener{
            override fun onItemClick(position: Int) {

                val intent = Intent(this@ListMatkulDiampuActivity, DetailMatkulDosenActivity::class.java)
                startActivity(intent)
            }
        })
    }
}