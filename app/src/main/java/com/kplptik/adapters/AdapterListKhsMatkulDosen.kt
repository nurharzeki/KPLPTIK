package com.kplptik.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.R
import com.kplptik.models.ListKhsDosen

class AdapterListKhsMatkulDosen (private val data: ArrayList<ListKhsDosen>):

    RecyclerView.Adapter<AdapterListKhsMatkulDosen.listKhsMatkulDosenHolder>() {

    private lateinit var listKhsMatkulDosenListener:clickListener

    interface clickListener{
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener : clickListener){
        listKhsMatkulDosenListener = listener
    }

    inner class listKhsMatkulDosenHolder(itemView: View,listener: clickListener):RecyclerView.ViewHolder(itemView){
        private val semester_mhs:TextView = itemView.findViewById(R.id.semesterViewDosen)
        private val tahun_ajar:TextView = itemView.findViewById(R.id.tahunAjarDosen)
        private val sks_total:TextView = itemView.findViewById(R.id.sksTotalDosen)
        private val ips:TextView = itemView.findViewById(R.id.ipsDosen)

        fun bind(data: ListKhsDosen){
            semester_mhs.text = data.semester_mhs.toString()
            tahun_ajar.text = data.tahun_ajar
            sks_total.text = data.sks_total.toString()
            ips.text = data.ips.toString()
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listKhsMatkulDosenHolder {
        val View = LayoutInflater.from(parent.context).inflate(R.layout.item_khs_dosen,parent,false)
        return listKhsMatkulDosenHolder(View,listKhsMatkulDosenListener)
    }

    override fun onBindViewHolder(holder: listKhsMatkulDosenHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    }






