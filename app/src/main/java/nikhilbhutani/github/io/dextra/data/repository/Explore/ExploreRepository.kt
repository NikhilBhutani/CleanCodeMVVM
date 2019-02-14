package nikhilbhutani.github.io.dextra.data.repository.Explore

import io.reactivex.Single
import nikhilbhutani.github.io.dextra.data.remote.models.ExploreModel.ExploreResponse

interface ExploreRepository {
    fun fetchExplorePage(): Single<ExploreResponse>
}