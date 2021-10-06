package com.example.mvpdemo.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.mvpdemo.R
import com.example.mvpdemo.model.WikiHomepage
import com.example.mvpdemo.ui.search.SearchActivity
import com.example.mvpdemo.util.errorDialog
import com.example.mvpdemo.util.parseHtml
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePageActivity : Activity(), HomeView {

    private val presenter: HomePresenter = HomePresenterImplementation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        presenter.setView(this)
        presenter.loadHomepage()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.homepage, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    override fun displayLoading() {
        wait_progress_bar.post {
            wait_progress_bar.visibility = View.VISIBLE
            homepage_sv.visibility = View.GONE
        }
    }

    override fun dismissLoading() {
        wait_progress_bar.post {
            wait_progress_bar.visibility = View.GONE
            homepage_sv.visibility = View.VISIBLE
        }
    }

    override fun displayHomePage(result: WikiHomepage) {
        homepage_tv.post {
            homepage_tv.text = result.htmlContent.parseHtml()
        }
    }

    override fun displayError(err: String?) {
        Log.d("Error", "Error Message" + err!!)
        runOnUiThread { errorDialog(this, err) }
    }
}