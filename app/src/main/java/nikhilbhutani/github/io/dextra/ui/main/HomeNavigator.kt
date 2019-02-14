package nikhilbhutani.github.io.dextra.ui.main

import nikhilbhutani.github.io.dextra.data.remote.models.ExploreModel.ExploreResponse

interface HomeNavigator {

    fun setExploreResponseList(exploreList: List<ExploreResponse.Explore>)
    fun handleError(throwable: Throwable?)
    fun openDexActivity(dexId: String)
    fun showToast()


}