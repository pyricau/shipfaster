package com.squareup.shipfaster.payment;

import com.squareup.otto.Bus;
import com.squareup.shipfaster.swipe.Card;
import org.junit.Before;
import org.junit.Test;
import retrofit.Callback;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PaymentTest {

  PaymentHandler paymentHandler;
  Bus bus;

  @Before public void setUp() {
    bus = mock(Bus.class);
    paymentHandler = new PaymentHandler();
    paymentHandler.paymentClient = new PaymentClient() {
      @Override public void pay(int amount, Card card, Callback<PaymentResult> callback) {
        callback.success(new PaymentResult(), null);
      }
    };
    paymentHandler.bus = bus;
  }

  @Test public void payment_handler_sends_success_event() {
    paymentHandler.startPayment(100, Card.fakeCard());
    verify(bus).post(any(PaymentSuccess.class));
  }
}
