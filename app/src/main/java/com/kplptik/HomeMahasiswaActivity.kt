package com.kplptik

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.kplptik.APIdatamodels.authentication.LoginResponse
import com.kplptik.APIdatamodels.authentication.UserResponse
import com.kplptik.databinding.ActivityHomeMahasiswaBinding
import com.kplptik.databinding.ActivityLoginBinding
import com.kplptik.networks.MainInterface
import com.kplptik.networks.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeMahasiswaActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeMahasiswaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeMahasiswaBinding.inflate(layoutInflater)
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
                    binding.namaMahasiswaHome.text=respon.name
                    binding.nimMahasiswaHome.text=respon.username
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Gagal jir", t.localizedMessage.toString())
            }

        })

    }

    fun onProfilMhsClick(view: View) {
        val profilMhsIntent = Intent(this@HomeMahasiswaActivity, ProfileMahasiswaActivity::class.java)
        startActivity(profilMhsIntent)
    }

    fun onKrsClick(view: View) {
        val krsIntent = Intent(this@HomeMahasiswaActivity, ListKrsMahasiswaActivity::class.java)
        startActivity(krsIntent)
    }

    fun onKhsClick(view: View) {
        val khsIntent = Intent(this@HomeMahasiswaActivity, ListKhsMhsActivity::class.java)
        startActivity(khsIntent)
    }

}