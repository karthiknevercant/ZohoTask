package com.example.zohotaskapp.database.converter

import androidx.room.TypeConverter
import com.example.zohotaskapp.model.Currency
import com.example.zohotaskapp.model.Language
import com.example.zohotaskapp.model.RegionalBloc
import com.example.zohotaskapp.model.Translations
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListConverters {
    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayListToString(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}

class DoubleListConverters {
    @TypeConverter
    fun fromString(value: String): List<Double> {
        val listType = object : TypeToken<List<Double>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayListToString(list: List<Double>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}

class LanguagesTypeConverter {
    @TypeConverter
    fun stringToLanguages(json: String): List<Language> {
        val gson = Gson()
        val type = object : TypeToken<List<Language>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun languageToString(list: List<Language>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Language>>() {}.type
        return gson.toJson(list, type)
    }
}


class CurrencyConverter {
    @TypeConverter
    fun stringToCurrency(json: String): List<Currency>? {
        val gson = Gson()
        val type = object : TypeToken<List<Currency>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun currencyToString(name: List<Currency>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Currency>>() {}.type
        return gson.toJson(name, type)
    }
}

class RegionalBlocConverter {
    @TypeConverter
    fun stringToRegionalBloc(json: String): List<RegionalBloc>? {
        val gson = Gson()
        val type = object : TypeToken<List<RegionalBloc>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun RegionalBlocToString(name: List<RegionalBloc>): String {
        val gson = Gson()
        val type = object : TypeToken<List<RegionalBloc>>() {}.type
        return gson.toJson(name, type)
    }
}

class TranslationsConverter {
    @TypeConverter
    fun stringToTranslations(json: String): Translations? {
        val gson = Gson()
        val type = object : TypeToken<Translations>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun translationsToString(translations: Translations): String {
        val gson = Gson()
        val type = object : TypeToken<Translations>() {}.type
        return gson.toJson(translations, type)
    }
}