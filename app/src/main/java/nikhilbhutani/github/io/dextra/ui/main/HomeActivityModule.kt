package nikhilbhutani.github.io.dextra.ui.main

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import nikhilbhutani.github.io.dextra.data.repository.Explore.ExploreRepository
import nikhilbhutani.github.io.dextra.data.repository.Explore.ExploreRepositoryImpl
import nikhilbhutani.github.io.dextra.di.scopes.ViewModelKey

@Module
abstract class HomeActivityModule {

    @Binds
    abstract fun bindsExploreRepository(exploreRepositoryImpl: ExploreRepositoryImpl): ExploreRepository

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindMyViewModel(homeViewModel: HomeViewModel): ViewModel


}