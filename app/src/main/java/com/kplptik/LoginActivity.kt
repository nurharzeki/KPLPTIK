package com.kplptik

import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.kplptik.APIdatamodels.authentication.LoginResponse
import com.kplptik.databinding.ActivityLoginBinding
import com.kplptik.networks.MainInterface
import com.kplptik.networks.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        val constraintLayout: ConstraintLayout = findViewById(R.id.loginLayout)
        val animationDrawable: AnimationDrawable = constraintLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(1500)
        animationDrawable.setExitFadeDuration(2000)
        animationDrawable.start()

        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE) ?: return
        val ditemukan = sharedPref.getString("token", null)

//        if (ditemukan!= null){
//
//        }
        val buttonLogin = binding.buttonLogin

        buttonLogin.setOnClickListener {

            val username = binding.editUsername.getText().toString()
            val password = binding.editPassword.getText().toString()
            Log.e("Login Res ->", username + " " + password)

            if (username == "" || password == "") {
                Toast.makeText(
                    this@LoginActivity,
                    "Isi Username dan Password terlebih dahulu",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
            val client: MainInterface = RetrofitConfig().getService()

                val call: Call<LoginResponse> =client.login(username, password)
                call.enqueue(object: Callback<LoginResponse>{
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        val respon: LoginResponse? = response.body()
                        Log.e("Success", respon.toString())
                        if (respon!=null && respon.user?.role == "d"){

                            val token : String? = respon.authorization?.token
                            val namaUser : String? = respon.user.name

                            val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
                            with(sharedPref.edit()){
                                putString("token", token)
                                putString("nama", namaUser)
                                apply()
                            }
                            intent = Intent(applicationContext, HomeDosenActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        if (respon!=null && respon.user?.role == "m") {

                            val token: String? = respon.authorization?.token
                            val namaUser: String? = respon.user.name

                            val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
                            with(sharedPref.edit()) {
                                putString("token", token)
                                putString("nama", namaUser)
                                apply()
                            }
                            intent = Intent(applicationContext, HomeMahasiswaActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            Toast.makeText(this@LoginActivity,"Username dan password anda salah",Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Jaringan anda bermasalah", Toast.LENGTH_SHORT).show()
                        Log.e("Fail", t.localizedMessage.toString())
                    }

                })

            }
        }

//        fun onClickListener(view: View) {
//            val homeDosenIntent = Intent(this@LoginActivity, HomeDosenActivity::class.java)
//            startActivity(homeDosenIntent)
//          Toast.makeText(this, "masok", Toast.LENGTH_SHORT).show()
//        }
//
//        fun onButtonLogin2ClickListener(view: View) {
//            val homeMahasiswaIntent = Intent(this@LoginActivity, HomeMahasiswaActivity::class.java)
//            startActivity(homeMahasiswaIntent)
//        }

    }
}