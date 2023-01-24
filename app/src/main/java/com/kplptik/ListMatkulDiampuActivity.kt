package com.kplptik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.APIdatamodels.MatkulDiampuModel.DataItem
import com.kplptik.APIdatamodels.MatkulDiampuModel.MatkulDiampuResponse
import com.kplptik.adapters.adapterMatkulDiampu
import com.kplptik.databinding.ActivityListMatkulDiampuBinding
import com.kplptik.models.MatkulDiampu
import com.kplptik.networks.MainInterface
import com.kplptik.networks.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListMatkulDiampuActivity : AppCompatActivity() {

    lateinit var rvlistmatkul:RecyclerView
    lateinit var adapter:adapterMatkulDiampu
    lateinit var binding: ActivityListMatkulDiampuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMatkulDiampuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        val progressbar = binding.progressBarListMatkul
        progressbar.visibility = View.GONE

        val adapter: adapterMatkulDiampu = adapterMatkulDiampu(ArrayList())
        val data =ArrayList<DataItem>()

//        val data =ArrayList<>()
//        data.add(MatkulDiampu(1,"DSIPW001","3 sks","Prof.Kemal.com","Pemograman Web", "Senin (08.00 - 12.00)", "H2.4"))
//        data.add(MatkulDiampu(2,"DSIPBO001","4 sks","Prof.Kemal.com","Pemograman Berorientasi Object", "Selasa (09.00 - 12.50)", "H2.3"))
//        data.add(MatkulDiampu(3,"DSIPA001","3 sks","Prof.Kemal.com","Pemograman Android", "Rabu (14.00 - 16.00)", "H2.4"))

        rvlistmatkul = binding.rvListMatKulDiampu

        val client: MainInterface = RetrofitConfig().getService()
        progressbar.visibility = View.VISIBLE

        val call: Call<MatkulDiampuResponse> = client.listmatkuldiampudosen(" ","198201182008121002")
        call.enqueue(object : Callback<MatkulDiampuResponse>{
            override fun onResponse(
                call: Call<MatkulDiampuResponse>,
                response: Response<MatkulDiampuResponse>
            ) {
                val respon:MatkulDiampuResponse? = response.body()
                if (respon != null){
                    val list: List<DataItem> = respon.data as List<DataItem>
                    adapter.setListMatkul(list as ArrayList<DataItem>)
                    progressbar.visibility = View.GONE
                }
                Log.d("Success", response.toString())
            }

            override fun onFailure(call: Call<MatkulDiampuResponse>, t: Throwable) {
                progressbar.visibility = View.GONE
                Toast.makeText(this@ListMatkulDiampuActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

        rvlistmatkul.layoutManager =LinearLayoutManager(this)
        rvlistmatkul.adapter = adapter

//        val getNim = intent.getStringExtra("nim")
//        binding.tvNimMhsLogKp.text = getNim.toString()

        adapter.setOnClickListener(object: adapterMatkulDiampu.clickListener{
            override fun onItemClick(position: Int) {
                val id: DataItem
                val intent = Intent(this@ListMatkulDiampuActivity, DetailMatkulDosenActivity::class.java)
                intent.putExtra("id-matkul", data[position].idMatkul)
                Log.e("IDSekarang", data[position].toString())
                startActivity(intent)
            }
        })
    }
}