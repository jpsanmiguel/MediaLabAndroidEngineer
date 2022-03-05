package sanmi.labs.medialabandroidengineer.feature_user.domain.model

import java.io.Serializable

data class User(
    val id: String,
    val imageUri: String,
    val name: String,
    val biography: String,
) : Serializable