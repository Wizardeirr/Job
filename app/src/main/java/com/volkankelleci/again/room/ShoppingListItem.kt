package com.volkankelleci.again.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "shopping_list_items")
data class ShoppingListItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    var amount: Int,
    val dateAdded: String
)
