package agency.five.codebase.android.movieapp.ui.moviedetails

data class MovieDetailsViewState(
    val id: Int = 1,
    val imageUrl: String? = " ",
    val voteAverage: Float = 0.0f,
    val title: String = " ",
    val overview: String = " ",
    val isFavorite: Boolean = false,
    val crew: List<CrewViewState> = listOf(),
    val cast: List<ActorViewState>  = listOf(),
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
