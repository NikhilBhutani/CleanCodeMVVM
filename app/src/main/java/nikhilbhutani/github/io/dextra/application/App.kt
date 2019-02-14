package nikhilbhutani.github.io.dextra.application

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import nikhilbhutani.github.io.dextra.di.app.AppComponent
import nikhilbhutani.github.io.dextra.di.app.AppModule
import nikhilbhutani.github.io.dextra.di.app.DaggerAppComponent
import nikhilbhutani.github.io.dextra.di.remotemodule.RemoteModule
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()

        initializeDagger()
        appComponent.inject(this)
    }

    @Suppress("DEPRECATION")
    private fun initializeDagger() {

        appComponent = DaggerAppComponent.builder()
            .application(this)
            .appModule(AppModule(this))
            .remoteModule(RemoteModule())
            .build()
    }


    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}