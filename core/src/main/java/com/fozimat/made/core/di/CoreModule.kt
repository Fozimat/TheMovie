package com.fozimat.made.core.di

import androidx.room.Room
import com.fozimat.made.core.data.MovieRepository
import com.fozimat.made.core.data.source.local.LocalDataSource
import com.fozimat.made.core.data.source.local.room.MovieDatabase
import com.fozimat.made.core.data.source.remote.RemoteDataSource
import com.fozimat.made.core.data.source.remote.network.ApiService
import com.fozimat.made.core.domain.repository.IMovieRepository
import com.fozimat.made.core.utils.AppExecutors
import com.fozimat.made.core.utils.Constant
import com.fozimat.made.core.utils.Constant.HOST_NAME
import com.fozimat.made.core.utils.Constant.PIN_HOSTNAME_1
import com.fozimat.made.core.utils.Constant.PIN_HOSTNAME_2
import com.fozimat.made.core.utils.Constant.PIN_HOSTNAME_3
import com.fozimat.made.core.utils.Constant.PIN_HOSTNAME_4
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("fozimat".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            Constant.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = HOST_NAME
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, PIN_HOSTNAME_1)
            .add(hostname, PIN_HOSTNAME_2)
            .add(hostname, PIN_HOSTNAME_3)
            .add(hostname, PIN_HOSTNAME_4)
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> { MovieRepository(get(), get(), get()) }
}

