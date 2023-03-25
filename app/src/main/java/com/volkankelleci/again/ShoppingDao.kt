package com.volkankelleci.again

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(vararg shopping: Shopping):List<Long>
    @Delete
    suspend fun deleteAll(shopping: Shopping)
    @Query("SELECT * FROM volkan")
    fun observeShopping():LiveData<List<Shopping>>
}