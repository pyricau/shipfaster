package com.squareup.shipfaster.common;

import android.app.Activity;
import com.squareup.otto.Bus;
import com.squareup.shipfaster.BuildConfig;
import com.squareup.shipfaster.cart.Cart;
import com.squareup.shipfaster.cart.CartActivity;
import com.squareup.shipfaster.log.EventLogger;
import com.squareup.shipfaster.payment.PaymentActivity;
import com.squareup.shipfaster.payment.PaymentClient;
import com.squareup.shipfaster.settings.FileBackedSettings;
import com.squareup.shipfaster.settings.Settings;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit.RestAdapter;

import static dagger.Provides.Type.SET;

@Module(injects = { CartActivity.class, PaymentActivity.class })
public class ProdAppModule {

  private final ShipFasterApplication application;

  public ProdAppModule(ShipFasterApplication application) {
    this.application = application;
  }

  @Provides Settings provideSettings(FileBackedSettings settings) {
    return settings;
  }

  @Provides @Singleton Bus provideBus() {
    return new Bus();
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
    RestAdapter.Builder builder = new RestAdapter.Builder();
    if (BuildConfig.DEBUG) {
      builder.setLogLevel(RestAdapter.LogLevel.FULL);
    }
    builder.setServer("https://squareup.com");
    return builder.build();
  }

  @Provides PaymentClient providePaymentClient(RestAdapter restAdapter) {
    return restAdapter.create(PaymentClient.class);
  }
}
