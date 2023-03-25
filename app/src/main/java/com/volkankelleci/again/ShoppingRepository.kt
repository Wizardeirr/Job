package com.volkankelleci.again

import androidx.lifecycle.LiveData

class ShoppingRepository (private val shoppingDao: ShoppingDao) {

     suspend fun insertItems(shopping: Shopping) {
        shoppingDao.insertItems(shopping)
    }

     suspend fun deleteAll(shopping: Shopping) {
        shoppingDao.deleteAll(shopping)
    }

     fun getShopping(): LiveData<List<Shopping>> {
        return shoppingDao.observeShopping()
    }



}