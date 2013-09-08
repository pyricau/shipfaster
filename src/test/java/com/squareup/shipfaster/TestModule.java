package com.squareup.shipfaster;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

import static org.mockito.Mockito.mock;

@Module(
    includes = RegisterModule.class,
    injects = CartTest.class,
    overrides = true //
)//
class TestModule {
  @Provides @Singleton Settings provideSettings() {
    return mock(Settings.class);
  }
}