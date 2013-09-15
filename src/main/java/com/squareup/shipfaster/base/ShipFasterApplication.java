package com.squareup.shipfaster.base;

import android.app.Activity;
import android.app.Application;
import dagger.ObjectGraph;

public class ShipFasterApplication extends Application {

  public static ShipFasterApplication from(Activity activity) {
    return (ShipFasterApplication) activity.getApplication();
  }

  private ObjectGraph objectGraph;
  private Activity resumedActivity;

  @Override public void onCreate() {
    super.onCreate();

    ShipFasterModule module = new ShipFasterModule(this);
    objectGraph = ObjectGraph.create(module);
  }

  public void inject(Object object) {
    objectGraph.inject(object);
  }

  public void setResumedActivity(Activity activity) {
    this.resumedActivity = activity;
  }

  /**
   * May return null
   */
  public Activity getResumedActivity() {
    return resumedActivity;
  }
}
