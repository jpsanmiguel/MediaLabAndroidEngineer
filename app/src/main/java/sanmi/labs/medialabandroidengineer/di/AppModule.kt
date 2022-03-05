package sanmi.labs.medialabandroidengineer.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import sanmi.labs.medialabandroidengineer.feature_user.presentation.view_model.UserListViewModel
import sanmi.labs.medialabandroidengineer.feature_user.presentation.view_model.UserProfileViewModel

val appModule = module {

    viewModel {
        UserListViewModel()
    }

    viewModel {
        UserProfileViewModel()
    }
}