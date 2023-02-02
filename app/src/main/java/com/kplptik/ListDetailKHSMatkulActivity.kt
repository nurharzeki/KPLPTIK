package com.kplptik

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.APIdatamodels.DetailKhsMhs.DetailItem
import com.kplptik.APIdatamodels.DetailKhsMhs.DetailKhsMahasiswaResponse
import com.kplptik.APIdatamodels.KhsMahasiswaModel.KhsMahasiswaResponse
import com.kplptik.adapters.AdapterListDetailKhsMatkul
import com.kplptik.adapters.AdapterListKhsMatkul
import com.kplptik.databinding.ActivityListDetailKhsMatkulBinding
import com.kplptik.models.ListDetailKhsMatkul
import com.kplptik.networks.MainInterface
import com.kplptik.networks.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListDetailKHSMatkulActivity : AppCompatActivity() {

    lateinit var binding: ActivityListDetailKhsMatkulBinding
    lateinit var adapter: AdapterListDetailKhsMatkul
    lateinit var rvlistdetailkhsmatkul: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDetailKhsMatkulBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE) ?: return
        val token = sharedPref.getString("token", null)
        Log.e("Token ->", token.toString())

        val getId = intent.getIntExtra("id_semester",9999999)
        val getSem = intent.getStringExtra("text_semester")
        val getIps = intent.getStringExtra("ips")

        val data = ArrayList<DetailItem>()
//        data.add(ListDetailKhsMatkul(1,"Manajemen Proyek Sistem Informasi",3,"A"))
//        data.add(ListDetailKhsMatkul(2,"Pemrograman Teknologi Bergerak",3,"B+"))
//        data.add(ListDetailKhsMatkul(3,"E Bisnis",3,"B-"))
//        data.add(ListDetailKhsMatkul(4,"Analisa dan erancangan Sistem, Informasi",3,"A"))
//        data.add(ListDetailKhsMatkul(5,"Tata Kelola",3,"B+"))
//        data.add(ListDetailKhsMatkul(6,"Proyek pengembangan Sistem Infoermasi",3,"B-"))

        rvlistdetailkhsmatkul = binding.rvListDetailKhs
        adapter = AdapterListDetailKhsMatkul(data)

        val client: MainInterface = RetrofitConfig().getService()

        val call: Call<DetailKhsMahasiswaResponse> = client.detailKhsMahasiswa("Bearer "+token,getId)
        call.enqueue(object : Callback<DetailKhsMahasiswaResponse> {
            override fun onResponse(

                call: Call<DetailKhsMahasiswaResponse>,
                response: Response<DetailKhsMahasiswaResponse>
            ) {
                val respon: DetailKhsMahasiswaResponse? = response.body()
                if (respon != null){

                    val list: List<DetailItem> = respon.detail as List<DetailItem>

                    binding.semesterMatkul.text = getSem.toString()
                    binding.ipsmatkul.text = getIps.toString()

                    adapter.setListDetailKhs(list as ArrayList<DetailItem>)
                }
                Log.d("Success", response.toString())

                adapter.setOnClickListener(object : AdapterListDetailKhsMatkul.clickListener{
                    override fun onItemClick(position: Int) {

                    }
                })

            }

            override fun onFailure(call: Call<DetailKhsMahasiswaResponse>, t: Throwable) {
                Toast.makeText(this@ListDetailKHSMatkulActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

        rvlistdetailkhsmatkul.layoutManager = LinearLayoutManager(this)
        rvlistdetailkhsmatkul.adapter = adapter

    }
}