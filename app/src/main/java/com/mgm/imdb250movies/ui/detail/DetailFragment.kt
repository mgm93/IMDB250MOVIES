package com.mgm.imdb250movies.ui.detail

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.mgm.imdb250movies.R
import com.mgm.imdb250movies.databinding.FragmentDetailBinding
import com.mgm.imdb250movies.db.FavMoviesEntity
import com.mgm.imdb250movies.utils.initRecycler
import com.mgm.imdb250movies.utils.showInvisible
import com.mgm.imdb250movies.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {
    //Binding
    private lateinit var binding : FragmentDetailBinding

    @Inject
    lateinit var actorsImagesAdapter: ActorsImagesAdapter
    @Inject
    lateinit var entity: FavMoviesEntity
    //Other
    private var movieId = 0
    private val viewModel : DetailViewModel by viewModels()
    private val args : DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = args.movieID
        if (movieId >0)
            viewModel.loadDetailMovie(movieId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            viewModel.detail.observe(viewLifecycleOwner){ response ->
                posterBigImg.load(response.poster)
                posterNormalImg.load(response.poster){
                    crossfade(true)
                    crossfade(800)
                }
                titleTxt.text = response.title
                movieRateTxt.text = response.imdbRating
                movieTimeTxt.text = response.runtime
                movieDateTxt.text = response.released
                summaryTxt.text = response.plot
                actorsTxt.text = response.actors
                //load image actors
                actorsImagesAdapter.differ.submitList(response.images)
                imagesRecyclerView.initRecycler(LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                    ,actorsImagesAdapter)

                //favorite click
                favImg.setOnClickListener {
                    entity.id = movieId
                    entity.title = response.title.toString()
                    entity.rate = response.imdbRating.toString()
                    entity.year = response.year.toString()
                    entity.country = response.country.toString()
                    entity.poster = response.poster.toString()
                    viewModel.favoriteMovie(movieId, entity)
                }
            }
            //default icon color value
            lifecycleScope.launchWhenCreated {
                if (viewModel.existMovie(movieId)) {
                    favImg.setColorFilter(ContextCompat.getColor(requireContext(),R.color.scarlet))
                }else
                    favImg.setColorFilter(ContextCompat.getColor(requireContext(),R.color.philippineSilver))
            }
            //Change icon color with click
            viewModel.isFavorite.observe(viewLifecycleOwner){
                if (it) {
                    favImg.setColorFilter(ContextCompat.getColor(requireContext(),R.color.scarlet))
                }else
                    favImg.setColorFilter(ContextCompat.getColor(requireContext(),R.color.philippineSilver))
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
            //back click
            backImg.setOnClickListener {
                findNavController().navigateUp()
            }

        }
    }


}