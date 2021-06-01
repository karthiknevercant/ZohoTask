package com.example.zohotaskapp.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Keep
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

@Keep
@Entity(tableName = "CurrencyTable")
@Parcelize
data class Currency(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val code: String?,
    val name: String?,
    val symbol: String?
) : Parcelable

@Keep
@Entity(tableName = "LanguageTable")
@Parcelize
data class Language(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val iso6391: String?,
    val iso6392: String?,
    val name: String?,
    val nativeName: String?
) : Parcelable

@Keep
@Entity(tableName = "RegionalBlocTable")
@Parcelize
data class RegionalBloc(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val acronym: String?,
    val name: String?,
    val otherAcronyms: List<String>?,
    val otherNames: List<String>?
) : Parcelable

@Keep
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

data class WeatherData(
    val base: String?,
    val clouds: Clouds?,
    val cod: Int?,
    val coord: Coord?,
    val dt: Int?,
    val id: Int?,
    val main: Main?,
    val name: String?,
    val sys: Sys?,
    val timezone: Int?,
    val visibility: Int?,
    val weather: List<Weather>?,
    val wind: Wind?
)

data class Clouds(
    val all: Int?
)

data class Coord(
    val lat: Double?,
    val lon: Double?
)

data class Main(
    val feelsLike: Double?,
    val grndLevel: Int?,
    val humidity: Int?,
    val pressure: Int?,
    val seaLevel: Int?,
    val temp: Double?,
    val tempMax: Double?,
    val tempMin: Double?
)

data class Sys(
    val country: String?,
    val sunrise: Int?,
    val sunset: Int?
)

data class Weather(
    val description: String?,
    val icon: String?,
    val id: Int?,
    val main: String?
)

data class Wind(
    val deg: Int?,
    val gust: Double?,
    val speed: Double?
)