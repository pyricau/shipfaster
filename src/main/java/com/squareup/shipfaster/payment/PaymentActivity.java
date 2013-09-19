package com.squareup.shipfaster.payment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.squareup.otto.Subscribe;
import com.squareup.shipfaster.cart.Cart;
import com.squareup.shipfaster.common.BaseActivity;
import com.squareup.shipfaster.swipe.Card;
import javax.inject.Inject;

public class PaymentActivity extends BaseActivity {

  public static void start(Activity activity, Card card) {
    Intent intent = new Intent(activity, PaymentActivity.class);
    intent.putExtra("card", card);
    activity.startActivity(intent);
  }

  @Inject Cart cart;
  @Inject PaymentHandler paymentHandler;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (!paymentHandler.isPaymentRunning()) {
      int amountDue = cart.getAmountDue();
      Card card = (Card) getIntent().getSerializableExtra("card");
      paymentHandler.startPayment(amountDue, card);
    }
  }

  @Override protected void onResume() {
    super.onResume();
    paymentHandler.replayResult();
  }

  @Override public void onBackPressed() {
    finish();
    paymentHandler.clearPayment();
    cart.clear();
  }

  @Subscribe public void onPaymentSuccess(PaymentSuccess event) {
    paymentHandler.clearPayment();
    // TODO Handle success
  }

  @Subscribe public void onPaymentFailure(PaymentFailure event) {
    paymentHandler.clearPayment();
    // TODO Handle failure
  }
}
