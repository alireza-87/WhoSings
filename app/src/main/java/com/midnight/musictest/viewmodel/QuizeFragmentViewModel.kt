package com.midnight.musictest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midnight.core.domain.ArtistModelCore
import com.midnight.core.domain.LyricModelCore
import com.midnight.core.domain.TrackModelCore
import com.midnight.core.domain.UserScoreModelCore
import com.midnight.core.helper.DataState
import com.midnight.core.usercases.GetLyric
import com.midnight.core.usercases.GetOneTrack
import com.midnight.core.usercases.GetTwoArtist
import com.midnight.core.usercases.InsertScore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizeFragmentViewModel @Inject constructor(val getOneTrack: GetOneTrack,val getTwoArtist: GetTwoArtist,val getLyric: GetLyric,val insertScore:InsertScore):ViewModel() {
    val trackLiveData : MutableLiveData<DataState<TrackModelCore>> = MutableLiveData()
    val artistLiveData : MutableLiveData<DataState<List<ArtistModelCore>?>> = MutableLiveData()
    val lyricLiveData : MutableLiveData<DataState<LyricModelCore?>> = MutableLiveData()

    fun getOneTrack(isConnected:Boolean,page:Int,pageSize:Int,country:String,hasLyric:Int){
        viewModelScope.launch {
            trackLiveData.value = DataState.Loading
            getOneTrack.execute(isConnected, page, pageSize, country, hasLyric).catch {
            }.collect {
                trackLiveData.value = it
            }
        }
    }

    fun getTwoArtist(isConnected:Boolean,artistId:Long){
        viewModelScope.launch {
            artistLiveData.value = DataState.Loading
            getTwoArtist.execute(isConnected,artistId ).catch {
            }.collect {
                artistLiveData.value = it
            }
        }
    }

    fun getLyric(isConnected:Boolean,trackId:Long){
        viewModelScope.launch {
            lyricLiveData.value = DataState.Loading
            getLyric.execute(isConnected,trackId ).catch {
            }.collect {
                lyricLiveData.value = it
            }
        }
    }

    fun insertScore(data:UserScoreModelCore){
        viewModelScope.launch {
            insertScore.execute(data).catch {

            }.collect {

            }
        }
    }
}