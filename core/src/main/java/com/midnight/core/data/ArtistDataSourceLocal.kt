package com.midnight.core.data

import com.midnight.core.domain.ArtistModelCore

interface ArtistDataSourceLocal {
    fun getTwoArtistRandom():List<ArtistModelCore>?
    fun insertArtist(data:List<ArtistModelCore>?):List<Long>
}