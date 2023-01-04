package agency.five.codebase.android.movieapp.data.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val concurrencyModule = module {
    single (named("dispatcherIO")){
        Dispatchers.IO
    }
}
