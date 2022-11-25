package agency.five.codebase.android.movieapp.mock

import kotlinx.coroutines.flow.StateFlow

object FavoritesDBMock {
    val favoriteIds: StateFlow<Set<Int>> = TODO()
    fun insert(movieId: Int) {
        favoriteIds.value.contains(movieId)
    }
    fun delete(movieId: Int) {
        favoriteIds.value.contains(movieId)
    }
}

