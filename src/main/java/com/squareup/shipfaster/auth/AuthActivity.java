package com.squareup.shipfaster.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.squareup.otto.Subscribe;
import com.squareup.shipfaster.base.BaseActivity;
import com.squareup.shipfaster.swipe.Card;
import javax.inject.Inject;

public class AuthActivity extends BaseActivity {

  public static void start(Activity activity, Card card) {
    Intent intent = new Intent(activity, AuthActivity.class);
    intent.putExtra("card", card);
    activity.startActivity(intent);
  }

  @Inject AuthHandler authHandler;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (!authHandler.isAuthRunning()) {
      Card card = (Card) getIntent().getSerializableExtra("card");
      authHandler.startAuth(card);
    }
  }

  @Override protected void onResume() {
    super.onResume();
    authHandler.replayResult();
  }

  @Override public void onBackPressed() {
    finish();
    authHandler.clearAuth();
  }

  @Subscribe public void onAuthSuccess(AuthSuccess event) {
    authHandler.clearAuth();
    // TODO Handle Success
  }

  @Subscribe public void onAuthFailure(AuthFailure event) {
    authHandler.clearAuth();
    // TODO Handle failure
  }
}
