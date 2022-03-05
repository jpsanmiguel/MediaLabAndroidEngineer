package sanmi.labs.medialabandroidengineer.util

import sanmi.labs.medialabandroidengineer.feature_user.domain.model.User
import kotlin.streams.asSequence

fun randomUser(): User {
    return User(
        name = randomString(40),
        biography = randomString(140),
        imageUri = randomString(160)
    )
}

fun randomString(length: Long): String {
    val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    return java.util.Random().ints(length, 0, source.length)
        .asSequence()
        .map(source::get)
        .joinToString("")
}