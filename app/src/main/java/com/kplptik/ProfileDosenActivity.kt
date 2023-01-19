package com.kplptik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.kplptik.APIdatamodels.MatkulDiampuModel.DataItem
import com.kplptik.APIdatamodels.MatkulDiampuModel.MatkulDiampuResponse
import com.kplptik.APIdatamodels.ProfilDosenModel.Data
import com.kplptik.APIdatamodels.ProfilDosenModel.ProfilDosenResponse
import com.kplptik.databinding.ActivityProfileDosenBinding
import com.kplptik.networks.MainInterface
import com.kplptik.networks.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileDosenActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileDosenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileDosenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        val client: MainInterface = RetrofitConfig().getService()

        val call: Call<ProfilDosenResponse> = client.profildosen(" ","199308152022032017")
        call.enqueue(object : Callback<ProfilDosenResponse> {
            override fun onResponse(
                call: Call<ProfilDosenResponse>,
                response: Response<ProfilDosenResponse>
            ) {
                val respon: ProfilDosenResponse? = response.body()
                if (respon != null){
//                    val list: List<Data> = respon.data as List<Data>
//                    adapter.setListMatkul(list as ArrayList<DataItem>)
                    binding.namaDosenLogin.text = respon.data?.namaDosen
                    binding.nipDosenLogin.text = respon.data?.nip
                    binding.emailDosenLogin.text = respon.data?.email
                    binding.jurusanDosenLogin.text = respon.data?.namaJur
                    binding.fakultasDosenLogin.text = respon.data?.namaFak
                    binding.alamatDosenLogin.text = respon.data?.alamat
                    if (respon.data?.jenisKelamin == "l"){
                        binding.genderDosenLogin.text = "Pria"
                    }else if (respon.data?.jenisKelamin == "p"){
                        binding.genderDosenLogin.text = "Wanita"
                    }else{
                        binding.genderDosenLogin.text = "Kamu Apa?"
                    }


                }
                Log.d("Hola!!", response.toString())
            }

            override fun onFailure(call: Call<ProfilDosenResponse>, t: Throwable) {

                Toast.makeText(this@ProfileDosenActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun onClickListener(view: View) {
        val logoutIntent = Intent(this@ProfileDosenActivity, LoginActivity::class.java)
        startActivity(logoutIntent)
    }
}