package com.kplptik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.kplptik.databinding.ActivityDetailMatkulDosenBinding
import com.kplptik.models.MatkulDiampu

class DetailMatkulDosenActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailMatkulDosenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMatkulDosenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val data: MatkulDiampu = MatkulDiampu(1,"DSIPW001","3 sks","Prof.Kemal.com","Pemograman Web", "Senin (08.00 - 12.00)", "H2.4")
        binding.namaMatkulDosen.text = data.nama_matkul
        binding.KodeMataKuliahDosen.text = data.kode_matkul
        binding.bobotMataKuliahDosen.text = data.bobot
        binding.dosPengDosen.text = data.pengampu
        binding.jadwalDosen.text = data.jadwal
        binding.kelasDosen.text = data.ruang_kuliah
    }
}