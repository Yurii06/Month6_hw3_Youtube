package com.geektech.month6_hw3.core.di

import com.geektech.month6_hw3.ui.playlist.PlaylistsViewModel
import com.geektech.month6_hw3.ui.details.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PlaylistsViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}