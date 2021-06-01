package com.example.zohotaskapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.zohotaskapp.database.converter.*
import com.example.zohotaskapp.model.*

@Database(
    entities = [CountryItem::class, Currency::class, Language::class, RegionalBloc::class, Translations::class],
    version = 1, exportSchema = false
)

@TypeConverters(
    StringListConverters::class,
    DoubleListConverters::class,
    CurrencyConverter::class,
    LanguagesTypeConverter::class,
    RegionalBlocConverter::class,
    TranslationsConverter::class
)
abstract class CountryDatabase : RoomDatabase() {

    abstract val countriesDao: CountryDao

    companion object {
        const val NAME = "countries"
    }

}