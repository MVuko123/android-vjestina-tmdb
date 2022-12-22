package agency.five.codebase.android.movieapp.data.network.model

import agency.five.codebase.android.movieapp.model.Crewman
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiCrew(
    @SerialName("crewId")
    val id: Int,
    @SerialName("firstNameCrew")
    val firstName: String,
    @SerialName("lastNameCrew")
    val lastName: String,
    @SerialName("job")
    val job: String
){
    fun toCrew() = Crewman(
            id = id,
            name = firstName+lastName,
            job = job
        )
}
