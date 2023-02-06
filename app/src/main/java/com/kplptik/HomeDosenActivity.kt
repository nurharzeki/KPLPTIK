package com.kplptik

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.kplptik.APIdatamodels.authentication.UserResponse
import com.kplptik.databinding.ActivityHomeDosenBinding
import com.kplptik.networks.MainInterface
import com.kplptik.networks.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeDosenActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeDosenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeDosenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE) ?: return
        val token = sharedPref.getString("token", null)
        Log.e("Token ->", token.toString())

        val client: MainInterface = RetrofitConfig().getService()

        val call: Call<UserResponse> =client.userCek("Bearer " + token)
        call.enqueue(object: Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val respon: UserResponse? = response.body()
                Log.e("Berhasil", respon.toString())
                if(respon != null){
                    binding.namaDosenHome.text=respon.name
                    binding.nikDosenHome.text=respon.username
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Gagal jir", t.localizedMessage.toString())
            }

        })

    }

    fun onMhsBimbinganClick(view: View) {
//        Toast.makeText(this, "Berhasil klik", Toast.LENGTH_SHORT).show()
        val listMhsIntent = Intent(this@HomeDosenActivity, MhsPaActivity::class.java)
        startActivity(listMhsIntent)
    }

    fun onListMatkulClick(view: View) {
//        Toast.makeText(this, "Berhasil klik", Toast.LENGTH_SHORT).show()
        val listMatkulIntent = Intent(this@HomeDosenActivity, ListMatkulDiampuActivity::class.java)
        startActivity(listMatkulIntent)
    }

    fun onProfilClick(view: View) {
        val profilIntent = Intent(this@HomeDosenActivity, ProfileDosenActivity::class.java)
        startActivity(profilIntent)
    }

//    fun onClickListener(view: View) {
//        val mhsPaAIntent = Intent(this@HomeDosenActivity, MhsPaActivity::class.java)
//        startActivity(mhsPaAIntent)
//    }
}