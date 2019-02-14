package nikhilbhutani.github.io.dextra.data.remote

import io.reactivex.Single
import nikhilbhutani.github.io.dextra.data.remote.models.ExploreModel.ExploreResponse
import nikhilbhutani.github.io.dextra.data.remote.models.UserModel.DexUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("explore")
    fun fetchExplorePage(): Single<ExploreResponse>

    @GET("explore/user")
    fun fetchDexUsers(@Query("dexId") dexId: String): Single<DexUserResponse>
}