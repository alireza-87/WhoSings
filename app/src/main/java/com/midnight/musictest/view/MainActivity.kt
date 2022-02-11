package com.midnight.musictest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.midnight.core.helper.DataState
import com.midnight.musictest.R
import com.midnight.musictest.databinding.ActivityMainBinding
import com.midnight.musictest.viewmodel.LoginFragmentViewModel
import com.midnight.musictest.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binder: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initLiveData()
        viewModel.getCurrentUser()
    }

    private fun initLiveData(){


        viewModel.userLiveData.observe(this, androidx.lifecycle.Observer {
            it?.let {
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
                val navController = navHostFragment.navController

                val navGraph = navController.navInflater.inflate(R.navigation.app_nav)

                when(it){
                    is DataState.Success->{
                        it.value?.let { it2->
                            navGraph.startDestination = R.id.main_fragment
                        }
                    }
                    else ->{
                        navGraph.startDestination = R.id.login_fragment
                    }
                }
                navController.graph = navGraph
            }

        })
    }

}