package com.kplptik

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.kplptik.APIdatamodels.DetailMahasiswaDosenModel.DetailMahasiswaDosenResponse
import com.kplptik.databinding.ActivityDetailMahasiswaDosenBinding
import com.kplptik.networks.MainInterface
import com.kplptik.networks.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailMahasiswaDosenActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailMahasiswaDosenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMahasiswaDosenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE) ?: return
        val token = sharedPref.getString("token", null)
        Log.e("Token ->", token.toString())

        val getNim = intent.getStringExtra("nimMahasiswa")

        val cardView = binding.cvDetailMahasiswaDosenPa
        val progressBar = binding.pbDetailMhsPa
        val btnLihatKHS = binding.buttonLihatKHS

        cardView.visibility = View.GONE
        progressBar.visibility = View.GONE

        val client: MainInterface = RetrofitConfig().getService()
        cardView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE

        val call: Call<DetailMahasiswaDosenResponse> = client.detailMahasiswaDosen("Bearer "+token,getNim.toString())
        call.enqueue(object : Callback<DetailMahasiswaDosenResponse> {
            override fun onResponse(
                call: Call<DetailMahasiswaDosenResponse>,
                response: Response<DetailMahasiswaDosenResponse>
            ) {
                val respon: DetailMahasiswaDosenResponse? = response.body()
                if (respon != null){
                    binding.namaMahasiswaBimbingan.text = respon.data?.namaMahasiswa
                    binding.NimMahasiswaBimbingan.text = respon.data?.nim
                    binding.AlamatMahasiswaBimbingan.text = respon.data?.alamat
                    binding.HpMahasiswaBimbingan.text = respon.data?.noHp
                    binding.JurusanMahasiswaBimbingan.text = respon.data?.namaJur
                    binding.FakultasMahasiswaBimbingan.text = respon.data?.namaFak
                    binding.EmailMahasiswaBimbingan.text = respon.data?.email
                }
                Log.d("Hola!!", response.toString())

                cardView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                btnLihatKHS.visibility = View.VISIBLE

            }

            override fun onFailure(call: Call<DetailMahasiswaDosenResponse>, t: Throwable) {
                Toast.makeText(this@DetailMahasiswaDosenActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                cardView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                btnLihatKHS.visibility = View.VISIBLE
            }
        })



        btnLihatKHS.setOnClickListener {

            val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
            val token = sharedPref.getString("token", null)
            Log.e("Token ->", token.toString())

            val getNim = intent.getStringExtra("nimMahasiswa")

            val client: MainInterface = RetrofitConfig().getService()
            cardView.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE

            val call: Call<DetailMahasiswaDosenResponse> = client.detailMahasiswaDosen("Bearer "+token,getNim.toString())
            call.enqueue(object : Callback<DetailMahasiswaDosenResponse> {
                override fun onResponse(
                    call: Call<DetailMahasiswaDosenResponse>,
                    response: Response<DetailMahasiswaDosenResponse>
                ) {
                    val respon: DetailMahasiswaDosenResponse? = response.body()
                    if (respon != null){

                        val khsIntent = Intent(this@DetailMahasiswaDosenActivity, ListKhsDosenActivity::class.java)
                        khsIntent.putExtra("nimMahasiswa",respon.data?.nim)
                        startActivity(khsIntent)

                    }
                    Log.d("Hola!!", response.toString())

                    cardView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }

                override fun onFailure(call: Call<DetailMahasiswaDosenResponse>, t: Throwable) {
                    Toast.makeText(this@DetailMahasiswaDosenActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                    cardView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            })
        }
    }

}