package com.kplptik.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.R

class AdapterListKhsMatkulDosen(private var data: ArrayList<com.kplptik.APIdatamodels.KhsMahasiswaModel.DetailItem>):

    RecyclerView.Adapter<AdapterListKhsMatkulDosen.listKhsMatkulDosenHolder>() {

    private lateinit var listKhsMatkulDosenListener:clickListener

    fun setListKhsDosen (data: ArrayList<com.kplptik.APIdatamodels.KhsMahasiswaModel.DetailItem>){
        this.data = data
        notifyDataSetChanged()
    }

    interface clickListener{
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: AdapterListKhsMatkul.clickListener){
        listKhsMatkulDosenListener = listener
    }

    inner class listKhsMatkulDosenHolder(itemView: View,listener: clickListener):RecyclerView.ViewHolder(itemView){
        private val semester_mhs:TextView = itemView.findViewById(R.id.semesterViewDosen)
        private val sks_total:TextView = itemView.findViewById(R.id.sksTotalDosen)
        private val ips:TextView = itemView.findViewById(R.id.ipsDosen)

        fun bind(data: com.kplptik.APIdatamodels.KhsMahasiswaModel.DetailItem){
            semester_mhs.text = data.semester.toString()
            sks_total.text = data.jumlahSks.toString()
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






