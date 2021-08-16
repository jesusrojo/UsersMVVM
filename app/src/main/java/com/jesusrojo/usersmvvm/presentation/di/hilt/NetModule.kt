package com.jesusrojo.usersmvvm.presentation.di.hilt


import com.jakewharton.espresso.OkHttp3IdlingResource
import com.jesusrojo.usersmvvm.data.api.UsersAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


// RETROFIT
private val okHttp2Client: OkHttpClient.Builder = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

private val client = okHttp2Client.build()

// TESTING
val idlingResource = OkHttp3IdlingResource.create("okhttp", client)



@Module
@InstallIn(SingletonComponent::class)
class NetModule {

//    // RETROFIT
//    private val okHttp2Client: OkHttpClient.Builder = OkHttpClient.Builder()
//                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//
//    private val client = okHttp2Client.build()
//
//    // TESTING
//    val idlingResource = OkHttp3IdlingResource.create("okhttp", client)

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
         return Retrofit.Builder()
             .addConverterFactory(GsonConverterFactory.create())
             .baseUrl(UsersAPIService.BASE_URL)
            // .client(client)
             .build()
    }

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit): UsersAPIService {
        return retrofit.create(UsersAPIService::class.java)
    }
}