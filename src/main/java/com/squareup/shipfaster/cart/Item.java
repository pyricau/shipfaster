package com.squareup.shipfaster.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Item {

  private static final String BASE = "http://i.imgur.com/";
  private static final String EXT = ".jpg";
  private static final String[] URLS =
      { BASE + "CqmBjo5" + EXT, BASE + "zkaAooq" + EXT, BASE + "0gqnEaY" + EXT,
          BASE + "9gbQ7YR" + EXT, BASE + "aFhEEby" + EXT, BASE + "0E2tgV7" + EXT,
          BASE + "P5JLfjk" + EXT, BASE + "nz67a4F" + EXT, BASE + "dFH34N5" + EXT,
          BASE + "FI49ftb" + EXT, BASE + "DvpvklR" + EXT, BASE + "DNKnbG8" + EXT,
          BASE + "yAdbrLp" + EXT, BASE + "55w5Km7" + EXT, BASE + "NIwNTMR" + EXT,
          BASE + "DAl0KB8" + EXT, BASE + "xZLIYFV" + EXT, BASE + "HvTyeh3" + EXT,
          BASE + "Ig9oHCM" + EXT, BASE + "7GUv9qa" + EXT, BASE + "i5vXmXp" + EXT,
          BASE + "glyvuXg" + EXT, BASE + "u6JF6JZ" + EXT, BASE + "ExwR7ap" + EXT,
          BASE + "Q54zMKT" + EXT, BASE + "9t6hLbm" + EXT, BASE + "F8n3Ic6" + EXT,
          BASE + "P5ZRSvT" + EXT, BASE + "jbemFzr" + EXT, BASE + "8B7haIK" + EXT,
          BASE + "aSeTYQr" + EXT, BASE + "OKvWoTh" + EXT, BASE + "zD3gT4Z" + EXT,
          BASE + "z77CaIt" + EXT, };

  public static Item newFakeItem() {
    return newFakeItemList().get(0);
  }

  public static List<Item> newFakeItemList() {
    List<Item> items = new ArrayList<Item>();
    Random random = new Random();
    int index = 1;
    for (String itemUrl : URLS) {
      int priceInCents = random.nextInt(1000000);
      items.add(new Item("Painting" + index, priceInCents, itemUrl));
      index++;
    }
    return items;
  }

  public final String name;
  public final int priceInCents;
  public final String photoUrl;

  public Item(String name, int priceInCents, String photoUrl) {
    this.name = name;
    this.priceInCents = priceInCents;
    this.photoUrl = photoUrl;
  }

  public String description() {
    return name + " $" + (priceInCents / 100f);
  }
}
