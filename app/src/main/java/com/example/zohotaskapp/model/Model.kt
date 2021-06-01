package com.example.zohotaskapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryItem(
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

@Parcelize
data class Currency(
    val code: String?,
    val name: String?,
    val symbol: String?
) : Parcelable

@Parcelize
data class Language(
    val iso6391: String?,
    val iso6392: String?,
    val name: String?,
    val nativeName: String?
) : Parcelable

@Parcelize
data class RegionalBloc(
    val acronym: String?,
    val name: String?,
    val otherAcronyms: List<String>?,
    val otherNames: List<String>?
) : Parcelable

@Parcelize
data class Translations(
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