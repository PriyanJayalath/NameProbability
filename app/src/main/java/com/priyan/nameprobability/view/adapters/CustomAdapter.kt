package com.priyan.nameprobability.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.priyan.nameprobability.R
import com.priyan.nameprobability.data.models.Country

class CustomAdapter(private val mList: List<Country>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val country = mList[position]

        holder.txtCountry.text = country.countryId
        holder.txtProbability.text = ""+country.probability

    }


    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val txtCountry: TextView = itemView.findViewById(R.id.txt_country)
        val txtProbability: TextView = itemView.findViewById(R.id.txt_probability)
    }
}