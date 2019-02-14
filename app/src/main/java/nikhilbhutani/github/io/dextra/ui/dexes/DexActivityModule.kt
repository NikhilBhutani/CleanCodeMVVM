package nikhilbhutani.github.io.dextra.ui.dexes

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import nikhilbhutani.github.io.dextra.data.repository.DexUsers.DexUserRepository
import nikhilbhutani.github.io.dextra.data.repository.DexUsers.DexUserRepositoryImpl
import nikhilbhutani.github.io.dextra.di.scopes.ViewModelKey


@Module
abstract class DexActivityModule {

    @Binds
    abstract fun bindsDexUserRespository(dexUserRepositoryImpl: DexUserRepositoryImpl): DexUserRepository

    @Binds
    @IntoMap
    @ViewModelKey(DexActivityViewModel::class)
    abstract fun bindMyDexViewModel(dexActivityViewModel: DexActivityViewModel): ViewModel


}