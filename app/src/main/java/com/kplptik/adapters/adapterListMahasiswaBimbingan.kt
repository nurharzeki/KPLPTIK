package com.kplptik.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.ListMahasiswaBimbingan
import com.kplptik.R
import com.kplptik.models.MahasiswaBimbingan

class adapterListMahasiswaBimbingan (private val data: ArrayList<MahasiswaBimbingan>):

    RecyclerView.Adapter<adapterListMahasiswaBimbingan.listMahasiswaHolder>() {

    private lateinit var listMahasiswaBimbinganListener: clickListener

    interface clickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: clickListener) {
        listMahasiswaBimbinganListener = listener
    }
    //AMAN====================


    inner class listMahasiswaHolder(itemView: View, listener: adapterListMahasiswaBimbingan.clickListener): RecyclerView.ViewHolder(itemView) {
        private val nama: TextView =itemView.findViewById(R.id.textNamaMhsPa)
        private val nim: TextView = itemView.findViewById(R.id.textNimMhsPa)

        fun bind(data: MahasiswaBimbingan){
            nama.text = data.nama
            nim.text = data.nim.toString()
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterListMahasiswaBimbingan.listMahasiswaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mhs_pa, parent, false)
        return listMahasiswaHolder(view , listMahasiswaBimbinganListener)
    }

    override fun onBindViewHolder(holder: adapterListMahasiswaBimbingan.listMahasiswaHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}