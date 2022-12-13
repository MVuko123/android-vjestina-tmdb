package agency.five.codebase.android.movieapp.data.di

import agency.five.codebase.android.movieapp.data.repository.MovieRepository
import agency.five.codebase.android.movieapp.data.repository.MovieRepositoryImpl
import org.koin.dsl.module


val dataModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(get(), get(), get(), get())
    }
}

 

