package com.mgm.imdb250movies.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.mgm.imdb250movies.databinding.FragmentHomeBinding
import com.mgm.imdb250movies.ui.home.adapters.TopMoviesAdapter
import com.mgm.imdb250movies.utils.initRecycler
import com.mgm.imdb250movies.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var topMoviesAdapter: TopMoviesAdapter
    //Other
    private val viewModel: HomeViewModel by viewModels()
    private val pagerSnapHelper :PagerSnapHelper by lazy { PagerSnapHelper() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Call Get top movies list
        viewModel.callTopMovies(3)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //InitView
        binding.apply {
            //Get Top Movies
            viewModel.topMoviesList.observe(viewLifecycleOwner){
                //setList in topMoviesAdapter
                topMoviesAdapter.differ.submitList(it.data)
                //Top Movies Recycler
                recyclerTopMovies.initRecycler(
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
                ,topMoviesAdapter
                )
                //PagerHelper
                pagerSnapHelper.attachToRecyclerView(recyclerTopMovies)
                topMoviesIndicator.attachToRecyclerView(recyclerTopMovies, pagerSnapHelper)
            }
        }
    }

}