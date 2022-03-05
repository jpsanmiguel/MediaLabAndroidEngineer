package sanmi.labs.medialabandroidengineer.feature_user.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sanmi.labs.medialabandroidengineer.feature_user.domain.model.User
import sanmi.labs.medialabandroidengineer.feature_user.util.Status

class UserListViewModel : ViewModel() {

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    private val usersList = mutableListOf<User>()

    private val _users = MutableLiveData<List<User>>(emptyList())
    val users: LiveData<List<User>>
        get() = _users

    private val _navigateToSelectedUser = MutableLiveData<User?>()
    val navigateToSelectedUser: LiveData<User?>
        get() = _navigateToSelectedUser

    fun navigateToSelectedUser(user: User) {
        _navigateToSelectedUser.value = user
    }

    fun finishedNavigateToSelectedUser() {
        _navigateToSelectedUser.value = null
    }

    fun saveUser(userUpdated: User) {
        val existingUser = _users.value?.firstOrNull { it.id == userUpdated.id }
        if (existingUser != null) {
            updateUser(existingUser, userUpdated)
        } else {
            usersList.add(userUpdated)
        }
        _users.value = usersList
    }

    fun removeUser(user: User) {
        usersList.removeAt(usersList.indexOf(usersList.first { it.id == user.id }))
        _users.value = usersList
    }

    private fun updateUser(user: User, userUpdated: User) {
        if (user.id == userUpdated.id) {
            user.imageUri = userUpdated.imageUri
            user.name = userUpdated.name
            user.biography = userUpdated.biography
        }
    }

}