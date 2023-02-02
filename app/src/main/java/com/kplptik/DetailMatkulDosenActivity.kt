package com.kplptik

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.APIdatamodels.ListV.DetailMatkulResponse
import com.kplptik.APIdatamodels.ListV.JadwalItem
import com.kplptik.adapters.AdapterDetailMatkulDosen
import com.kplptik.databinding.ActivityDetailMatkulDosenBinding
import com.kplptik.networks.MainInterface
import com.kplptik.networks.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMatkulDosenActivity : AppCompatActivity() {

    lateinit var adapter: AdapterDetailMatkulDosen
    lateinit var binding: ActivityDetailMatkulDosenBinding
    lateinit var rvlistjadwal: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMatkulDosenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE) ?: return
        val token = sharedPref.getString("token", null)
        Log.e("Token ->", token.toString())

        val data = ArrayList<JadwalItem>()

        rvlistjadwal = binding.rvListJadwal
        adapter = AdapterDetailMatkulDosen(data)
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
                Log.d("SuccDetailResponse", response.body().toString())

                if (respon != null) {

                    binding.namaMatkulDosen.text = respon.data?.namaMk
                    binding.KodeMataKuliahDosen.text = respon.data?.regMk
                    binding.bobotMataKuliahDosen.text = respon.data?.sks.toString()


                    val list : List<JadwalItem> = respon.data?.jadwal as List<JadwalItem>
                    adapter.setListJadwal(list as ArrayList<JadwalItem>)


                }

                adapter.setOnClickListener(object : AdapterDetailMatkulDosen.clickListener{
                    override fun onItemClick(position: Int) {
                        
//                        Toast.makeText(this@DetailMatkulDosenActivity, "owch", Toast.LENGTH_SHORT).show()
                    }
                })

            }

            override fun onFailure(call: Call<DetailMatkulResponse>, t: Throwable) {
                Log.e("FailDetailResponse", t.localizedMessage)
            }

        })
        rvlistjadwal.layoutManager = LinearLayoutManager(this)
        rvlistjadwal.adapter = adapter
    }
}