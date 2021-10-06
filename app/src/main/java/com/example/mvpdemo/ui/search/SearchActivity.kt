package com.example.mvpdemo.ui.search

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvpdemo.R
import com.example.mvpdemo.model.SearchEntry
import com.example.mvpdemo.util.errorDialog
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : Activity(), SearchResultView {

    private var searchPresenter: SearchPresenter = SearchPresenterImplementation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        actionBar?.setHomeAsUpIndicator(R.drawable.ic_home)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        results_rv.layoutManager = LinearLayoutManager(this)

        searchPresenter.setView(this)
    }

    // Create the menu entries
    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    // Bind menu entries with their actions
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)

        menu?.findItem(R.id.search)?.let { menuItem ->
            (menuItem.actionView as? SearchView)?.apply {
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(query: String) = true
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        searchPresenter.getEntry(query ?: "")
                        return true
                    }
                })

                queryHint = getString(R.string.search_hint)
            }

            menuItem.expandActionView()
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun displayLoading() {
        wait_progress_bar.post {
            wait_progress_bar.visibility = View.VISIBLE
            results_rv.visibility = View.GONE
        }
    }

    override fun dismissLoading() {
        wait_progress_bar.post {
            wait_progress_bar.visibility = View.GONE
            results_rv.visibility = View.VISIBLE
        }
    }

    override fun displayEntries(results: List<SearchEntry>) {
        results_rv.post() {
            results_rv.adapter = SearchResultAdapter(this, results)
        }
    }

    override fun displayError(err: String?) {
        Log.d("Error", err!!)
        runOnUiThread { errorDialog(this, err) }
    }
}