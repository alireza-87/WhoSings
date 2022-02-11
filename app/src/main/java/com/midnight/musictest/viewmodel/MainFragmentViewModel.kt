package com.midnight.musictest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midnight.core.domain.QueryUsersScoresCore
import com.midnight.core.domain.UserModelCore
import com.midnight.core.helper.DataState
import com.midnight.core.usercases.GetCurrentUser
import com.midnight.core.usercases.GetScoreList
import com.midnight.core.usercases.Logout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(val getScoreList: GetScoreList,val logout: Logout,private val getCurrentUser: GetCurrentUser):ViewModel() {
    val scoresLiveData : MutableLiveData<DataState<List<QueryUsersScoresCore>?>> = MutableLiveData()
    val logoutLiveData : MutableLiveData<DataState<Int>> = MutableLiveData()
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

    fun logout(userName:String){
        viewModelScope.launch {
            logout.execute(userName).catch {
                logoutLiveData.value = DataState.LocalError(it.message)
            }.collect {
                logoutLiveData.value = it
            }
        }
    }

    fun getScoreList(){
        viewModelScope.launch {
            scoresLiveData.value = DataState.Loading
            getScoreList.execute().catch {
                scoresLiveData.value = DataState.LocalError(it.message)
            }.collect {
                scoresLiveData.value = it
            }
        }
    }
}