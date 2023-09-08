package com.geektech.month6_hw3.core.di

import com.geektech.month6_hw3.data.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single { Repository(get()) }
}