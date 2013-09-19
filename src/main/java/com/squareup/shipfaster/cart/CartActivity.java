package com.squareup.shipfaster.cart;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.squareup.shipfaster.R;
import com.squareup.shipfaster.common.BaseActivity;
import com.squareup.shipfaster.swipe.CardReader;
import javax.inject.Inject;

public class CartActivity extends BaseActivity {

  @Inject Cart cart;
  @Inject CardReader cardReader;
  private ItemAdapter itemAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.cart);

    itemAdapter = new ItemAdapter(this);

    ListView itemListView = findById(R.id.item_list);
    itemListView.setAdapter(itemAdapter);
    itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Item item = itemAdapter.getItem(position);
        cart.addItem(item);
        updateTitle();
      }
    });
  }

  private void updateTitle() {
    setTitle("Cart: $" + cart.getAmountDue() / 100f);
  }

  @Override protected void onResume() {
    super.onResume();
    updateTitle();
    cardReader.start();
  }

  @Override protected void onPause() {
    cardReader.stop();
    super.onPause();
  }
}
