package com.cursokotlin.mvvmexample.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cursokotlin.mvvmexample.data.database.dao.RemissionDao
import com.cursokotlin.mvvmexample.data.database.entities.RemissionEntity

@Database(entities = [RemissionEntity::class], version = 1)
abstract class RemissionDatabase: RoomDatabase() {

    abstract fun getRemissionDao(): RemissionDao
}