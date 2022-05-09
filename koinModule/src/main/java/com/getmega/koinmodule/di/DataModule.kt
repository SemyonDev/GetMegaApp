package com.getmega.koinmodule.di


import com.getmega.data.network.repository.TrendingRepositoryImpl
import com.getmega.domain.repository.TrendingRepository
import com.getmega.domain.usecase.TrendingUseCase
import com.getmega.domain.usecase.TrendingUseCaseImpl
import org.koin.dsl.module


val repositoryModule = module {
    single<TrendingRepository> { TrendingRepositoryImpl(get()) }
}

val useCaseModule = module {
    single<TrendingUseCase> { TrendingUseCaseImpl(get()) }
}