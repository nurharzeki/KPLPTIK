package com.kplptik

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kplptik.APIdatamodels.authentication.LogoutResponse
import com.kplptik.databinding.ActivityProfileMahasiswaBinding
import com.kplptik.networks.MainInterface
import com.kplptik.networks.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileMahasiswaActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileMahasiswaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileMahasiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE) ?: return
        val token = sharedPref.getString("token", null)
        Log.e("Token ->", token.toString())

        val buttonLogout = binding.buttonLogoutMahasiswa

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
                        Toast.makeText(this@ProfileMahasiswaActivity, respon.message, Toast.LENGTH_SHORT).show()
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