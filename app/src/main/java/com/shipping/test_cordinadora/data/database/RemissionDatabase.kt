package com.shipping.test_cordinadora.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shipping.test_cordinadora.data.database.dao.RemissionDao
import com.shipping.test_cordinadora.data.database.entities.RemissionEntity

@Database(entities = [RemissionEntity::class], version = 1)
abstract class RemissionDatabase: RoomDatabase() {

    abstract fun getRemissionDao(): RemissionDao
}