package agency.five.codebase.android.movieapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {
    @Query("SELECT * FROM favoriteMovie")
    fun favorites(): Flow<List<DbFavoriteMovie>>

    @Insert(onConflict = REPLACE)
    fun insertIntoFavorites(favoriteMovie: DbFavoriteMovie)

    @Query("Delete from favoriteMovie where movieId=:movieId")
    fun deleteFromFavorites(movieId: Int)
}
