package com.bignerdranch.nyethack

import java.io.File

class PremadeWorldMap {

    companion object{
        private const val MAPS_FILEPATH = "nyethack.maps"

        fun load() = File(MAPS_FILEPATH).readBytes()
    }
}