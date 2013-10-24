package com.squareup.shipfaster.common;

import android.app.Activity;
import android.app.Application;
import com.squareup.picasso.Picasso;
import com.squareup.shipfaster.BuildConfig;
import dagger.ObjectGraph;

public class ShipFasterApplication extends Application {

  public static ShipFasterApplication from(Activity activity) {
    return (ShipFasterApplication) activity.getApplication();
  }

  private ObjectGraph objectGraph;
  private Activity resumedActivity;

  @Override public void onCreate() {
    super.onCreate();
    Picasso.with(this).setDebugging(BuildConfig.DEBUG);

    if (BuildConfig.DEBUG) {
      Object prodModule = new ProdAppModule(this);
      Object devModule = new DevAppModule();
      objectGraph = ObjectGraph.create(prodModule, devModule);
    } else {
      Object prodModule = new ProdAppModule(this);
      objectGraph = ObjectGraph.create(prodModule);
    }
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
