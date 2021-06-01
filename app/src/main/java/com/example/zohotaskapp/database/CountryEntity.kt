package com.example.zohotaskapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CountryTable")
data class CountryEntity(@PrimaryKey val id: Int, val name: String, val flag: String)