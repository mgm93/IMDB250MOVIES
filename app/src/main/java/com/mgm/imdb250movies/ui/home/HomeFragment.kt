package com.mgm.imdb250movies.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.GeneratedAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.mgm.imdb250movies.databinding.FragmentHomeBinding
import com.mgm.imdb250movies.databinding.ItemHomeLastMoviesBinding
import com.mgm.imdb250movies.ui.home.adapters.GenresListAdapter
import com.mgm.imdb250movies.ui.home.adapters.LastMoviesAdapter
import com.mgm.imdb250movies.ui.home.adapters.TopMoviesAdapter
import com.mgm.imdb250movies.utils.initRecycler
import com.mgm.imdb250movies.utils.showInvisible
import com.mgm.imdb250movies.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var topMoviesAdapter: TopMoviesAdapter

    @Inject
    lateinit var genresListAdapter: GenresListAdapter

    @Inject
    lateinit var lastMovieAdapter: LastMoviesAdapter

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
        viewModel.loadTopMovies(1)
        viewModel.loadGenres()
        viewModel.loadLastMovies()
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

            //Get Genres
            viewModel.genresList.observe(viewLifecycleOwner){
                //setList in topMoviesAdapter
                genresListAdapter.differ.submitList(it)
                //Top Movies Recycler
                recyclerGenres.initRecycler(
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
                    ,genresListAdapter
                )
            }

            //Get Last Movies
            viewModel.lastMoviesList.observe(viewLifecycleOwner){
                //setList in topMoviesAdapter
                lastMovieAdapter.setData(it.data)
                //Top Movies Recycler
                recyclerLastMovies.initRecycler(
                    LinearLayoutManager(context)
                    ,lastMovieAdapter
                )
            }
            //Click
            lastMovieAdapter.setOmItemClickListener {
                val direction  = HomeFragmentDirections.actionToDetail(it.id!!.toInt())
                findNavController().navigate(direction)
            }
            //loading
            viewModel.loading.observe(viewLifecycleOwner){
                if (it){
                    loading.showInvisible(true)
                    nested.showInvisible(false)
                }else{
                    loading.showInvisible(false)
                    nested.showInvisible(true)
                }
            }
        }
    }

}