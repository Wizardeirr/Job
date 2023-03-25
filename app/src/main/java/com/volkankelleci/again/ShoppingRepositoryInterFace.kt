package com.volkankelleci.again

import androidx.lifecycle.LiveData

interface ShoppingRepositoryInterFace {
    suspend fun insertItems (shopping: Shopping)
    suspend fun deleteAll(shopping: Shopping)
    fun getShopping(): LiveData<List<Shopping>>


}