package com.squareup.shipfaster.payment;

public class PaymentSuccess {
  public final PaymentResult paymentResult;

  public PaymentSuccess(PaymentResult paymentResult) {
    this.paymentResult = paymentResult;
  }
}