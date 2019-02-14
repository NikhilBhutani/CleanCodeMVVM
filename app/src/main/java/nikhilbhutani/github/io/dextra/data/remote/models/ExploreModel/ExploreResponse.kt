package nikhilbhutani.github.io.dextra.data.remote.models.ExploreModel

import com.google.gson.annotations.SerializedName

data class ExploreResponse(@SerializedName("explore") val explore: List<Explore>) {

    data class Explore(
        @SerializedName("title") val title: String, @SerializedName("sectionType") val sectionType: String,
        @SerializedName("entities") val entities: List<Entities>
    )

    data class Entities(
        @SerializedName("_id") val id: String,
        @SerializedName("firstName") val firstName: String,
        @SerializedName("lastName") val lastName: String,
        @SerializedName("totalProjectClaps") val totalProjectClaps: Int,
        @SerializedName("userTerm") val userTerm: String, @SerializedName("imageUrl") val imageUrl: String,
        @SerializedName("actionUrl") val actionUrl: String, @SerializedName("dex") val dex: Dex
    )

    data class Dex(
        @SerializedName("_id") val id: String, @SerializedName("userTerm") val userTerm: String, @SerializedName(
            "imageUrl"
        ) val imageUrl: String
    )

}