package com.squareup.shipfaster.cart;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import com.squareup.shipfaster.common.ShipFasterModule;
import com.squareup.shipfaster.settings.Settings;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

import static org.mockito.Mockito.mock;

@Module(
    includes = ShipFasterModule.class,
    injects = CartTest.class,
    overrides = true
)
class CartTestModule {
  @Provides @Singleton Settings provideSettings() {
    return mock(Settings.class);
  }

  @Provides @Singleton Bus provideTestBus() {
    return new Bus(ThreadEnforcer.ANY);
  }
}