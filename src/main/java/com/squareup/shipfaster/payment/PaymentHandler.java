package com.squareup.shipfaster.payment;

import com.squareup.otto.Bus;
import com.squareup.shipfaster.swipe.Card;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@Singleton
public class PaymentHandler {

  @Inject PaymentClient paymentClient;
  @Inject Bus bus;

  private boolean paymentRunning;
  private Object result;

  public void startPayment(int amountDue, Card card) {
    paymentRunning = true;
    paymentClient.pay(amountDue, card, new Callback<PaymentResult>() {
      @Override public void success(PaymentResult paymentResult, Response response) {
        if (paymentRunning) {
          result = new PaymentSuccess(paymentResult);
          bus.post(result);
        }
      }

      @Override public void failure(RetrofitError retrofitError) {
        if (paymentRunning) {
          result = new PaymentFailure();
          bus.post(result);
        }
      }
    });
  }

  public boolean isPaymentRunning() {
    return paymentRunning;
  }

  public void clearPayment() {
    paymentRunning = false;
    result = null;
  }

  public void replayResult() {
    if (result != null) {
      bus.post(result);
    }
  }
}
