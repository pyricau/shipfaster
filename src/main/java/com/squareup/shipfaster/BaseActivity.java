package com.squareup.shipfaster;

import android.app.Activity;
import android.os.Bundle;
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
}
