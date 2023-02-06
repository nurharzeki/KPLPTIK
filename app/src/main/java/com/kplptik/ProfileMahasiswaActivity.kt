package com.kplptik

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kplptik.APIdatamodels.ProfilMahasiswaModel.ProfilMahasiswaResponse
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

        val scrollView = binding.svProfilMahasiswa
        val progressBar = binding.pbProfilMahasiswa

        scrollView.visibility = View.GONE
        progressBar.visibility = View.GONE

        val client: MainInterface = RetrofitConfig().getService()
        progressBar.visibility = View.VISIBLE

        val call: Call<ProfilMahasiswaResponse> = client.profilmahasiswa("Bearer "+token)
        call.enqueue(object : Callback<ProfilMahasiswaResponse> {
            override fun onResponse(
                call: Call<ProfilMahasiswaResponse>,
                response: Response<ProfilMahasiswaResponse>
            ) {
                val respon: ProfilMahasiswaResponse? = response.body()
                if (respon != null){
//                    val list: List<Data> = respon.data as List<Data>
//                    adapter.setListMatkul(list as ArrayList<DataItem>)
                    binding.namaMahasiswaLogin.text = respon.profil?.namaMahasiswa
                    binding.nimMahasiswaLogin.text = respon.profil?.nim
                    binding.emailMahasiswaLogin.text = respon.profil?.email
                    binding.noHpMahasiswaLogin.text = respon.profil?.noHp
                    binding.alamatMahasiswaLogin.text = respon.profil?.alamat
                    binding.alamatMahasiswaLogin.text = respon.profil?.alamat
                    if (respon.profil?.jenisKelamin == "l"){
                        binding.genderMahasiswaLogin.text = "Laki-laki"
                    }else if (respon.profil?.jenisKelamin == "p"){
                        binding.genderMahasiswaLogin.text = "Perempuan"
                    }else{
                        binding.genderMahasiswaLogin.text = "Mencurigakan hmmm?"
                    }

                }
                Log.d("Hola!!", response.toString())

                scrollView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<ProfilMahasiswaResponse>, t: Throwable) {
                Toast.makeText(this@ProfileMahasiswaActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                scrollView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        })


        val buttonLogout = binding.buttonLogoutMahasiswa

        buttonLogout.setOnClickListener {

            val client: MainInterface = RetrofitConfig().getService()
            progressBar.visibility = View.VISIBLE

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

                    val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putString("token", null)
                        putString("nama", null)
                        apply()
                    }
                    intent = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                    progressBar.visibility = View.GONE
                }
                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                    Toast.makeText( applicationContext,"Fail calling response", Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.GONE
                }

            })
        }
    }
}