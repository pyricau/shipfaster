package com.squareup.shipfaster;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class Cart {

  private final Settings settings;
  private final List<Item> items = new ArrayList<Item>();

  @Inject public Cart(Settings settings) {
    this.settings = settings;
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
}
