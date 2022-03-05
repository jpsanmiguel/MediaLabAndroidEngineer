package sanmi.labs.medialabandroidengineer.feature_user.presentation.view_model

import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import sanmi.labs.medialabandroidengineer.feature_user.domain.model.User
import sanmi.labs.medialabandroidengineer.feature_user.domain.use_case.UserUseCases

class UserProfileViewModel(
    private val userUseCases: UserUseCases,
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun initViewModel(user: User) {
        _user.value = user.copy()
    }

    fun setUserImage(imageUri: String) {
        _user.value?.imageUri = imageUri
    }

    fun setUserName(name: Editable) {
        _user.value?.name = name.toString().trim()
    }

    fun setUserBiography(biography: Editable) {
        _user.value?.biography = biography.toString().trim()
    }

    fun getSelectedUser(): User {
        return _user.value ?: User()
    }
}