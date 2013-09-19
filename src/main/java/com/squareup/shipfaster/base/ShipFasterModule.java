package com.squareup.shipfaster.base;

import android.app.Activity;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import com.squareup.shipfaster.auth.AuthActivity;
import com.squareup.shipfaster.cart.CartActivity;
import com.squareup.shipfaster.auth.AuthClient;
import com.squareup.shipfaster.cart.Cart;
import com.squareup.shipfaster.log.EventLogger;
import com.squareup.shipfaster.settings.FileBackedSettings;
import com.squareup.shipfaster.settings.Settings;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit.RestAdapter;

import static dagger.Provides.Type.SET;

@Module(injects = { CartActivity.class, AuthActivity.class })
public class ShipFasterModule {

  private final ShipFasterApplication application;

  public ShipFasterModule(ShipFasterApplication application) {
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

  @Provides RestAdapter provideRestAdapter() {
    return new RestAdapter.Builder().setServer("https://squareup.com").build();
  }

  @Provides AuthClient provideAuthService(RestAdapter restAdapter) {
    return restAdapter.create(AuthClient.class);
  }
}
