package com.mgm.imdb250movies.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.google.android.material.snackbar.Snackbar
import com.mgm.imdb250movies.R
import com.mgm.imdb250movies.databinding.FragmentRegisterBinding
import com.mgm.imdb250movies.models.register.BodyRegister
import com.mgm.imdb250movies.utils.StoreUserData
import com.mgm.imdb250movies.utils.showInvisible
import com.mgm.imdb250movies.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    //Binding
    private lateinit var binding: FragmentRegisterBinding

    //Injects
    @Inject
    lateinit var storeUserData: StoreUserData

    @Inject
    lateinit var register: BodyRegister

    //ViewModel
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            //Click
            submitBtn.setOnClickListener { view ->
                val name = nameEdt.text.toString()
                val email = emailEdt.text.toString()
                val password = passwordEdt.text.toString()
                if (name.isEmpty() || email.isEmpty() || password.isEmpty())
                    Snackbar.make(view, getString(R.string.fillAllFields), Snackbar.LENGTH_SHORT)
                        .show()
                else {
                    register.email = email
                    register.name = name
                    register.password = password
                }
                //Send data
                viewModel.sendUserRegister(register)
                //loading
                viewModel.loading.observe(viewLifecycleOwner) { isShown ->
                    if (isShown) {
                        submitLoading.showInvisible(true)
                        submitBtn.showInvisible(false)
                    } else {
                        submitLoading.showInvisible(false)
                        submitBtn.showInvisible(true)
                    }
                }
                //Register
                viewModel.registerUser.observe(viewLifecycleOwner) {
                    lifecycle.coroutineScope.launchWhenCreated {
                        storeUserData.saveUserToken(it.name.toString())
                    }
                }
            }
        }
    }

}