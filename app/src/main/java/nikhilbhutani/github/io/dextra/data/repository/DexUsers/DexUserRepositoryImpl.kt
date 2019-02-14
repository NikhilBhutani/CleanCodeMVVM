package nikhilbhutani.github.io.dextra.data.repository.DexUsers

import io.reactivex.Single
import nikhilbhutani.github.io.dextra.data.DataManager
import nikhilbhutani.github.io.dextra.data.remote.models.UserModel.DexUserResponse
import javax.inject.Inject

class DexUserRepositoryImpl @Inject constructor(var dataManager: DataManager) : DexUserRepository {

    override fun fetchDexUsers(dexId: String): Single<DexUserResponse> {
        return dataManager.fetchDexUsers(dexId)
    }
}