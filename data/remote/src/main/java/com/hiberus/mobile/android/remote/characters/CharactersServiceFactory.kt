package com.hiberus.mobile.android.remote.characters

import com.facebook.stetho.okhttp3.StethoInterceptor
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

    fun makeCharactersService(isDebug: Boolean): CharactersService {
        val okHttpClient = makeOkHttpClient(
            makeLoggingInterceptor(isDebug),
            makeStethoInterceptor(isDebug)
        )
        val retrofit = Retrofit.Builder()
            .baseUrl(CharactersService.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CharactersService::class.java)
    }

    private fun makeOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        stethoInterceptor: StethoInterceptor?
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
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

        stethoInterceptor?.let { okHttpClient.addNetworkInterceptor(it) }

        return okHttpClient.build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        return logging
    }

    private fun makeStethoInterceptor(isDebug: Boolean): StethoInterceptor? {
        return if (isDebug) {
            StethoInterceptor()
        } else {
            null
        }
    }
}