object Versions {
    val kotlin = "1.3.61"
    val coreKtx = "1.9.0"
    val appCompat = "1.6.1"
    val material= "1.6.1"
    val constraintLayout = "2.1.4"

    val junit = "4.13.2"
    val mockk = "1.13.5"
    val ktCoroutine = "1.5.2"
    val extJunit = "1.1.5"
    val testRunner = "1.5.2"
    val testRuler = "1.5.0"
    val espresso = "3.5.1"
    val mockito = "4.0.0"

    val webSocket = "1.5.2"
    val navigation = "2.5.3"
    val retrofit = "2.9.0"
    val gsonConverter = "2.9.0"
    val okhttp = "4.9.0"
    val okhttpInterceptor = "5.0.0-alpha.3"
    val chucker = "3.5.2"
    val rxAndroid = "3.0.0"
    val rxJava = "3.1.2"
    val rxBinding = "3.0.0"
    val glide = "4.12.0"
    val prettyTime = "4.0.4.Final"
    val dagger = "2.44"
    val hilt = "1.0.0"
    val roomRx = "2.6.0-alpha01"
    val roomPaging = "2.4.0-alpha05"
    val room = "2.5.1"
    val androidJunit = "1.1.3"
    val paging = "3.1.1"
    val koin = "3.4.2"
    val startup = "1.1.0"
    val rxKotlin ="3.0.1"
    val extAdd = "3.19.0"
    val roomRx3 = "2.6.1"

}

object Deps {
    val kotlin_sblib= "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val material = "com.google.android.material:material:${Versions.material}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    val junit = "junit:junit:${Versions.junit}"
    val mockk = "io.mockk:mockk-android:${Versions.mockk}"
    val ktCoroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.ktCoroutine}"
    val testJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    val testRunner = "androidx.test:runner:${Versions.testRunner}"
    val testRuler = "androidx.test:rules:${Versions.testRuler}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val espressoIntent = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
    val mockito = "org.mockito.kotlin:mockito-kotlin:${Versions.mockito}"

    val navigationIu = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val rxJava2 = "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofit}"
    val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.gsonConverter}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpInterceptor}"
    val chucker = "com.github.chuckerteam.chucker:library:${Versions.chucker}"
    val chuckerNoop = "com.github.chuckerteam.chucker:library-no-op:${Versions.chucker}"
    val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxAndroid}"
    val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.rxJava}"
    val rxKotlin = "io.reactivex.rxjava3:rxkotlin:${Versions.rxKotlin}"
    val rxBinding = "com.jakewharton.rxbinding3:rxbinding:${Versions.rxBinding}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    val pretty = "org.ocpsoft.prettytime:prettytime:${Versions.prettyTime}"
    val dagger = "com.google.dagger:hilt-android:${Versions.dagger}"
    val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.dagger}"
    val hiltAndroidCompiler = "androidx.hilt:hilt-compiler:${Versions.hilt}"
    val room = "androidx.room:room-ktx:${Versions.room}"
    val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    val roomRxJava = "androidx.room:room-ktx:${Versions.roomRx}"
    val roomPaging = "androidx.room:room-paging:${Versions.roomPaging}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    val androidJunit = "androidx.test.ext:junit:${Versions.androidJunit}"
    val pagingRuntime = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
    val pagingRxJava = "androidx.paging:paging-rxjava3:${Versions.paging}"
    val koin = "io.insert-koin:koin-android:${Versions.koin}"
    val startup = "androidx.startup:startup-runtime:${Versions.startup}"
    val extAdd = "com.oratakashi:AndroidViewBinding:${Versions.extAdd}"
    val roomRx3 = "androidx.room:room-rxjava3:${Versions.roomRx3}"
    val webSocket = "org.java-websocket:Java-WebSocket:${Versions.webSocket}"


}