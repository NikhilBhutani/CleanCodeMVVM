package nikhilbhutani.github.io.dextra.data.remote

import io.reactivex.Single
import nikhilbhutani.github.io.dextra.data.remote.models.ExploreModel.ExploreResponse
import nikhilbhutani.github.io.dextra.data.remote.models.UserModel.DexUserResponse

interface ApiHelper {

    fun fetchExplorePage(): Single<ExploreResponse>

    fun fetchDexUsers(dexId: String): Single<DexUserResponse>

}