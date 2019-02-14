package nikhilbhutani.github.io.dextra.data.repository.Explore

import io.reactivex.Single
import nikhilbhutani.github.io.dextra.data.DataManager
import nikhilbhutani.github.io.dextra.data.remote.models.ExploreModel.ExploreResponse
import javax.inject.Inject

class ExploreRepositoryImpl @Inject constructor(var dataManager: DataManager) : ExploreRepository {

    override fun fetchExplorePage(): Single<ExploreResponse> {
        return dataManager.fetchExplorePage()
    }
}