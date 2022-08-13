package com.kelly.coinplace.common

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

object Constants {
    const val BASE_URL = "https://api.coinpaprika.com/v1/"

    var ERROR_MESSAGE = ""

    fun <T> deserializeError(value: String, errorClass: Class<T>): Class<T> {
        return Gson().fromJson(value, errorClass::class.java)
    }

    fun getOkhttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder().addInterceptor(interceptor)
            .readTimeout(100, TimeUnit.SECONDS)
            .callTimeout(100, TimeUnit.SECONDS).build()
    }

    fun errorHandler(throwable: Throwable): Throwable {
        throwable.printStackTrace()
        return when (throwable) {
            is SocketException -> {
                Throwable("Connection Aborted, Check your internet connection")
            }
            is ConnectException -> {
                Throwable("Connection error, Check your internet connection")
            }
            is SocketTimeoutException -> {
                Throwable("Connection taking too long. Please try again")
            }
            is IOException -> {
                Throwable("Couldn't reach server, please check your internet connection")
            }
            else -> {
                Throwable("An unknown error occurred")
            }
        }
    }
}