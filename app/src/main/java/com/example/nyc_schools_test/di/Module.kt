package com.example.nyc_schools_test.di

import android.util.Log
import com.example.nyc_schools_test.common.BASE_URL
import com.example.nyc_schools_test.model.remote.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ViewModelComponent::class)
interface ServiceModule {

    @ViewModelScoped
    @Binds
    fun bindRepository(
        repositoryImpl: RepositoryImpl
    ): Repository

    @ViewModelScoped
    @Binds
    fun bindRemoteDataSource(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ): RemoteDataSource

//    @ViewModelScoped
//    @Binds
//    fun bindLocalDataSource(
//        localDataSourceImpl: LocalDataSourceImpl
//    ): LocalDataSource

    companion object {

        @Provides
        fun provideExceptionHandler(): CoroutineExceptionHandler =
            CoroutineExceptionHandler { context, throwable ->
                Log.d(
                    "ViewModel",
                    "provideExceptionHandler: $throwable"
                )
            }


        @Provides
        fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO


        @Provides
        fun provideCoroutineScope(dispatcher: CoroutineDispatcher): CoroutineScope =
            CoroutineScope(dispatcher)


//        @Provides
//        @ProductionDB
//        fun provideRoom(@ApplicationContext context: Context): HeroesDB =
//            Room.databaseBuilder(
//                context,
//                HeroesDB::class.java, DATABASE_NAME
//            ).fallbackToDestructiveMigration().build()


//        @Provides
//        fun provideDao(@ProductionDB dataBase: HeroesDB.Companion, context: Context) =
//            dataBase.getInstance(context).heroesDao()

        @Provides
        fun provideService(): RemoteApi =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkHttpClient())
                .build()
                .create(RemoteApi::class.java)


        @Provides
        fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
    }
}



