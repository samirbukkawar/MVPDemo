package com.example.mvpdemo.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpdemo.R
import com.example.mvpdemo.model.SearchEntry
import com.example.mvpdemo.util.parseHtml

class SearchResultAdapter(private val context: Context, private val results: List<SearchEntry>) :
    RecyclerView.Adapter<SearchResultAdapter.SearchResultHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultHolder =
        SearchResultHolder(
            LayoutInflater.from(context).inflate(R.layout.search_result, parent, false)
        )


    override fun onBindViewHolder(holder: SearchResultHolder, position: Int) {
        holder?.let {
            it.titleView.text = results[position].title
            it.snippetView.text = results[position].snippet.parseHtml()
        }
    }

    override fun getItemCount(): Int = results.size


    inner class SearchResultHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.title_tv)
        val snippetView: TextView = view.findViewById(R.id.snippet_tv)
    }
}