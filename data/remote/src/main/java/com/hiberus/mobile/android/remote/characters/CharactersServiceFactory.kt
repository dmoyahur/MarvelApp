package com.hiberus.mobile.android.remote.characters

import com.hiberus.mobile.android.remote.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

object CharactersServiceFactory {

    private const val HASH_ALGORITHM = "MD5"
    private const val HASH_BYTE_FORMAT = "%02x"
    private const val TIMESTAMP = "ts"
    private const val API_KEY = "apikey"
    private const val HASH = "hash"

    fun makeCharactersService(): CharactersService {
        val okHttpClient = makeOkHttpClient(
            makeLoggingInterceptor()
        )
        val retrofit = Retrofit.Builder()
            .baseUrl(CharactersService.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CharactersService::class.java)
    }

    private fun makeOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val originalUrl = chain.request().url

                val timeStamp = System.currentTimeMillis()
                val input = "${timeStamp}${BuildConfig.MARVEL_PRIVATE_KEY}${BuildConfig.MARVEL_PUBLIC_KEY}"
                val hash = MessageDigest.getInstance(HASH_ALGORITHM)
                    .digest(input.toByteArray()).joinToString("") {
                        HASH_BYTE_FORMAT.format(it)
                    }
                val url = originalUrl.newBuilder()
                    .addQueryParameter(TIMESTAMP, "$timeStamp")
                    .addQueryParameter(API_KEY, BuildConfig.MARVEL_PUBLIC_KEY)
                    .addQueryParameter(HASH, hash)
                    .build()

                request.url(url)
                return@addInterceptor chain.proceed(request.build())
            }
            .addNetworkInterceptor(httpLoggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return logging
    }
}