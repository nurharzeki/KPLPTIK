package com.kplptik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun onClickListener(view: View) {
        val homeDosenIntent = Intent(this@LoginActivity, HomeDosenActivity::class.java)
        startActivity(homeDosenIntent)
        Toast.makeText(this, "masok", Toast.LENGTH_SHORT).show()
    }

    fun onButtonLogin2ClickListener(view: View) {
        val homeMahasiswaIntent = Intent(this@LoginActivity, HomeMahasiswaActivity::class.java)
        startActivity(homeMahasiswaIntent)
    }

}