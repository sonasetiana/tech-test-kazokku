package com.sonasetiana.techtestkozokku.domain.di

import com.sonasetiana.techtestkozokku.domain.modules.detail.DetailInteractor
import com.sonasetiana.techtestkozokku.domain.modules.detail.DetailUseCase
import com.sonasetiana.techtestkozokku.domain.modules.favorite.FavoriteInteractor
import com.sonasetiana.techtestkozokku.domain.modules.favorite.FavoriteUseCase
import com.sonasetiana.techtestkozokku.domain.modules.timeline.TimeLineInteractor
import com.sonasetiana.techtestkozokku.domain.modules.timeline.TimeLineUseCase
import com.sonasetiana.techtestkozokku.domain.modules.user.UserInteractor
import com.sonasetiana.techtestkozokku.domain.modules.user.UserUseCase
import org.koin.dsl.module

val domainModules = module {
    factory<UserUseCase> { UserInteractor(get()) }
    factory<DetailUseCase> { DetailInteractor(get()) }
    factory<TimeLineUseCase> { TimeLineInteractor(get()) }
    factory<FavoriteUseCase> { FavoriteInteractor(get()) }
}