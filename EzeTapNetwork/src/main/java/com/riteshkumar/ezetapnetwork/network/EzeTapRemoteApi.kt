package com.riteshkumar.ezetapnetwork.network

import android.util.Log
import androidx.annotation.WorkerThread
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import java.io.InputStream
import java.net.URL
import java.util.concurrent.TimeUnit

class EzeTapRemoteApi {
    private var okHttpClient: OkHttpClient? = null

    init {
        val okHttpClientBuilder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor {
                message -> Log.v("OkHttp", message)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(interceptor)
        okHttpClient = okHttpClientBuilder
            .connectTimeout(15000, TimeUnit.MILLISECONDS)
            .readTimeout(30000, TimeUnit.MILLISECONDS)
            .writeTimeout(30000, TimeUnit.MILLISECONDS)
            .build()
    }

    @WorkerThread
    fun fetchCustomUI(url: String?): JSONObject {
        val networkResponseString: String
        val httpUrl: HttpUrl? = url?.toHttpUrlOrNull()
        httpUrl?.newBuilder()?.build()
        val request: Request = Request.Builder().url(httpUrl!!).build()
        val response = okHttpClient?.newCall(request)?.execute()

        if (response?.isSuccessful?.not() == true) {
            throw Exception("getting Error in Response. HttpCode: ${response.code}")
        }
        val body: ResponseBody? = response?.body
        return if (body != null) {
            val responseString = body.string()
            networkResponseString = responseString
            JSONObject(networkResponseString)
        } else {
            throw Exception("getting Error in Response. HttpCode: ${response?.code}")
        }
    }

    fun fetchImage(url: String?): ByteArray? {
        return try {
            val inputStream: InputStream = URL(url).content as InputStream
            inputStream.readBytes()
        } catch (e: Exception) {
            null
        }
    }
}