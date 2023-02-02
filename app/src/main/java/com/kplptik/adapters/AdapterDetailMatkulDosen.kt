package com.kplptik.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.APIdatamodels.ListV.Data
import com.kplptik.APIdatamodels.ListV.JadwalItem
import com.kplptik.R

class AdapterDetailMatkulDosen (private var data: ArrayList<JadwalItem>):

    RecyclerView.Adapter<AdapterDetailMatkulDosen.detailMatkulHolder>() {

    private lateinit var detailMatkulListener: clickListener

    fun setListJadwal (data: ArrayList<JadwalItem>){
        this.data = data
        notifyDataSetChanged()
    }

    interface clickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: clickListener) {
        detailMatkulListener = listener
    }

    inner class detailMatkulHolder(itemView: View, listener: clickListener): RecyclerView.ViewHolder(itemView) {
        private val hari: TextView =itemView.findViewById(R.id.textHariMatkul)
        private val jam: TextView = itemView.findViewById(R.id.textJadwalMatkul)
        private val ruang: TextView = itemView.findViewById(R.id.textRuangMatkul)


        fun bind(data: JadwalItem){


            hari.text = data.namaHari
            jam.text = data.jamKuliah
            ruang.text = data.kodeRuang
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): detailMatkulHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_jadwal_matkul, parent, false)
        return detailMatkulHolder(view , detailMatkulListener)
    }

    override fun onBindViewHolder(holder: detailMatkulHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}