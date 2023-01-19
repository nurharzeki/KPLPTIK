package com.kplptik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class DetailMahasiswaDosenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_mahasiswa_dosen)

    }

    fun onClickListener(view: View) {
        val khsIntent = Intent(this@DetailMahasiswaDosenActivity, ListKhsDosenActivity::class.java)
        startActivity(khsIntent)
    }
}