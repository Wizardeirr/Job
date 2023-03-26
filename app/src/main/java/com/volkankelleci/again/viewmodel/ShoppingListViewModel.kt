package com.volkankelleci.again.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.volkankelleci.again.repo.ShoppingListRepository
import com.volkankelleci.again.room.ShoppingListItem
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ShoppingListViewModel(private val repository: ShoppingListRepository) : ViewModel(){
    val shoppingList: LiveData<List<ShoppingListItem>> = repository.getAllItems().asLiveData()

    fun addItem(item: ShoppingListItem) {
        viewModelScope.launch {
            repository.insert((item))

        }
    }

    fun updateItem(item: ShoppingListItem) {
        viewModelScope.launch {
            repository.update(item)
        }
    }

    fun deleteItem(item: ShoppingListItem) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }

    fun clearList() {
        viewModelScope.launch {
            repository.clearList()
        }
    }

    fun getItemById(itemId: Long): ShoppingListItem? {
        return runBlocking { repository.getItemById(itemId) }
    }

    fun get(date: String): LiveData<List<ShoppingListItem>> {
        return repository.getListsByDate(date)
    }

}
