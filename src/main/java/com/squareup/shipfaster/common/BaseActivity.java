package com.squareup.shipfaster.common;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import javax.inject.Inject;

public abstract class BaseActivity extends Activity {

  @Inject BusRegistrar busRegistrar;
  ShipFasterApplication application;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    application = ShipFasterApplication.from(this);
    application.inject(this);
  }

  @Override protected void onResume() {
    super.onResume();
    application.setResumedActivity(this);
    busRegistrar.registerSubscribers();
  }

  @Override protected void onPause() {
    busRegistrar.unregisterSubscribers();
    application.setResumedActivity(null);
    super.onPause();
  }

  public <T extends View> T findById(int resId) {
    return (T) findViewById(resId);
  }
}
