package nikhilbhutani.github.io.dextra.di.factorymodule

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import nikhilbhutani.github.io.dextra.factory.AppViewModelFactory

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: AppViewModelFactory): ViewModelProvider.Factory

}