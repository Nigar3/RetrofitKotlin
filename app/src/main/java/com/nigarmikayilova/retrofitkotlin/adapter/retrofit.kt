package com.nigarmikayilova.retrofitkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nigarmikayilova.retrofitkotlin.R
import com.nigarmikayilova.retrofitkotlin.databinding.RecyclerRawBinding
import com.nigarmikayilova.retrofitkotlin.model.CryptoModel

class retrofitAdapter(private val cryptoList:ArrayList<CryptoModel>,private val listener: Listener):RecyclerView.Adapter<retrofitAdapter.retroHolder>() {

    interface Listener {
        fun onItemClick(cryptoModel: CryptoModel)
    }
    private var colors:Array<String> = arrayOf("#ead7b8","#e6bd9f","#d5ad8b","#cb9e7c","#db9674","#bd845d","#796f63","#474342")

    //binding elave etmek
    class retroHolder(val binding: RecyclerRawBinding):RecyclerView.ViewHolder(binding.root) {


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): retroHolder {
        val binding= RecyclerRawBinding.inflate(LayoutInflater.from(parent.context),parent,false)
         return retroHolder(binding)
    }

    override fun getItemCount(): Int {
        return cryptoList.count()
    }

    override fun onBindViewHolder(holder: retroHolder, position: Int) {
        holder.itemView.setOnClickListener{
            listener.onItemClick(cryptoList.get(position))
        }
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position %8]))

        holder.binding.textName.text=cryptoList.get(position).currency
        holder.binding.textPrice.text=cryptoList.get(position).price
    }
}