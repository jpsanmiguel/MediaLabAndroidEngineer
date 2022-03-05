package sanmi.labs.medialabandroidengineer.presentation.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.inject
import sanmi.labs.medialabandroidengineer.core.base.BaseTest
import sanmi.labs.medialabandroidengineer.feature_user.domain.model.User
import sanmi.labs.medialabandroidengineer.feature_user.presentation.view_model.UserProfileViewModel
import sanmi.labs.medialabandroidengineer.util.MainCoroutineRule
import sanmi.labs.medialabandroidengineer.util.randomString
import sanmi.labs.medialabandroidengineer.util.randomUser

@RunWith(JUnit4::class)
class UserProfileViewModelTest : BaseTest() {

    private val viewModel by inject<UserProfileViewModel>()

    private val user = randomUser()

    private val imageUri = randomString(200)
    private val name = randomString(40)
    private val biography = randomString(140)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Test
    fun `User by default is null`() = runBlocking {
        val value = viewModel.user.value
        assert(value == null)
    }

    @Test
    fun `After initViewModel user no longer null`() = runBlocking {
        viewModel.initViewModel(user)

        val value = viewModel.user.value

        assert(value != null)
        assertEquals(value, user)
    }

    @Test
    fun `Set user image`() = runBlocking {
        viewModel.initViewModel(user)

        viewModel.user.observeForever { }

        viewModel.setUserImage(imageUri)

        val value = viewModel.user.value

        assert(value != null)
        assertEquals(value?.imageUri, imageUri)
    }

    @Test
    fun `Set user name`() = runBlocking {
        viewModel.initViewModel(user)

        viewModel.user.observeForever { }

        viewModel.setUserName(name)

        val value = viewModel.user.value

        assert(value != null)
        assertEquals(value?.name, name)
    }

    @Test
    fun `Set user biography`() = runBlocking {
        viewModel.initViewModel(user)

        viewModel.user.observeForever { }

        viewModel.setUserBiography(biography)

        val value = viewModel.user.value

        assert(value != null)
        assertEquals(value?.biography, biography)
    }

    @Test
    fun `Get selected user`() = runBlocking {
        viewModel.initViewModel(user)

        val value = viewModel.getSelectedUser()

        assertEquals(value, user)
    }

    @Test
    fun `Check if form is complete with empty user`() = runBlocking {
        viewModel.initViewModel(User())

        val value = viewModel.isFormComplete()

        assertEquals(value, false)
    }

    @Test
    fun `Check if form is complete with user only with name`() = runBlocking {
        viewModel.initViewModel(User(name = randomString(40)))

        val value = viewModel.isFormComplete()

        assertEquals(value, true)
    }
}