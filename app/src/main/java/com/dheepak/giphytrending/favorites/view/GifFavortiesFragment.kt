package com.dheepak.giphytrending.favorites.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.dheepak.giphytrending.R
import com.dheepak.giphytrending.favorites.viewmodel.FavoritesViewmodel
import com.dheepak.giphytrending.common.adapter.TrendingGifsListAdapter
import com.dheepak.giphytrending.common.model.DataItem
import kotlinx.android.synthetic.main.fragment_gif_favorties.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class GifFavortiesFragment : Fragment() {

    private val favoritesViewmodel: FavoritesViewmodel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gif_favorties, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val trendingGifsListAdapter = TrendingGifsListAdapter(object : TrendingGifsListAdapter.OnCLick{
            override fun performOperationForFavoriteClick(dataItem: DataItem) {
                favoritesViewmodel.deleteFromFavorites(dataItem.id)
            }

        },true)
        favorites_list_view.apply {
            val gridSpanCount = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                2
            } else {
                4
            }
            layoutManager = GridLayoutManager(context, gridSpanCount)
            setHasFixedSize(true)
            adapter = trendingGifsListAdapter
        }
        lifecycleScope.launch {
            favoritesViewmodel.getFavorites().collect {
                trendingGifsListAdapter.submitData(it)
            }
        }
    }

}