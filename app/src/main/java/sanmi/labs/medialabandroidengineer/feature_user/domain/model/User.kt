package sanmi.labs.medialabandroidengineer.feature_user.domain.model

import java.io.Serializable

data class User(
    val id: String,
    var imageUri: String,
    var name: String,
    var biography: String,
) : Serializable