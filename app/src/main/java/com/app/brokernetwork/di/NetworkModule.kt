package com.app.brokernetwork.di

import android.content.Context
import com.app.brokernetwork.App
import com.app.brokernetwork.data.network.NetworkResponseAdapterFactory
import com.app.brokernetwork.domain.repository.BrokerApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        private const val READ_TIMEOUT = 30L
        private const val WRITE_TIMEOUT = 30L
        private const val CONNECTION_TIMEOUT = 10L
        private const val CACHE_SIZE_BYTES = 10 * 1024 * 1024L
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient, gsonConverterFactory: GsonConverterFactory) : Retrofit {
        return Retrofit.Builder().baseUrl("https://run.mocky.io/v3/")
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
    }

    @Provides
    fun gsonConverter() : GsonConverterFactory{
        return GsonConverterFactory.create(GsonBuilder().create())
    }

    @Provides
    fun provideClient(
        interceptor: Interceptor,
        cache: Cache
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient().newBuilder().apply {
            cache(cache)
            addInterceptor(interceptor)
            addInterceptor(loggingInterceptor)
            connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        }.build()
    }

    @Provides
    fun provideInterceptor(): Interceptor {
        return Interceptor{
            val requestBuilder = it.request().newBuilder()
            it.proceed(requestBuilder.build())
        }
    }

    @Provides
    fun provideCache(context: Context): Cache {
        val cacheDir = File(context.cacheDir.absolutePath, "HttpCache")
        return Cache(cacheDir, CACHE_SIZE_BYTES)
    }

    @Provides
    fun provideApplication(@ApplicationContext app: Context): App {
        return app as App
    }

    @Provides
    fun provideApplicationContext(application: App): Context = application.applicationContext

    @Provides
    fun provideBrokerApi(retrofit: Retrofit): BrokerApi = retrofit.create(BrokerApi::class.java)
}