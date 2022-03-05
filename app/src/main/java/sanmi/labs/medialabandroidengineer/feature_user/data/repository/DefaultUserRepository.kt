package sanmi.labs.medialabandroidengineer.feature_user.data.repository

import sanmi.labs.medialabandroidengineer.feature_user.domain.model.User
import sanmi.labs.medialabandroidengineer.feature_user.domain.repository.UserRepository

class DefaultUserRepository : UserRepository {
    private val userList = mutableListOf<User>()

    override fun getUsers(): List<User> {
        return userList
    }

    override fun saveUser(user: User): List<User> {
        return userList.apply {
            val existingUser = firstOrNull { it.id == user.id }
            if (existingUser != null) {
                existingUser.apply {
                    imageUri = user.imageUri
                    name = user.name
                    biography = user.biography
                }
            } else {
                add(user)
            }
        }
    }

    override fun deleteUser(user: User): List<User> {
        return userList.apply {
            removeAt(userList.indexOf(userList.first { it.id == user.id }))
        }
    }
}