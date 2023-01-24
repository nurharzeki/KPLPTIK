package com.kplptik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.kplptik.APIdatamodels.MatkulDiampuModel.DetailMatkulDiampuResponse
import com.kplptik.databinding.ActivityDetailMatkulDosenBinding
import com.kplptik.models.MatkulDiampu
import com.kplptik.networks.MainInterface
import com.kplptik.networks.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        val getId =intent.getIntExtra("id-matkul",10000)

//        val client: MainInterface = RetrofitConfig().getService()
//        val call: Call<DetailMatkulDiampuResponse> = client.detailmatkuldiampudosen("", 4)
//        Log.e("IDdarisebelah", getId.toString())
//        call.enqueue(object : Callback<DetailMatkulDiampuResponse>{
//            override fun onResponse(
//                call: Call<DetailMatkulDiampuResponse>,
//                response: Response<DetailMatkulDiampuResponse>
//            ) {
//                val respon: DetailMatkulDiampuResponse? = response.body()
//                Log.e("SuccDetailResponse", response.body().toString())
//            }
//
//            override fun onFailure(call: Call<DetailMatkulDiampuResponse>, t: Throwable) {
//                Log.e("FailDetailResponse", t.localizedMessage)
//            }

//        })
    }
}