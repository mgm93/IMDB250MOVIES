package com.mgm.imdb250movies.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mgm.imdb250movies.utils.Constants

/**
 * Created by Majid-Golmoradi on 9/14/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Entity(tableName = Constants.FAV_TABLE)
data class FavMoviesEntity(
    @PrimaryKey
    var id:Int=0,
    var title: String="",
    var poster: String="",
    var rate : String="",
    var year: String="",
    var country:String=""
)
