package com.example.mvpdemo.ui.home

import android.util.Log
import com.example.mvpdemo.model.HomepageResult
import com.example.mvpdemo.network.Homepage
import com.example.mvpdemo.network.UnsafeOkHttpClient
import com.example.mvpdemo.network.WikiApi
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

class HomePresenterImplementation : HomePresenter {

    private lateinit var homeView: HomeView

    //This is an unsafe solution. Used it for Demo stuff
    private val client: OkHttpClient =  UnsafeOkHttpClient.getUnsafeOkHttpClient().build()
    private val api: WikiApi = WikiApi(client)
    private val homepage: Homepage = Homepage(api)

    override fun setView(homeView: HomeView) {
        this.homeView = homeView
    }

    override fun loadHomepage() {
        homeView.displayLoading()
        homepage.get().enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                homeView.dismissLoading()
                if (response?.isSuccessful == true) {
                    response.let {
                        HomepageResult(it).homepage?.let {
                            homeView.displayHomePage(it)
                        } ?: run {
                            homeView.displayError(response.message())
                        }
                    }
                } else {
                    homeView.displayError(response?.message())
                }
            }

            override fun onFailure(call: Call?, t: IOException?) {
                Log.d("HomePage", " :: >> " + t?.message)
                homeView.dismissLoading()
                homeView.displayError(t?.message)
                t?.printStackTrace()
            }
        })
    }

}