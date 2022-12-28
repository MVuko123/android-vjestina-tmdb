package agency.five.codebase.android.movieapp.mock

import kotlinx.coroutines.flow.*

object FavoritesDBMock {
    private val _favoriteIds = MutableStateFlow<Set<Int>>(setOf())
    val favoriteIds: StateFlow<Set<Int>> = _favoriteIds.asStateFlow()

    fun insert(movieId: Int) {
        _favoriteIds.update {
            it.plus(movieId)
        }
    }

    fun delete(movieId: Int) {
        _favoriteIds.update {
            it.minus(movieId)
        }
    }
}
