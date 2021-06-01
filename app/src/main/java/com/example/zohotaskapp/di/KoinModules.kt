package com.example.zohotaskapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.zohotaskapp.AppConstants
import com.example.zohotaskapp.api.CountryApi
import com.example.zohotaskapp.database.CountryDao
import com.example.zohotaskapp.database.CountryDatabase
import com.example.zohotaskapp.modules.countries.CountriesRepositary
import com.example.zohotaskapp.modules.countries.CountriesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig.DEBUG
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {

    fun provideCountrytApi(retrofit: Retrofit): CountryApi {
        return retrofit.create(CountryApi::class.java)
    }

    single { provideCountrytApi(get()) }
}

val databaseModule = module {

    fun provideDatabase(application: Application): CountryDatabase {
        return Room.databaseBuilder(application, CountryDatabase::class.java, CountryDatabase.NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: CountryDatabase): CountryDao {
        return database.countriesDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}

val networkModule = module {
    val connectTimeout: Long = 40// 20s
    val readTimeout: Long = 40 // 20s

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
        if (DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    single {
        provideRetrofit(get(), AppConstants.BASE_URL)
    }

    single { provideHttpClient() }
}

val repositoryModule = module {
    fun provideCountryRepository(
        api: CountryApi,
        context: Context,
        dao: CountryDao
    ): CountriesRepositary {
        return CountriesRepositary.CountriesRepositaryImpl(api, context, dao)
    }
    single { provideCountryRepository(get(), androidContext(), get()) }
}

val viewModelModule = module {
    viewModel {
        CountriesViewModel(repository = get())
    }

}