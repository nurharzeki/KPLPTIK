package com.kplptik

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.kplptik.APIdatamodels.ListV.DetailMatkulResponse
import com.kplptik.databinding.ActivityDetailMatkulDosenBinding
import com.kplptik.models.MatkulDiampu
import com.kplptik.networks.MainInterface
import com.kplptik.networks.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class DetailMatkulDosenActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailMatkulDosenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMatkulDosenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


//        val data: MatkulDiampu = MatkulDiampu(1,"DSIPW001","3 sks","Prof.Kemal.com","Pemograman Web", "Senin (08.00 - 12.00)", "H2.4")
//        binding.namaMatkulDosen.text = data.nama_matkul
//        binding.KodeMataKuliahDosen.text = data.kode_matkul
//        binding.bobotMataKuliahDosen.text = data.bobot
//        binding.dosPengDosen.text = data.pengampu
//        binding.jadwalDosen.text = data.jadwal
//        binding.kelasDosen.text = data.ruang_kuliah

        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE) ?: return
        val token = sharedPref.getString("token", null)
        Log.e("Token ->", token.toString())

        val getId =intent.getIntExtra("id_matkul",1)

        val client: MainInterface = RetrofitConfig().getService()
        val call: Call<DetailMatkulResponse> = client.detailMatkuldosen("Bearer "+token, getId)
        Log.e("SuccessToGetID", getId.toString())

        call.enqueue(object : Callback<DetailMatkulResponse>{
            override fun onResponse(
                call: Call<DetailMatkulResponse>,
                response: Response<DetailMatkulResponse>
            ) {
                val respon: DetailMatkulResponse? = response.body()
                Log.e("SuccDetailResponse", response.body().toString())
                binding.namaMatkulDosen.text = respon?.data?.namaMk
                binding.KodeMataKuliahDosen.text =respon?.data?.regMk
                binding.bobotMataKuliahDosen.text = respon?.data?.sks
                binding.dosPengDosen.text = respon?.data?.namaDosen
//                binding.jadwalDosen.text = respon?.data?.namaHari
//                binding.jadwalDosen2.text = respon?.data?.jamKuliah
//                binding.kelasDosen.text = respon?.data?.kodeRuang
            }

            override fun onFailure(call: Call<DetailMatkulResponse>, t: Throwable) {
                Log.e("FailDetailResponse", t.localizedMessage)
            }

        })
    }
}