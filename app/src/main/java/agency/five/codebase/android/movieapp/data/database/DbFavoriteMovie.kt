package agency.five.codebase.android.movieapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
data class Movie(
        @PrimaryKey
        val movieId: Int,
        val posterUrl: String
)
