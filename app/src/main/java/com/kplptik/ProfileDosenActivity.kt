package com.kplptik

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kplptik.APIdatamodels.ProfilDosenModel.ProfilDosenResponse
import com.kplptik.APIdatamodels.authentication.LogoutResponse
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

        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE) ?: return
        val token = sharedPref.getString("token", null)
        Log.e("Token ->", token.toString())

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


        val buttonLogout = binding.buttonLogoutDosen

        buttonLogout.setOnClickListener {

            val client: MainInterface = RetrofitConfig().getService()
            val call: Call<LogoutResponse> = client.logout("Bearer " + token)
            call.enqueue(object : Callback<LogoutResponse> {
                override fun onResponse(
                    call: Call<LogoutResponse>,
                    response: Response<LogoutResponse>
                ) {
                    val respon: LogoutResponse? = response.body()
                    if (respon != null){
                        Toast.makeText(this@ProfileDosenActivity, respon.message, Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Fail calling response",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
            val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("token", null)
                putString("nama", null)
                apply()
            }
            intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

//    fun onClickListener(view: View) {
//        val logoutIntent = Intent(this@ProfileDosenActivity, LoginActivity::class.java)
//        startActivity(logoutIntent)
//    }
