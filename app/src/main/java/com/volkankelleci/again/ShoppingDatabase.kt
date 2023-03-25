package com.volkankelleci.again

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Shopping::class], version =2)
abstract class ShoppingDatabase:RoomDatabase() {
    abstract fun shoppingDao():ShoppingDao

    companion object{

    @Volatile private var INSTANCE:ShoppingDatabase?=null

        @OptIn(InternalCoroutinesApi::class)
        fun getDataBase(context: Context):ShoppingDatabase{
            val tempInstance= INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance=Room.databaseBuilder(context.applicationContext,
                ShoppingDatabase::class.java,"name").fallbackToDestructiveMigration().build()
                INSTANCE=instance
                return instance
            }
        }
        }

    }
