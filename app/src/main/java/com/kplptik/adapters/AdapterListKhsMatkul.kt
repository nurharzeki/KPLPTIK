package com.kplptik.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.APIdatamodels.KhsMahasiswaModel.DetailItem
import com.kplptik.APIdatamodels.ListMahasiswaBimbinganModel.DataItem
import com.kplptik.R
import com.kplptik.models.ListKhsMatkul

class AdapterListKhsMatkul (private var data: ArrayList<DetailItem>):

    RecyclerView.Adapter<AdapterListKhsMatkul.listKhsMatkulHolder>() {

    private lateinit var listKhsMatkulListener:clickListener

    fun setListMahasiswa (data: ArrayList<DetailItem>){
        this.data = data
        notifyDataSetChanged()
    }

    interface clickListener{
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener : clickListener){
        listKhsMatkulListener = listener
    }

    inner class listKhsMatkulHolder(itemView: View,listener: clickListener):RecyclerView.ViewHolder(itemView){
        private val semester_mhs:TextView = itemView.findViewById(R.id.semesterView)
//        private val tahun_ajar:TextView = itemView.findViewById(R.id.tahunAjar)
        private val sks_total:TextView = itemView.findViewById(R.id.sksTotal)
        private val ips:TextView = itemView.findViewById(R.id.ips)

        fun bind(data: DetailItem){
            semester_mhs.text = data.semester.toString()
//            tahun_ajar.text = data.tahun_ajar
            sks_total.text = data.jumlahSks.toString()
            ips.text = data.ips.toString()
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listKhsMatkulHolder {
        val View = LayoutInflater.from(parent.context).inflate(R.layout.item_khs_mhs,parent,false)
        return listKhsMatkulHolder(View,listKhsMatkulListener)
    }

    override fun onBindViewHolder(holder: listKhsMatkulHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    }






