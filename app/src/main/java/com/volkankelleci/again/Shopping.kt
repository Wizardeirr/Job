package com.volkankelleci.again

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "volkan")
data class Shopping(
    var item: String,
    var count: Int,
    var time:String?

){
    @PrimaryKey(autoGenerate = true)
    var id: Int?=null
}