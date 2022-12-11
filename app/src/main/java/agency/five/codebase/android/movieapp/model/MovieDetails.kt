package agency.five.codebase.android.movieapp.model

import agency.five.codebase.android.movieapp.data.network.model.ApiCast
import agency.five.codebase.android.movieapp.data.network.model.ApiCrew
import agency.five.codebase.android.movieapp.data.network.model.ApiMovie

data class MovieDetails(
    val movie: ApiMovie.Companion,
    val voteAverage: Float,
    val releaseDate: String,
    val language: String,
    val runtime: Int,
    val crew: List<ApiCrew>,
    val cast: List<ApiCast>,
    val isFavorite: Boolean,
)
