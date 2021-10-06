package com.example.mvpdemo.ui.home

import com.example.mvpdemo.model.WikiHomepage

interface HomeView {

    fun displayLoading()
    fun dismissLoading()
    fun displayHomePage(result : WikiHomepage)
    fun displayError(err : String?)
}