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
class LoginFragmentViewModel @Inject constructor(private val insertUser: InsertUser):ViewModel() {
    val insertLiveData : MutableLiveData<DataState<Long>> = MutableLiveData()
    val userLiveData : MutableLiveData<DataState<UserModelCore?>> = MutableLiveData()

    fun insertUser(data:UserModelCore){
        viewModelScope.launch {
            insertLiveData.value = DataState.Loading
            insertUser.execute(data).catch {
                insertLiveData.value = DataState.LocalError(it.message)
            }.collect {
                insertLiveData.value = it
            }
        }
    }

}