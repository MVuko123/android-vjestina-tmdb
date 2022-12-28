package agency.five.codebase.android.movieapp.model

import agency.five.codebase.android.movieapp.ui.home.upcomingCategory

enum class MovieCategory {
    POPULAR_STREAMING,
    POPULAR_ON_TV,
    POPULAR_FOR_RENT,
    POPULAR_IN_THEATRES,

    NOW_PLAYING_MOVIES,
    NOW_PLAYING_TV,

    UPCOMING_TODAY,
    UPCOMING_THIS_WEEK;

    companion object {
        fun getByOrdinal(ordinal: Int) = values().firstOrNull { it.ordinal == ordinal }
        //oridnal gives a position
        //returns the first matching element
    }
}
