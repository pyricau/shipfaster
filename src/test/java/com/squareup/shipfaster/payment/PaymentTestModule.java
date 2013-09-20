package com.squareup.shipfaster.payment;

import com.squareup.shipfaster.common.ShipFasterModule;
import com.squareup.shipfaster.swipe.Card;
import dagger.Module;
import dagger.Provides;
import retrofit.Callback;
import retrofit.RestAdapter;

@Module(
    includes = ShipFasterModule.class,
    injects = PaymentTest.class,
    overrides = true
)
class PaymentTestModule {

  @Provides PaymentClient providePaymentClient(RestAdapter adapter) {
    return new PaymentClient() {
      @Override public void pay(int amount, Card card, Callback<PaymentResult> callback) {
        callback.success(new PaymentResult(), null);
      }
    };
  }
}