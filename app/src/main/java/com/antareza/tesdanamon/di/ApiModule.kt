package com.antareza.tesdanamon.di

import com.antareza.tesdanamon.BuildConfig
import com.antareza.tesdanamon.data.reqres.web.PhotoApi
import com.antareza.tesdanamon.data.reqres.web.PhotoApiClient
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.oratakashi.viewbinding.core.tools.retrofit.createOkHttpClient
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    single {
        ChuckerCollector(
            context = androidContext(),
            showNotification = BuildConfig.DEBUG,
            retentionPeriod = RetentionManager.Period.ONE_DAY
        )
    }

    single {
        ChuckerInterceptor.Builder(androidContext())
            .apply {
                collector(get())
                maxContentLength(250_000L)
                alwaysReadResponseBody(true)
            }
            .build()
    }

    single {
        Interceptor { chain ->
            var request: Request = chain.request()
            val url: HttpUrl = request.url.newBuilder()
                .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
    }

    single {
        createOkHttpClient(
            arrayOf(
                get<ChuckerInterceptor>(),
                get()
            ),
            null,
            null,
            BuildConfig.DEBUG
        )
    }


    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // Adapter for RxJava3
            .build()
    }

    single {
        get<Retrofit>().create(PhotoApiClient::class.java)
    }

    single { PhotoApi(get()) }


}