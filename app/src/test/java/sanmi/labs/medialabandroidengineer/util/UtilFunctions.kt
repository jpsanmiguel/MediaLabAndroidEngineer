package sanmi.labs.medialabandroidengineer.util

import sanmi.labs.medialabandroidengineer.feature_user.domain.model.User
import sanmi.labs.medialabandroidengineer.feature_user.domain.repository.UserRepository
import kotlin.streams.asSequence

fun addNUsersToRepository(userRepository: UserRepository, numOfUsers: Int) {
    for (i in 0 until numOfUsers) {
        userRepository.saveUser(randomUser())
    }
}

fun addNUsersBeforeAndAfterUser(userRepository: UserRepository, numOfUsers: Int): User {
    addNUsersToRepository(userRepository, numOfUsers)

    val userSaved = randomUser()
    userRepository.saveUser(userSaved)

    addNUsersToRepository(userRepository, numOfUsers)

    return userSaved
}

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