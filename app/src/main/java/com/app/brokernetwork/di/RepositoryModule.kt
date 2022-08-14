package com.app.brokernetwork.di

import com.app.brokernetwork.data.repository.BrokerRepositoryImpl
import com.app.brokernetwork.domain.repository.BrokerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repository: BrokerRepositoryImpl): BrokerRepository
}