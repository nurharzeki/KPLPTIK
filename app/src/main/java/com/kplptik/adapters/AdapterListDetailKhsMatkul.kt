package com.kplptik.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.R
import com.kplptik.models.ListDetailKhsMatkul

class AdapterListDetailKhsMatkul(private val data: ArrayList<ListDetailKhsMatkul>) :

    RecyclerView.Adapter<AdapterListDetailKhsMatkul.ListDetailKhsMatkulHolder>(){
    private lateinit var listDetailKhsMatkulListener: clickListener

    interface clickListener{
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: clickListener){
        listDetailKhsMatkulListener = listener
    }

    inner class ListDetailKhsMatkulHolder(itemView : View, listener: clickListener):RecyclerView.ViewHolder(itemView) {
        private val nama_matkul_khs:TextView = itemView.findViewById(R.id.namaMatkulKhs)
        private val sks_khs:TextView = itemView.findViewById(R.id.sksKhs)
        private val nilai_matkul_khs:TextView = itemView.findViewById(R.id.nilaiKhsMhs)

        fun bind(data: ListDetailKhsMatkul){
            nama_matkul_khs.text = data.nama_matkul_khs
            sks_khs.text = data.sks_matkul
            nilai_matkul_khs.text = data.nilai_matkul
        }

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDetailKhsMatkulHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_detail_khs_matkul,parent,false)
        return ListDetailKhsMatkulHolder(view,listDetailKhsMatkulListener)
    }

    override fun onBindViewHolder(holder: ListDetailKhsMatkulHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

