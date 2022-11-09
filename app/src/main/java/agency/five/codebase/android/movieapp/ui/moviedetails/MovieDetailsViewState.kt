package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.ui.component.ActorCardViewState
import agency.five.codebase.android.movieapp.ui.component.CrewCardViewState

data class MovieDetailsViewState(
    val id: Int,
    val imageUrl: String?,
    val voteAverage: Float,
    val title: String,
    val overview: String,
    val isFavorite: Boolean,
    val crew: List<CrewViewState>,
    val cast: List<ActorViewState>,
)

data class CrewViewState(
    val id: Int,
    val name: String,
    val role: String,
)

data class ActorViewState(
    val id: Int,
    val imageUrl: String?,
    val name: String,
    val character: String,
)
