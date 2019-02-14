package nikhilbhutani.github.io.dextra.data.remote

import io.reactivex.Single
import nikhilbhutani.github.io.dextra.data.remote.models.ExploreModel.ExploreResponse
import nikhilbhutani.github.io.dextra.data.remote.models.UserModel.DexUserResponse
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val apiService: ApiService) : ApiHelper {


    override fun fetchExplorePage(): Single<ExploreResponse> {
        return apiService.fetchExplorePage()
    }

    override fun fetchDexUsers(dexId: String): Single<DexUserResponse> {
        return apiService.fetchDexUsers(dexId)
    }


}