package com.geektech.month6_hw3.core.di

import com.geektech.month6_hw3.core.network.RemoteDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { RemoteDataSource(get()) }
}