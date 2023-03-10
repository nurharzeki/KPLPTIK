package com.kplptik

import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

//        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE) ?: return
//        val token = sharedPref.getString("token", null)
//        Log.e("Token ->", token.toString())
//
//        if(token != null){
//            val client: MainInterface = RetrofitConfig().getService()
//            val call: Call<UserResponse> = client.userCek("Bearer "+token)
//            call.enqueue(object : Callback<UserResponse>{
//                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
//                    val respon : UserResponse? = response.body()
//
//                    if (respon!=null && respon.role == "d"){
//                        intent = Intent(applicationContext, HomeDosenActivity::class.java)
//                        startActivity(intent)
//                        finish()
//                    }
//                    if (respon!=null && respon.role == "m"){
//                        intent = Intent(applicationContext, HomeMahasiswaActivity::class.java)
//                        startActivity(intent)
//                        finish()
//                    }
//
//                }
//
//                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                    Toast.makeText(this@LoginActivity, "Jaringan anda bermasalah", Toast.LENGTH_SHORT).show()
//                    Log.e("Fail", t.localizedMessage.toString())
//                }
//
//            })
//        }

        val buttonLogin = binding.buttonLogin

        buttonLogin.setOnClickListener {

            val progressBar = binding.pbLogin
            val textLayoutUsername = binding.textLayoutUsername
            val textLayoutPassword = binding.textLayoutPassword

            progressBar.visibility = View.GONE

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

                progressBar.visibility = View.VISIBLE
                textLayoutUsername.visibility = View.GONE
                textLayoutPassword.visibility = View.GONE

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
                        } else if (respon!=null && respon.user?.role == "m") {

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
                        }else{
                            Toast.makeText(this@LoginActivity,"Username dan password anda salah",Toast.LENGTH_SHORT).show()
                        }
                        progressBar.visibility = View.GONE
                        textLayoutUsername.visibility = View.VISIBLE
                        textLayoutPassword.visibility = View.VISIBLE
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Jaringan anda bermasalah", Toast.LENGTH_SHORT).show()
                        Log.e("Fail", t.localizedMessage.toString())
                        progressBar.visibility = View.GONE
                        textLayoutUsername.visibility = View.VISIBLE
                        textLayoutPassword.visibility = View.VISIBLE
                    }
                })
            }
        }
    }
}