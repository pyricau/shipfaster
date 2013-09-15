package com.squareup.shipfaster.auth;

import com.squareup.otto.Bus;
import com.squareup.shipfaster.swipe.Card;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@Singleton
public class AuthHandler {

  @Inject AuthClient authClient;
  @Inject Bus bus;

  private boolean authRunning;
  private Object result;

  public void startAuth(Card card) {
    authRunning = true;
    authClient.auth(card, new Callback<Auth>() {
      @Override public void success(Auth auth, Response response) {
        if (authRunning) {
          result = new AuthSuccess(auth);
          bus.post(result);
        }
      }

      @Override public void failure(RetrofitError retrofitError) {
        if (authRunning) {
          result = new AuthFailure();
          bus.post(result);
        }
      }
    });
  }

  public boolean isAuthRunning() {
    return authRunning;
  }

  public void clearAuth() {
    authRunning = false;
    result = null;
  }

  public void replayResult() {
    if (result != null) {
      bus.post(result);
    }
  }
}
