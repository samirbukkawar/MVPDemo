package com.example.mvpdemo.network

class Homepage(private val api: WikiApi) {
    fun get() = api.getHomePage()
}