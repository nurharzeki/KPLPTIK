package com.kplptik

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.kplptik.APIdatamodels.authentication.UserResponse
import com.kplptik.networks.MainInterface
import com.kplptik.networks.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("CustomSplashScreen")
class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE) ?: return
        val token = sharedPref.getString("token", null)
        Log.e("Token ->", token.toString())

        if(token != null){
            val client: MainInterface = RetrofitConfig().getService()
            val call: Call<UserResponse> = client.userCek("Bearer $token")
            call.enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    val respon : UserResponse? = response.body()

                    if (respon!=null && respon.role == "d"){
                        intent = Intent(applicationContext, HomeDosenActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    if (respon!=null && respon.role == "m"){
                        intent = Intent(applicationContext, HomeMahasiswaActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Toast.makeText(this@SplashscreenActivity, "Jaringan anda bermasalah", Toast.LENGTH_SHORT).show()
                    t.localizedMessage?.let { Log.e("Fail", it) }
                }

            })
        }else {
            intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
//        Handler(Looper.getMainLooper()).postDelayed({
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//        }, 3000)
    }
}