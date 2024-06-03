package com.example.hissesenetleritahmin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class HarfAdapter(private var mList: List<HarfData>) :
    RecyclerView.Adapter<HarfAdapter.HarfViewHolder>() {

    inner class HarfViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleSozluk : TextView = itemView.findViewById(R.id.titleSozluk)
        val harfKelime : TextView = itemView.findViewById(R.id.harfKelime)
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)

        fun collapseExpandedView(){
            harfKelime.visibility = View.GONE
        }
    }

    fun setFilteredList(mList: List<HarfData>){
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HarfViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return HarfViewHolder((view))
    }


    override fun onBindViewHolder(holder: HarfViewHolder, position: Int) {

        val harfData = mList[position]
        holder.titleSozluk.text = harfData.title
        holder.harfKelime.text = harfData.kelime

        val isExpandable : Boolean = harfData.isExpandable
        holder.harfKelime.visibility = if(isExpandable) View.VISIBLE else View.GONE

        holder.constraintLayout.setOnClickListener {
            isAnyItemExpanded(position)
            harfData.isExpandable = !harfData.isExpandable
            notifyItemChanged(position, Unit)
        }

    }

    private fun isAnyItemExpanded(position: Int) {
       val  temp = mList.indexOfFirst {
           it.isExpandable
       }
        if (temp >= 0 && temp != position){
            mList[temp].isExpandable = false
            notifyItemChanged(temp, 0)
        }
    }

    override fun onBindViewHolder(
        holder: HarfViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {

        if(payloads.isNotEmpty() && payloads[0] == 0){
            holder.collapseExpandedView()
        }else{
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}