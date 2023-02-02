package com.kplptik.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.APIdatamodels.ListV.DataItem

import com.kplptik.R

class adapterMatkulDiampu (private var data: ArrayList<DataItem>):

    RecyclerView.Adapter<adapterMatkulDiampu.listMatkulHolder>() {

    private lateinit var listMatkulListener: clickListener

    fun setListMatkul (data: ArrayList<DataItem>){
        this.data = data
        notifyDataSetChanged()
    }

    interface clickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: clickListener) {
        listMatkulListener = listener
    }

    inner class listMatkulHolder(itemView: View, listener: clickListener): RecyclerView.ViewHolder(itemView) {
        private val nama_Matkul: TextView =itemView.findViewById(R.id.namaitemMatkul)
        private val kodeMatkul: TextView = itemView.findViewById(R.id.kodeMatkulDosen)


        fun bind(data: DataItem){


            nama_Matkul.text = data.namaMk
            kodeMatkul.text = data.regMk
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listMatkulHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_matkul_yang_diampu_dosen, parent, false)
        return listMatkulHolder(view , listMatkulListener)
    }

    override fun onBindViewHolder(holder: listMatkulHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}