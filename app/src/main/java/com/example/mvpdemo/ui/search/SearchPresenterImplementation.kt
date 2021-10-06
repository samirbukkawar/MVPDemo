package com.example.mvpdemo.ui.search

import com.example.mvpdemo.model.SearchResult
import com.example.mvpdemo.network.UnsafeOkHttpClient
import com.example.mvpdemo.network.Wiki
import com.example.mvpdemo.network.WikiApi
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

class SearchPresenterImplementation : SearchPresenter {

    private lateinit var searchResultView: SearchResultView

    private val client: OkHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient().build()
    private val api: WikiApi = WikiApi(client)
    private val wiki: Wiki = Wiki(api)

    override fun setView(entryView: SearchResultView) {
        this.searchResultView = entryView
    }

    override fun getEntry(query: String) {
        searchResultView.displayLoading()
        wiki.search(query).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                searchResultView.dismissLoading()
                //Everything is ok, show the result if not null
                if (response?.isSuccessful == true) {
                    SearchResult(response).list?.let {
                        searchResultView.displayEntries(it)
                    }
                } else {
                    searchResultView.displayError(response?.message())
                }
            }

            override fun onFailure(call: Call?, t: IOException?) {
                searchResultView.dismissLoading()
                searchResultView.displayError(t?.message)
                t?.printStackTrace()
            }
        })
    }
}