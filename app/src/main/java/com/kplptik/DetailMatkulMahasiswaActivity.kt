package com.kplptik

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.kplptik.APIdatamodels.DetailMatkulMahasiswaModel.DetailMatkulMahasiswaResponse
import com.kplptik.APIdatamodels.ProfilDosenModel.ProfilDosenResponse
import com.kplptik.databinding.ActivityDetailMatkulMahasiswaBinding
import com.kplptik.networks.MainInterface
import com.kplptik.networks.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMatkulMahasiswaActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailMatkulMahasiswaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMatkulMahasiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE) ?: return
        val token = sharedPref.getString("token", null)
        Log.e("Token ->", token.toString())

        val progressBar = binding.pbDetailMatkulMhs
        val cardView = binding.cvDetailMatkulMhs

        progressBar.visibility = View.GONE
        cardView.visibility = View.GONE

        val getId =intent.getIntExtra("id_matkul",1)

        val client: MainInterface = RetrofitConfig().getService()
        progressBar.visibility = View.VISIBLE

        val call: Call<DetailMatkulMahasiswaResponse> = client.detailMatkulMahasiswa("Bearer "+token,getId)
        call.enqueue(object : Callback<DetailMatkulMahasiswaResponse> {
            override fun onResponse(
                call: Call<DetailMatkulMahasiswaResponse>,
                response: Response<DetailMatkulMahasiswaResponse>
            ) {
                val respon: DetailMatkulMahasiswaResponse? = response.body()
                if (respon != null){
//                    val list: List<Data> = respon.data as List<Data>
//                    adapter.setListMatkul(list as ArrayList<DataItem>)
//                    binding.namaDosenLogin.text = respon.data?.namaDosen
                    binding.namaMatkulMahasiswa.text = respon.data?.namaMk
                    binding.KodeMataKuliahMahasiswa.text = respon.data?.regMk
                    binding.bobotMataKuliahMahasiswa.text = respon.data?.sks + " SKS"
                    binding.dosPengMahasiswa.text = respon.data?.namaDosen
                    binding.jadwalHariMahasiswa.text = respon.data?.namaHari
                    binding.jadwalJamMahasiswa.text = respon.data?.jamKuliah
                    binding.kelasMahasiswa.text = respon.data?.kodeRuang

                }
                Log.d("Hola!!", response.toString())

                progressBar.visibility = View.GONE
                cardView.visibility = View.VISIBLE
            }

            override fun onFailure(call: Call<DetailMatkulMahasiswaResponse>, t: Throwable) {
                Toast.makeText(this@DetailMatkulMahasiswaActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
                cardView.visibility = View.VISIBLE
            }
        })

    }
}