package com.getmega.getmegaapp.di

import com.getmega.getmegaapp.HomeActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val presentationModule = module {
    viewModel { HomeActivityViewModel(get()) }

}
