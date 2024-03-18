package com.antareza.tesdanamon.di

import com.antareza.tesdanamon.data.reqres.local.db.RoomDB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { RoomDB(androidContext()) }
    single { get<RoomDB>().user() }
}