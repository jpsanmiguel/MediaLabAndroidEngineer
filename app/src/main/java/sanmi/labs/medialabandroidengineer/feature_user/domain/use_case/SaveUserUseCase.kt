package sanmi.labs.medialabandroidengineer.feature_user.domain.use_case

import sanmi.labs.medialabandroidengineer.feature_user.domain.model.User
import sanmi.labs.medialabandroidengineer.feature_user.domain.repository.UserRepository

class SaveUserUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(user: User): List<User> {
        return userRepository.saveUser(user)
    }
}