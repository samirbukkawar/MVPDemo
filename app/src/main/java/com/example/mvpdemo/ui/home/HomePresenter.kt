package com.example.mvpdemo.ui.home

interface HomePresenter {

    fun setView(homepageView: HomeView)

    fun loadHomepage()
}