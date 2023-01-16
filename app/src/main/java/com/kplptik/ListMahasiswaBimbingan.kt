package com.kplptik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.adapters.adapterListMahasiswaBimbingan
import com.kplptik.adapters.adapterMatkulDiampu
import com.kplptik.databinding.ActivityListMahasiswaBimbinganBinding
import com.kplptik.models.MahasiswaBimbingan
import com.kplptik.models.MatkulDiampu

class ListMahasiswaBimbingan : AppCompatActivity() {
    lateinit var rvListMhsBimbingan: RecyclerView
    lateinit var adapter:adapterListMahasiswaBimbingan
    lateinit var binding:ActivityListMahasiswaBimbinganBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListMahasiswaBimbinganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data =ArrayList<MahasiswaBimbingan>()
        data.add(MahasiswaBimbingan("Harriko Nur Harzeki",2011521024))
        data.add(MahasiswaBimbingan("Johnny Iqbal",2011521999))
        data.add(MahasiswaBimbingan("Satrio Bambang Nasution",1111521090))
        data.add(MahasiswaBimbingan("Joko Wididi ft. Ahok JR",2211522090))
        data.add(MahasiswaBimbingan("Nurharzeki Reborn",2011522090))

        rvListMhsBimbingan = binding.rvListMhsBimbingan
        adapter = adapterListMahasiswaBimbingan(data)

        rvListMhsBimbingan.layoutManager = LinearLayoutManager(this)
        rvListMhsBimbingan.adapter = adapter

        adapter.setOnClickListener(object: adapterListMahasiswaBimbingan.clickListener{
            override fun onItemClick(position: Int) {
                val detailMhsIntent = Intent(this@ListMahasiswaBimbingan, DetailMahasiswaDosenActivity::class.java)
                startActivity(detailMhsIntent)
            }
        })

    }
}