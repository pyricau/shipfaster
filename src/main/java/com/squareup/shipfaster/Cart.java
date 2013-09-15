package com.squareup.shipfaster;

import android.app.Activity;
import android.content.Intent;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class Cart {

  private final Settings settings;
  private final Provider<Activity> resumedActivityProvider;
  private final List<Item> items = new ArrayList<Item>();

  @Inject public Cart(Settings settings, Provider<Activity> resumedActivityProvider) {
    this.settings = settings;
    this.resumedActivityProvider = resumedActivityProvider;
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
    Activity resumedActivity = resumedActivityProvider.get();
    if (resumedActivity != null) {
      Intent intent = new Intent(resumedActivity, AuthActivity.class);
      intent.putExtra("card", card);
      resumedActivity.startActivity(intent);
    }
  }
}
