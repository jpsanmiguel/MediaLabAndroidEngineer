package sanmi.labs.medialabandroidengineer.feature_user.domain.model

import java.io.Serializable
import java.util.*

data class User(
    val id: String = UUID.randomUUID().toString(),
    var imageUri: String = "",
    var name: String = "",
    var biography: String = "",
) : Serializable