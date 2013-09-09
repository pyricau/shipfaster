package com.squareup.shipfaster;

import android.content.Context;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Cart {

  private final Settings settings;
  private final Context context;
  private final List<Item> items = new ArrayList<Item>();

  private boolean authStarted;

  @Inject public Cart(Settings settings, Context context) {
    this.settings = settings;
    this.context = context;
  }

  public boolean canSwipeCard() {
    return getAmountDue() > 0 && settings.acceptsCreditCards();
  }

  public int getAmountDue() {
    int sumInCents = 0;
    for (Item item : items) {
      sumInCents += item.priceInCents;
    }
    return sumInCents;
  }

  public void addItem(Item item) {
    items.add(item);
  }


  @Subscribe public void onSwipe(SwipeEvent event) {
    if (event.successfulSwipe && canSwipeCard()) {
      startAuth(event.card);
    }
  }

  private void startAuth(Card card) {
    authStarted = true;
    //Intent intent = new Intent(context, AuthActivity.class);
    //intent.putExtra("card", card);
    //context.startActivity(intent);
  }

  public boolean authStarted() {
    return authStarted;
  }
}
