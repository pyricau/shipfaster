package com.squareup.shipfaster;

import android.app.Activity;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import com.squareup.shipfaster.log.EventLogger;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

import static dagger.Provides.Type.SET;

@Module(injects = { CartActivity.class, AuthActivity.class })
public class CartModule {

  private final ShipFasterApplication application;

  public CartModule(ShipFasterApplication application) {
    this.application = application;
  }

  @Provides Settings provideSettings(FileBackedSettings settings) {
    return settings;
  }

  @Provides @Singleton Bus provideBus() {
    return new Bus(ThreadEnforcer.MAIN);
  }

  @Provides(type = SET) @RegisterOnResume Object registerCart(Cart cart) {
    return cart;
  }

  @Provides(type = SET) @RegisterOnResume Object registerEventLogger(EventLogger logger) {
    return logger;
  }

  @Provides Activity provideResumedActivity() {
    return application.getResumedActivity();
  }
}
