package com.volkankelleci.again.datewithcontent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.volkankelleci.again.databinding.ItemsAndDateRawBinding
import com.volkankelleci.again.room.ShoppingListItem

class DateWithContentAdapter(var historyitems: List<ShoppingListItem>) :
    RecyclerView.Adapter<DateWithContentAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemsAndDateRawBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =ItemsAndDateRawBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date = historyitems[position]
        holder.binding.dateForItem.text=date.name
        holder.binding.amount.text= date.amount.toString()


    }

    override fun getItemCount() = historyitems.size


}
