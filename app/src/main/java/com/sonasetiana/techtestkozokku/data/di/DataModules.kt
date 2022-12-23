package com.sonasetiana.techtestkozokku.data.di

import androidx.room.Room
import com.sonasetiana.techtestkozokku.BuildConfig
import com.sonasetiana.techtestkozokku.data.local.datasource.LocalDataSource
import com.sonasetiana.techtestkozokku.data.local.datasource.LocalDataSourceImpl
import com.sonasetiana.techtestkozokku.data.local.db.LocalDatabase
import com.sonasetiana.techtestkozokku.data.remote.datasource.RemoteDataSource
import com.sonasetiana.techtestkozokku.data.remote.datasource.RemoteDataSourceImpl
import com.sonasetiana.techtestkozokku.data.remote.network.ApiServices
import com.sonasetiana.techtestkozokku.data.remote.network.HeaderInterceptor
import com.sonasetiana.techtestkozokku.data.repository.LocalRepositoryImpl
import com.sonasetiana.techtestkozokku.data.repository.RemoteRepositoryImpl
import com.sonasetiana.techtestkozokku.domain.repository.LocalRepository
import com.sonasetiana.techtestkozokku.domain.repository.RemoteRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            LocalDatabase::class.java,
            "techtest.db"
        ).fallbackToDestructiveMigration().build()
    }
    factory { get<LocalDatabase>().favoriteDao() }
    factory { get<LocalDatabase>().ownerDao() }
    factory { get<LocalDatabase>().userDao() }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(HeaderInterceptor())
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiServices::class.java)
    }
}

val repositoryModule = module {
    single<LocalDataSource> { LocalDataSourceImpl(get(), get(), get()) }
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    single<RemoteRepository> { RemoteRepositoryImpl(get()) }
    single<LocalRepository> { LocalRepositoryImpl(get()) }
}