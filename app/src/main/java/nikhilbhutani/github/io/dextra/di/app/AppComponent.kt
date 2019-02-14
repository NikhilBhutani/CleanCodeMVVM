package nikhilbhutani.github.io.dextra.di.app

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import nikhilbhutani.github.io.dextra.application.App
import nikhilbhutani.github.io.dextra.di.factorymodule.ViewModelFactoryModule
import nikhilbhutani.github.io.dextra.di.remotemodule.RemoteModule
import nikhilbhutani.github.io.dextra.utils.rxutils.SchedulerProvider
import javax.inject.Singleton


@Component(
    modules = arrayOf(
        AndroidInjectionModule::class, AppModule::class, ActivityBuilderModule::class, RemoteModule::class,
        ViewModelFactoryModule::class
    )
)
@Singleton
interface AppComponent {

    fun inject(app: App)

    fun schedulerProvider(): SchedulerProvider

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder

        fun appModule(appModule: AppModule): Builder
        fun remoteModule(remoteModule: RemoteModule): Builder
        fun build(): AppComponent

    }

}