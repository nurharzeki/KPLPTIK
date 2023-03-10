package com.kplptik

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.APIdatamodels.KhsMahasiswaBimbinganModel.ListKhsDosenResponse
import com.kplptik.APIdatamodels.KhsMahasiswaModel.DetailItem
import com.kplptik.APIdatamodels.KhsMahasiswaModel.KhsMahasiswaResponse
import com.kplptik.adapters.AdapterListKhsMatkul
import com.kplptik.adapters.AdapterListKhsMatkulDosen
import com.kplptik.databinding.ActivityListKhsDosenBinding
import com.kplptik.networks.MainInterface
import com.kplptik.networks.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListKhsDosenActivity : AppCompatActivity() {

    lateinit var binding: ActivityListKhsDosenBinding
    lateinit var adapter: AdapterListKhsMatkulDosen
    lateinit var rvlistkhssemesterdosen: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListKhsDosenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE) ?:return
        val token = sharedPref.getString("token", null)
        Log.e("Token ->", token.toString())


        val data = ArrayList<com.kplptik.APIdatamodels.KhsMahasiswaBimbinganModel.DetailItem>()

//        data.add(ListKhsDosen(1,"Ganjil 2020",22, 3.55F))
//        data.add(ListKhsDosen(2,"Genap 2021",22,3.55F))
//        data.add(ListKhsDosen(3,"Ganjil 2021",22,3.55F))
//        data.add(ListKhsDosen(4,"Genap 2022",22,3.55F))
//        data.add(ListKhsDosen(5,"Ganjil 2022",22,3.55F))
//        data.add(ListKhsDosen(1,"Ganjil 2020",22, 3.55F))
//        data.add(ListKhsDosen(2,"Genap 2021",22,3.55F))
//        data.add(ListKhsDosen(3,"Ganjil 2021",22,3.55F))
//        data.add(ListKhsDosen(4,"Genap 2022",22,3.55F))
//        data.add(ListKhsDosen(5,"Ganjil 2022",22,3.55F))

        rvlistkhssemesterdosen = binding.rvListKhsSemesterDosen
        adapter = AdapterListKhsMatkulDosen(data)
        val getNim = intent.getStringExtra("nimMahasiswa")
        val client: MainInterface = RetrofitConfig().getService()

        val call: Call<ListKhsDosenResponse> = client.listKhsDosen("Bearer "+token,getNim.toString())
        call.enqueue(object : Callback<ListKhsDosenResponse> {
            override fun onResponse(

                call: Call<ListKhsDosenResponse>,
                response: Response<ListKhsDosenResponse>
            ) {
                val respon: ListKhsDosenResponse? = response.body()
                if (respon != null){

                    val list: List<com.kplptik.APIdatamodels.KhsMahasiswaBimbinganModel.DetailItem> = respon.detail as List<com.kplptik.APIdatamodels.KhsMahasiswaBimbinganModel.DetailItem>
                    binding.textNamaMhsDosen.text = list[0].namaMahasiswa
                    adapter.setListKhsDosen(list as ArrayList<com.kplptik.APIdatamodels.KhsMahasiswaBimbinganModel.DetailItem>)
                }
                Log.d("Success", response.toString())

                adapter.setOnClickListener(object : AdapterListKhsMatkul.clickListener{
                    override fun onItemClick(position: Int) {
                        val intent = Intent(this@ListKhsDosenActivity, ListDetailKhsDosenActivity::class.java)
                        if (respon != null) {
                            intent.putExtra("semester",respon.detail?.get(position)?.semester)
                            intent.putExtra("ips",respon.detail?.get(position)?.ips)
                            intent.putExtra("nim",getNim)
                            intent.putExtra("nama",respon.detail?.get(position)?.namaMahasiswa)
                            intent.putExtra("id",respon.detail?.get(position)?.id)
                        }
                        startActivity(intent)
                    }
                })

            }

            override fun onFailure(call: Call<ListKhsDosenResponse>, t: Throwable) {
                Toast.makeText(this@ListKhsDosenActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })


        rvlistkhssemesterdosen.layoutManager = LinearLayoutManager(this)
        rvlistkhssemesterdosen.adapter = adapter



    }
}