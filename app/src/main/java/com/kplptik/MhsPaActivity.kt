package com.kplptik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.adapters.MhsPaAdapter
import com.kplptik.databinding.ActivityMhsPaBinding
import com.kplptik.models.MhsPa

class MhsPaActivity : AppCompatActivity() {
    lateinit var rvListMhsPaActivity: RecyclerView
    lateinit var adapter: MhsPaAdapter
    lateinit var binding: ActivityMhsPaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMhsPaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = ArrayList<MhsPa>()
        data.add(MhsPa("Hafiz Aulia","2011522028"))
        data.add(MhsPa("Harriko Zeki","2011522001"))
        data.add(MhsPa("Kemal","2011522002"))
        data.add(MhsPa("Iqbal Fitrahul","2011522003"))
        data.add(MhsPa("Boby D","2011522004"))
        data.add(MhsPa("Dwisuci ","2011522005"))
        data.add(MhsPa("Reyhan R","2011522006"))
        data.add(MhsPa("Zaim M","2011522007"))
        data.add(MhsPa("Apis","2011522008"))
        data.add(MhsPa("Andi","2011522009"))

        rvListMhsPaActivity = binding.rvMhsPa
        adapter = MhsPaAdapter(data)

        rvListMhsPaActivity.layoutManager = LinearLayoutManager(this)
        rvListMhsPaActivity.adapter = adapter

        adapter.setOnClickListener(object: MhsPaAdapter.clickListener{
            override fun onItemClick(position: Int){
                val detailMhsPaIntent = Intent (this@MhsPaActivity, DetailMahasiswaDosenActivity::class.java)
            }
        })

    }

}