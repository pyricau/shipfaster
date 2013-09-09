package com.squareup.shipfaster;

import android.app.Activity;
import android.os.Bundle;
import com.squareup.otto.Bus;
import dagger.ObjectGraph;
import java.util.Set;
import javax.inject.Inject;

public class CartActivity extends Activity {

  @Inject Cart cart;

  @Inject @RegisterOnBus Set<Object> subscribers;
  @Inject Bus bus;
  @Inject CardReader cardReader;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Creation of the object graph
    CartModule module = new CartModule();
    module.setContext(this);
    ObjectGraph objectGraph = ObjectGraph.create(module);
    objectGraph.inject(this);

    // Registration of bus subscribers
    for(Object subscriber : subscribers) {
      bus.register(subscriber);
    }

    setContentView(R.layout.cart);
  }

  @Override protected void onResume() {
    super.onResume();
    cardReader.start();
  }

  @Override protected void onPause() {
    super.onPause();
    cardReader.stop();
  }
}
