package com.kplptik.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.R
import com.kplptik.models.ListKrsMahasiswa

class KrsMahasiswaAdapter (private val data: ArrayList<ListKrsMahasiswa>):
    RecyclerView.Adapter<KrsMahasiswaAdapter.listKrsMahasiswaHolder>() {

    interface clickListener {
        fun onItemClick(position: Int)
    }


    inner class listKrsMahasiswaHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val namaKlsMhs: TextView = itemView.findViewById(R.id.textNamaKlsMhs)
        private val waktuKlsMhs : TextView = itemView.findViewById(R.id.textWaktuKlsMhs)
        private val lokasiKlsMhs : TextView = itemView.findViewById(R.id.textLokasiKlsMhs)

        fun bind(data: ListKrsMahasiswa){
            namaKlsMhs.text = data.nama_kls_mhs
            waktuKlsMhs.text= data.waktu_kls_mhs
            lokasiKlsMhs.text = data.lokasi_kls_mhs

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listKrsMahasiswaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_krs_mahasiswa,parent,false)
        return listKrsMahasiswaHolder(view)
    }

    override fun onBindViewHolder(holder: listKrsMahasiswaHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}