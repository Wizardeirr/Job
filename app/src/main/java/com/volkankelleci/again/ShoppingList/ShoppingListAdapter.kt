package com.volkankelleci.again.ShoppingList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.volkankelleci.again.room.ShoppingListItem
import com.volkankelleci.again.databinding.HomeRawBinding
import com.volkankelleci.again.viewmodel.ShoppingListViewModel

class ShoppingListAdapter(var items: List<ShoppingListItem>,private val viewModel:ShoppingListViewModel) :
    RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {

    class ViewHolder(val binding: HomeRawBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding=HomeRawBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemShopping = items[position]
        holder.binding.textForItem.text=itemShopping.name
        holder.binding.amount.text = itemShopping.amount.toString()
        holder.binding.delete.setOnClickListener {
            viewModel.deleteItem(itemShopping)
        }


        holder.binding.plus.setOnClickListener {

            itemShopping.amount++
            viewModel.addItem(itemShopping)
        }
        holder.binding.minus.setOnClickListener {
            if (itemShopping.amount>0){
                itemShopping.amount--
                viewModel.addItem(itemShopping)
            }else{
                Toast.makeText(it.context, "You can't get negative item count", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}
