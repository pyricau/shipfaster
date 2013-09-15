package com.squareup.shipfaster.swipe;

import java.io.Serializable;

public class Card implements Serializable {

  private final String cardNumber;

  public Card(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public static Card fakeCard() {
    return new Card("1234567890123456");
  }

}
