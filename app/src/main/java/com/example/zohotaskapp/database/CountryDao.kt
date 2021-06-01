package com.example.zohotaskapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zohotaskapp.model.CountryItem

@Dao
interface CountryDao {
    @Query("SELECT * FROM CountryTable")
    fun getCountries(): List<CountryItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(users: List<CountryItem>)
}