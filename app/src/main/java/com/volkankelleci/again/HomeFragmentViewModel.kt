package com.volkankelleci.again

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragmentViewModel(application: Application):AndroidViewModel(application) {
    val getShopping:LiveData<List<Shopping>>
    private val repository:ShoppingRepository

    init {
        val shoppingDao=ShoppingDatabase.getDataBase(application).shoppingDao()
        repository= ShoppingRepository(shoppingDao)
        getShopping=repository.getShopping()
    }
    fun addShopping(shopping: Shopping){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertItems(shopping)
        }

    }
    fun deleteShopping(shopping: Shopping){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll(shopping)
        }
    }

}