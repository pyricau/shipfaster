package com.squareup.shipfaster;

import com.squareup.otto.Bus;
import com.squareup.otto.DeadEventHandler;
import com.squareup.otto.TestBus;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

import static org.mockito.Mockito.mock;

@Module(
    includes = CartModule.class,
    injects = CartTest.class,
    overrides = true //
)//
class TestModule {
  @Provides @Singleton Settings provideSettings() {
    return mock(Settings.class);
  }

  @Provides @Singleton Bus provideTestBus() {
    return new TestBus(DeadEventHandler.IGNORE_DEAD_EVENTS);
  }
}