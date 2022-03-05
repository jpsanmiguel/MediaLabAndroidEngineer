package sanmi.labs.medialabandroidengineer.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.inject
import sanmi.labs.medialabandroidengineer.core.base.BaseTest
import sanmi.labs.medialabandroidengineer.feature_user.domain.repository.UserRepository
import sanmi.labs.medialabandroidengineer.util.randomString
import sanmi.labs.medialabandroidengineer.util.randomUser
import sanmi.labs.medialabandroidengineer.util.addNUsersBeforeAndAfterUser
import sanmi.labs.medialabandroidengineer.util.addNUsersToRepository

@RunWith(JUnit4::class)
class DefaultUserRepositoryTest : BaseTest() {

    private val userRepository by inject<UserRepository>()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun test_user_repository_get_user_list_empty_list() = runBlocking {

        val dataReceived = userRepository.getUsers()

        assertNotNull(dataReceived)
        assertEquals(dataReceived.size, 0)
    }

    @Test
    fun test_user_repository_save_user_empty_list() = runBlocking {
        val userToSave = randomUser()

        val dataReceived = userRepository.saveUser(userToSave)

        assertNotNull(dataReceived)
        assertEquals(dataReceived.size, 1)
        assertEquals(dataReceived[0], userToSave)
        // Get users test
        assertEquals(dataReceived, userRepository.getUsers())
        assertEquals(dataReceived.size, userRepository.getUsers().size)
    }

    @Test
    fun test_user_repository_save_user_more_items_in_list() = runBlocking {
        val numOfUsers = 4
        addNUsersToRepository(userRepository, numOfUsers)

        val userToSave = randomUser()
        val dataReceived = userRepository.saveUser(userToSave)

        assertNotNull(dataReceived)
        assertEquals(dataReceived.size, numOfUsers + 1)
        assertEquals(dataReceived[numOfUsers], userToSave)
        // Get users test
        assertEquals(dataReceived, userRepository.getUsers())
        assertEquals(dataReceived.size, userRepository.getUsers().size)
    }

    @Test
    fun test_user_repository_save_user_existing_in_list() = runBlocking {
        val numOfUsers = 3
        val userSaved = addNUsersBeforeAndAfterUser(userRepository, numOfUsers)

        val dataReceived = userRepository.saveUser(
            userSaved.copy(
                name = randomString(40),
                biography = randomString(140),
                imageUri = randomString(200)
            )
        )

        assertNotNull(dataReceived)
        // Save user test
        assertEquals(dataReceived.size, (numOfUsers * 2) + 1)
        assertEquals(dataReceived[numOfUsers], userSaved)
        assertEquals(dataReceived[numOfUsers].id, userSaved.id)
        assertEquals(dataReceived[numOfUsers].name, userSaved.name)
        assertEquals(dataReceived[numOfUsers].biography, userSaved.biography)
        assertEquals(dataReceived[numOfUsers].imageUri, userSaved.imageUri)
        // Get users test
        assertEquals(dataReceived, userRepository.getUsers())
        assertEquals(dataReceived.size, userRepository.getUsers().size)
    }

    @Test
    fun test_user_repository_delete_only_user_in_list() = runBlocking {
        val userToSave = randomUser()

        userRepository.saveUser(userToSave)

        val dataReceived = userRepository.deleteUser(userToSave)

        assertNotNull(dataReceived)
        assertEquals(dataReceived.size, 0)
        // Get users test
        assertEquals(dataReceived, userRepository.getUsers())
        assertEquals(dataReceived.size, userRepository.getUsers().size)
    }

    @Test
    fun test_user_repository_delete_user_more_in_list() = runBlocking {
        val numOfUsers = 4
        val userSaved = addNUsersBeforeAndAfterUser(userRepository, numOfUsers)

        val dataReceived = userRepository.deleteUser(userSaved)

        assertNotNull(dataReceived)
        assertEquals(dataReceived.size, numOfUsers * 2)
        // Get users test
        assertEquals(dataReceived, userRepository.getUsers())
        assertEquals(dataReceived.size, userRepository.getUsers().size)
    }

}