package sanmi.labs.medialabandroidengineer.feature_user.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sanmi.labs.medialabandroidengineer.feature_user.domain.model.User
import sanmi.labs.medialabandroidengineer.feature_user.domain.use_case.UserUseCases

class UserListViewModel(
    private val userUseCases: UserUseCases,
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    private val _navigateToSelectedUser = MutableLiveData<User?>()
    val navigateToSelectedUser: LiveData<User?>
        get() = _navigateToSelectedUser

    init {
        _users.value = userUseCases.getUsersUseCase()
    }

    fun navigateToSelectedUser(user: User) {
        _navigateToSelectedUser.value = user
    }

    fun finishedNavigateToSelectedUser() {
        _navigateToSelectedUser.value = null
    }

    fun saveUser(user: User) {
        _users.value = userUseCases.saveUserUseCase(user)
    }

    fun removeUser(position: Int) {
        _users.value = userUseCases.deleteUserUseCase(getUserAtPosition(position))
    }

    private fun getUserAtPosition(position: Int): User {
        return _users.value?.get(position) ?: User()
    }

}