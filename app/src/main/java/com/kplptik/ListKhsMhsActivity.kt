package com.kplptik

import android.content.Context
import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.APIdatamodels.KhsMahasiswaModel.DetailItem
import com.kplptik.APIdatamodels.KhsMahasiswaModel.KhsMahasiswaResponse
import com.kplptik.APIdatamodels.KrsMahasiswaModel.KrsMahasiswaResponse
import com.kplptik.adapters.AdapterListKhsMatkul
import com.kplptik.databinding.ActivityListKhsMhsBinding
import com.kplptik.models.ListKhsMatkul
import com.kplptik.networks.MainInterface
import com.kplptik.networks.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListKhsMhsActivity : AppCompatActivity() {

    lateinit var binding: ActivityListKhsMhsBinding
    lateinit var adapter: AdapterListKhsMatkul
    lateinit var rvlistkhssemester: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListKhsMhsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE) ?: return
        val token = sharedPref.getString("token", null)
        Log.e("Token ->", token.toString())

        val data = ArrayList<DetailItem>()
//        data.add(ListKhsMatkul(1,"Ganjil 2020",22, 3.55F))
//        data.add(ListKhsMatkul(2,"Genap 2021",22,3.55F))
//        data.add(ListKhsMatkul(3,"Ganjil 2021",22,3.55F))
//        data.add(ListKhsMatkul(4,"Genap 2022",22,3.55F))
//        data.add(ListKhsMatkul(5,"Ganjil 2022",22,3.55F))

        rvlistkhssemester = binding.rvListKhsSemester
        adapter = AdapterListKhsMatkul(data)

        val client: MainInterface = RetrofitConfig().getService()

        val call: Call<KhsMahasiswaResponse> = client.listKhsMahasiswa("Bearer "+token)
        call.enqueue(object : Callback<KhsMahasiswaResponse> {
            override fun onResponse(

                call: Call<KhsMahasiswaResponse>,
                response: Response<KhsMahasiswaResponse>
            ) {
                val respon: KhsMahasiswaResponse? = response.body()
                if (respon != null){

                    val list: List<DetailItem> = respon.detail as List<DetailItem>
//                    binding.textSemesterKrs.text = list[0].semester
                    adapter.setListMahasiswa(list as ArrayList<DetailItem>)
                }
                Log.d("Success", response.toString())

                adapter.setOnClickListener(object : AdapterListKhsMatkul.clickListener{
                    override fun onItemClick(position: Int) {
                        val intent = Intent(this@ListKhsMhsActivity, ListDetailKHSMatkulActivity::class.java)
                        if (respon != null) {
                            intent.putExtra("text_semester",respon.detail?.get(position)?.semester)
                            intent.putExtra("ips",respon.detail?.get(position)?.ips)
                            intent.putExtra("id_semester", respon.detail?.get(position)?.id)
                        }
                        startActivity(intent)
                    }
                })

            }

            override fun onFailure(call: Call<KhsMahasiswaResponse>, t: Throwable) {
                Toast.makeText(this@ListKhsMhsActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

        rvlistkhssemester.layoutManager = LinearLayoutManager(this)
        rvlistkhssemester.adapter = adapter


    }
}