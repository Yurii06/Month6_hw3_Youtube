package com.geektech.month6_hw3.core.di

import com.geektech.month6_hw3.core.network.networkModule

val koinModules = listOf(
    repositoryModule,
    networkModule,
    remoteDataSourceModule,
    viewModelModule,
)