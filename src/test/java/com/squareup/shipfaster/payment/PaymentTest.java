package com.squareup.shipfaster.payment;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.shipfaster.swipe.Card;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import retrofit.Callback;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class PaymentTest {

  PaymentHandler paymentHandler;
  Bus bus;

  @Before public void setUp() {
    bus = new Bus();
    paymentHandler = new PaymentHandler();
    paymentHandler.paymentClient = new PaymentClient() {
      @Override public void pay(int amount, Card card, Callback<PaymentResult> callback) {
        callback.success(new PaymentResult(), null);
      }
    };
    paymentHandler.bus = bus;
  }

  @Test public void payment_handler_sends_success_event() {
    class Subscriber {
      PaymentSuccess event;

      @Subscribe public void onSuccess(PaymentSuccess event) {
        this.event = event;
      }
    }
    Subscriber subscriber = new Subscriber();
    bus.register(subscriber);

    paymentHandler.startPayment(100, Card.fakeCard());

    assertThat(subscriber.event).isNotNull();
  }
}
