package nikhilbhutani.github.io.dextra.di.app

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable
import nikhilbhutani.github.io.dextra.data.AppDataManager
import nikhilbhutani.github.io.dextra.data.DataManager
import nikhilbhutani.github.io.dextra.data.remote.AppApiHelper
import nikhilbhutani.github.io.dextra.utils.rxutils.AppSchedulerProvider
import nikhilbhutani.github.io.dextra.utils.rxutils.SchedulerProvider
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Reusable
    fun provideAppContext(): Context {
        return application
    }

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @Singleton
    fun providesDataManager(appApiHelper: AppApiHelper): DataManager {
        return AppDataManager(appApiHelper)
    }
}