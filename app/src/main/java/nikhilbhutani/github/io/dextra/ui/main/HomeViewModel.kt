package nikhilbhutani.github.io.dextra.ui.main

import android.util.Log
import io.reactivex.disposables.Disposable
import nikhilbhutani.github.io.dextra.data.DataManager
import nikhilbhutani.github.io.dextra.data.remote.models.ExploreModel.ExploreResponse
import nikhilbhutani.github.io.dextra.data.repository.Explore.ExploreRepository
import nikhilbhutani.github.io.dextra.ui.base.BaseViewModel
import nikhilbhutani.github.io.dextra.utils.rxutils.SchedulerProvider
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val exploreRepository: ExploreRepository, val dataManager: DataManager,
    val schedulerProvider: SchedulerProvider
) : BaseViewModel<HomeNavigator>() {


    fun fetchData() {
        setupObserverForExploreResponse()
            .let {
                getCompositeDisposable().add(it)
            }
    }

    private fun setupObserverForExploreResponse(): Disposable {
        return exploreRepository
            .fetchExplorePage()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe { t1: ExploreResponse?, t2: Throwable? ->

                Log.i("Explore Response ", " $t1")
                getNavigator()?.setExploreResponseList(t1!!.explore)
            }

    }


}