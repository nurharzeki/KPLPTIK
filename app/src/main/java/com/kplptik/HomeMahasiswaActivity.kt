package com.kplptik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HomeMahasiswaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_mahasiswa)
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