package com.foreks.android.cicek.di


import com.foreks.android.cicek.data.ProductListService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideProductListService(retrofit: Retrofit): ProductListService =
        retrofit.create(ProductListService::class.java)


}