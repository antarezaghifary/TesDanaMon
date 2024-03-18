package com.antareza.tesdanamon.di

import androidx.paging.PagingConfig
import org.koin.dsl.module

val pagingModule = module {
    single { PagingModule().providePageConfig() }
}
class PagingModule {
    fun providePageConfig(): PagingConfig = PagingConfig(
        initialLoadSize = 20,
        pageSize = 10,
        enablePlaceholders = false,
        prefetchDistance = 1
    )
}