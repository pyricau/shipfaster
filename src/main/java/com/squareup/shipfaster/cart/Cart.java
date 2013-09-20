package com.squareup.shipfaster.cart;

import android.app.Activity;
import android.widget.Toast;
import com.squareup.otto.Subscribe;
import com.squareup.shipfaster.payment.PaymentActivity;
import com.squareup.shipfaster.settings.Settings;
import com.squareup.shipfaster.swipe.Card;
import com.squareup.shipfaster.swipe.SwipeEvent;
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
      startPayment(event.card);
    }
  }

  private void startPayment(Card card) {
    Activity resumedActivity = resumedActivityProvider.get();
    if (resumedActivity != null) {
      Toast.makeText(resumedActivity, "Successful swipe!", Toast.LENGTH_SHORT).show();
      PaymentActivity.start(resumedActivity, card);
    }
  }

  public void clear() {
    items.clear();
  }
}
