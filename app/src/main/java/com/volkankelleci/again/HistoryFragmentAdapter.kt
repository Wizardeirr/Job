package com.volkankelleci.again

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.volkankelleci.again.databinding.HomeRawBinding

class HistoryFragmentAdapter(var shop:List<Shopping>, private val viewModel: HomeFragmentViewModel): RecyclerView.Adapter<HistoryFragmentAdapter.homeViewHolder>() {

     class homeViewHolder(val binding: HomeRawBinding):RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): homeViewHolder {
        val itemBinding=HomeRawBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return homeViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: homeViewHolder, position: Int) {
        val shoppingItem=shop[position]
        holder.binding.textForItem.text=shoppingItem.time

        holder.binding.textForItem.setOnClickListener{
        val action =HistoryFragmentDirections.actionHistoryFragmentToHistoryWithItemsFragment(
            shoppingItem.time!!,
            shoppingItem.item,
            shoppingItem.count)
        Navigation.findNavController(it).navigate(action)

        }
        holder.binding.delete.visibility=View.INVISIBLE
        holder.binding.textForItem.visibility=View.VISIBLE
        holder.binding.amount.visibility= View.INVISIBLE
        holder.binding.minus.visibility= View.INVISIBLE
        holder.binding.plus.visibility= View.INVISIBLE



    }

    override fun getItemCount(): Int {
        return shop.size
    }


}