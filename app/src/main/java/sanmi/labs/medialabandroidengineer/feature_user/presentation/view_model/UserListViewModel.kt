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

    init {
        _users.value = listOf(
            User("1", "", "Juan Sanmiguel", "This is my biography."),
            User("2", "", "Jorge Sanmiguel", "This is my biography.")
        )
    }

}