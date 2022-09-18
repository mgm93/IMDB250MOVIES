package com.mgm.imdb250movies.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mgm.imdb250movies.R
import com.mgm.imdb250movies.databinding.FragmentFavoriteBinding
import com.mgm.imdb250movies.ui.home.HomeFragmentDirections
import com.mgm.imdb250movies.utils.initRecycler
import com.mgm.imdb250movies.utils.showInvisible
import com.mgm.imdb250movies.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    //Binding
    private lateinit var binding : FragmentFavoriteBinding
    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter

    //Other
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            //Call db favorite
            viewModel.loadFavorite()
            //Observe List
            viewModel.response.observe(viewLifecycleOwner){
                favoriteAdapter.setData(it)
                searchRecycler.initRecycler(LinearLayoutManager(requireContext()),favoriteAdapter)
            }
            //Click
            favoriteAdapter.setOmItemClickListener {
                val direction  = HomeFragmentDirections.actionToDetail(it.id)
                findNavController().navigate(direction)
            }
            //Empty Item
            viewModel.emptyResponse.observe(viewLifecycleOwner){
                if (it){
                    emptyList.showInvisible(true)
                    searchRecycler.showInvisible(false)
                }else{
                    emptyList.showInvisible(false)
                    searchRecycler.showInvisible(true)
                }
            }
        }
    }

}