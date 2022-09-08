package com.mgm.imdb250movies.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.mgm.imdb250movies.R
import com.mgm.imdb250movies.databinding.FragmentSplashBinding
import com.mgm.imdb250movies.utils.StoreUserData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {
    //Binding
    private lateinit var binding : FragmentSplashBinding

    //Other
    @Inject
    lateinit var storeUserData: StoreUserData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set delay
        lifecycle.coroutineScope.launchWhenCreated {
            delay(1000)
            //check user data
           storeUserData.getUserToken().collect{
               if (it.isEmpty()) {
                   findNavController().navigate(R.id.actionSplashToRegister)
               }else{
                   findNavController().navigate(R.id.actionToHome)
               }
           }
        }
    }

}