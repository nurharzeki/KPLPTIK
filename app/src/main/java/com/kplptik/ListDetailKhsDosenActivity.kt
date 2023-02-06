package com.kplptik

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.APIdatamodels.DetailKhsDosenModel.DetailKhsDosenResponse
import com.kplptik.APIdatamodels.DetailKhsDosenModel.DetailKhsItem
import com.kplptik.adapters.AdapterListDetailKhsMatkulDosen
import com.kplptik.databinding.ActivityListDetailKhsDosenBinding
import com.kplptik.networks.MainInterface
import com.kplptik.networks.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListDetailKhsDosenActivity : AppCompatActivity() {

    lateinit var binding: ActivityListDetailKhsDosenBinding
    lateinit var adapter: AdapterListDetailKhsMatkulDosen
    lateinit var rvlistdetailkhsmatkuldosen: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDetailKhsDosenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE) ?: return
        val token = sharedPref.getString("token", null)
        Log.e("Token ->", token.toString())

        val getSemester = intent.getStringExtra("semester")
        val getIps = intent.getStringExtra("ips")
        val getNim = intent.getStringExtra("nim")
        val getNama = intent.getStringExtra("nama")
        val getId = intent.getIntExtra("id",1)


        val data = ArrayList<DetailKhsItem>()
//        data.add(ListDetailKhsMatkulDosen(1,"Manajemen Proyek Sistem Informasi",3,"A"))
//        data.add(ListDetailKhsMatkulDosen(2,"Pemrograman Teknologi Bergerak",3,"B+"))
//        data.add(ListDetailKhsMatkulDosen(3,"E Bisnis",3,"B-"))
//        data.add(ListDetailKhsMatkulDosen(4,"Analisa dan erancangan Sistem, Informasi",3,"A"))
//        data.add(ListDetailKhsMatkulDosen(5,"Tata Kelola",3,"B+"))
//        data.add(ListDetailKhsMatkulDosen(6,"Proyek pengembangan Sistem Infoermasi",3,"B-"))

        rvlistdetailkhsmatkuldosen = binding.rvListDetailKhsDosen
        adapter = AdapterListDetailKhsMatkulDosen(data)

        val client: MainInterface = RetrofitConfig().getService()

        val call: Call<DetailKhsDosenResponse> = client.detailKhsDosen("Bearer "+token,getNim.toString(),getId.toString())
        call.enqueue(object : Callback<DetailKhsDosenResponse> {
            override fun onResponse(

                call: Call<DetailKhsDosenResponse>,
                response: Response<DetailKhsDosenResponse>
            ) {
                val respon: DetailKhsDosenResponse? = response.body()
                if (respon != null){

                    val list: List<DetailKhsItem> = respon.detailKhs as List<DetailKhsItem>
                    binding.textNamaMhsDosen2.text = getNama
                    binding.semesterMatkul2.text = "Semester "+getSemester
                    binding.ipsmatkul2.text = "IPS : "+getIps
                    adapter.setListDetailKhs(list as ArrayList<DetailKhsItem>)
                }
                Log.d("Success", response.toString())

                adapter.setOnClickListener(object : AdapterListDetailKhsMatkulDosen.clickListener{
                    override fun onItemClick(position: Int) {

                    }
                })

            }

            override fun onFailure(call: Call<DetailKhsDosenResponse>, t: Throwable) {
                Toast.makeText(this@ListDetailKhsDosenActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

        rvlistdetailkhsmatkuldosen.layoutManager = LinearLayoutManager(this)
        rvlistdetailkhsmatkuldosen.adapter = adapter

    }
}