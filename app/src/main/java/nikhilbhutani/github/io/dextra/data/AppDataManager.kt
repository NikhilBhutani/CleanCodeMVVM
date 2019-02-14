package nikhilbhutani.github.io.dextra.data

import io.reactivex.Single
import nikhilbhutani.github.io.dextra.data.remote.ApiHelper
import nikhilbhutani.github.io.dextra.data.remote.models.ExploreModel.ExploreResponse
import nikhilbhutani.github.io.dextra.data.remote.models.UserModel.DexUserResponse
import javax.inject.Inject

class AppDataManager @Inject constructor(private val apiHelper: ApiHelper) : DataManager {

    override fun fetchExplorePage(): Single<ExploreResponse> {
        return apiHelper.fetchExplorePage()
    }

    override fun fetchDexUsers(dexId: String): Single<DexUserResponse> {
        return apiHelper.fetchDexUsers(dexId)
    }

}