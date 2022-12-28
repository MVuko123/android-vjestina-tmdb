package agency.five.codebase.android.movieapp.data.network.model

import agency.five.codebase.android.movieapp.data.network.BASE_IMAGE_URL
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiMovieDetails(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("vote_average")
    val voteAverage: Float,
    @SerialName("original_language")
    val language: String,
    @SerialName("runtime")
    val runtime: Int,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("overview")
    val overview: String
){
    fun toMovieDetails(
        crew: List<ApiCrew>,
        cast: List<ApiCast>,
        isFavorite: Boolean
    ) = MovieDetails(
           movie = Movie(
               id = id,
               title = title,
               overview = overview,
               imageUrl = "$BASE_IMAGE_URL/$posterPath",
               isFavorite = isFavorite
           ),
           voteAverage = voteAverage,
           releaseDate = releaseDate,
           language = language,
           runtime = runtime,
           crew = crew.map { it.toCrew() } ,
           cast = cast.map { it.toCast() },
           isFavorite = isFavorite
    )
}
