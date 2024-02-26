package com.cursokotlin.mvvmexample.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cursokotlin.mvvmexample.data.database.entities.RemissionEntity

@Dao
interface RemissionDao {

    @Query("SELECT * FROM remission_table ORDER BY id DESC")
    suspend fun getAllQuotes():List<RemissionEntity>
    @Query("SELECT * FROM remission_table ORDER BY `order` ASC LIMIT :pageSize OFFSET :offset")
    suspend fun getRemissionsInBatches(pageSize: Int, offset: Int): List<RemissionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remissionModel:List<RemissionEntity>)

    @Query("DELETE FROM remission_table")
    suspend fun deleteAllQuotes()

    @Update
    fun updateOrder(items: List<RemissionEntity>):Int
}



