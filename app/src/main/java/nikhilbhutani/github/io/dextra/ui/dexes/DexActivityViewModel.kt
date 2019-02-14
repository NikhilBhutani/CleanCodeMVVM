package nikhilbhutani.github.io.dextra.ui.dexes

import io.reactivex.disposables.Disposable
import nikhilbhutani.github.io.dextra.data.remote.models.UserModel.DexUserResponse
import nikhilbhutani.github.io.dextra.data.repository.DexUsers.DexUserRepository
import nikhilbhutani.github.io.dextra.ui.base.BaseViewModel
import nikhilbhutani.github.io.dextra.utils.rxutils.SchedulerProvider
import javax.inject.Inject

class DexActivityViewModel @Inject constructor(
    val userRepository: DexUserRepository,
    val schedulerProvider: SchedulerProvider
) : BaseViewModel<DexNavigator>() {


    fun fetchUserList(dexId: String) {
        setupObserverForUserListResponse(dexId)
            .let {
                getCompositeDisposable().add(it)
            }
    }

    private fun setupObserverForUserListResponse(dexId: String): Disposable {
        return userRepository
            .fetchDexUsers(dexId)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe { t1: DexUserResponse?, t2: Throwable? ->

                getNavigator()?.setUserList(t1!!.user, t1.next)
            }

    }


}