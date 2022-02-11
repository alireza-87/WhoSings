package com.midnight.musictest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midnight.core.domain.UserModelCore
import com.midnight.core.helper.DataState
import com.midnight.core.usercases.GetCurrentUser
import com.midnight.core.usercases.InsertUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val getCurrentUser: GetCurrentUser):ViewModel() {
    val userLiveData : MutableLiveData<DataState<UserModelCore?>> = MutableLiveData()

    fun getCurrentUser(){
        viewModelScope.launch {
            getCurrentUser.execute().catch {
                userLiveData.value = DataState.LocalError()
            }.collect {
                userLiveData.value = it
            }
        }
    }
}