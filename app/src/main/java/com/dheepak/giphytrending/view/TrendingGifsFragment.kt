package com.dheepak.giphytrending.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.dheepak.giphytrending.R
import com.dheepak.giphytrending.view.adapter.TrendingGifsListAdapter
import com.dheepak.giphytrending.viewmodel.TrendingViewModel
import kotlinx.android.synthetic.main.fragment_trending_gifs.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class TrendingGifsFragment : Fragment() {

    private val trendingViewModel: TrendingViewModel by viewModel()
    lateinit var trendingGifsListAdapter: TrendingGifsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trending_gifs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupDataFlow()
    }

    private fun setupRecyclerView() {
        trendingGifsListAdapter = TrendingGifsListAdapter()
        trending_list_view.apply {
            val gridSpanCount = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
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
    }
}