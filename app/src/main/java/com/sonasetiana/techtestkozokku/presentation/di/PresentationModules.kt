package com.sonasetiana.techtestkozokku.presentation.di

import com.sonasetiana.techtestkozokku.presentation.modules.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModules = module {
    viewModel { UserViewModel(get()) }
}