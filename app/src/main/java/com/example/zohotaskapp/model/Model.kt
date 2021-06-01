package com.example.zohotaskapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "CountryTable")
@Parcelize
data class CountryItem(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val alpha2Code: String?,
    val alpha3Code: String?,
    val altSpellings: List<String>?,
    val area: Double?,
    val borders: List<String>?,
    val callingCodes: List<String>?,
    val capital: String?,
    val cioc: String?,
    val currencies: List<Currency>?,
    val demonym: String?,
    val flag: String?,
    val gini: Double?,
    val languages: List<Language>?,
    val latlng: List<Double>?,
    val name: String? = null,
    val nativeName: String?,
    val numericCode: String?,
    val population: Int?,
    val region: String?,
    val regionalBlocs: List<RegionalBloc>?,
    val subregion: String?,
    val timezones: List<String>?,
    val topLevelDomain: List<String>?,
    val translations: Translations?
) : Parcelable

@Entity(tableName = "CurrencyTable")
@Parcelize
data class Currency(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val code: String?,
    val name: String?,
    val symbol: String?
) : Parcelable

@Entity(tableName = "LanguageTable")
@Parcelize
data class Language(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val iso6391: String?,
    val iso6392: String?,
    val name: String?,
    val nativeName: String?
) : Parcelable

@Entity(tableName = "RegionalBlocTable")
@Parcelize
data class RegionalBloc(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val acronym: String?,
    val name: String?,
    val otherAcronyms: List<String>?,
    val otherNames: List<String>?
) : Parcelable

@Entity(tableName = "TranslationsTable")
@Parcelize
data class Translations(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val br: String?,
    val de: String?,
    val es: String?,
    val fa: String?,
    val fr: String?,
    val hr: String?,
    val it: String?,
    val ja: String?,
    val nl: String?,
    val pt: String?
) : Parcelable