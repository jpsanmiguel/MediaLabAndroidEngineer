package sanmi.labs.medialabandroidengineer.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import sanmi.labs.medialabandroidengineer.feature_user.data.repository.DefaultUserRepository
import sanmi.labs.medialabandroidengineer.feature_user.domain.repository.UserRepository
import sanmi.labs.medialabandroidengineer.feature_user.domain.use_case.SaveUserUseCase
import sanmi.labs.medialabandroidengineer.feature_user.domain.use_case.DeleteUserUseCase
import sanmi.labs.medialabandroidengineer.feature_user.domain.use_case.GetUsersUseCase
import sanmi.labs.medialabandroidengineer.feature_user.domain.use_case.UserUseCases
import sanmi.labs.medialabandroidengineer.feature_user.presentation.view_model.UserListViewModel
import sanmi.labs.medialabandroidengineer.feature_user.presentation.view_model.UserProfileViewModel

val appModule = module {

    single<UserRepository> {
        DefaultUserRepository()
    }

    single {
        UserUseCases(
            GetUsersUseCase(get()),
            SaveUserUseCase(get()),
            DeleteUserUseCase(get()),
        )
    }

    viewModel {
        UserListViewModel(get())
    }

    viewModel {
        UserProfileViewModel()
    }
}