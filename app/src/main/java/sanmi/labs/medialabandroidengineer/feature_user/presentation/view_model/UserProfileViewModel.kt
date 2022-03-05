package sanmi.labs.medialabandroidengineer.feature_user.presentation.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import sanmi.labs.medialabandroidengineer.feature_user.domain.model.User

class UserProfileViewModel(
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun initViewModel(user: User) {
        _user.value = user
    }

    fun setUserImage(imageUri: String) {
        _user.value?.imageUri = imageUri
    }
}