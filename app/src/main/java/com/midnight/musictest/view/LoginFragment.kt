package com.midnight.musictest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.midnight.core.domain.UserModelCore
import com.midnight.core.helper.DataState
import com.midnight.musictest.R
import com.midnight.musictest.databinding.FragmentLoginBinding
import com.midnight.musictest.viewmodel.LoginFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment:Fragment() {
    private lateinit var binder:FragmentLoginBinding
    private val viewModel:LoginFragmentViewModel by viewModels()
    private var errorDialog: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_login,container,false)
        initActions()
        initLiveData()
        return binder.root
    }

    private fun initActions(){
        binder.buttonLogin.setOnClickListener {
            binder.editTextUserName.text.let {
                viewModel.insertUser(UserModelCore(it.toString(), true))
            }

        }
    }

    private fun initLiveData(){
        viewModel.insertLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when(it){
                is DataState.Loading ->{
                    showLoading(true)
                }
                is DataState.LocalError -> {
                    showError(getString(R.string.error_user_exist))
                }
                is DataState.Success->{
                    openMainFragment()
                }
            }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun showLoading(isShow:Boolean){
        when (isShow){
            true ->{
                binder.progressBar.visibility = View.VISIBLE
            }
            false ->{
                binder.progressBar.visibility = View.GONE
            }
        }
    }

    private fun showError(data: String) {
        binder.progressBar.visibility = View.GONE
        errorDialog = Snackbar.make(binder.root, data, Snackbar.LENGTH_LONG)
        errorDialog?.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        errorDialog?.dismiss()
        binder.unbind()
    }

    private fun openMainFragment(){
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment(),NavOptions.Builder().setPopUpTo(findNavController().graph.startDestination, true).build())

    }

}