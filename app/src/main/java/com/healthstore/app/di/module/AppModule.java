package com.healthstore.app.di.module;

import android.app.Application;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    ObjectMapper providerObjectMapper(){
        return new ObjectMapper();
    }



}
