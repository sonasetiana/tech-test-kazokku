package com.sonasetiana.techtestkozokku.presentation.di

import com.sonasetiana.techtestkozokku.presentation.modules.detail.DetailViewModel
import com.sonasetiana.techtestkozokku.presentation.modules.timeline.TimeLineViewModel
import com.sonasetiana.techtestkozokku.presentation.modules.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModules = module {
    viewModel { UserViewModel(get()) }
    viewModel { TimeLineViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}