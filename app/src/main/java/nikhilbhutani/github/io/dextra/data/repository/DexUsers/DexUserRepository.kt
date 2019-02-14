package nikhilbhutani.github.io.dextra.data.repository.DexUsers

import io.reactivex.Single
import nikhilbhutani.github.io.dextra.data.remote.models.UserModel.DexUserResponse

interface DexUserRepository {
    fun fetchDexUsers(dexId: String): Single<DexUserResponse>

}