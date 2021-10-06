package com.example.mvpdemo.network

class Wiki(private val api: WikiApi) {
    fun search(searchText: String) = api.search(searchText)
}