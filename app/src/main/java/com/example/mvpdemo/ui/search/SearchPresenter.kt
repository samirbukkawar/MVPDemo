package com.example.mvpdemo.ui.search

interface SearchPresenter {

    fun setView(entryView: SearchResultView)

    fun getEntry(query: String)
}