package agency.five.codebase.android.movieapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbFavoriteMovie(
        @PrimaryKey
        val movieId: Int,
        val posterUrl: String
)
