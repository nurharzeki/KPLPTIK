package com.kplptik.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.APIdatamodels.KrsMahasiswaModel.DetailItem
import com.kplptik.APIdatamodels.ListMahasiswaBimbinganModel.DataItem
import com.kplptik.R

class KrsMahasiswaAdapter (private var data: ArrayList<DetailItem>):

    RecyclerView.Adapter<KrsMahasiswaAdapter.listKrsMahasiswaHolder>() {

    private lateinit var krsMahasiswaListener: clickListener

    fun setListMahasiswa (data: ArrayList<DetailItem>){
        this.data = data
        notifyDataSetChanged()
    }

    interface clickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: clickListener){
        krsMahasiswaListener = listener
    }


    inner class listKrsMahasiswaHolder(itemView: View, listener: clickListener ): RecyclerView.ViewHolder(itemView) {
        private val namaKlsMhs: TextView = itemView.findViewById(R.id.textNamaKlsMhs)
        private val waktuKlsMhs : TextView = itemView.findViewById(R.id.textWaktuKelas)
        private val HariKlsMhs : TextView = itemView.findViewById(R.id.textHariKelas)
        private val lokasiKlsMhs : TextView = itemView.findViewById(R.id.textRuangKelas)

        fun bind(data: DetailItem){
            namaKlsMhs.text = data.namaMk
            waktuKlsMhs.text= data.jamKuliah
            HariKlsMhs.text= data.namaHari
            lokasiKlsMhs.text = data.kodeRuang

        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listKrsMahasiswaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_krs_mahasiswa,parent,false)
        return listKrsMahasiswaHolder(view, krsMahasiswaListener)
    }

    override fun onBindViewHolder(holder: listKrsMahasiswaHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}