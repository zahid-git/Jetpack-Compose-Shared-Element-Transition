package com.zahid.sharedtransition.di

import com.zahid.sharedtransition.data.repository.ProductRepositoryImpl
import com.zahid.sharedtransition.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ProductModule {

    @Binds
    @Singleton
    fun bindProductRepository(implementation: ProductRepositoryImpl): ProductRepository


}