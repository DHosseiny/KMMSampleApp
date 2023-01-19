package com.jetbrains.handson.androidApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jetbrains.handson.kmm.shared.entity.Properties
import com.jetbrains.handson.kmm.shared.entity.Property

class PropertiesRvAdapter(var properties: List<Property>) :
    RecyclerView.Adapter<PropertiesRvAdapter.PropertiesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertiesViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_property, parent, false)
            .run(::PropertiesViewHolder)
    }

    override fun getItemCount(): Int = properties.count()

    override fun onBindViewHolder(holder: PropertiesViewHolder, position: Int) {
        holder.bindData(properties[position])
    }

    inner class PropertiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val missionNameTextView = itemView.findViewById<TextView>(R.id.missionName)

        fun bindData(property: Property) {
            val ctx = itemView.context
            missionNameTextView.text = ctx.getString(R.string.mission_name_field, property.name)
        }
    }
}