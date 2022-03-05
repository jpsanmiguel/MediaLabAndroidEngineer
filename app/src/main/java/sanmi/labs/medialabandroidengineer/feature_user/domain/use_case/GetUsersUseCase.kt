package sanmi.labs.medialabandroidengineer.feature_user.domain.use_case

import sanmi.labs.medialabandroidengineer.feature_user.domain.model.User
import sanmi.labs.medialabandroidengineer.feature_user.domain.repository.UserRepository

class GetUsersUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(): List<User> {
        return userRepository.getUsers()
    }
}