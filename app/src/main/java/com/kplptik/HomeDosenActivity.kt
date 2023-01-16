package com.kplptik

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class HomeDosenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_dosen)
    }

    fun onMhsBimbinganClick(view: View) {
//        Toast.makeText(this, "Berhasil klik", Toast.LENGTH_SHORT).show()
        val listMhsIntent = Intent(this@HomeDosenActivity, ListMahasiswaBimbingan::class.java)
        startActivity(listMhsIntent)
    }

    fun onListMatkulClick(view: View) {
//        Toast.makeText(this, "Berhasil klik", Toast.LENGTH_SHORT).show()
        val listMatkulIntent = Intent(this@HomeDosenActivity, ListMatkulDiampuActivity::class.java)
        startActivity(listMatkulIntent)
    }

    fun onProfilClick(view: View) {
        val profilIntent = Intent(this@HomeDosenActivity, ProfileDosenActivity::class.java)
        startActivity(profilIntent)
    }

//    fun onClickListener(view: View) {
//        val mhsPaAIntent = Intent(this@HomeDosenActivity, MhsPaActivity::class.java)
//        startActivity(mhsPaAIntent)
//    }
}