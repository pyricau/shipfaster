package com.squareup.shipfaster;

import android.os.Bundle;
import android.view.View;
import javax.inject.Inject;

public class CartActivity extends BaseActivity {

  @Inject Cart cart;
  @Inject CardReader cardReader;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.cart);

    findViewById(R.id.add_banana).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        cart.addItem(Item.newBanana());
      }
    });
  }

  @Override protected void onResume() {
    super.onResume();
    cardReader.start();
  }

  @Override protected void onPause() {
    cardReader.stop();
    super.onPause();
  }
}
