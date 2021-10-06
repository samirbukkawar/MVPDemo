package com.example.mvpdemo.network

import com.example.mvpdemo.util.Constants
import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

class WikiApi(private val client: OkHttpClient) {

    fun search(query: String): Call {
        val urlBuilder =
            HttpUrl.parse("${Constants.PROTOCOL}://${Constants.LANGUAGE}.${Constants.BASE_URL}")
                ?.newBuilder()
                ?.addQueryParameter("action", "query")
                ?.addQueryParameter("list", "search")
                ?.addQueryParameter("format", "json")
                ?.addQueryParameter("srsearch", query)

        return Request.Builder()
            .url(urlBuilder?.build())
            .get()
            .build()
            .let {
                client.newCall(it)
            }
    }

    fun getHomePage(): Call {
        val urlBuilder =
            HttpUrl.parse("${Constants.PROTOCOL}://${Constants.LANGUAGE}.${Constants.BASE_URL}")
                ?.newBuilder()
                ?.addQueryParameter("action", "parse")
                ?.addQueryParameter("page", "Main Page")
                ?.addQueryParameter("format", "json")

        return Request.Builder()
            .url(urlBuilder?.build())
            .get()
            .build()
            .let {
                client.newCall(it)
            }
    }

}