package agency.five.codebase.android.movieapp.data.network.model

import agency.five.codebase.android.movieapp.model.Actor
import agency.five.codebase.android.movieapp.model.Crewman
import agency.five.codebase.android.movieapp.model.Movie
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
        movie: Movie,
        crew: List<Crewman>,
        cast: List<Actor>
    ):MovieDetails{
       return MovieDetails(
           movie = movie,
           voteAverage = voteAverage,
           releaseDate = releaseDate,
           language = language,
           runtime = runtime,
           crew = crew,
           cast = cast
       )
    }
}