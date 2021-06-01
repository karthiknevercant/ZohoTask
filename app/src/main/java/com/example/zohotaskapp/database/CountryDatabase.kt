package com.example.zohotaskapp.database

import androidx.room.RoomDatabase

abstract class CountryDatabase : RoomDatabase() {
    abstract val countriesDao: CountryDao
}