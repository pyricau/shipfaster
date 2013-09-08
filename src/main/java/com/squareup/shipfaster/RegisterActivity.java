package com.squareup.shipfaster;

import android.app.Activity;
import android.os.Bundle;
import dagger.ObjectGraph;
import javax.inject.Inject;

public class RegisterActivity extends Activity {

  @Inject Cart cart;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ObjectGraph.create(new RegisterModule()).inject(this);

  }
}
