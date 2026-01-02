package com.example.shopease.di
import android.app.Application
import androidx.room.Room
import com.example.shopease.data.local.ShopDatabase
import com.example.shopease.data.remote.ShopApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun getDatabase(app: Application): ShopDatabase {
        return Room.databaseBuilder(
            app,
            ShopDatabase::class.java,
            "shop_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun getDao(db: ShopDatabase) = db.getDao()

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getProductApi(retrofit: Retrofit): ShopApi {
        return retrofit.create(ShopApi::class.java)
    }

}