package com.kplptik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ProfileMahasiswaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_mahasiswa)
    }

    fun onClickListener(view: View) {
        val logoutIntent = Intent(this@ProfileMahasiswaActivity, LoginActivity::class.java)
        startActivity(logoutIntent)
    }
}