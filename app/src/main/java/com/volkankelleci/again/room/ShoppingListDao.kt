package com.volkankelleci.again.room

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ShoppingListItem): Long

    @Update
    suspend fun update(item: ShoppingListItem)

    @Delete
    suspend fun delete(item: ShoppingListItem)

    @Query("DELETE FROM shopping_list_items")
    suspend fun deleteAll()

    @Query("SELECT * FROM shopping_list_items ORDER BY dateAdded DESC")
    fun getAllItems(): Flow<List<ShoppingListItem>>

    @Query("SELECT * FROM shopping_list_items WHERE id = :itemId")
    suspend fun getItemById(itemId: Long): ShoppingListItem?

    @Query("SELECT * FROM shopping_list_items WHERE dateAdded = :date")
    fun getListsByDate(date: String): LiveData<List<ShoppingListItem>>
}