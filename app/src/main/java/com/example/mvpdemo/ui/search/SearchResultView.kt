package com.example.mvpdemo.ui.search

import com.example.mvpdemo.model.SearchEntry

interface SearchResultView {
    fun displayLoading()

    fun dismissLoading()

    fun displayEntries(results: List<SearchEntry>)

    fun displayError(error: String?)
}