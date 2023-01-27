package com.kplptik

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.APIdatamodels.ListMahasiswaBimbinganModel.DataItem
import com.kplptik.APIdatamodels.ListMahasiswaBimbinganModel.ListMahasiswaResponse

import com.kplptik.adapters.MhsPaAdapter
import com.kplptik.databinding.ActivityMhsPaBinding
import com.kplptik.models.MhsPa
import com.kplptik.networks.MainInterface
import com.kplptik.networks.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MhsPaActivity : AppCompatActivity() {
    lateinit var rvListMhsPaActivity: RecyclerView
    lateinit var adapter: MhsPaAdapter
    lateinit var binding: ActivityMhsPaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMhsPaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE) ?: return
        val token = sharedPref.getString("token", null)
        Log.e("Token ->", token.toString())

        val data = ArrayList<DataItem>()
//        data.add(MhsPa("Hafiz Aulia","2011522028"))
//        data.add(MhsPa("Harriko Zeki","2011522001"))
//        data.add(MhsPa("Kemal","2011522002"))
//        data.add(MhsPa("Iqbal Fitrahul","2011522003"))
//        data.add(MhsPa("Boby D","2011522004"))
//        data.add(MhsPa("Dwisuci ","2011522005"))
//        data.add(MhsPa("Reyhan R","2011522006"))
//        data.add(MhsPa("Zaim M","2011522007"))
//        data.add(MhsPa("Apis","2011522008"))
//        data.add(MhsPa("Andi","2011522009"))

        rvListMhsPaActivity = binding.rvMhsPa
        adapter = MhsPaAdapter(data)

        val client: MainInterface = RetrofitConfig().getService()

        val call: Call<ListMahasiswaResponse> = client.listMahasiswaBimbingan("Bearer "+token)
        call.enqueue(object : Callback<ListMahasiswaResponse> {
            override fun onResponse(
                call: Call<ListMahasiswaResponse>,
                response: Response<ListMahasiswaResponse>
            ) {
                val respon: ListMahasiswaResponse? = response.body()
                if (respon != null){
                    val list: List<DataItem> = respon.data as List<DataItem>
                    adapter.setListMahasiswa(list as ArrayList<DataItem>)
                }
                Log.d("Success", response.toString())
            }

            override fun onFailure(call: Call<ListMahasiswaResponse>, t: Throwable) {
                Toast.makeText(this@MhsPaActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

        rvListMhsPaActivity.layoutManager = LinearLayoutManager(this)
        rvListMhsPaActivity.adapter = adapter

        adapter.setOnClickListener(object: MhsPaAdapter.clickListener{
            override fun onItemClick(position: Int){
                val detailMhsPaIntent = Intent (this@MhsPaActivity, DetailMahasiswaDosenActivity::class.java)
                startActivity(detailMhsPaIntent)
            }
        })

    }

}