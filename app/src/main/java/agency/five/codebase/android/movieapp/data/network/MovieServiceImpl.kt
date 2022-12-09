package agency.five.codebase.android.movieapp.data.network

import agency.five.codebase.android.movieapp.data.network.model.ApiMovieDetails
import agency.five.codebase.android.movieapp.data.network.model.MovieCreditsResponse
import agency.five.codebase.android.movieapp.data.network.model.MovieResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
private const val BASE_URL = "https://api.themoviedb.org/3"
private const val API_KEY = "da8a17106e7ca013dd7b54ed7a3a10f2"

class MovieServiceImpl(private val client: HttpClient) : MovieService {
    override suspend fun fetchPopularMovies(): MovieResponse {
        return client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                path("/movie/popular?api_key=$API_KEY")
            }
        }
    }

    override suspend fun fetchNowPlayingMovies(): MovieResponse {
        return client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                path("/movie/now_playing?api_key=$API_KEY")
            }
        }
    }

    override suspend fun fetchUpcomingMovies(): MovieResponse {
        return client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                path("/movie/upcoming?api_key=$API_KEY")
            }
        }
    }

    override suspend fun fetchTopRatedMovies(): MovieResponse {
        return client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                path("/movie/top_rated?api_key=$API_KEY")
            }
        }
    }

    override suspend fun fetchMovieDetails(movieId: Int): ApiMovieDetails {
        return client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                path("/movie/$movieId?api_key=$API_KEY")
            }
        }
    }

    override suspend fun fetchMovieCredits(movieId: Int): MovieCreditsResponse {
        return client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                path("/movie/$movieId/credits?api_key=$API_KEY")
            }
        }
    }
}