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
import sanmi.labs.medialabandroidengineer.feature_user.presentation.view_model.UserListViewModel
import sanmi.labs.medialabandroidengineer.util.randomUser

@RunWith(JUnit4::class)
class UserListViewModelTest : BaseTest() {

    private val viewModel by inject<UserListViewModel>()

    private val userOne = randomUser()
    private val userTwo = randomUser()
    private val userThree = randomUser()

    private fun addThreeUsersToViewModel() {
        viewModel.saveUser(userOne)
        viewModel.saveUser(userTwo)
        viewModel.saveUser(userThree)
    }

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `Getting users empty list on create`() = runBlocking {
        assertEquals(viewModel.users.value?.size, 0)
    }

    @Test
    fun `Navigate to selected user`() = runBlocking {
        viewModel.navigateToSelectedUser.observeForever {}

        viewModel.navigateToSelectedUser(userOne)

        val value = viewModel.navigateToSelectedUser.value
        assert(value != null)
        assertEquals(value, userOne)
    }

    @Test
    fun `Save user with empty list`() = runBlocking {

        viewModel.users.observeForever {}

        viewModel.saveUser(userOne)

        val value = viewModel.users.value

        assert(value != null)
        assertEquals(value?.size, 1)
        assertEquals(value?.get(0), userOne)
    }

    @Test
    fun `Save multiple users in list`() = runBlocking {
        viewModel.users.observeForever {}

        addThreeUsersToViewModel()

        val value = viewModel.users.value

        assert(value != null)
        assertEquals(value?.size, 3)
        assertEquals(value?.get(0), userOne)
        assertEquals(value?.get(1), userTwo)
        assertEquals(value?.get(2), userThree)
    }

    @Test
    fun `Delete all users from list`() = runBlocking {
        viewModel.users.observeForever {}

        addThreeUsersToViewModel()

        viewModel.removeUser(0)

        var value = viewModel.users.value

        assert(value != null)
        assertEquals(value?.size, 2)
        assertEquals(value?.get(0), userTwo)
        assertEquals(value?.get(1), userThree)

        viewModel.removeUser(0)

        value = viewModel.users.value

        assert(value != null)
        assertEquals(value?.size, 1)
        assertEquals(value?.get(0), userThree)

        viewModel.removeUser(0)

        value = viewModel.users.value

        assert(value != null)
        assertEquals(value?.size, 0)
    }
}