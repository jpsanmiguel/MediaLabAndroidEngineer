package sanmi.labs.medialabandroidengineer.feature_user.domain.repository

import sanmi.labs.medialabandroidengineer.feature_user.domain.model.User

interface UserRepository {

    fun getUsers(): List<User>

    fun saveUser(user: User): List<User>

    fun deleteUser(user: User): List<User>
}