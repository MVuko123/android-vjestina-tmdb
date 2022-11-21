package agency.five.codebase.android.movieapp.ui.moviedetails.mapper

import agency.five.codebase.android.movieapp.model.MovieDetails
import agency.five.codebase.android.movieapp.ui.moviedetails.ActorViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.CrewViewState
import agency.five.codebase.android.movieapp.ui.moviedetails.MovieDetailsViewState

class MovieDetailsMapperImpl() : MovieDetailsMapper {
    override fun toMovieDetailsViewState(movieDetails: MovieDetails): MovieDetailsViewState =
        MovieDetailsViewState(
            movieDetails.movie.id,
            movieDetails.movie.imageUrl,
            movieDetails.voteAverage,
            movieDetails.movie.title,
            movieDetails.movie.overview,
            movieDetails.movie.isFavorite,
            movieDetails.crew.map { crewman ->
                CrewViewState(
                    crewman.id,
                    crewman.name,
                    crewman.job
                )
            },
            movieDetails.cast.map { actor ->
                ActorViewState(
                    actor.id,
                    actor.imageUrl,
                    actor.name,
                    actor.character
                )
            }
        )
}
