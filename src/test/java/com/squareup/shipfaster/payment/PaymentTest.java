package com.squareup.shipfaster.payment;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.shipfaster.common.ShipFasterModule;
import com.squareup.shipfaster.swipe.Card;
import dagger.ObjectGraph;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
public class PaymentTest {

  @Inject PaymentHandler paymentHandler;
  @Inject Bus bus;

  @Before public void setUp() {
    ObjectGraph.create(new ShipFasterModule(null), new PaymentTestModule()).inject(this);
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
