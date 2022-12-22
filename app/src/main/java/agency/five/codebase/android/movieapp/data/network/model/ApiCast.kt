package agency.five.codebase.android.movieapp.data.network.model

import agency.five.codebase.android.movieapp.data.network.BASE_IMAGE_URL
import agency.five.codebase.android.movieapp.model.Actor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiCast(
    @SerialName("castId")
    val id: Int,
    @SerialName("firstNameCast")
    val firstName: String,
    @SerialName("lastNameCast")
    val lastName: String,
    @SerialName("character")
    val character: String,
    @SerialName("castPath")
    val castPath: String?
){
    fun toCast() = Actor(
            id = id,
            name = firstName+lastName,
            character = character,
            imageUrl = "$BASE_IMAGE_URL/$castPath"
    )

}
