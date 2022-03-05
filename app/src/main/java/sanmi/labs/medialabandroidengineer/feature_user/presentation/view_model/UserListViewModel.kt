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

    private val _users = MutableLiveData<List<User>>(emptyList())
    val users: LiveData<List<User>>
        get() = _users

    private val _navigateToSelectedUser = MutableLiveData<User?>()
    val navigateToSelectedUser: LiveData<User?>
        get() = _navigateToSelectedUser

    init {
        _users.value = listOf(
            User("1", "", "Juan Sanmiguel", "This is my biography."),
            User("2", "", "Juan Sanmiguel", "This is my biography."),
            User("3", "", "Juan Sanmiguel", "This is my biography."),
            User("4", "", "Juan Sanmiguel", "This is my biography."),
            User("5", "", "Juan Sanmiguel", "This is my biography."),
            User("6", "", "Juan Sanmiguel", "This is my biography."),
            User("7", "", "Juan Sanmiguel", "This is my biography."),
            User("8", "", "Juan Sanmiguel", "This is my biography."),
            User("9", "", "Juan Sanmiguel", "This is my biography."),
            User("10", "", "Juan Sanmiguel", "This is my biography."),
            User("11", "", "Juan Sanmiguel", "This is my biography."),
            User("12", "", "Juan Sanmiguel", "This is my biography."),
            User("13", "", "Juan Sanmiguel", "This is my biography."),
            User("14", "", "Juan Sanmiguel", "This is my biography."),
            User("15", "", "Juan Sanmiguel", "This is my biography."),
            User("16", "", "Juan Sanmiguel", "This is my biography."),
            User("17", "", "Juan Sanmiguel", "This is my biography."),
            User("18", "", "Juan Sanmiguel", "This is my biography."),
            User("19", "", "Juan Sanmiguel", "This is my biography."),
            User("20", "", "Juan Sanmiguel", "This is my biography."),
        )
    }

    fun navigateToSelectedUser(user: User) {
        _navigateToSelectedUser.value = user
    }

    fun finishedNavigateToSelectedUser() {
        _navigateToSelectedUser.value = null
    }

}