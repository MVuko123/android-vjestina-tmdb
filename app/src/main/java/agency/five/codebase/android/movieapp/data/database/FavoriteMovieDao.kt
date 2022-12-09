package agency.five.codebase.android.movieapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {
    @Query("SELECT * FROM DbFavoriteMovie")
    fun favorites(): Flow<List<DbFavoriteMovie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIntoFavorites(dbFavoriteMovie: DbFavoriteMovie)

    @Delete
    fun deleteFromFavorites(dbFavoriteMovie: DbFavoriteMovie)
}
