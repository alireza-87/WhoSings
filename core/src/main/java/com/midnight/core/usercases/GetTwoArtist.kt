package com.midnight.core.usercases

import com.midnight.core.data.ArtistRepository
import com.midnight.core.domain.ArtistModelCore
import com.midnight.core.helper.DataState
import kotlinx.coroutines.flow.Flow

class GetTwoArtist constructor(val repo:ArtistRepository) {
    fun execute(isConnected:Boolean,artistId:Long):Flow<DataState<List<ArtistModelCore>?>>{
        return repo.getTwoArtistRandom(isConnected, artistId)
    }
}