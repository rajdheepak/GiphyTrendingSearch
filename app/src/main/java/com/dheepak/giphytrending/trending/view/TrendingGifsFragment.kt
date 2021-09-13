package com.dheepak.giphytrending.trending.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.dheepak.giphytrending.R
import com.dheepak.giphytrending.common.adapter.TrendingGifsListAdapter
import com.dheepak.giphytrending.common.model.DataItem
import com.dheepak.giphytrending.trending.viewmodel.TrendingViewModel
import kotlinx.android.synthetic.main.fragment_trending_gifs.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel

class TrendingGifsFragment : Fragment() {

    private val trendingViewModel: TrendingViewModel by viewModel()
    lateinit var trendingGifsListAdapter: TrendingGifsListAdapter
    lateinit var searchGifsListAdapter: TrendingGifsListAdapter
    private var searchJob: Job? = null
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trending_gifs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupRecyclerView()
        setupDataFlow()
        setupFavorites()
        setupSearchView()
        setupRefresh()
    }

    private fun setupRefresh() {
        swipe_refresh.setOnRefreshListener {
            swipe_refresh.isRefreshing = false
            trendingGifsListAdapter.refresh()
            searchGifsListAdapter.refresh()
        }
    }

    private fun setupSearchView() {
        search_view.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchJob?.cancel()
                if (newText?.isEmpty()!!) {
                    trending_list_view.apply {
                        val gridSpanCount =
                            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                                2
                            } else {
                                4
                            }
                        layoutManager = GridLayoutManager(context, gridSpanCount)
                        setHasFixedSize(true)
                        adapter = trendingGifsListAdapter
                    }
                } else {
                    searchJob = coroutineScope.launch {
                        delay(500)
                        lifecycleScope.launch {
                            trending_list_view.apply {
                                val gridSpanCount =
                                    if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                                        2
                                    } else {
                                        4
                                    }
                                layoutManager = GridLayoutManager(context, gridSpanCount)
                                setHasFixedSize(true)
                                adapter = searchGifsListAdapter
                            }
                            trendingViewModel.actionSearch(newText)
                        }
                    }
                }
                return false
            }

        })
    }

    private fun setupFavorites() {
        trendingViewModel.favoritesData().observe(viewLifecycleOwner, {
            trendingGifsListAdapter.updateFavorites(it)
            searchGifsListAdapter.updateFavorites(it)
        })
    }

    private fun setupAdapter() {
        trendingGifsListAdapter = TrendingGifsListAdapter(object : TrendingGifsListAdapter.OnCLick {
            override fun performOperationForFavoriteClick(dataItem: DataItem) {
                trendingViewModel.performFavoriteAction(dataItem)
            }

        })
        searchGifsListAdapter = TrendingGifsListAdapter(object : TrendingGifsListAdapter.OnCLick {
            override fun performOperationForFavoriteClick(dataItem: DataItem) {
                trendingViewModel.performFavoriteAction(dataItem)
            }

        })
    }

    private fun setupRecyclerView() {
        trending_list_view.apply {
            val gridSpanCount =
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    2
                } else {
                    4
                }
            layoutManager = GridLayoutManager(context, gridSpanCount)
            setHasFixedSize(true)
            adapter = trendingGifsListAdapter
        }
    }

    private fun setupDataFlow() {
        lifecycleScope.launch {
            trendingViewModel.trendingGifsData.collect {
                trendingGifsListAdapter.submitData(it)
            }
        }
        trendingViewModel.searchGifsData().observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                searchGifsListAdapter.submitData(it)
            }
        })
    }
}