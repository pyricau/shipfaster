package com.squareup.shipfaster.payment;

import com.squareup.shipfaster.swipe.Card;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Path;

public interface PaymentClient {

  @POST("/pay/{amount}")
  void pay(@Path("amount") int amount, @Body Card card, Callback<PaymentResult> callback);
}
