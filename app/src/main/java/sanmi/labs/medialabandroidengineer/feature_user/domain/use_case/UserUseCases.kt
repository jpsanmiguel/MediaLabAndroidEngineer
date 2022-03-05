package sanmi.labs.medialabandroidengineer.feature_user.domain.use_case

data class UserUseCases(
    val getUsersUseCase: GetUsersUseCase,
    val saveUserUseCase: SaveUserUseCase,
    val deleteUserUseCase: DeleteUserUseCase
)