package com.example.hissesenetleritahmin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SoruAdapter(val soruList: List<Soru>) :
       RecyclerView.Adapter<SoruAdapter.SoruVH>(){
    class SoruVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var  soruTxt : TextView = itemView.findViewById(R.id.soru)
        var  cevapTxt : TextView = itemView.findViewById(R.id.cevap)
        var linearLayout : LinearLayout = itemView.findViewById(R.id.linearLayout)
        var expandableLayout : RelativeLayout = itemView.findViewById(R.id.expandable_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoruVH {
        val  view : View = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)

        return  SoruVH(view)
    }

    override fun getItemCount(): Int {
        return soruList.size
    }

    override fun onBindViewHolder(holder: SoruVH, position: Int) {
        val soru : Soru = soruList[position]
        holder.soruTxt.text = soru.soru
        holder.cevapTxt.text = soru.cevap

        val isExpandable : Boolean = soruList[position].expandable
        holder.expandableLayout.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.linearLayout.setOnClickListener {
            val soru = soruList[position]
            soru.expandable = !soru.expandable
            notifyItemChanged(position)
        }
    }
}