package sanmi.labs.medialabandroidengineer.domain.use_case

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.inject
import sanmi.labs.medialabandroidengineer.core.base.BaseTest
import sanmi.labs.medialabandroidengineer.feature_user.domain.repository.UserRepository
import sanmi.labs.medialabandroidengineer.feature_user.domain.use_case.DeleteUserUseCase
import sanmi.labs.medialabandroidengineer.feature_user.domain.use_case.GetUsersUseCase
import sanmi.labs.medialabandroidengineer.feature_user.domain.use_case.SaveUserUseCase
import sanmi.labs.medialabandroidengineer.util.addNUsersToRepository
import sanmi.labs.medialabandroidengineer.util.randomUser

@RunWith(JUnit4::class)
class UserUseCasesTest : BaseTest() {

    private val userRepository by inject<UserRepository>()
    private val getUsersUseCase by inject<GetUsersUseCase>()
    private val saveUserUseCase by inject<SaveUserUseCase>()
    private val deleteUsersUseCase by inject<DeleteUserUseCase>()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `Getting users empty list`() = runBlocking {
        val users = getUsersUseCase()
        assertEquals(users.size, 0)
    }


    @Test
    fun `Getting users three items in list`() = runBlocking {
        addNUsersToRepository(userRepository, 3)
        assertEquals(getUsersUseCase().size, 3)
    }

    @Test
    fun `Getting users empty list after added and deleted all elements`() = runBlocking {
        val userOne = randomUser()
        val userTwo = randomUser()
        val userThree = randomUser()

        saveUserUseCase(userOne)
        saveUserUseCase(userTwo)
        saveUserUseCase(userThree)

        deleteUsersUseCase(userOne)
        deleteUsersUseCase(userTwo)
        deleteUsersUseCase(userThree)

        assertEquals(getUsersUseCase().size, 0)
    }

    @Test
    fun `Getting users after added and deleted each element`() = runBlocking {
        val userOne = randomUser()
        val userTwo = randomUser()
        val userThree = randomUser()

        saveUserUseCase(userOne)
        assertEquals(getUsersUseCase().size, 1)

        saveUserUseCase(userTwo)
        assertEquals(getUsersUseCase().size, 2)

        saveUserUseCase(userThree)
        assertEquals(getUsersUseCase().size, 3)

        assertEquals(getUsersUseCase()[0], userOne)
        assertEquals(getUsersUseCase()[1], userTwo)
        assertEquals(getUsersUseCase()[2], userThree)

        deleteUsersUseCase(userOne)
        assertEquals(getUsersUseCase()[0], userTwo)
        assertEquals(getUsersUseCase().size, 2)

        deleteUsersUseCase(userTwo)
        assertEquals(getUsersUseCase()[0], userThree)
        assertEquals(getUsersUseCase().size, 1)

        deleteUsersUseCase(userThree)
        assertEquals(getUsersUseCase().size, 0)
    }

}