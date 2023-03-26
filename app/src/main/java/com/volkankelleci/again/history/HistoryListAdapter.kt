package com.volkankelleci.again.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.volkankelleci.again.room.ShoppingListItem
import com.volkankelleci.again.databinding.DateRawBinding

class HistoryListAdapter(var dates: List<ShoppingListItem>):
    RecyclerView.Adapter<HistoryListAdapter.ViewHolder>() {

    class ViewHolder(val binding: DateRawBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =DateRawBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date = dates[position]
        holder.binding.dateForItem.text = date.dateAdded
        holder.binding.dateForItem.setOnClickListener {
            val action=HistoryListFragmentDirections.actionBlankFragment2ToDateWithContentFragment(date.dateAdded)
            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun getItemCount() = dates.size

    fun setData(listDate: List<ShoppingListItem>){
        this.dates = listDate
        notifyDataSetChanged()
    }

}
