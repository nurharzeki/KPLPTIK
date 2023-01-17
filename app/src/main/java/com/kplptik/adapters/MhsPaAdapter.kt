package com.kplptik.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kplptik.R
import com.kplptik.models.MhsPa

class MhsPaAdapter (private val data: ArrayList<MhsPa>):

    RecyclerView.Adapter<MhsPaAdapter.listMhsPaHolder>(){

    private lateinit var mhsPaListener: clickListener

    interface clickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: clickListener){
        mhsPaListener = listener
    }

    inner class listMhsPaHolder(itemView: View, listener: clickListener): RecyclerView.ViewHolder(itemView){
        private val namaMhsPa: TextView = itemView.findViewById(R.id.textNamaMhsPa)
        private val nimMhsPa: TextView = itemView.findViewById(R.id.textNimMhsPa)

        fun bind(data: MhsPa){
            namaMhsPa.text = data.nama_mhs_pa
            nimMhsPa.text = data.nim_mhs_pa
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listMhsPaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mhs_pa,parent,false)
        return listMhsPaHolder(view, mhsPaListener)
    }

    override fun onBindViewHolder(holder: listMhsPaHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
    }