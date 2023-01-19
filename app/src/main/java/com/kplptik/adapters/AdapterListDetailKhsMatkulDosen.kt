package com.kplptik.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.R
import com.kplptik.models.ListDetailKhsMatkul
import com.kplptik.models.ListDetailKhsMatkulDosen

class AdapterListDetailKhsMatkulDosen(private val data: ArrayList<ListDetailKhsMatkulDosen>) :

    RecyclerView.Adapter<AdapterListDetailKhsMatkulDosen.ListDetailKhsMatkulDosenHolder>(){
    private lateinit var listDetailKhsMatkulDosenListener: clickListener

    interface clickListener{
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: clickListener){
        listDetailKhsMatkulDosenListener = listener
    }

    inner class ListDetailKhsMatkulDosenHolder(itemView : View, listener: clickListener):RecyclerView.ViewHolder(itemView) {
        private val nama_matkul_khs:TextView = itemView.findViewById(R.id.namaMatkulKhsDosen)
        private val sks_khs:TextView = itemView.findViewById(R.id.sksKhsDosen)
        private val nilai_matkul_khs:TextView = itemView.findViewById(R.id.nilaiKhsDosen)

        fun bind(data: ListDetailKhsMatkulDosen){
            nama_matkul_khs.text = data.nama_matkul_khs
            sks_khs.text = data.sks_matkul.toString()
            nilai_matkul_khs.text = data.nilai_matkul
        }

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDetailKhsMatkulDosenHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_detail_khs_dosen,parent,false)
        return ListDetailKhsMatkulDosenHolder(view,listDetailKhsMatkulDosenListener)
    }

    override fun onBindViewHolder(holder: ListDetailKhsMatkulDosenHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

