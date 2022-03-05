package sanmi.labs.medialabandroidengineer.feature_user.presentation.view_model

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sanmi.labs.medialabandroidengineer.core.util.UiText
import sanmi.labs.medialabandroidengineer.feature_user.domain.model.User
import sanmi.labs.medialabandroidengineer.R

class UserProfileViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val _nameEditTextError = MutableLiveData<UiText>()
    val nameEditTextError: LiveData<UiText>
        get() = _nameEditTextError

    fun initViewModel(user: User) {
        _user.value = user.copy()
    }

    fun setUserImage(imageUri: String) {
        _user.value?.imageUri = imageUri
    }

    fun setUserNameEditable(name: Editable) {
        setUserName(name.toString())
    }

    fun setUserName(name: String) {
        _user.value?.name = name.trim()
    }

    fun setUserBiographyEditable(biography: Editable) {
        setUserBiography(biography.toString())
    }

    fun setUserBiography(biography: String) {
        _user.value?.biography = biography.trim()
    }

    fun getSelectedUser(): User {
        return _user.value ?: User()
    }

    fun isFormComplete(): Boolean {
        return when {
            getSelectedUser().name.isBlank() -> {
                viewModelScope.launch {
                    _nameEditTextError.value = UiText.StringResource(R.string.name_cant_be_empty)
                }
                false
            }
            else -> {
                true
            }
        }
    }
}