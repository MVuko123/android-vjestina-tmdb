package agency.five.codebase.android.movieapp.data.di

import agency.five.codebase.android.movieapp.data.database.FavoriteMovieDao
import agency.five.codebase.android.movieapp.data.database.FavoriteMovieDao_Impl
import agency.five.codebase.android.movieapp.data.database.MovieAppDatabase
import agency.five.codebase.android.movieapp.data.repository.MovieRepositoryImpl
import androidx.room.Room
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val APP_DATABASE_NAME = "app_database.db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            MovieAppDatabase::class.java,
            APP_DATABASE_NAME,
        ).build()
    }
    fun favoriteMovieDao(db: MovieAppDatabase):FavoriteMovieDao = db.favoriteMovieDao()
    single{
        favoriteMovieDao(get())
    }
}
