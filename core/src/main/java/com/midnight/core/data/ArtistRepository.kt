package com.midnight.core.data

import com.midnight.core.domain.ArtistModelCore
import com.midnight.core.helper.ApiCallHelper
import com.midnight.core.helper.DataState
import com.midnight.core.helper.NetworkErrorType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class ArtistRepository constructor(private val local:ArtistDataSourceLocal,private val remote:ArtistDataSourceRemote) {
    fun getTwoArtistRandom(isConnected:Boolean,artistId:Long):Flow<DataState<List<ArtistModelCore>?>> = flow {
        val localData: List<ArtistModelCore>? = local.getTwoArtistRandom()
        when {
            localData!=null&&localData.size==2->{
                emit(DataState.Success(localData))
            }
            isConnected->{
                ApiCallHelper.safeApiCall { remote.getRelatedArtist(artistId,1,2) }
                    .catch {
                        emit(DataState.LocalError())
                    }.collect {
                        when (it) {
                            is DataState.Success -> {
                                local.insertArtist(it.value)
                                emit(DataState.Success(it.value))
                            }
                            is DataState.LocalError -> {
                                if (localData==null||localData.size!=2){
                                    emit(DataState.LocalError())
                                }

                            }
                            is DataState.NetworkError -> {
                                if (localData==null||localData.size!=2){
                                    emit(DataState.NetworkError(it.networkError))
                                }

                            }
                            else -> emit(DataState.LocalError())
                        }
                    }
            }
            else->{
                if (localData==null||localData.size!=2) {
                    emit(DataState.NetworkError(NetworkErrorType.NetworkConnection()))
                }

            }
        }
    }.flowOn(Dispatchers.IO).catch {
        emit(DataState.LocalError())
    }
}