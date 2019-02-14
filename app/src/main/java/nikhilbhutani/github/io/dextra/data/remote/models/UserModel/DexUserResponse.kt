package nikhilbhutani.github.io.dextra.data.remote.models.UserModel

import com.google.gson.annotations.SerializedName

class DexUserResponse(@SerializedName("users") val user: List<User>, @SerializedName("next") val next: String) {

    class User(
        @SerializedName("firstName") val firstName: String,
        @SerializedName("lastName") val lastName: String,
        @SerializedName("totalProjectClaps") val totalProjectClaps: Int,
        @SerializedName("imageUrl") val imageUrl: String
    )

}