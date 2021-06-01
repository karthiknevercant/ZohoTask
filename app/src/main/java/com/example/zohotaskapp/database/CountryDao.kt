package com.example.zohotaskapp.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CountryDao {
    @Query("SELECT * FROM CountryTable")
    fun getCountries(): List<CountryEntity>
}