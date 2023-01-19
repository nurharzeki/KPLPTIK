package com.kplptik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager

class HomeMahasiswaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_mahasiswa)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
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