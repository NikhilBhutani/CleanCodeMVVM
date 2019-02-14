package nikhilbhutani.github.io.dextra.ui.dexes

import nikhilbhutani.github.io.dextra.data.remote.models.UserModel.DexUserResponse

interface DexNavigator {

    fun handleError(throwable: Throwable?)
    fun setUserList(
        user: List<DexUserResponse.User>,
        next: String
    )

    fun showToast()

}