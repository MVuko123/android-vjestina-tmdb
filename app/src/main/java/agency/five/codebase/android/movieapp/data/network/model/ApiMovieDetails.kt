package agency.five.codebase.android.movieapp.data.network.model


import agency.five.codebase.android.movieapp.model.MovieDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiMovieDetails(
    @SerialName("movieDetailsId")
    val id: Int,
    @SerialName("movieTitle")
    val title: String,
    @SerialName("releaseDate")
    val releaseDate: String,
    @SerialName("voteAverage")
    val voteAverage: Float,
    @SerialName("language")
    val language: String,
    @SerialName("runtime")
    val runtime: Int,
){
    fun toMovieDetails(
        crew: List<ApiCrew>,
        cast: List<ApiCast>,
        isFavorite: Boolean
    ):MovieDetails{
       return MovieDetails(
           movie = ApiMovie,
           voteAverage = voteAverage,
           releaseDate = releaseDate,
           language = language,
           runtime = runtime,
           crew = crew,
           cast = cast,
           isFavorite = isFavorite
       )
    }
}
