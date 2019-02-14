package nikhilbhutani.github.io.dextra.di.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import nikhilbhutani.github.io.dextra.ui.dexes.DexActivity
import nikhilbhutani.github.io.dextra.ui.dexes.DexActivityModule
import nikhilbhutani.github.io.dextra.ui.main.HomeActivity
import nikhilbhutani.github.io.dextra.ui.main.HomeActivityModule

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = arrayOf(HomeActivityModule::class))
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = arrayOf(DexActivityModule::class))
    abstract fun dexActivity(): DexActivity

}