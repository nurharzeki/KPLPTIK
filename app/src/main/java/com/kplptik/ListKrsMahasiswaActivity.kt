package com.kplptik

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.APIdatamodels.KrsMahasiswaModel.DetailItem
import com.kplptik.APIdatamodels.KrsMahasiswaModel.KrsMahasiswaResponse
import com.kplptik.adapters.KrsMahasiswaAdapter
import com.kplptik.databinding.ActivityListKrsMahasiswaBinding
import com.kplptik.networks.MainInterface
import com.kplptik.networks.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListKrsMahasiswaActivity : AppCompatActivity() {
    lateinit var rvListKrsMahasiswaActivity: RecyclerView
    lateinit var adapter: KrsMahasiswaAdapter
    lateinit var binding: ActivityListKrsMahasiswaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListKrsMahasiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE) ?: return
        val token = sharedPref.getString("token", null)
        Log.e("Token ->", token.toString())

        val progressBar = binding.pbListKrsMhs
        progressBar.visibility = View.GONE

        val data = ArrayList<DetailItem>()
//        data.add(KrsMahasiswa("PBO C","Rabu (15.00 - 17.00)","H 2.5"))
//        data.add(KrsMahasiswa("APSI A","Senin (15.00 - 17.00)","H 2.4"))
//        data.add(KrsMahasiswa("MPSI","Kamis (15.00 - 17.00)","H 2.3"))
//        data.add(KrsMahasiswa("PPSI B","Senin (15.00 - 17.00)","H 2.6"))
//        data.add(KrsMahasiswa("AKDAT A","Jumat (15.00 - 17.00)","H 2.9"))
//        data.add(KrsMahasiswa("E-Bisnis C","Selasa (15.00 - 17.00)","H 2.10"))
//        data.add(KrsMahasiswa("TAKEL","Selasa (15.00 - 17.00)","H 2.2"))
//        data.add(KrsMahasiswa("E COMMERCE A","Kamis (15.00 - 17.00)","H 2.5"))
//        data.add(KrsMahasiswa("ISI B","Selasa (15.00 - 17.00)","H 2.5"))

        rvListKrsMahasiswaActivity = binding.rvKrsKelas
        adapter = KrsMahasiswaAdapter(data)

        val client: MainInterface = RetrofitConfig().getService()
        progressBar.visibility = View.VISIBLE

        val call: Call<KrsMahasiswaResponse> = client.listKrsMahasiswa("Bearer "+token)
        call.enqueue(object : Callback<KrsMahasiswaResponse> {
            override fun onResponse( call: Call<KrsMahasiswaResponse>, response: Response<KrsMahasiswaResponse>) {
                val respon: KrsMahasiswaResponse? = response.body()
                if (respon != null){
                    val list: List<DetailItem> = respon.detail as List<DetailItem>
                    binding.textSemesterKrs.text = "Semester : " + list[0].semester
                    adapter.setListMahasiswa(list as ArrayList<DetailItem>)
                }
                Log.d("Success", response.toString())

                adapter.setOnClickListener(object: KrsMahasiswaAdapter.clickListener{
                    override fun onItemClick(position: Int) {
                        val detailKrsMhsIntent = Intent(this@ListKrsMahasiswaActivity, DetailMatkulMahasiswaActivity::class.java)
                        if (respon != null) {
                            detailKrsMhsIntent.putExtra("id_matkul", respon.detail?.get(position)?.idMk)
                        }
                        startActivity(detailKrsMhsIntent)
                    }
                })
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<KrsMahasiswaResponse>, t: Throwable) {
                Toast.makeText(this@ListKrsMahasiswaActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
            }

        })

        rvListKrsMahasiswaActivity.layoutManager = LinearLayoutManager(this)
        rvListKrsMahasiswaActivity.adapter = adapter


    }
}