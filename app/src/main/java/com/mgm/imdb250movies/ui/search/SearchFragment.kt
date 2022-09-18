package com.mgm.imdb250movies.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mgm.imdb250movies.R
import com.mgm.imdb250movies.databinding.FragmentSearchBinding
import com.mgm.imdb250movies.ui.home.HomeFragmentDirections
import com.mgm.imdb250movies.ui.home.adapters.LastMoviesAdapter
import com.mgm.imdb250movies.ui.home.adapters.TopMoviesAdapter
import com.mgm.imdb250movies.utils.initRecycler
import com.mgm.imdb250movies.utils.showInvisible
import com.mgm.imdb250movies.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    //Binding
    private lateinit var binding : FragmentSearchBinding
    @Inject
    lateinit var lastMoviesAdapter: LastMoviesAdapter
    //Other
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            //Search box
            edtSearch.addTextChangedListener {
                it.let {
                    //call search
                    viewModel.loadSearch(it.toString())
                }
            }
            //Observe List
            viewModel.response.observe(viewLifecycleOwner){
                lastMoviesAdapter.setData(it.data)
                searchRecycler.initRecycler(LinearLayoutManager(requireContext()),lastMoviesAdapter)
            }
            //Click
            lastMoviesAdapter.setOmItemClickListener {
                val direction  = HomeFragmentDirections.actionToDetail(it.id!!.toInt())
                findNavController().navigate(direction)
            }
            //Loading
            viewModel.loading.observe(viewLifecycleOwner){
                if (it){
                    loading.showInvisible(true)
                }else{
                    loading.showInvisible(false)
                }
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