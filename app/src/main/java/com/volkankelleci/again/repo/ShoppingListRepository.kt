package com.volkankelleci.again.repo

import androidx.lifecycle.LiveData
import com.volkankelleci.again.room.ShoppingListDao
import com.volkankelleci.again.room.ShoppingListItem
import kotlinx.coroutines.flow.Flow

class ShoppingListRepository(private val dao: ShoppingListDao) {

    fun getAllItems(): Flow<List<ShoppingListItem>> {
        return dao.getAllItems()
    }

    suspend fun insert(item: ShoppingListItem) {
        dao.insert(item)
    }

    suspend fun update(item: ShoppingListItem) {
        dao.update(item)
    }

    suspend fun delete(item: ShoppingListItem) {
        dao.delete(item)
    }
    suspend fun clearList() {
        dao.deleteAll()
    }

    suspend fun getItemById(itemId: Long): ShoppingListItem? {
        return dao.getItemById(itemId)
    }

    fun getListsByDate(date: String): LiveData<List<ShoppingListItem>> {
        return dao.getListsByDate(date)
    }
}
