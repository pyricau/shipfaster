package com.squareup.shipfaster.cart;

public class Item {

  public static Item newBanana() {
    return new Item("Banana", 1100);
  }

  public final String name;
  public final int priceInCents;

  public Item(String name, int priceInCents) {
    this.name = name;
    this.priceInCents = priceInCents;
  }
}
