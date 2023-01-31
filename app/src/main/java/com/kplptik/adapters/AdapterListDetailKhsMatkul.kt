package com.kplptik.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.APIdatamodels.DetailKhsMhs.DetailItem
import com.kplptik.R
import com.kplptik.models.ListDetailKhsMatkul

class AdapterListDetailKhsMatkul(private var data: ArrayList<DetailItem>) :

    RecyclerView.Adapter<AdapterListDetailKhsMatkul.ListDetailKhsMatkulHolder>(){
    private lateinit var listDetailKhsMatkulListener: clickListener

    fun setListDetailKhs (data: ArrayList<DetailItem>){
        this.data = data
        notifyDataSetChanged()
    }

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

        fun bind(data: DetailItem){
            nama_matkul_khs.text = data.namaMk
            sks_khs.text = data.sks
            nilai_matkul_khs.text = data.nilaiHuruf
        }

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDetailKhsMatkulHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_khs,parent,false)
        return ListDetailKhsMatkulHolder(view,listDetailKhsMatkulListener)
    }

    override fun onBindViewHolder(holder: ListDetailKhsMatkulHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

