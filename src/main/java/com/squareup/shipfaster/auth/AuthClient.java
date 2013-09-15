package com.squareup.shipfaster.auth;

import com.squareup.shipfaster.swipe.Card;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface AuthClient {

  @POST("/auth") void auth(@Body Card card, Callback<Auth> authCallback);

}
