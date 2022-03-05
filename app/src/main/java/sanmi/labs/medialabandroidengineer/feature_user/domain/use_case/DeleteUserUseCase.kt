package sanmi.labs.medialabandroidengineer.feature_user.domain.use_case

import sanmi.labs.medialabandroidengineer.feature_user.domain.model.User
import sanmi.labs.medialabandroidengineer.feature_user.domain.repository.UserRepository

class DeleteUserUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(user: User): List<User> {
        return userRepository.deleteUser(user)
    }
}