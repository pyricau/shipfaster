package com.squareup.shipfaster;

import android.content.Context;
import com.squareup.otto.Bus;
import com.squareup.otto.OttoBus;
import com.squareup.shipfaster.log.EventLogger;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

import static dagger.Provides.Type.SET;

@Module(injects = CartActivity.class)
public class CartModule {

  private final Context context;

  public CartModule(Context context) {
    this.context= context;
  }

  @Provides Settings provideSettings(FileBackedSettings settings) {
    return settings;
  }

  @Provides @Singleton Bus provideBus() {
    return new OttoBus();
  }

  @Provides(type = SET) @RegisterOnBus Object registerCart(Cart cart) {
    return cart;
  }

  @Provides(type = SET) @RegisterOnBus Object registerEventLogger(EventLogger logger) {
    return logger;
  }

  @Provides Context provideContext() {
    return context;
  }
}
